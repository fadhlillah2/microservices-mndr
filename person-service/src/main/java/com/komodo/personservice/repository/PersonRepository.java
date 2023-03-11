package com.komodo.personservice.repository;

import com.komodo.personservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "SELECT * FROM Person WHERE name = :name", nativeQuery = true)
    List<Person> findByName(@Param("name") String name);

}
