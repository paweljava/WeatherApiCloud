package com.weather.app.service;

import com.weather.app.model.City;
import com.weather.app.repository.CityRepository;
import com.weather.web.client.WeatherClient;
import com.weather.web.client.dto.WeatherBitResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.weather.app.EWeatherDtoList.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class WeatherServiceTest {

    private static final String DATE = "2022-11-14";
    private static final City CITY_1 = new City(3097421L, "Jastarnia", "82", "PL", 54.69606, 18.67873);
    private static final City CITY_2 = new City(3374036L, "Bridgetown", "08", "BB", "Barbados", 13.10732, -59.62021);
    private static final List<City> CITY_LIST = List.of(CITY_1, CITY_2);
    private static final WeatherBitResponseDto WEATHER_BIT_RESPONSE_DTO_1 = new WeatherBitResponseDto(CITY_1.getCityName(), WEATHER_LIST_1);
    private static final WeatherBitResponseDto WEATHER_BIT_RESPONSE_DTO_2 = new WeatherBitResponseDto(CITY_2.getCityName(), WEATHER_LIST_2);
    private static final List<WeatherBitResponseDto> WEATHER_BIT_RESPONSE_DTO_LIST = List.of(WEATHER_BIT_RESPONSE_DTO_1, WEATHER_BIT_RESPONSE_DTO_2);

    private final WeatherClient weatherClient = mock(WeatherClient.class);
    private final CityRepository cityRepository = mock(CityRepository.class);
    private final WeatherService weatherService = new WeatherService(weatherClient, cityRepository);

    @Test
    void should_get_city_by_formula() {
        // given
        // when
        final var result = weatherService.getCityWeather(WEATHER_BIT_RESPONSE_DTO_LIST);

        // then
        assertEquals(WEATHER_BIT_RESPONSE_DTO_1.cityName(), result.cityName());
        assertEquals(WEATHER_BIT_RESPONSE_DTO_1.data(), result.data());
        assertNotEquals(WEATHER_BIT_RESPONSE_DTO_2.cityName(), result.cityName());
        assertNotEquals(WEATHER_BIT_RESPONSE_DTO_2.data(), result.data());
    }

    @Test
    void should_get_weather() {
        // given
        given(cityRepository.getCityList()).willReturn(CITY_LIST);
        given(weatherClient.getForecastByCoordinates(CITY_1.getLat(), CITY_1.getLon())).willReturn(WEATHER_BIT_RESPONSE_DTO_1);
        given(weatherClient.getForecastByCoordinates(CITY_2.getLat(), CITY_2.getLon())).willReturn(WEATHER_BIT_RESPONSE_DTO_2);

        // when
        final var result = weatherService.getWeather(DATE);

        // then
        assertEquals(CITY_2.getCityName(), result.get(0).cityName());
        assertEquals(WEATHER_34.forecastDate(), result.get(0).data().get(0).forecastDate());
        assertEquals(WEATHER_34.averageTemp(), result.get(0).data().get(0).averageTemp());
        assertEquals(WEATHER_34.windSpeed(), result.get(0).data().get(0).windSpeed());
        assertNotEquals(WEATHER_1.forecastDate(), result.get(0).data().get(0).forecastDate());
        assertNotEquals(WEATHER_10.averageTemp(), result.get(0).data().get(0).averageTemp());
        assertNotEquals(WEATHER_4.windSpeed(), result.get(0).data().get(0).windSpeed()
        );
    }
}