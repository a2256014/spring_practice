package com.example.hello.aop;

import com.example.hello.dto.PutRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {
    // ZG9neXVu dogyun
    @Pointcut("execution(* com.example.hello.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(com.example.hello.annotation.Decode)")
    private void enableDecode(){}

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();

        for(Object arg : args){
            if(arg instanceof PutRequest){
                PutRequest putRequest = PutRequest.class.cast(arg);
                String base64Name = putRequest.getName();
                String name = new String(Base64.getDecoder().decode(base64Name),"UTF-8");
                putRequest.setName(name);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        if(returnObj instanceof PutRequest){
            PutRequest putRequest = PutRequest.class.cast(returnObj);
            String name = putRequest.getName();
            String base64Name = Base64.getEncoder().encodeToString(name.getBytes());
            putRequest.setName(base64Name);
        }

    }

}
