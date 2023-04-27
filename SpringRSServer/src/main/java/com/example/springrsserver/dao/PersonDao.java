package com.example.springrsserver.dao;

import com.example.springrsserver.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonDao extends CrudRepository<Person, Integer> {

    @Query(value = "SELECT p FROM Person p WHERE p.firstName LIKE %:name% OR p.lastName LIKE %:name%")
    List<Person> findPersonByName(@Param("name") String name);

}
