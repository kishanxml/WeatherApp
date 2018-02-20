package com.weather.service;

import com.weather.pojo.AverageData;
import com.weather.entity.WeatherData;
import java.util.List;
import java.util.Optional;

public interface WeatherDataService {

    List<String> getCityList();

    public String getLatestProperty(String city, String propoerty);

    public  WeatherData getLatestWeather(String city);

    public List<AverageData> getHourlyWeather(String city);

    public List<AverageData> getDailyWeather(String city);

    public WeatherData create(WeatherData data);
}
