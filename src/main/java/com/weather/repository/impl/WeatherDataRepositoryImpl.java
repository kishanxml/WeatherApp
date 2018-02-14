package com.weather.repository.impl;

import com.weather.entity.WeatherData;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import com.weather.repository.WeatherDataRepository;

@Repository
public class WeatherDataRepositoryImpl implements WeatherDataRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WeatherData> getCityList() {
        TypedQuery<WeatherData> query = em.createNamedQuery("WeatherData.getCityList", WeatherData.class);
        return query.getResultList();
    }

    @Override
    public WeatherData getLatestWeather(String City) {
        TypedQuery<WeatherData> query = em.createNamedQuery("WeatherData.getLatestWeather", WeatherData.class);
        query.setParameter("pCity", City);
        WeatherData wd = (WeatherData) query.getResultList();
        return wd;
    }

    @Override
    public String getAVGWeather(String City) {
        TypedQuery<String> query = em.createNamedQuery("WeatherData.getAVGWeather", String.class);
        query.setParameter("pCity", City);
        String avg = query.getSingleResult();
        return avg;
    }

    @Override
    public WeatherData create(WeatherData user) {
        em.persist(user);
        em.persist(user.getWind());
        return user;
    }

}
