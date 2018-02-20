package com.weather.repository.impl;

import com.weather.entity.WeatherData;
import com.weather.pojo.AverageData;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.weather.repository.WeatherDataRepository;
import java.util.Optional;
import javax.persistence.Query;

@Repository
public class WeatherDataRepositoryImpl implements WeatherDataRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<String> getCityList() {
        TypedQuery<String> query = em.createNamedQuery("WeatherData.getCityList", String.class);
        return query.getResultList();
    }

    @Override
    public Optional<WeatherData> getLatestWeather(String City) {
        TypedQuery<WeatherData> query = em.createNamedQuery("WeatherData.getLatestWeather", WeatherData.class);
        query.setParameter("pCity", City);
        query.setMaxResults(1);
        return Optional.ofNullable((WeatherData) query.getSingleResult());

    }

    @Override
    public WeatherData create(WeatherData user) {
        em.persist(user);
        em.persist(user.getWind());
        return user;
    }

    @Override
    public String getLatestProperty(String city, String property) {
        Query q = em.createNativeQuery("SELECT wd." + property + " FROM weatherdata wd where wd.city='" + city + "' order by wd.date desc,wd.time DESC");
        q.setMaxResults(1);
        return (String) q.getSingleResult();
    }

    @Override
    public List<AverageData> getHourlyWeather(String City) {
        Query query = em.createNamedQuery("WeatherData.getHourlyAverage");
        query.setParameter("pCity", City);
        return query.getResultList();
    }

    @Override
    public List<AverageData> getDailyWeather(String City) {
        Query query = em.createNamedQuery("WeatherData.getDailyAverage");
        query.setParameter("pCity", City);
        return query.getResultList();
    }
}
