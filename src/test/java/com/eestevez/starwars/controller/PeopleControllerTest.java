package com.eestevez.starwars.controller;

import com.eestevez.starwars.dto.PeopleDto;
import com.eestevez.starwars.model.Person;
import com.eestevez.starwars.service.PeopleService;
import com.eestevez.starwars.service.sort.PersonSortingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeopleController.class)
public class PeopleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeopleService peopleService;

    @MockBean
    private PersonSortingService personSortingService;

    @Test
    void testGetPeople() throws Exception {
        PeopleDto mockDto = new PeopleDto();
        mockDto.setCount(1);
        Person person = new Person(
                "Luke Skywalker",    // name
                "19BBY",             // birthYear
                "blue",              // eyeColor
                "male",              // gender
                "blond",             // hairColor
                "172",               // height
                "77",                // mass
                "fair",              // skinColor
                "Tatooine",          // homeworld
                null,                // created
                null                 // edited
        );
        mockDto.setResults(List.of(person));

        Mockito.when(peopleService.getPeoplePage(anyInt())).thenReturn(mockDto);

        Mockito.when(personSortingService.sort(Mockito.anyList(), Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(List.of(person));

        mockMvc.perform(get("/people")
                        .param("page", "1")
                        .param("sort", "name")
                        .param("asc", "true"))
                .andExpect(status().isOk())
                .andExpect(view().name("people"))
                .andExpect(model().attributeExists("people"))
                .andExpect(model().attributeExists("pageInfo"))
                .andExpect(model().attribute("sort", "name"))
                .andExpect(model().attribute("asc", true));
    }
}
