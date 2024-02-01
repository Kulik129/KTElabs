package com.example.producingwebservice.repository;

import com.example.producingwebservice.entity.Country;
import io.spring.guides.gs_producing_web_service.Currency;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CountryRepository {
    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setId(1L);
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setPopulation(46704314);
        spain.setCurrency(Currency.EUR);
        countries.put(spain.getName(), spain);

        Country poland = new Country();
        poland.setId(2L);
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38186860);

        countries.put(poland.getName(), poland);

        Country uk = new Country();
        uk.setId(3L);
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);

        countries.put(uk.getName(), uk);

        Country rus = new Country();
        rus.setId(4L);
        rus.setName("Russian Federation");
        rus.setCapital("Moscow");
        rus.setCurrency(Currency.RUB);
        rus.setPopulation(143400000);

        countries.put(rus.getName(), rus);
    }

    public Country findCountry(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return countries.get(name);
    }

    public Country findCountryById(Long id) {
        Assert.notNull(id, "The country's name must not be null");
        return countries.get(id);
    }
}