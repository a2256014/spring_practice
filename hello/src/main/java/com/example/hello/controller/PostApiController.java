package com.example.hello.controller;

import com.example.hello.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData){
        requestData.entrySet().forEach(entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }

    @PostMapping("/post2")
    public void post2(@RequestBody PostRequestDto postRequestDto){
        System.out.println("\n" + postRequestDto.toString());
    }
}
