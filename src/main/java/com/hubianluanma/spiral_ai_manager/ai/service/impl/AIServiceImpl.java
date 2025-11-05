package com.hubianluanma.spiral_ai_manager.ai.service.impl;

import com.hubianluanma.spiral_ai_manager.ai.AIProviderFactory;
import com.hubianluanma.spiral_ai_manager.ai.model.AIRequest;
import com.hubianluanma.spiral_ai_manager.ai.provider.AIProvider;
import com.hubianluanma.spiral_ai_manager.ai.service.AIService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/5 14:03
 */
@Service
public class AIServiceImpl implements AIService {

    private final AIProviderFactory factory;

    public AIServiceImpl(AIProviderFactory factory) {
        this.factory = factory;
    }

    @Override
    public Flux<String> chat(String providerName, AIRequest request) {
        AIProvider provider = factory.get(providerName);
        return provider.chat(request);
    }
}
