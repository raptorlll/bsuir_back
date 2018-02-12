package com.leonov.springboot.jwt.integration.service;

import java.util.List;

public interface CrudServiceInterface<T, K>  {
    List<T> findAll();

    T save(T u);

    T findOne(K id);

    T update(T u);

    void delete(K id);
}
