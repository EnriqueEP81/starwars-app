package com.eestevez.starwars.controller;

import com.eestevez.starwars.dto.PageInfo;
import com.eestevez.starwars.dto.StarshipDto;
import com.eestevez.starwars.model.Starship;
import com.eestevez.starwars.service.StarshipService;
import com.eestevez.starwars.service.sort.StarshipSortingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StarshipController {

    private final StarshipService starshipService;
    private final StarshipSortingService starshipSortingService;

    public StarshipController(StarshipService starshipService, StarshipSortingService starshipSortingService){
        this.starshipService = starshipService;
        this.starshipSortingService = starshipSortingService;
    }

    @GetMapping("/starships")
    public String getPeople(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(defaultValue = "true") boolean asc,
            Model model){

        StarshipDto starshipDto = starshipService.getStarShipsPage(page);
        if (sort != null) {
            List<Starship> sorted = starshipSortingService.sort(starshipDto.getResults(), sort, asc);
            starshipDto.setResults(sorted);
        }
        int totalPages = (int) Math.ceil((double) starshipDto.getCount() / 10);

        model.addAttribute("starships", starshipDto);
        model.addAttribute("pageInfo", new PageInfo(page, totalPages));
        model.addAttribute("sort", sort);
        model.addAttribute("asc", asc);
        return "starships";
    }
}
