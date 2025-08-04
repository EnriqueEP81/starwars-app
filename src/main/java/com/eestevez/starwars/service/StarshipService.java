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

    public StarshipDto getStarships(int page, String sort, boolean asc, boolean search, String searchBy, String filter) {
        if (starshipCache.isEmpty() || search) {
            starshipCache = getAllStarships();
        }
        List<Starship> filtered = filterStarships(starshipCache, searchBy, filter);
        if (sort != null) {
            filtered  = starshipSortingService.sort(filtered, sort, asc);
        }
        int pageSize = 10;
        int totalPages = (int) Math.ceil((double) filtered.size() / pageSize);
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, filtered .size());
        List<Starship> paginated = filtered .subList(fromIndex, toIndex);

        StarshipDto dto = new StarshipDto();
        dto.setCount(filtered .size());
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

    private List<Starship> filterStarships(List<Starship> starships, String searchBy, String filter) {
        if (filter == null || filter.isBlank()) {
            return starships;
        }
        String filterLower = filter.toLowerCase();

        return starships.stream()
                .filter(s -> {
                    if ("model".equalsIgnoreCase(searchBy)) {
                        return s.model().toLowerCase().contains(filterLower);
                    } else {
                        return s.name().toLowerCase().contains(filterLower);
                    }
                })
                .toList();
    }

    public void setStarshipCache(List<Starship> starshipCache) {
        this.starshipCache = starshipCache;
    }
}
