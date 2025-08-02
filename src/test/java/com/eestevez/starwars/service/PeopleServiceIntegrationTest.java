package com.eestevez.starwars.service;

import com.eestevez.starwars.dto.PeopleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PeopleServiceIntegrationTest {
    @Autowired
    private PeopleService personService;

    @Test
    void testGetPeopleFirstPage() {
        PeopleDto personDto = personService.getPeople();

        assertNotNull(personDto);
        assertTrue(personDto.getResults().size() > 0, "Should return at least one person");

        System.out.println("Count: " + personDto.getCount());

    }
}
