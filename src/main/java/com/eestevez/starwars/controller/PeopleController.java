package com.eestevez.starwars.controller;

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
    public String getPeople(@RequestParam(name = "sort", required = false) String sort, Model model){
        model.addAttribute("people", peopleService.getPeople(1));
        return "people";
    }
}
