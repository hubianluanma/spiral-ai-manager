package com.hubianluanma.spiral_ai_manager.ai.provider.impl;

import com.hubianluanma.spiral_ai_manager.ai.model.AIRequest;
import com.hubianluanma.spiral_ai_manager.ai.provider.AIProvider;
import com.hubianluanma.spiral_ai_manager.utils.WebClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * @author huhailong
 * @version 1.0
 * @description: deepseek provider service implements
 * @date 2025/11/5 14:00
 */
@Component
public class DeepSeekProvider implements AIProvider {

    private final WebClientUtils client = WebClientUtils.create("https://api.deepseek.com/v1");

    @Value("${api-key.deepseek}")
    private String apiKey;

    @Override
    public String getName() {
        return "deepseek";
    }

    @Override
    public Flux<String> chat(AIRequest request) {
        return client.sse("/chat/completions", Map.of(
                "model", request.getModel() != null ? request.getModel() : "deepseek-chat",
                "messages", request.getMessages(),
                "stream", request.isStream(),
                "think", request.isThink()
        ), apiKey);
    }
}
