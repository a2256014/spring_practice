package com.example.client.service;

import com.example.client.dto.RequestForm;
import com.example.client.dto.UserDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //http://localhost/api/server 서버에 통신
    //response
    public UserDto get(String name, int age) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server")
                .queryParam("name", name)
                .queryParam("age", age)
                .encode()
                .build()
                .toUri();

        System.out.println(uri);

        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
//        ResponseEntity<String > result = restTemplate.getForEntity(uri, String.class);
        ResponseEntity<UserDto> result = restTemplate.getForEntity(uri, UserDto.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    //http://localhost/api/server/user 서버에 통신
    public UserDto post(UserDto userDto) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user")
                .encode()
                .build()
                //user/{userId}/name/{userName}
//                .expand(100,"steve")  //순차적으로 들어감
                .toUri();
        System.out.println(uri);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> result = restTemplate.postForEntity(uri, userDto, UserDto.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public RequestForm<UserDto> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/name/{name}/age/{age}")
                .encode()
                .build()
                .expand("steve",100)
                .toUri();
        System.out.println(uri);

        UserDto userDto = new UserDto();
        userDto.setName("steve");
        userDto.setAge(100);

        RequestForm<UserDto> reqForm = new RequestForm<>();
        reqForm.setHeader(new RequestForm.Header());
        reqForm.setReqBody(userDto);

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<RequestForm<UserDto>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","dogyun")
                .header("custom-header","kim")
                .body(reqForm);

        ResponseEntity<RequestForm<UserDto>> response =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {
                });
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String naverOpenApiGet(){
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/blog.json")
                .queryParam("query","갈비찜")
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","sim")
                .encode()
                .build()
                .toUri();
        System.out.println(uri);

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","pq5Vitl5uZqemIAEZ5zD")
                .header("X-Naver-Client-Secret","KwEV0x6rHT")
                .build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String > response = restTemplate.exchange(requestEntity,String.class);

        System.out.println(response.getBody());

        return response.getBody();
    }
}
