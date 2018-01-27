package com.nouhoun.springboot.jwt.integration.service;

import com.nouhoun.springboot.jwt.integration.domain.CustomerInformation;

import java.util.List;

public interface CrudServiceInterface<T, K>  {
    List<T> findAll();

    T save(T u);

    T findOne(K id);

    void delete(K id);
}
