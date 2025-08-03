package com.eestevez.starwars.service;

import com.eestevez.starwars.dto.StarshipDto;
import com.eestevez.starwars.model.Starship;
import com.eestevez.starwars.service.sort.StarshipSortingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class StarshipService {
    private final RestTemplate restTemplate;
    private final StarshipSortingService starshipSortingService;
    private final String baseSwapiUrl;

    private List<Starship> starshipCache = new ArrayList<>();

    public StarshipService(RestTemplate restTemplate, @Value("${swapi.base.url}") String baseSwapiUrl,
                           StarshipSortingService starshipSortingService) {
        this.restTemplate = restTemplate;
        this.baseSwapiUrl = baseSwapiUrl;
        this.starshipSortingService =starshipSortingService;
    }

    public StarshipDto getStartships() {
        String url = String.format("%s/starships", baseSwapiUrl);
        return restTemplate.getForObject(url, StarshipDto.class);
    }

    public StarshipDto getStarships(int page, String sort, boolean asc, boolean search) {
        if (starshipCache.isEmpty() || search) {
            starshipCache = getAllStarships();
            if (sort != null) {
                starshipCache = starshipSortingService.sort(starshipCache, sort, asc);
            }
        }
        int pageSize = 10;
        int totalPages = (int) Math.ceil((double) starshipCache.size() / pageSize);
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, starshipCache.size());
        List<Starship> paginated = starshipCache.subList(fromIndex, toIndex);

        StarshipDto dto = new StarshipDto();
        dto.setCount(starshipCache.size());
        dto.setResults(paginated);
        dto.setTotalPages(totalPages);
        return dto;
    }

    private List<Starship> getAllStarships() {
        List<Starship> result = new ArrayList<>();
        String url = String.format("%s/starships", baseSwapiUrl);
        StarshipDto dto;
        do {
            dto = restTemplate.getForObject(url, StarshipDto.class);
            result.addAll(dto.getResults());
            url = dto.getNext();
        } while (url != null);
        return result;
    }
}
