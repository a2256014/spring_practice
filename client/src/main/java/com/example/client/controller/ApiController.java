package com.example.client.controller;

import com.example.client.dto.RequestForm;
import com.example.client.dto.UserDto;
import com.example.client.service.RestTemplateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ApiController {
    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

//    @GetMapping("")
//    public UserDto getHello(@RequestParam String name, @RequestParam int age){
//        return restTemplateService.get(name,age);
//    }
    @GetMapping("")
    public RequestForm<UserDto> getHello(){
        return restTemplateService.genericExchange();
    }

    @PostMapping("/user")
    public UserDto post(@RequestBody UserDto userDto){
        return restTemplateService.post(userDto);
    }

    @GetMapping("/naver")
    public String naverOpenApiGet(){
        return restTemplateService.naverOpenApiGet();
    }
}
