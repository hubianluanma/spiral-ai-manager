package com.hubianluanma.spiral_ai_manager.ai.service;

import com.hubianluanma.spiral_ai_manager.ai.model.AIRequest;
import reactor.core.publisher.Flux;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/5 14:02
 */
public interface AIService {

    Flux<String> chat(String providerName, AIRequest request);
}
