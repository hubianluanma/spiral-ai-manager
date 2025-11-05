package com.hubianluanma.spiral_ai_manager.ai.provider.impl;

import com.hubianluanma.spiral_ai_manager.ai.model.AIRequest;
import com.hubianluanma.spiral_ai_manager.ai.provider.AIProvider;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author huhailong
 * @version 1.0
 * @description: deepseek provider service implements
 * @date 2025/11/5 14:00
 */
@Component
public class DeepSeekProvider implements AIProvider {
    @Override
    public String getName() {
        return "deepseek";
    }

    @Override
    public Flux<String> chat(AIRequest request) {
        return null;
    }
}
