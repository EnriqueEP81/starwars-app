package com.eestevez.starwars.config;


import com.eestevez.starwars.model.Person;
import com.eestevez.starwars.model.Starship;
import com.eestevez.starwars.service.sort.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sorter<Person> createdPersonSorter() {
        return new GenericSorter<>(Person::created, "created");
    }

    @Bean
    public Sorter<Person> namePersonSorter() {
        return new GenericSorter<>(Person::name, "name");
    }

    @Bean
    public Sorter<Starship> createdStarshipSorter() {
        return new GenericSorter<>(Starship::created, "created");
    }

    @Bean
    public Sorter<Starship> nameStarshipSorter() {
        return new GenericSorter<>(Starship::name, "name");
    }
}
