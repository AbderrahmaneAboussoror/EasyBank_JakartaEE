package com.example.solution_jee.service;

import com.example.solution_jee.dao.script.employee.IEmployeeDAO;
import com.example.solution_jee.model.Person;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class EmployeeService implements IPersonService{
    @Inject
    private IEmployeeDAO employeeDAO;

    @Override
    public List<Person> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Optional<Person> findById(String s) {
        return employeeDAO.findById(s);
    }

    @Override
    public Optional<Person> save(Person person) {
        return employeeDAO.save(person);
    }

    @Override
    public Optional<Person> update(Person person) {
        return employeeDAO.update(person);
    }

    @Override
    public boolean delete(String s) {
        return employeeDAO.delete(s);
    }
}
