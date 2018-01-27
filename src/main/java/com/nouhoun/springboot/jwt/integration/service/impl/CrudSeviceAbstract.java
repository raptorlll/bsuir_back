package com.nouhoun.springboot.jwt.integration.service.impl;

import org.springframework.data.repository.CrudRepository;
import java.io.Serializable;
import java.util.List;

abstract class CrudSeviceAbstract<T, K extends Serializable> {
    abstract protected CrudRepository<T, K> getRepository();

    public List<T> findAll() {
        return (List<T>) getRepository().findAll();
    }

    public void delete(K id) {
        getRepository().delete(id);
    }

    public T findOne(K id) {
        return getRepository().findOne(id);
    }

    public T save(T u) {
        return getRepository().save(u);
    }
}
