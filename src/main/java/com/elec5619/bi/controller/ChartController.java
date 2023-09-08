package com.elec5619.bi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec5619.bi.annotation.AuthCheck;
import com.elec5619.bi.common.BaseResponse;
import com.elec5619.bi.common.DeleteRequest;
import com.elec5619.bi.common.ErrorCode;
import com.elec5619.bi.common.ResultUtils;
import com.elec5619.bi.constant.CommonConstant;
import com.elec5619.bi.constant.FileConstant;
import com.elec5619.bi.constant.UserConstant;
import com.elec5619.bi.exception.BusinessException;
import com.elec5619.bi.exception.ThrowUtils;
import com.elec5619.bi.model.dto.chart.*;
import com.elec5619.bi.model.dto.file.UploadFileRequest;
import com.elec5619.bi.model.entity.Chart;
import com.elec5619.bi.model.entity.User;
import com.elec5619.bi.model.enums.FileUploadBizEnum;
import com.elec5619.bi.service.ChartService;
import com.elec5619.bi.service.OpenAiService;
import com.elec5619.bi.service.UserService;
import com.elec5619.bi.utils.ExcelUtils;
import com.elec5619.bi.utils.SqlUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 帖子接口
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


    // private static final Gson GSON = new Gson();//Gson是Google提供的用来在Java对象和JSON数据之间进行映射的Java类库。

    // region 增删改查

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
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = chartService.getById(id);
        if (chart == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(chart);
    }

    /**
     * 分页获取列表（封装类
     *
     * @param chartQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Chart>> listChartByPage(@RequestBody ChartQueryRequest chartQueryRequest,
                                                     HttpServletRequest request) {
        long current = chartQueryRequest.getCurrent();
        long size = chartQueryRequest.getPageSize();
        // 限制爬虫
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
    @PostMapping("/my/list/page")
    public BaseResponse<Page<Chart>> listMyChartByPage(@RequestBody ChartQueryRequest chartQueryRequest,
                                                       HttpServletRequest request) {
        if (chartQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        chartQueryRequest.setUserId(loginUser.getId());
        long current = chartQueryRequest.getCurrent();
        long size = chartQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Chart> chartPage = chartService.page(new Page<>(current, size),
                getQueryWrapper(chartQueryRequest));
        return ResultUtils.success(chartPage);
    }

    // endregion

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
     * @return
     */
    @PostMapping("/gen")
    public BaseResponse<String> genChartByAi(@RequestPart("file") MultipartFile multipartFile,
                                             GenChartByAiRequest genChartByAiRequest, HttpServletRequest request) {

        String name = genChartByAiRequest.getName();
        String goal = genChartByAiRequest.getGoal();
        String chartType = genChartByAiRequest.getChartType();
        String apiKey = this.apiKey;

        //校验登录
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NO_AUTH_ERROR);

        //校验传入的参数
        ThrowUtils.throwIf(StringUtils.isBlank(goal), ErrorCode.PARAMS_ERROR, "分析目标不能为空");
        ThrowUtils.throwIf(StringUtils.isNotBlank(name) && name.length() > 100, ErrorCode.PARAMS_ERROR, "图表名称不能超过100个字符");

        // TODO: 8/9/2023 优化prompt，这样token太多了
        //Prompt
        String prompt = "You are a data analyst and front-end developer, next I will give you content in the following fixed format:\n" +
                "Analysis Requirement:\n" +
                "{Requirements or goals for data analysis}\n" +
                "Raw Data:\n" +
                "{csv format raw data, separated by , }\n" +
                "Based on these two parts, please generate the content in the following specified format (in addition to not outputting any extra beginnings, endings, comments)\n" +
                "【【【【【\n" +
                "{front-end Echarts V5 option configuration object js code, reasonable data visualisation, do not generate any redundant content, such as comments. If i don't give you the chart type, please choose the best type for me to generate the Echarts.\n For example {\n" +
                "  xAxis: {\n" +
                "    type: 'category',\n" +
                "    data: ['2023/5/1', '2023/5/2', '2023/5/3', '2023/5/4', '2023/5/5', '2023/5/6', '2023/5/7', '2023/5/8', '2023/5/9', '2023/5/10', '2023/5/11', '2023/5/12', '2023/5/13', '2023/5/14', '2023/5/15', '2023/5/16', '2023/5/17', '2023/5/18', '2023/5/19', '2023/5/20', '2023/5/21', '2023/5/22', '2023/5/23', '2023/5/24', '2023/5/25', '2023/5/26', '2023/5/27', '2023/5/28', '2023/5/29', '2023/5/30', '2023/5/31', '2023/6/1'],\n" +
                "  },\n" +
                "  yAxis: [\n" +
                "    {\n" +
                "      type: 'value',\n" +
                "      name: '销售额（元）',\n" +
                "      position: 'left',\n" +
                "      min: 0,\n" +
                "      max: 130000,\n" +
                "    },\n" +
                "    {\n" +
                "      type: 'value',\n" +
                "      name: '广告投入（元）',\n" +
                "      position: 'right',\n" +
                "      min: 0,\n" +
                "      max: 16000,\n" +
                "    },\n" +
                "  ],\n" +
                "  series: [\n" +
                "    {\n" +
                "      name: '销售额（元）',\n" +
                "      type: 'line',\n" +
                "      data: [120000, 98000, 78000, 92000, 105000, 88000, 104000, 123000, 97000, 116000, 98000, 82000, 103000, 92000, 118000, 107000, 96000, 110000, 89000, 104000, 119000, 93000, 101000, 85000, 112000, 99000, 107000, 116000, 91000, 106000, 120000, 98000],\n" +
                "      yAxisIndex: 0,\n" +
                "    },\n" +
                "    {\n" +
                "      name: '广告投入（元）',\n" +
                "      type: 'line',\n" +
                "      data: [15000, 10000, 8000, 11000, 13000, 9000, 12000, 15000, 10000, 14000, 10000, 8000, 12000, 9000, 14000, 12000, 10000, 13000, 8000, 12000, 14000, 9000, 11000, 8000, 13000, 10000, 12000, 14000, 9000, 12000, 15000, 10000],\n" +
                "      yAxisIndex: 1,\n" +
                "    },\n" +
                "    {\n" +
                "      name: '市场份额（%）',\n" +
                "      type: 'bar',\n" +
                "      barWidth: '50%',\n" +
                "      data: [25, 21.2, 18.3, 22.1, 23.8, 19.4, 24.7, 27.9, 22.3, 26.8, 21.2, 18.6, 23.5, 21.2, 27.2, 24.8, 20.5, 25.3, 18.9, 23.7, 27.1, 20.4, 22.8, 18.4, 25.1, 22.5, 24.7, 26.6, 20.6, 24.3, 26.4, 21.3],\n" +
                "    },\n" +
                "  ],\n" +
                "}" +
                "}\n" +
                "】】】】】\n" +
                "{clear conclusion of data analysis, the more detailed the better, do not generate redundant comments}";

        // TODO: 8/9/2023 目前之拼接了goal，还需要拼接别的。
        //构造用户输入
        StringBuilder userInputs = new StringBuilder();
        userInputs.append("Analyse goal: ").append(goal).append("\n");

        // TODO: 8/9/2023 格式处理，删掉null或者别的乱七八糟
        //读取用户上传的excel文件，进行处理，获得压缩后的数据（csv格式）
        String result = ExcelUtils.excelToCsv(multipartFile);
        userInputs.append("Raw data: ").append(result).append("\n");

        // 调用OpenAiService类里面的方法
        List<Map<String, Object>> messages = openAiService.createMessages(prompt, userInputs.toString());
        String response = openAiService.generateContent(apiKey, messages);

        System.out.println(response);

        //提取content部分的内容
        String content = getOpenAiResultContent(response);

        // 提取生成的代码
        String code = processOpenAiResultToCode(content);
        System.out.println(code);

        //提取文字分析结果
        String analysisResult = processOpenAiResultToAnalysisResult(content);
        System.out.println(analysisResult);

        // todo 根据登录的用户，保存到数据库



        //返回结果
        return ResultUtils.success(code + "\n" + analysisResult);
    }

    /**
     * 获取OpenAI的响应字符串中的content部分
     * @param result
     * @return
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
     * @return 处理后的内容字符串
     */
    private String processOpenAiResultToCode(String content) {
        int start = content.indexOf("【【【【【");
        int end = content.indexOf("】】】】】");
        String code = content.substring(start + 5, end);
        code = code.replaceAll("\\\\n", "\n");
        code = code.replaceAll("\\\\\"", "\"");
        return code;
    }

    private String processOpenAiResultToAnalysisResult(String content) {
        int start = content.indexOf("】】】】】");
        String analysisResult = content.substring(start + 5);
        return analysisResult;
    }

    /**
     * 获取查询包装类
     *
     * @param chartQueryRequest
     * @return
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
