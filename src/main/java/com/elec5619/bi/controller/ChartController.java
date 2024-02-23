package com.elec5619.bi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec5619.bi.annotation.AuthCheck;
import com.elec5619.bi.common.BaseResponse;
import com.elec5619.bi.common.DeleteRequest;
import com.elec5619.bi.common.ErrorCode;
import com.elec5619.bi.common.ResultUtils;
import com.elec5619.bi.constant.CommonConstant;
import com.elec5619.bi.constant.PromptConstant;
import com.elec5619.bi.constant.UserConstant;
import com.elec5619.bi.exception.BusinessException;
import com.elec5619.bi.exception.ThrowUtils;
import com.elec5619.bi.model.dto.chart.*;
import com.elec5619.bi.model.entity.Chart;
import com.elec5619.bi.model.entity.User;
import com.elec5619.bi.model.vo.ChartResponse;
import com.elec5619.bi.service.ChartService;
import com.elec5619.bi.service.OpenAiService;
import com.elec5619.bi.service.UserService;
import com.elec5619.bi.utils.ExcelUtils;
import com.elec5619.bi.utils.SqlUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 图表接口
 *
 * @author Zhaohao Lu
 */
@RestController
@RequestMapping("/chart")
@Slf4j
public class ChartController {

    @Resource
    private ChartService chartService;

    @Resource
    private UserService userService;

    @Resource
    private OpenAiService openAiService;

    @Value("${openai.apiKey}")
    private String apiKey;


    // 增删改查

    /**
     * 创建
     *
     * @param chartAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addChart(@RequestBody ChartAddRequest chartAddRequest, HttpServletRequest request) {
        if (chartAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartAddRequest, chart);
        User loginUser = userService.getLoginUser(request);
        chart.setUserId(loginUser.getId());
        boolean result = chartService.save(chart);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newChartId = chart.getId();
        return ResultUtils.success(newChartId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteChart(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Chart oldChart = chartService.getById(id);
        ThrowUtils.throwIf(oldChart == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldChart.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = chartService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param chartUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateChart(@RequestBody ChartUpdateRequest chartUpdateRequest) {
        if (chartUpdateRequest == null || chartUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartUpdateRequest, chart);
        long id = chartUpdateRequest.getId();
        // 判断是否存在
        Chart oldChart = chartService.getById(id);
        ThrowUtils.throwIf(oldChart == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = chartService.updateById(chart);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Chart> getChartById(long id, HttpServletRequest request) {
        if (id <= 0) {
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR);
        }
        Chart chart = chartService.getById(id);
        if (chart == null) {
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(chart);
    }

    @GetMapping("/getChartsByUserId")
    public BaseResponse<List<Chart>> getChartByUserId(long id, HttpServletRequest request) {
        if (id <= 0) {
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR);
        }
        List<Chart> chart = chartService.getChartByUserId(id);
        if (chart == null) {
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(chart);
    }

    /**
     * 分页获取列表
     *
     * @param chartQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/chart")
    public BaseResponse<Page<Chart>> listChartByPage(@RequestBody ChartQueryRequest chartQueryRequest,
                                                     HttpServletRequest request) {
        long current = chartQueryRequest.getCurrent();
        long size = chartQueryRequest.getPageSize();
        // pagesize不能大于20（限制爬虫）
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Chart> chartPage = chartService.page(new Page<>(current, size),
                getQueryWrapper(chartQueryRequest));
        return ResultUtils.success(chartPage);
    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param chartQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/chart")
    public BaseResponse<Page<Chart>> listMyChartByPage(@RequestBody ChartQueryRequest chartQueryRequest,
                                                       HttpServletRequest request) {
        if (chartQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        chartQueryRequest.setUserId(loginUser.getId());
        long current = chartQueryRequest.getCurrent();
        long size = chartQueryRequest.getPageSize();
        // pagesize不能大于20（限制爬虫）
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Chart> chartPage = chartService.page(new Page<>(current, size),
                getQueryWrapper(chartQueryRequest));
        return ResultUtils.success(chartPage);
    }

    // 增删改查结束

    /**
     * 编辑（用户）
     *
     * @param chartEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editChart(@RequestBody ChartEditRequest chartEditRequest, HttpServletRequest request) {
        if (chartEditRequest == null || chartEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartEditRequest, chart);
        User loginUser = userService.getLoginUser(request);
        long id = chartEditRequest.getId();
        // 判断是否存在
        Chart oldChart = chartService.getById(id);
        ThrowUtils.throwIf(oldChart == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        if (!oldChart.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = chartService.updateById(chart);
        return ResultUtils.success(result);
    }

    /**
     * 智能分析（包括上传文件、分析、生成图表）
     *
     * @param multipartFile
     * @param genChartByAiRequest
     * @param request
     * @return 返回生成的图表代码和分析结论
     * @Author Zhaohao Lu
     */
    @PostMapping("/gen")
    public BaseResponse<ChartResponse> genChartByAi(@RequestPart("file") MultipartFile multipartFile,
                                                    GenChartByAiRequest genChartByAiRequest, HttpServletRequest request) {

        String name = genChartByAiRequest.getName();
        String goal = genChartByAiRequest.getGoal();
        String chartType = genChartByAiRequest.getChartType();
        String apiKey = this.apiKey;
        String prompt = null;

        //校验登录
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NO_AUTH_ERROR);

        // TODO: 9/9/2023 如果分析目标为空，那就默认指定一个分析目标
        //校验传入的参数（chartType、goal、name）
        ThrowUtils.throwIf(StringUtils.isBlank(goal), ErrorCode.PARAMS_ERROR, "分析目标不能为空");
        ThrowUtils.throwIf(StringUtils.isNotBlank(name) && name.length() > 100, ErrorCode.PARAMS_ERROR, "图表名称不能超过100个字符");

        //初步校验文件后缀
        //1. 获取上传的文件名
        String originalFilename = multipartFile.getOriginalFilename();
        ThrowUtils.throwIf(StringUtils.isBlank(originalFilename), ErrorCode.PARAMS_ERROR, "上传文件不能为空");
        //2. 获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //3. 校验文件后缀，只允许上传csv文件和excel文件
        ThrowUtils.throwIf(!suffix.equals("csv") && !suffix.equals("xlsx") && !suffix.equals("xls"), ErrorCode.PARAMS_ERROR, "上传文件格式不符合要求");

        //根据用户的输入，构造prompt
        if (StringUtils.isBlank(chartType)) {
            //如果用户没有输入图表类别，就使用默认的图表类别
            prompt = PromptConstant.DEFAULT_PROMPT;
        }//否则，检测用户输入的图表类型，并且选择对应prompt
        else {
            switch (chartType) {
                case "line":
                    prompt = PromptConstant.LINE_CHART_PROMPT;
                    break;
                case "bar":
                    prompt = PromptConstant.BAR_CHART_PROMPT;
                    break;
                case "pie":
                    prompt = PromptConstant.PIE_CHART_PROMPT;
                    break;
                case "scatter":
                    prompt = PromptConstant.SCATTER_CHART_PROMPT;
                    break;
                default:
                    prompt = PromptConstant.DEFAULT_PROMPT;
                    break;
            }
        }

        // TODO: 8/9/2023 图表类别应该在前端只给选项，不能让用户乱输入。
        //构造用户输入
        StringBuilder userInputs = new StringBuilder();
        userInputs.append("Analyse goal: ").append(goal).append("\n");
        //1. 如果用户选择了图表类别，就把图表类别拼接到用户输入里面
        if (StringUtils.isNotBlank(chartType)) {
            userInputs.append("Please use this chart type: ").append(chartType).append("\n");
        }
        //2. 如果用户输入了图表名称，就把图表名称拼接到用户输入里面
        if (StringUtils.isNotBlank(name)) {
            userInputs.append("Chart name: ").append(name).append("\n");
        }

        // TODO: 8/9/2023 格式处理，删掉null或者别的乱七八糟
        //如果文件后缀是csv，就直接读取文件内容，如果是excel文件，就先转换成csv文件，再读取文件内容
        String result;
        if (suffix.equals("csv")) {
            //读取文件内容
            result = ExcelUtils.csvToString(multipartFile);
        } else {
            //读取用户上传的excel文件，进行处理，获得压缩后的数据（csv格式）
            result = ExcelUtils.excelToCsv(multipartFile);
        }

        userInputs.append("Raw data: ").append(result).append("\n");


        // 调用OpenAiService类里面的方法，获得代码和分析结论
        List<Map<String, Object>> messages = openAiService.createMessages(prompt, userInputs.toString());
        String response = openAiService.generateContent(apiKey, messages);

        System.out.println(response);

        //如果出现如下错误信息
        //  "error": {
        //    "message": "This model's maximum context length is 4097 tokens. However, you requested 5787 tokens (1691 in the messages, 4096 in the completion). Please reduce the length of the messages or completion.",
        //    "type": "invalid_request_error",
        //    "param": "messages",
        //    "code": "context_length_exceeded"
        //  }
        //}
        //说明用户输入的内容太长了，超过了4097个token，需要把用户输入的内容缩短一点
        if (response.contains("context_length_exceeded")) {
            //提取出当前请求的token数，返回给前端
            int start = response.indexOf("However, you requested ");
            int end = response.indexOf(" tokens (");
            String tokenNum = response.substring(start + 23, end);
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "请求的token数为：" + tokenNum + "，超过了4097个token，请缩短输入的内容");
        }

        // 提取content部分的内容，这样才能拿到生成的代码和分析结论
        String content = getOpenAiResultContent(response);
        System.out.println("Returns...: "+ content);

        // 提取生成的代码
        String chartCode = processOpenAiResultToCode(content).trim();
        System.out.println("chartCode: "+ chartCode);

        //提取文字分析结果
        String analysisResult = processOpenAiResultToAnalysisResult(content).trim();
        System.out.println("analysisResult: " + analysisResult);

        //保存图表到数据库
        Chart chart = new Chart();
        chart.setUserId(loginUser.getId());
        chart.setName(name);
        chart.setGoal(goal);
        chart.setChartType(chartType);
        chart.setGenChart(chartCode);
        chart.setGenResult(analysisResult);
        boolean saveResult = chartService.save(chart);
        ThrowUtils.throwIf(!saveResult, ErrorCode.OPERATION_ERROR,"保存图表失败");

        ChartResponse chartResponse = new ChartResponse();
        chartResponse.setChartId(chart.getId());
        chartResponse.setGenChart(chartCode);
        chartResponse.setGenResult(analysisResult);

        return ResultUtils.success(chartResponse);

        //返回结果
//        return ResultUtils.success(chartCode + "\n" + analysisResult);
    }

    /**
     * 获取OpenAI的响应字符串中的content部分
     *
     * @param result
     * @return 提取到的content内容
     * @Author Zhaohao Lu
     */
    private String getOpenAiResultContent(String result) {
        try {
            // 将响应字符串解析为 JsonObject
            JsonObject response = JsonParser.parseString(result).getAsJsonObject();

            // 提取 choices 数组的第一个元素
            JsonArray choices = response.getAsJsonArray("choices");
            if (choices != null && choices.size() > 0) {
                JsonObject choice = choices.get(0).getAsJsonObject();

                // 提取 message 中的 content 字段
                JsonObject message = choice.getAsJsonObject("message");
                if (message != null) {
                    String content = message.get("content").getAsString();
                    return content;
                }
            }
            // 如果无法提取有效的信息，返回空字符串或其他适当的默认值
            return "";
        } catch (Exception e) {
            // 处理解析异常
            e.printStackTrace();
            return ""; // 返回空字符串或其他适当的默认值
        }
    }

    /**
     * 从响应中提取代码
     *
     * @param content OpenAI 的响应字符串
     * @return 处理后的代码字符串
     * @Author Zhaohao Lu
     */
    private String processOpenAiResultToCode(String content) {
        try {
//            int start = content.indexOf("【【【【【");
            int end = content.indexOf("】】】】】");
            String code = content.substring(0, end);

            //如果code包含“option =”，则要提取"option ="后面的内容，一直到"};"
            if (code.contains("option =")) {
                int start1 = code.indexOf("option =");
                int end1 = code.indexOf("};");
                code = code.substring(start1 + 8, end1 + 1);
            }
            //如果包含"option="，则要提取"option="后面的内容，一直到"};"
            else if (code.contains("option=")) {
                int start1 = code.indexOf("option=");
                int end1 = code.indexOf("};");
                code = code.substring(start1 + 7, end1 + 1);
            }
            //如果包含 var option =
            else if (code.contains("var option = ")) {
                int start1 = code.indexOf("var option = ");
                int end1 = code.indexOf("};");
                code = code.substring(start1 + 13, end1 + 1);
            }
            //如果包含"option":，则只要{及以后的内容，一直到结尾
            else if (code.contains("\"option\":")) {
                int start1 = code.indexOf("{");
                code = code.substring(start1);
            }
            //如果前面有无用的注释，就把注释一直到{之间的代码删掉
            else if (code.contains("//")) {
                int start1 = code.indexOf("//");
                int end1 = code.indexOf("{");
                code = code.substring(end1);
            }

            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 从响应中提取分析结论
     * @param content
     * @return 处理后的分析结论
     *
     * @Author Zhaohao Lu
     */
    private String processOpenAiResultToAnalysisResult(String content) {
        int start = content.indexOf("】】】】】");
        String analysisResult = content.substring(start + 5);
        return analysisResult;
    }

    /**
     * 获取查询包装类
     *
     * @param chartQueryRequest
     * @return  查询包装类
     *
     * @Author Zhaohao Lu
     */
    private QueryWrapper<Chart> getQueryWrapper(ChartQueryRequest chartQueryRequest) {
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        if (chartQueryRequest == null) {
            return queryWrapper;
        }

        Long id = chartQueryRequest.getId();
        String name = chartQueryRequest.getName();
        String goal = chartQueryRequest.getGoal();
        String chartType = chartQueryRequest.getChartType();
        Long userId = chartQueryRequest.getUserId();
        String sortField = chartQueryRequest.getSortField();
        String sortOrder = chartQueryRequest.getSortOrder();

        //queryWrapper.eq里面的参数是boolean类型，如果为true就会拼接到sql语句里面，如果为false就不会拼接到sql语句里面
        queryWrapper.eq(id != null && id > 0, "id", id);//如果存在id，就把id拼接到sql语句里面
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "id", userId);
        queryWrapper.eq(StringUtils.isNotBlank(goal), "goal", goal);
        queryWrapper.eq(StringUtils.isNotBlank(chartType), "chartType", chartType);
        queryWrapper.eq("isDelete", false);//查询的时候只查询isDelete为false的数据
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);//排序，ASC是升序，DESC是降序

        return queryWrapper;
    }

}
