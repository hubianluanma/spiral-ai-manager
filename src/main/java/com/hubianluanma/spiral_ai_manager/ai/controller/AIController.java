package com.hubianluanma.spiral_ai_manager.ai.controller;

import com.hubianluanma.spiral_ai_manager.ai.model.AIRequest;
import com.hubianluanma.spiral_ai_manager.ai.service.AIService;
import com.hubianluanma.spiral_ai_manager.security.config.CustomUserDetails;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/5 14:04
 */
@RestController
@RequestMapping("/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping(value = "/chat/{provider}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chat(@PathVariable("provider") String provider, @RequestBody AIRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        request.setStream(true);
        return aiService.chat(provider, request);
    }
}
