package com.nouhoun.springboot.jwt.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

abstract public class CrudAbstract<T, K> {
    abstract public CrudServiceInterface<T, K> getService();

    @RequestMapping(value = "", method = RequestMethod.POST)
    public T saveClientInformation(@RequestBody T information) {
        try {
            getService().save(information);
        }catch (Exception e){
            System.out.println("e");
        }

        return  information;
    }

    @GetMapping(value = "")
    public Collection<T> getItems() {
        return getService().findAll();
    }

    @GetMapping(value = "/{id}")
    public T getItem(@PathVariable(value = "id") K id) {
        return getService().findOne(id);
    }

    @PutMapping(value = "/{id}")
    public T updateItem(@PathVariable(value = "id") K id, @RequestBody T information) {
        System.out.println("put");

        try{
            return getService().update(information);
        } catch (Exception e) {
            System.out.println("Error while put");
        }
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteItem(@PathVariable(value = "id") K id) {
        getService().delete(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
