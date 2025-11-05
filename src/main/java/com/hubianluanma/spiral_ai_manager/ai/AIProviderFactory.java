package com.hubianluanma.spiral_ai_manager.ai;

import com.hubianluanma.spiral_ai_manager.ai.provider.AIProvider;
import com.hubianluanma.spiral_ai_manager.ai.provider.impl.DeepSeekProvider;
import com.hubianluanma.spiral_ai_manager.ai.provider.impl.OllamaProvider;
import com.hubianluanma.spiral_ai_manager.ai.provider.impl.OpenAIProvider;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author huhailong
 * @version 1.0
 * @description: 根据具体的配置选择合适的 Provider
 * @date 2025/11/5 14:01
 */
@Component
public class AIProviderFactory {

    private final Map<String, AIProvider> providerMap;

    public AIProviderFactory(OllamaProvider ollamaProvider, DeepSeekProvider deepSeekProvider, OpenAIProvider openAIProvider) {
        this.providerMap = Map.of(
          ollamaProvider.getName(), ollamaProvider,
          deepSeekProvider.getName(), deepSeekProvider,
          openAIProvider.getName(), openAIProvider
        );
    }

    public AIProvider get(String name) {
        AIProvider provider = providerMap.get(name);
        if (provider == null) {
            throw new IllegalArgumentException("未知的 AI Provider：" + name);
        }
        return provider;
    }
}
