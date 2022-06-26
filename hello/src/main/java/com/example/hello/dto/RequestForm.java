package com.example.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RequestForm<T> {
    private Header header;
    private T reqBody;


    @Data
    @NoArgsConstructor
    public static class Header{
        String RequestStatus;
    }
}
