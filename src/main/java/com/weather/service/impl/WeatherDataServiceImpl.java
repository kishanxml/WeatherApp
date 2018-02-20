package com.weather.service.impl;

import com.weather.pojo.AverageData;
import com.weather.entity.WeatherData;
import com.weather.exception.NotFoundException;
import com.weather.repository.WeatherDataRepository;
import com.weather.service.WeatherDataService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private WeatherDataRepository repository;

    public WeatherDataServiceImpl(WeatherDataRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public WeatherData create(WeatherData user) {
        return repository.create(user);
    }

    @Override
    public List<String> getCityList() {
        return repository.getCityList();
    }

    @Override
    public WeatherData getLatestWeather(String city) {
        return repository.getLatestWeather(city).orElseThrow(() -> new NotFoundException("No Data available for " + city));
    }

    @Override
    public String getLatestProperty(String city, String property) {
        return repository.getLatestProperty(city, property);
    }

    @Override
    public List<AverageData> getHourlyWeather(String city) {
        return repository.getHourlyWeather(city);
    }

    @Override
    public List<AverageData> getDailyWeather(String city) {
        return repository.getDailyWeather(city);
    }
}
