package com.hubianluanma.spiral_ai_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/1 23:36
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello, World!";
    }
}
