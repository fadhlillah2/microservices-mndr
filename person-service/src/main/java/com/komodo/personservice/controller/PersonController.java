package com.komodo.personservice.controller;

import com.komodo.personservice.dto.BookingAcknowledgement;
import com.komodo.personservice.dto.BookingRequest;
import com.komodo.personservice.dto.PersonDTO;
import com.komodo.personservice.dto.ResponseDTO;
import com.komodo.personservice.entity.Person;
import com.komodo.personservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {
    private RestTemplate restTemplate;
    private final PersonService personService;

    @Autowired
    public PersonController(RestTemplate restTemplate, PersonService personService) {
        this.restTemplate = restTemplate;
        this.personService = personService;
    }

    @GetMapping("/tes")
    public ResponseEntity<String> hello() {
        String message = "Hello, world!";
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/get")
    public String getData() {
        return restTemplate.getForObject("http://localhost:8081/api/product/tes", String.class);
    }

    @GetMapping
    public List<Person> getAllPerson(){
        log.info("Hit controller for getting all lessons");
        return personService.getAllPerson();
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO<Object>> create(@RequestBody PersonDTO requestBody) {
        personService.createPerson(requestBody);
        ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("success add person")
                .data(requestBody)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/findActiveUsersWithAgeGreaterThan")
    public List<Person> findActiveUsersWithAgeGreaterThan(int age){
        log.info("Hit controller for findActiveUsersWithAgeGreaterThan");
        return personService.findActiveUsersWithAgeGreaterThan(age);
    }

    @GetMapping("/findByName")
    public List<Person> findByName(String name){
        log.info("Hit controller for findByName");
        return personService.findByName(name);
    }

    @GetMapping("/findByNamev2")
    public List<Person> findByNamev2(String name){
        log.info("Hit controller for findByName");
        return personService.findByNamev2(name);
    }

    @PostMapping("/booking")
    public BookingAcknowledgement bookTicket(@RequestBody BookingRequest request){
        return personService.bookTicket(request);
    }

}
