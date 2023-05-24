package com.weather.app.service;

import com.weather.app.model.City;
import com.weather.app.repository.CityRepository;
import com.weather.app.repository.DbRepository;
import com.weather.web.client.WeatherClient;
import com.weather.web.client.model.VisualCrossingApiDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.weather.app.EWeatherDtoList.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class WeatherServiceTest {

    private static final LocalDate DATE = LocalDate.parse("2023-05-25");
    private static final City CITY_1 = new City("Jastarnia", 54.7007,18.67);
    private static final City CITY_2 = new City("Bridgetown", 13.112,-59.6127);
    private static final List<City> CITY_LIST = List.of(CITY_1, CITY_2);
    private static final VisualCrossingApiDto VISUAL_CROSSING_API_DTO1 = new VisualCrossingApiDto(CITY_1.getCityName(), WEATHER_LIST_1);
    private static final VisualCrossingApiDto VISUAL_CROSSING_API_DTO2 = new VisualCrossingApiDto(CITY_2.getCityName(), WEATHER_LIST_2);
    private static final List<VisualCrossingApiDto> WEATHER_BIT_RESPONSE_DTO_LIST = List.of(VISUAL_CROSSING_API_DTO1, VISUAL_CROSSING_API_DTO2);

    private final WeatherClient weatherClient = mock(WeatherClient.class);
    private final CityRepository cityRepository = mock(CityRepository.class);
    private final DbRepository dbRepository = mock((DbRepository.class));
    private final WeatherService weatherService = new WeatherService(weatherClient, cityRepository, dbRepository);

    @Test
    void should_get_cities_weathers() {
        // given
        given(cityRepository.findAll()).willReturn(CITY_LIST);

        // when
        final var result = weatherService.getCitiesWeathersLambda(WEATHER_BIT_RESPONSE_DTO_LIST, DATE);

        // then
        assertEquals(VISUAL_CROSSING_API_DTO2.cityName(), result.get(0).cityName());
        assertEquals(VISUAL_CROSSING_API_DTO2.days().get(4).forecastDate(), result.get(0).days().get(0).forecastDate());
        assertEquals(VISUAL_CROSSING_API_DTO2.days().get(4).averageTemp(), result.get(0).days().get(0).averageTemp());
        assertEquals(VISUAL_CROSSING_API_DTO2.days().get(4).windSpeed(), result.get(0).days().get(0).windSpeed());
        assertNotEquals(VISUAL_CROSSING_API_DTO1.cityName(), result.get(0).cityName());
    }

    @Test
    void should_get_weather() {
        // given
        given(cityRepository.findAll()).willReturn(CITY_LIST);
        given(weatherClient.getForecastByCoordinates(CITY_1.getLat(), CITY_1.getLon())).willReturn(VISUAL_CROSSING_API_DTO1);
        given(weatherClient.getForecastByCoordinates(CITY_2.getLat(), CITY_2.getLon())).willReturn(VISUAL_CROSSING_API_DTO2);

        // when
        final var result = weatherService.getWeather(DATE);

        // then
        assertEquals(CITY_2.getCityName(), result.get(0).cityName());
        assertEquals(WEATHER_25.forecastDate(), result.get(0).days().get(0).forecastDate());
        assertEquals(WEATHER_25.averageTemp(), result.get(0).days().get(0).averageTemp());
        assertEquals(WEATHER_25.windSpeed(), result.get(0).days().get(0).windSpeed());
        assertNotEquals(WEATHER_1.forecastDate(), result.get(0).days().get(0).forecastDate());
        assertNotEquals(WEATHER_10.averageTemp(), result.get(0).days().get(0).averageTemp());
        assertNotEquals(WEATHER_4.windSpeed(), result.get(0).days().get(0).windSpeed());
    }
}