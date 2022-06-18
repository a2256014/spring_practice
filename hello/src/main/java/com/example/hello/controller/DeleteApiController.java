package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public String  delete(@PathVariable(name = "userId") String id, @RequestParam String account){
        System.out.println(id);
        System.out.println(account);
        return "success delete";
    }
}
