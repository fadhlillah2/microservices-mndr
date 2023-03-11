package com.komodo.personservice.service;


import com.komodo.personservice.dto.PersonDTO;
import com.komodo.personservice.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPerson();

    void createPerson(PersonDTO requestBody);

    List<Person> findByName(String name);

    List<Person> findActiveUsersWithAgeGreaterThan(int age);
}
