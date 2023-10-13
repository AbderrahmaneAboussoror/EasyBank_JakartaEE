package com.example.solution_jee.service;

import com.example.solution_jee.model.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    Optional<Person> save(Person person);
    Optional<Person> update(Person person);
    boolean delete(String s);
    Optional<Person> findById(String s);
    List<Person> findAll();
}
