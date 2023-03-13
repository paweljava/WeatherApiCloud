package com.weather.app

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.weather.app.dto.ResponseWeatherDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import static com.github.tomakehurst.wiremock.client.WireMock.verify
import static com.weather.web.client.WeatherClient.API_KEY
import static java.nio.charset.StandardCharsets.UTF_8
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
//@WireMockTest(httpPort = 9090)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class WeatherRestApiIntegrationTestSpock extends Specification {

    @Value('${api_host}')
    String apiHost

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper objectMapper

    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(9090)

    def setup() {
        WIRE_MOCK_SERVER.start()
        configureFor("localhost", 9090)
        println("Serwer jest uruchomiony na porcie: " + WIRE_MOCK_SERVER.getOptions().portNumber())
    }

    def cleanup() {
        WIRE_MOCK_SERVER.stop()
    }

    def "should get best weather conditions"() {
        given:
        // Jastarnia
        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(54.69606)))
                .withQueryParam("lon", equalTo(String.valueOf(18.67873)))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/jastarnia-response.json")))

        // Bridgetown
        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(13.10732)))
                .withQueryParam("lon", equalTo(String.valueOf(-59.62021)))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/bridgetown-response.json")))

        // Fortaleza
        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(-3.71722)))
                .withQueryParam("lon", equalTo(String.valueOf(-38.54306)))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/fortelaza-response.json")))

        // Pissoúri
        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(34.66942)))
                .withQueryParam("lon", equalTo(String.valueOf(32.70132)))
                .withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/pissoúri-response.json")))

        when:
        var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/weather/2022-11-16"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn()

        then:
        var response = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(UTF_8), ResponseWeatherDto.class)

        verifyAll {
            assertEquals("Bridgetown", response.cityName())
            assertEquals(27.3, response.weatherDtoList().get(0).averageTemp())
            assertEquals(5.3, response.weatherDtoList().get(0).windSpeed())
            verify(getRequestedFor(urlPathEqualTo("/v2.0/forecast/daily")))
        }
    }
}

