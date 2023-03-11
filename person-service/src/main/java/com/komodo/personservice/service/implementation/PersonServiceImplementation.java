package com.komodo.personservice.service.implementation;

import com.komodo.personservice.dto.PersonDTO;
import com.komodo.personservice.entity.Person;
import com.komodo.personservice.repository.PersonRepository;
import com.komodo.personservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImplementation implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

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


}
