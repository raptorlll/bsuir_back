package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

abstract public class CrudAbstract<T, K> {
    abstract public CrudServiceInterface<T, K> getService();

    @ApiOperation(value = "Save availabe items")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Successfully saved")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public T saveItem(@RequestBody T information) {
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
