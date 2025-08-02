package com.eestevez.starwars.service;

import com.eestevez.starwars.dto.PeopleDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PeopleService {
    private final RestTemplate restTemplate;
    private final String peopleUrl;

    public PeopleService(RestTemplate restTemplate, @Value("${swapi.people.url}") String peopleUrl) {
        this.restTemplate = restTemplate;
        this.peopleUrl = peopleUrl;
    }

    public PeopleDto getPeople(int page) {
        String url = peopleUrl + "?page=" + page;
        return restTemplate.getForObject(url, PeopleDto.class);
    }
}
