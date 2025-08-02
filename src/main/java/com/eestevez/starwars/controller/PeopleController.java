package com.eestevez.starwars.controller;

import com.eestevez.starwars.dto.PageInfo;
import com.eestevez.starwars.dto.PeopleDto;
import com.eestevez.starwars.service.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService){
        this.peopleService = peopleService;
    }

    @GetMapping("/people")
    public String getPeople(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "sort", required = false) String sort,
            Model model){

        PeopleDto peopleDto = peopleService.getPeoplePage(page);
        int totalPages = (int) Math.ceil((double) peopleDto.getCount() / 10);

        model.addAttribute("people", peopleDto);
        model.addAttribute("pageInfo", new PageInfo(page, totalPages));
        model.addAttribute("sort", sort);
        return "people";
    }
}
