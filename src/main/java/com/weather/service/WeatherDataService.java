package com.weather.service;

import com.weather.entity.WeatherData;
import java.util.List;

public interface WeatherDataService {

    public List<WeatherData> findAll() throws Exception;

    public WeatherData findOne(String id) throws Exception;

    public WeatherData create(WeatherData user) throws Exception;

    public WeatherData update(String id, WeatherData user) throws Exception;

    public void delete(String id) throws Exception;
}
