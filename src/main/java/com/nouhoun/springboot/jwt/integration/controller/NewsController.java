package com.nouhoun.springboot.jwt.integration.controller;

//import com.nouhoun.springboot.jwt.integration.domain.RandomCity;
import com.nouhoun.springboot.jwt.integration.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private GenericService newsService;


//    @RequestMapping(value = "/test")
//    public List<RandomCity> getTest() {
//        return newsService.findAllRandomCities();
//    }
}
