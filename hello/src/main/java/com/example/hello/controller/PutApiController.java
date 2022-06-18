package com.example.hello.controller;

import com.example.hello.annotation.Decode;
import com.example.hello.dto.PutRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @Decode
    @PutMapping("/put/{userId}")
    public PutRequest put(@RequestBody PutRequest putRequest, @PathVariable(name = "userId") Long id){
        System.out.println(putRequest.toString());
        System.out.println(id);
        return putRequest;
    }
}
