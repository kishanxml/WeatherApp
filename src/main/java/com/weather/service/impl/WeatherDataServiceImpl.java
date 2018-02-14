package com.weather.service.impl;

import com.weather.entity.WeatherData;
import com.weather.exception.BadRequestException;
import com.weather.repository.WeatherDataRepository;
import com.weather.service.WeatherDataService;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private WeatherDataRepository repository;

    public WeatherDataServiceImpl(WeatherDataRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public WeatherData findOne(String id) throws NotFoundException {
        return repository.findOne(id)
                .orElseThrow(() -> new NotFoundException("WeatherData with id " + id + " does not exist"));
    }

    @Override
    @Transactional
    public WeatherData create(WeatherData user) {
        return repository.create(user);
    }

    @Override
    @Transactional
    public WeatherData update(String id, WeatherData user) throws NotFoundException {
        repository.findOne(id).orElseThrow(() -> new NotFoundException("WeatherData with id " + id + " does not exist"));
        return repository.update(user);
    }

}
