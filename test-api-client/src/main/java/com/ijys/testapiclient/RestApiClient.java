package com.ijys.testapiclient;

import com.ijys.testapiclient.vo.SimpleData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestApiClient {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String simpleApiUrl = "http://localhost:8080/getData?name=jaye.jung";

        ResponseEntity<SimpleData> response = restTemplate.getForEntity(simpleApiUrl, SimpleData.class);
        System.out.println(response.getBody().toString());
    }
}
