package com.eestevez.starwars.service;

import com.eestevez.starwars.dto.StarshipDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarshipService {
    private final RestTemplate restTemplate;
    private final String baseSwapiUrl;

    public StarshipService(RestTemplate restTemplate, @Value("${swapi.base.url}") String baseSwapiUrl) {
        this.restTemplate = restTemplate;
        this.baseSwapiUrl = baseSwapiUrl;
    }

    public StarshipDto getStartships() {
        String url = String.format("%s/starships", baseSwapiUrl);
        return restTemplate.getForObject(url, StarshipDto.class);
    }

    public StarshipDto getStarShipsPage(int page) {
        String url = String.format("%s/starships/?page=%d", baseSwapiUrl, page);
        return restTemplate.getForObject(url, StarshipDto.class);
    }
}
