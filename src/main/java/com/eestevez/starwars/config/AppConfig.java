package com.eestevez.starwars.config;


import com.eestevez.starwars.service.sort.CreatedPersonSorter;
import com.eestevez.starwars.service.sort.CreatedStarshipSorter;
import com.eestevez.starwars.service.sort.NamePersonSorter;
import com.eestevez.starwars.service.sort.NameStarshipSorter;
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
    public CreatedPersonSorter createdPersonSorter() {return new CreatedPersonSorter();}

    @Bean
    public NamePersonSorter namePersonSorter() {return new NamePersonSorter();}

    @Bean
    public CreatedStarshipSorter createsStartShipSorter() {return new CreatedStarshipSorter();}

    @Bean
    public NameStarshipSorter nameStartshipSorter() {return new NameStarshipSorter();}
}
