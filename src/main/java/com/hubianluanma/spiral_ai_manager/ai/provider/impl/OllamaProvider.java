package com.hubianluanma.spiral_ai_manager.ai.provider.impl;

import com.hubianluanma.spiral_ai_manager.ai.model.AIRequest;
import com.hubianluanma.spiral_ai_manager.ai.provider.AIProvider;
import com.hubianluanma.spiral_ai_manager.utils.WebClientUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * @author huhailong
 * @version 1.0
 * @description: ollama provider service implements
 * @date 2025/11/5 13:59
 */
@Component
public class OllamaProvider implements AIProvider {

    private final WebClientUtils client = WebClientUtils.create("http://localhost:11434");

    @Override
    public String getName() {
        return "ollama";
    }

    @Override
    public Flux<String> chat(AIRequest request) {
        return client.sse("/api/chat", Map.of(
                "model", request.getModel() != null ? request.getModel() : "qwen3",
                "messages", request.getMessages(),
                "stream", request.isStream(),
                "think", request.isThink()
        ));
    }
}
