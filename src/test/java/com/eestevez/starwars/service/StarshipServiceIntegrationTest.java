package com.eestevez.starwars.service;

import com.eestevez.starwars.dto.StarshipDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StarshipServiceIntegrationTest {
    @Autowired
    private StarshipService starshipService;

    @Test
    void testGetPeopleFirstPage() {
        StarshipDto starshipDto = starshipService.getStartships();

        assertNotNull(starshipDto);
        assertTrue(starshipDto.getResults().size() > 0, "Should return at least one person");

        System.out.println("Count: " + starshipDto.getCount());

    }
}
