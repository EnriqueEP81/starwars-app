package com.eestevez.starwars.service;

import com.eestevez.starwars.dto.PeopleDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PeopleService {
    private final RestTemplate restTemplate;
    private final String baseSwapiUrl;

    public PeopleService(RestTemplate restTemplate, @Value("${swapi.base.url}") String baseSwapiUrl) {
        this.restTemplate = restTemplate;
        this.baseSwapiUrl = baseSwapiUrl;
    }

    public PeopleDto getPeople() {
        String url = String.format("%s/people", baseSwapiUrl);
        return restTemplate.getForObject(url, PeopleDto.class);
    }

    public PeopleDto getPeoplePage(int page) {
        String url = String.format("%s/people/?page=%d", baseSwapiUrl, page);
        return restTemplate.getForObject(url, PeopleDto.class);
    }
}
