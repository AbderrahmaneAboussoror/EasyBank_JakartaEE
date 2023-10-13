package com.example.solution_jee.dao;

import java.util.List;
import java.util.Optional;

public interface IData<E, ID> {
    Optional<E> save (E entity);
    Optional<E> update(E entity);
    boolean delete(ID id);
    List<E> findAll();
    Optional<E> findById(ID id);
}
