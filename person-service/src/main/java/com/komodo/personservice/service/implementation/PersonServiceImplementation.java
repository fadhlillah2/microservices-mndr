package com.komodo.personservice.service.implementation;

import com.komodo.personservice.dto.PersonDTO;
import com.komodo.personservice.entity.Person;
import com.komodo.personservice.repository.PersonRepository;
import com.komodo.personservice.service.PersonService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImplementation implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Override
    public List<Person> getAllPerson() {

        List<Person> personList = personRepository.findAll();

        log.info("Getting All Lessons from databases");
        return personList;
    }

    @Override
    public void createPerson(PersonDTO requestBody) {
        Person person = modelMapper.map(requestBody, Person.class);
        personRepository.save(person);
        log.info("Success add lesson : {}", requestBody.toString());
    }

    @Override
    public List<Person> findActiveUsersWithAgeGreaterThan(int age) {
        return personRepository.findAll()
                .stream()
                .filter(Person::isActive)
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByName(String name) {
        return (List<Person>) personRepository.findByName(name);
    }


    @Override
    public List<Person> findByNamev2(String name) {
        String queryString = "SELECT e FROM Person e WHERE e.name = :name";
        Query query = entityManager.createQuery(queryString, Person.class);
        query.setParameter("name", name);
        List<Person> person = query.getResultList();
        if(person.isEmpty()) return new ArrayList<>();
        return person;
    }

}
