package com.example.hello.controller;

import com.example.hello.annotation.Timer;
import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @Timer
    @GetMapping(path = "/hello")
    public String hello(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "get hello";
    }

    /*
    @RequestMapping(path = "/hi", method = RequestMethod.GET)  //  GET POST PUT DELETE 다 작동
    public String hi(){
        return "hi";
    }
    */

    @GetMapping("/path-variable/{id}")
    public String pathVariable(@PathVariable(name = "id") String pathName){
        System.out.println("pathVariable : " + pathName);
        return pathName;
    }

    //  ? 쿼리파라미터 (key:value) &
    //  query-param?user=kimdogyun&email=a2256014@naver.com
    @GetMapping("/query-param")
    public String queryParam(@RequestParam Map<String ,String >queryParam){
        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach(entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = "+entry.getValue());
        });
        return sb.toString();
    }

    @GetMapping("/query-param2")
    public String queryParam2(@RequestParam String name,
                              @RequestParam String email){
        System.out.println(name+" "+email);
        return name+" "+email;
    }

    //  제일 많이 쓰는 형식
    @GetMapping("/query-param3")
    public String queryParam3(UserRequest userRequest){
        System.out.println(userRequest.getName());
//        System.out.println(userRequest.getEmail());

        return userRequest.toString();
    }
}
