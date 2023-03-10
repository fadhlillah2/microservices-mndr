package com.komodo.personservice.controller;

import com.komodo.personservice.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class personController {
    private RestTemplate restTemplate;

    @Autowired
    public personController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/tes")
    public String hello(){
        return "welcome to springboot";
    }

    @GetMapping("/get")
    public String getData() {
        return restTemplate.getForObject("http://localhost:8081/api/product/tes", String.class);
    }

}
