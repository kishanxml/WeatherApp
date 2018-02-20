package com.weather.repository;

import com.weather.pojo.AverageData;
import com.weather.entity.WeatherData;
import java.util.List;
import java.util.Optional;

public interface WeatherDataRepository {

    List<String> getCityList();

    public String getLatestProperty(String City, String property);

    public Optional<WeatherData> getLatestWeather(String City);

    public List<AverageData> getHourlyWeather(String City);

    public List<AverageData> getDailyWeather(String City);

    public WeatherData create(WeatherData data);

}
