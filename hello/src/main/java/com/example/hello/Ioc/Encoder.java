package com.example.hello.Ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
public class Encoder {
    IEncoder iEncoder = null;

//    public Encoder(@Qualifier("base64Encoder") IEncoder iEncoder){
//        this.iEncoder = iEncoder;
//    }
    public Encoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return this.iEncoder.encode(message);
    }
}
