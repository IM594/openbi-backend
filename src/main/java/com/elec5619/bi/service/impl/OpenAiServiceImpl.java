package com.elec5619.bi.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.elec5619.bi.common.ErrorCode;
import com.elec5619.bi.exception.BusinessException;
import com.elec5619.bi.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    //最大token数量，指的是生成的文本的长度
    private static final int MAX_TOKENS = 2048;

    /**
     * 实现 生成内容 的方法
     *
     * @param apiKey
     * @param messages
     * @return
     */
    @Override
    public String generateContent(String apiKey, List<Map<String, Object>> messages) {
//        String url = "https://api.openai.com/v1/chat/completions";
        String url = "https://api.dexiangit.com/v1/chat/completions";

        // 创建请求参数
        Map<String, Object> paramMap = createOpenAiRequest(messages);
        System.out.println(paramMap);
        String result = sendPostRequest(url, apiKey, paramMap);
        //返回结果
        return result;
    }

    /**
     * 实现 创建消息 的方法
     *
     * @param prompt
     * @param userInput
     * @return
     */
    @Override
    public List<Map<String, Object>> createMessages(String prompt, String userInput) {
        List<Map<String, Object>> messages = new ArrayList<>();
        messages.add(createOneMessage("system", prompt));
        messages.add(createOneMessage("user", userInput));
        // 可以继续添加其他类型的消息
        return messages;
    }

    /**
     * 创建 一个消息
     *
     * @param role
     * @param content
     * @return
     */
    private Map<String, Object> createOneMessage(String role, String content) {
        Map<String, Object> message = new HashMap<>();
        message.put("role", role);
        message.put("content", content);
        return message;
    }

    /**
     * 创建 OpenAI 请求参数
     *
     * @param messages
     * @return
     */
    private Map<String, Object> createOpenAiRequest(List<Map<String, Object>> messages) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        paramMap.put("messages", messages);
        paramMap.put("max_tokens", MAX_TOKENS);
        paramMap.put("temperature", 0.5);
        paramMap.put("top_p", 1);
        paramMap.put("n", 1);
        paramMap.put("stream", false);
        paramMap.put("logprobs", null);
        return paramMap;
    }

    /**
     * 发送 POST 请求
     *
     * @param url
     * @param apiKey
     * @param paramMap
     * @return
     */
    private String sendPostRequest(String url, String apiKey, Map<String, Object> paramMap) {
        String json = JSONUtil.toJsonStr(paramMap);
        try {
            HttpResponse response = HttpRequest.post(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .body(json)
                    .execute();
            return response.body();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "OpenAI响应错误");
        }
    }
}
