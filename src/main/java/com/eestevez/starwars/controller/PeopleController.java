package com.eestevez.starwars.controller;

import com.eestevez.starwars.dto.PageInfo;
import com.eestevez.starwars.dto.PeopleDto;
import com.eestevez.starwars.service.PeopleService;
import com.eestevez.starwars.service.sort.PersonSortingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eestevez.starwars.model.Person;

import java.util.List;

@Controller
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonSortingService personSortingService;

    public PeopleController(PeopleService peopleService, PersonSortingService personSortingService){
        this.peopleService = peopleService;
        this.personSortingService = personSortingService;
    }

    @GetMapping("/people")
    public String getPeople(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(defaultValue = "true") boolean asc,
            Model model){

        PeopleDto peopleDto = peopleService.getPeoplePage(page);
        if (sort != null) {
            List<Person> sorted = personSortingService.sort(peopleDto.getResults(), sort, asc);
            peopleDto.setResults(sorted);
        }
        int totalPages = (int) Math.ceil((double) peopleDto.getCount() / 10);

        model.addAttribute("people", peopleDto);
        model.addAttribute("pageInfo", new PageInfo(page, totalPages));
        model.addAttribute("sort", sort);
        model.addAttribute("asc", asc);
        return "people";
    }
}
