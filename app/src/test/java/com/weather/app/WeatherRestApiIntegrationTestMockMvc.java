package com.weather.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.weather.app.model.controller.WeatherControllerDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.weather.web.client.WeatherClient.API_KEY;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WeatherRestApiIntegrationTestMockMvc {

    @Value("${api_host}")
    private String apiHost;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(9090);

    private final LocalDate date = LocalDate.parse("2023-05-30");

    @BeforeAll
    public static void startServer() {
        configureFor("localhost", 9090);
        WIRE_MOCK_SERVER.start();
        System.out.println("WireMock Server is running on port " + WIRE_MOCK_SERVER.getOptions().portNumber());
    }

    @Test
    void should_get_best_weather_conditions() throws Exception {
        // given
        // Jastarnia
        stubJastarnia();
        // Bridgetown
        stubBridgetown();

        // Fortaleza
        stubFor(WireMock.get(urlPathEqualTo("/VisualCrossingWebServices/rest/services/timeline/-3.71841,-38.5429"))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/fortelaza-response.json")));

        // Pissoúri
        stubFor(WireMock.get(urlPathEqualTo("/VisualCrossingWebServices/rest/services/timeline/34.667,32.7004"))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/pissoúri-response.json")));

        // Mauritius
        stubFor(WireMock.get(urlPathEqualTo("/VisualCrossingWebServices/rest/services/timeline/-20.4697,57.3444"))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/lemorne-response.json")));


        // when
        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/weather/" + date))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        // then
        final var response = asList(objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(UTF_8), WeatherControllerDto[].class));

        assertEquals("Le Morne", response.get(0).cityName());
        assertEquals(24.6, response.get(0).weatherDtoList().get(0).averageTemp());
        assertEquals(32.4, response.get(0).weatherDtoList().get(0).windSpeed());
    }

    private static void stubBridgetown() {
        stubFor(WireMock.get(urlPathEqualTo("/VisualCrossingWebServices/rest/services/timeline/13.112,-59.6127"))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/bridgetown-response.json")));
    }

    private static void stubJastarnia() {
        stubFor(WireMock.get(urlPathEqualTo("/VisualCrossingWebServices/rest/services/timeline/54.7007,18.67"))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/jastarnia-response.json")));
    }
}