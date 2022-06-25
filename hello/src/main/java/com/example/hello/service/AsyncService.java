package com.example.hello.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {

//    @Async
//    public void hello() {
//        for(int i=0;i<10;i++){
//            try {
//                Thread.sleep(2000);
//                log.info("thread sleep...");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    public String hello() {
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(2000);
                log.info("thread sleep...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "async hello";
    }
    //여러개의 api를 전송하고 그 정보를 join해야 할 때 사용하는 것이 제일 좋다.
    @Async("async-thread")
    public CompletableFuture run(){
        return new AsyncResult(hello()).completable();
    }
}
