package com.example.hello.controller;

import com.example.hello.annotation.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
@Auth
public class PrivateController {

    @GetMapping("/hello")
    public String hello(){
        return "private hello";
    }
}
