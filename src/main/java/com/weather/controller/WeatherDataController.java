package com.weather.controller;

import com.weather.entity.WeatherData;
import com.weather.service.WeatherDataService;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherDataController {

    private WeatherDataService service;

    public WeatherDataController(WeatherDataService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String greeting(@RequestBody WeatherData data) {
        try {
            System.out.println(data);
            service.create(data);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "getting ";
    }

    @RequestMapping(value = "/test")
    public String greeting() {
        return "hi HIHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHh";
    }
}
