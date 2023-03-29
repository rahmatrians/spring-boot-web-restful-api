package com.rianzessh.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")
public class WelcomeController {

    @GetMapping("yolo")
    public String welcome() {
        return "yoo, wassap!!!";
    }

    @PostMapping("bye")
    public String bye() {
        return "nice to meet yah!!!";
    }
}