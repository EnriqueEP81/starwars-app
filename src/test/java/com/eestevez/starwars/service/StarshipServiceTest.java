package com.eestevez.starwars.service;

import com.eestevez.starwars.dto.StarshipDto;
import com.eestevez.starwars.model.Starship;
import com.eestevez.starwars.service.sort.StarshipSortingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockRestServiceServer
public class StarshipServiceTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StarshipService starshipService;

    @Autowired
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        StarshipSortingService sortingService = Mockito.mock(StarshipSortingService.class);
        starshipService = new StarshipService(restTemplate, "http://fake-swapi.com/api", sortingService);
        starshipService.setStarshipCache(List.of(
                createStarship("Death Star", "DS-1 Orbital Battle Station", "Battlestation"),
                createStarship("Millennium Falcon", "YT-1300 light freighter", "Freighter"),
                createStarship("X-wing", "T-65 X-wing", "Starfighter"))
        );
    }

    @Test
    void testGetStarships_returnsMockedResponse() {
        String mockResponse = """
            {
                "count": 1,
                "next": null,
                "previous": null,
                "results": [
                    {
                        "name": "Death Star",
                        "model": "DS-1 Orbital Battle Station",
                        "manufacturer": "Imperial Department of Military Research",
                        "created": "2014-12-10T16:36:50.509000Z",
                        "edited": "2014-12-10T16:36:50.509000Z"
                    }
                ]
            }
            """;

        mockServer.expect(requestTo("http://fake-swapi.com/api/starships"))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));

        StarshipDto starshipDto = starshipService.getStarships(1, null, true, true, null, null);

        assertNotNull(starshipDto);
        assertEquals(1, starshipDto.getCount());
        assertEquals("Death Star", starshipDto.getResults().get(0).name());
    }

    @Test
    void testFilterByName() {
        StarshipDto dto = starshipService.getStarships(1, null, true, false, "name", "Falcon");

        assertEquals(1, dto.getCount());
        assertEquals("Millennium Falcon", dto.getResults().get(0).name());
    }

    @Test
    void testFilterByModel() {
        StarshipDto dto = starshipService.getStarships(1, null, true, false, "model", "X-wing");

        assertEquals(1, dto.getCount());
        assertEquals("X-wing", dto.getResults().get(0).name());
    }

    @Test
    void testFilterEmptyReturnsAll() {
        StarshipDto dto = starshipService.getStarships(1, null, true, false, "name", "");

        assertEquals(3, dto.getCount());
    }

    private Starship createStarship(String name, String model, String type) {
        return new Starship(
                name,
                model,
                "",
                "n/a",
                "0",
                "0",
                type,
                null,
                null
        );
    }
}
