package com.hubianluanma.spiral_ai_manager.ai.model;

import lombok.Data;

import java.util.List;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/5 14:06
 */
@Data
public class AIRequest {
    /**
     * 提示词
     */
    private List<AIRequestMsg> messages;
    /**
     * 模型
     */
    private String model;
    /**
     * 是否是流式
     */
    private boolean stream;
    /**
     * 是否开启 thinking 模式
     */
    private boolean think;
}
