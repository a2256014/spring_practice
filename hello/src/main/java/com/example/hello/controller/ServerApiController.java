package com.example.hello.controller;

import com.example.hello.dto.RequestForm;
import com.example.hello.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/server")
@Slf4j
public class ServerApiController {

    @GetMapping("")
    public UserRequest get(@RequestParam String name, @RequestParam int age) {
        UserRequest userRequest = new UserRequest();
        userRequest.setAge(age);
        userRequest.setName(name);
        return userRequest;
    }

    @PostMapping("/user")
    public UserRequest post(@RequestBody UserRequest userRequest) {

        return userRequest;
    }

    @PostMapping("/name/{name}/age/{age}")
    public RequestForm<UserRequest> exchange(@RequestBody RequestForm<UserRequest> userRequest,
                                             @PathVariable String name,
                                             @PathVariable int age,
                                             @RequestHeader("x-authorization") String x,
                                             @RequestHeader("custom-header") String custom,
                                             HttpEntity<String> entity
    ) {
        log.info(String.valueOf(entity));
        log.info("name : {}, age : {}", name, age);
        log.info("x-authorization : {}", x);
        log.info("custom-header : {}", custom);


        return userRequest;
    }
}
