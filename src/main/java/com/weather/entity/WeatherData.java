package com.weather.entity;

import com.weather.pojo.AverageData;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@NamedQueries({
    @NamedQuery(name = "WeatherData.getCityList", query = "SELECT distinct wd.city FROM WeatherData wd"),
    @NamedQuery(name = "WeatherData.getLatestWeather", query = "SELECT wd FROM WeatherData wd where wd.city=:pCity order by wd.date desc,wd.time DESC"), // @NamedQuery(name = "WeatherData.getHourlyAverage", query = "SELECT wd.date as timestamp,AVG(wd.temperature),AVG(wd.humidity),AVG(wd.pressure),wd.city,wd.description,wd.id FROM WeatherData wd where wd.city=:pCity group by wd.date,wd.time")
})

@SqlResultSetMapping(name = "WeatherDataMapping",
        classes = {
            @ConstructorResult(
                    targetClass = AverageData.class,
                    columns = {
                        @ColumnResult(name = "date", type = String.class),
                        @ColumnResult(name = "temperature", type = Double.class),
                        @ColumnResult(name = "humidity", type = Double.class),
                        @ColumnResult(name = "pressure", type = Double.class),
                        @ColumnResult(name = "city", type = String.class)
                    })
        })

@NamedNativeQueries({
    @NamedNativeQuery(name = "WeatherData.getDailyAverage",
            query = "SELECT DATE_FORMAT(wd.date, '%Y-%m-%d') as date,AVG(wd.temperature) as temperature,AVG(wd.humidity) as humidity,AVG(wd.pressure) as pressure,wd.city as city FROM WeatherData wd where wd.city=:pCity group by wd.date",
            resultSetMapping = "WeatherDataMapping"),
    @NamedNativeQuery(name = "WeatherData.getHourlyAverage",
            query = "SELECT   MAKETIME(Hour(wd.time),0,0) date,AVG(wd.temperature) as temperature,AVG(wd.humidity) as humidity,AVG(wd.pressure) as pressure,wd.city as city FROM WeatherData wd where wd.city=:pCity GROUP BY wd.date,Hour(wd.time)",
            resultSetMapping = "WeatherDataMapping")
})

@Entity
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Basic(optional = false)
    private java.sql.Date date;

    @Basic(optional = false)
    private java.sql.Time time;

    @PrePersist
    public void calculateDateTime() {
        try {
            String originalString = this.timestamp;
            java.util.Date tempdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).parse(originalString);
            this.time = new Time(tempdate.getTime());
            this.date = new java.sql.Date(tempdate.getTime());
        } catch (ParseException ex) {
            System.out.println(ex);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String city;
    private String description;
    private String humidity;
    private String pressure;
    private String temperature;
    @Transient
    private String timestamp;

    @OneToOne
    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    private NamedQuery NamedNativeQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
