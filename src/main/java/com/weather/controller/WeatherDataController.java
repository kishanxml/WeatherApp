package com.weather.controller;

import com.weather.constants.URI;
import com.weather.entity.WeatherData;
import com.weather.pojo.AverageData;
import com.weather.service.WeatherDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
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

    @RequestMapping(method = RequestMethod.POST, value = URI.INSERT)
    @ApiOperation(value = "Insert Weather Data", notes = "Insert Weather Data in JSON using POST method")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal Server Error"),})
    public WeatherData insertData(@RequestBody WeatherData data) {
        return service.create(data);
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.CITYLIST)
    @ApiOperation(value = "Get City List which ever reported Weather Data")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal Server Error"),})
    public List<String> getCityList() {
        return service.getCityList();
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.GET_LATEST_PROPERTY)
    @ApiOperation(value = "Get Latest Weather Data report of City")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal Server Error"),})
    public String getLatestWeather(@RequestParam String city, @RequestParam String property) {
        return service.getLatestProperty(city, property);
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.GET_LATEST_WEATHER)
    @ApiOperation(value = "Get Latest Weather property like humedity,temprature,pressure of City", notes = "please provide property and city as parameter")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal Server Error"),})
    public WeatherData getLatestWeather(@RequestParam String city) {
        return service.getLatestWeather(city);
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.AVG_HOURLY)
    @ApiOperation(value = "Get Horly average weather data of the City", notes = "Returns a Json list of average Property")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal Server Error"),})
    public List<AverageData> getHourlyAvgWeather(@RequestParam String city) {
        return service.getHourlyWeather(city);
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.AVG_DAILY)
    @ApiOperation(value = "Get Daily average weather data of the City", notes = "Returns a Json list of average Property")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Internal Server Error"),})
    public List<AverageData> getDailyAvgWeather(@RequestParam String city) {
        return service.getDailyWeather(city);
    }
}
