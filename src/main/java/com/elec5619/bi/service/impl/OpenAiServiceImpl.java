package com.elec5619.bi.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.elec5619.bi.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    /**
     * 实现生成内容的方法
     * @param apiKey
     * @param messages
     * @return
     */
    @Override
    public String generateContent(String apiKey, List<Map<String, Object>> messages) {
        String url = "https://api.openai.com/v1/chat/completions";

        // 创建请求参数
        Map<String, Object> paramMap = createOpenAiRequest(messages);
        System.out.println(paramMap);
        String result = sendPostRequest(url, apiKey, paramMap);
        return result;
    }

    /**
     * 实现创建消息的方法
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
     * 创建一个消息
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

    private Map<String, Object> createOpenAiRequest(List<Map<String, Object>> messages) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        paramMap.put("messages", messages);
        paramMap.put("max_tokens", 2048);
        paramMap.put("temperature", 0.5);
        paramMap.put("top_p", 1);
        paramMap.put("n", 1);
        paramMap.put("stream", false);
        paramMap.put("logprobs", null);
        return paramMap;
    }

    private String sendPostRequest(String url, String apiKey, Map<String, Object> paramMap) {
        String json = JSONUtil.toJsonStr(paramMap);
        HttpResponse response = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .body(json)
                .execute();

        return response.body();
    }
}
