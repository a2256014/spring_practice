package com.example.hello.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DollarCalculator implements ICalculator{

    private int price = 1;
    private final MarketApi marketApi;

    @Override
    public void init(){
        this.price = marketApi.connect();
    }

    //계산기에 커넥트 기능 필요없음
//    public int connect(){
//        return 1100;
//    }

    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;
        return x - y;
    }
}
