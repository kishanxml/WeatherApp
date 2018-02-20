package com.weather.pojo;

public class AverageData {

    String date;
    Double humidity;
    Double pressure;
    String city;
    Double temperature;

    public AverageData(String date, Double temprature, Double humidity, Double pressure, String city) {
        this.date = date;
        this.temperature = temprature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temprature) {
        this.temperature = temprature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
