package com.example.hello.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    @Pointcut("execution(* com.example.hello.controller..*.*(..))")
    private void cut(){}

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("method : "+method.getName());

        System.out.println("---------------aop-----------------------");
        Object[] args = joinPoint.getArgs();

        for(Object arg : args){
            System.out.println("type : "+arg.getClass().getSimpleName());
            System.out.println("value : "+arg);
        }
        System.out.println("---------------aop-----------------------");
    }

    @AfterReturning(value = "cut()", returning = "obj")
    public void afterReturn(JoinPoint joinPoint, Object obj){
        System.out.print("\n");
        System.out.println("returnObj : "+obj);
        System.out.print("\n");
    }

}
