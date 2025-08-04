package com.eestevez.starwars.controller;

import com.eestevez.starwars.dto.StarshipDto;
import com.eestevez.starwars.model.Starship;
import com.eestevez.starwars.service.StarshipService;
import com.eestevez.starwars.service.sort.StarshipSortingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.OffsetDateTime;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StarshipController.class)
public class StarshipControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StarshipService starshipService;

    @MockBean
    private StarshipSortingService starshipSortingService;

    @Test
    void testGetStarships_returnsViewWithModel() throws Exception {
        StarshipDto mockDto = new StarshipDto();
        mockDto.setCount(1);
        mockDto.setTotalPages(1);
        mockDto.setResults(List.of(new Starship(
                "X-wing",
                "T-65",
                "12.5",
                "1050",
                "1",
                "0",
                "Starfighter",
                OffsetDateTime.now(),
                OffsetDateTime.now()
        )));

        Mockito.when(starshipService.getStarships(anyInt(), any(), anyBoolean(), anyBoolean(), anyString(), any()))
                .thenReturn(mockDto);

        mockMvc.perform(get("/starships")
                        .param("page", "1")
                        .param("searchBy", "name")
                        .param("filter", "X-wing"))
                .andExpect(status().isOk())
                .andExpect(view().name("starships"))
                .andExpect(model().attributeExists("starships"))
                .andExpect(model().attributeExists("pageInfo"))
                .andExpect(model().attribute("filter", "X-wing"))
                .andExpect(model().attribute("searchBy", "name"));
    }
}
