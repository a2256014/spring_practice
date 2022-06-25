package com.example.hello.controller;


import com.example.hello.dto.UserDto;
import com.example.hello.service.AsyncService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController // 해당 클래스는 REST API 처리하는 컨트롤러
@RequestMapping("/api") // URI 지정해주는 어노테이션
@Validated
@RequiredArgsConstructor
public class ApiController {

    private final AsyncService asyncService;

    @GetMapping("/hello")   //  http://localhost:9090/api/hello
    public String hello() {
        asyncService.hello();
        log.info("async end");
        return "hello spring boot";
    }

    @GetMapping("/helloAsync")   //  http://localhost:9090/api/hello
    public CompletableFuture asyncHello() {
        log.info("asyncHello getMethod");
        return asyncService.run();
    }

    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(x -> {
                FieldError field = (FieldError) x;
                String message = x.getDefaultMessage();

                System.out.println(field.getField() + " : " + message);

                sb.append("field : " + field.getField());
                sb.append("message : " + message);
            });
            int a = user.getAge() + 1;
            log.info("user : {}", user);    //slf4j
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
//        System.out.println(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUser")
    public UserDto getUser(
            @Size(min = 1, max = 5)
            @RequestParam String name,
            @NotNull
            @RequestParam int age
    ) {
        UserDto user = new UserDto();
        user.setAge(age);
        user.setName(name);

        return user;
    }

}
