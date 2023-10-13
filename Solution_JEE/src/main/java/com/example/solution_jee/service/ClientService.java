package com.example.solution_jee.service;
import com.example.solution_jee.dao.script.client.IClientDAO;
import com.example.solution_jee.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.NamedQuery;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Named
@ApplicationScoped
public class ClientService implements IPersonService{
    private IClientDAO clientDAO;

    @Inject
    public ClientService(IClientDAO iClientDAO) {
        this.clientDAO = iClientDAO;
    }

    @Override
    public List<Person> findAll() {
        return clientDAO.findAll();
    }

    @Override
    public boolean delete(String s) {
        return clientDAO.delete(s);
    }

    @Override
    public Optional<Person> findById(String s) {
        return clientDAO.findById(s);
    }

    @Override
    public Optional<Person> save(Person person) {
        return clientDAO.save(person);
    }

    @Override
    public Optional<Person> update(Person person) {
        return clientDAO.update(person);
    }
}
