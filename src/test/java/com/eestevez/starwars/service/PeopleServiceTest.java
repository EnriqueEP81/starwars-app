package com.eestevez.starwars.service;

import com.eestevez.starwars.dto.PeopleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
public class PeopleServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PeopleService peopleService;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void testGetPeople() {
        String mockResponse = """
            {
                "count": 1,
                "next": null,
                "previous": null,
                "results": [
                    {
                        "name": "Luke Skywalker",
                        "height": "172",
                        "mass": "77",
                        "hair_color": "blond",
                        "skin_color": "fair",
                        "eye_color": "blue",
                        "birth_year": "19BBY",
                        "gender": "male",
                        "created": "2014-12-09T13:50:51.644000Z",
                        "edited": "2014-12-20T21:17:56.891000Z"
                    }
                ]
            }
            """;

        mockServer.expect(requestTo("https://swapi.py4e.com/api/people"))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));

        PeopleDto dto = peopleService.getPeople();

        assertNotNull(dto);
        assertEquals(1, dto.getCount());
        assertEquals("Luke Skywalker", dto.getResults().get(0).name());

        mockServer.verify();
    }
}
