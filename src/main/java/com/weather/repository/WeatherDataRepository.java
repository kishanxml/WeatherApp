package com.weather.repository;

import com.weather.entity.WeatherData;
import java.util.List;

public interface WeatherDataRepository {

    List<WeatherData> getCityList();

    public String getAVGWeather(String City);

    public WeatherData getLatestWeather(String City);

    public WeatherData create(WeatherData user);

}
