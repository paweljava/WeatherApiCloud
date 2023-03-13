package com.weather.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.weather.app.dto.ResponseWeatherDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.weather.web.client.WeatherClient.API_KEY;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WireMockTest(httpPort = 9090)
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

    private final String date = "2022-11-16";

    @BeforeAll
    public static void startServer() {
        configureFor("localhost", 9090);
        WIRE_MOCK_SERVER.start();
        System.out.println("WireMock Serwer is running on port " + WIRE_MOCK_SERVER.getOptions().portNumber());
    }

    @Test
    void should_get_best_weather_conditions() throws Exception {
        // given
        // Jastarnia
        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(54.69606)))
                .withQueryParam("lon", equalTo(String.valueOf(18.67873)))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/jastarnia-response.json")));

        // Bridgetown
        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(13.10732)))
                .withQueryParam("lon", equalTo(String.valueOf(-59.62021)))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/bridgetown-response.json")));

        // Fortaleza
        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(-3.71722)))
                .withQueryParam("lon", equalTo(String.valueOf(-38.54306)))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/fortelaza-response.json")));

        // Pissoúri
        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(34.66942)))
                .withQueryParam("lon", equalTo(String.valueOf(32.70132)))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/pissoúri-response.json")));

        // when
        var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/weather/" + date))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        // then
        var response = asList(objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(UTF_8), ResponseWeatherDto[].class));

        assertEquals("Fortaleza", response.get(0).cityName());
        assertEquals(29.7, response.get(0).weatherDtoList().get(0).averageTemp());
        assertEquals(3.7, response.get(0).weatherDtoList().get(0).windSpeed());
        //verify(getRequestedFor(urlPathEqualTo("/v2.0/forecast/daily")));
    }

    /*@Test
    void should_call_get_weather_forecast_and_return_an_error_due_not_found_request() throws Exception {

        stubFor(WireMock.get(urlPathEqualTo(apiHost + "/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(34.66942)))
                .withQueryParam("lon", equalTo(String.valueOf(32.70132)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(notFound()));

        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .willReturn(notFound()));

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/" + date))
                .andDo(print()).andExpect(status().isNotFound());

        verify(getRequestedFor(urlPathEqualTo("/v2.0/forecast/daily")));
        mockmvc spring how to tested
    }*/
}
