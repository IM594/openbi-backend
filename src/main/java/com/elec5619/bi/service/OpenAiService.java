package com.elec5619.bi.service;

import java.util.List;
import java.util.Map;

public interface OpenAiService {

    /**
     * 生成内容
     * @param apiKey
     * @param messages
     * @return
     */
    String generateContent(String apiKey, List<Map<String, Object>> messages);

    /**
     * 创建消息
     * @param prompt
     * @param userInput
     * @return
     */
    List<Map<String, Object>> createMessages(String prompt, String userInput);
}
