package com.komodo.personservice.service;


import com.komodo.personservice.dto.BookingAcknowledgement;
import com.komodo.personservice.dto.BookingRequest;
import com.komodo.personservice.dto.PersonDTO;
import com.komodo.personservice.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPerson();

    void createPerson(PersonDTO requestBody);

    List<Person> findByName(String name);

    List<Person> findActiveUsersWithAgeGreaterThan(int age);

    List<Person> findByNamev2(String name);

    BookingAcknowledgement bookTicket(BookingRequest request);
}
