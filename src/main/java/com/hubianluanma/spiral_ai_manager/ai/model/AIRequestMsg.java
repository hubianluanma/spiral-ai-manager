package com.hubianluanma.spiral_ai_manager.ai.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/5 14:36
 */
@Data
public class AIRequestMsg {

    /**
     * system-系统指令（角色设定，行为指令）
     * user-用户输入
     * assistant-模型的历史输出
     */
    private String role;
    private String content;
}
