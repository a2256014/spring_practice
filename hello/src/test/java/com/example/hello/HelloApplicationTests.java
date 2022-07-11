package com.example.hello;

import com.example.hello.dto.CarDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    //카멜 스네이크 변환 테스트
    void contextLoads() {

        var objectMapper = new ObjectMapper();
        var car = new CarDto();
        car.setCarNumber("11가 1234");
        car.setName("BMW");

        try {
            var text = objectMapper.writeValueAsString(car);
            System.out.println("text : "+text);

            var objectCar = objectMapper.readValue(text, CarDto.class);
            System.out.println("object : "+objectCar);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
