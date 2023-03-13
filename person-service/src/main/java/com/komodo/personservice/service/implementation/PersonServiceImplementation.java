package com.komodo.personservice.service.implementation;

import com.komodo.personservice.dto.BookingAcknowledgement;
import com.komodo.personservice.dto.BookingRequest;
import com.komodo.personservice.dto.PersonDTO;
import com.komodo.personservice.entity.PaymentInfo;
import com.komodo.personservice.entity.Person;
import com.komodo.personservice.repository.PaymentInfoRepository;
import com.komodo.personservice.repository.PersonRepository;
import com.komodo.personservice.service.PersonService;
import com.komodo.personservice.utils.PaymentUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"persons"})
public class PersonServiceImplementation implements PersonService {
    private final PersonRepository personRepository;

    private final PaymentInfoRepository paymentInfoRepository;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Override
    @Cacheable
    public List<Person> getAllPerson() {

        List<Person> personList = personRepository.findAll();

        log.info("Getting All Lessons from databases");
        return personList;
    }

    @Override
    @CacheEvict(value = "persons", allEntries = true)
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
                .toList();
    }

    @Override
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
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

    @Override
    @Transactional
    public BookingAcknowledgement bookTicket(BookingRequest request) {
        Person person = request.getPerson();
        person = personRepository.save(person);

        PaymentInfo paymentInfo = request.getPaymentInfo();

        PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), person.getFare());

        paymentInfo.setPersonId(person.getPId());
        paymentInfo.setAmount(person.getFare());
        paymentInfoRepository.save(paymentInfo);
        return new BookingAcknowledgement("SUCCESS", person.getFare(), UUID.randomUUID().toString().split("-")[0], person);
    }

}
