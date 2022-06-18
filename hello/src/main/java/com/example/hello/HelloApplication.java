package com.example.hello;

import com.example.hello.Ioc.ApplicationContextProvider;
import com.example.hello.Ioc.Base64Encoder;
import com.example.hello.Ioc.Encoder;
import com.example.hello.Ioc.UrlEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);

        //bean
        ApplicationContext context = ApplicationContextProvider.getContext();

//        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
//        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

//        Encoder encoder = new Encoder(urlEncoder);
//        Encoder encoder = context.getBean(Encoder.class);
        Encoder encoder = context.getBean("url",Encoder.class);
        String url = "www.naver.com/abd";

        System.out.println(encoder.encode(url));
    }
}

//spring container(bean을 관리하는 곳으로 객체를 spring이 직접 관리(Ioc))
@Configuration
class AppConfig{
    @Bean("base64")
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }
    @Bean("url")
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}