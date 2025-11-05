package com.hubianluanma.spiral_ai_manager.ai.provider;

import com.hubianluanma.spiral_ai_manager.ai.model.AIRequest;
import reactor.core.publisher.Flux;

/**
 * @author huhailong
 * @version 1.0
 * @description: ai 提供方抽象接口
 * @date 2025/11/5 13:58
 */
public interface AIProvider {

    String getName();

    Flux<String> chat(AIRequest request);
}
