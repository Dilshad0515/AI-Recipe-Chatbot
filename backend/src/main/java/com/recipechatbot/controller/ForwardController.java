package com.recipechatbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardController {

    @GetMapping(value = {
            "/",
            "/login",
            "/chat",
            "/recipes",
            "/settings"
    })
    public String forward() {
        return "forward:/index.html";
    }
}
