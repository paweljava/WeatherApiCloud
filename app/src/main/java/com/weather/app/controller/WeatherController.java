package com.weather.app.controller;

import com.weather.app.model.controller.WeatherControllerDto;
import com.weather.app.service.WeatherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

import static com.weather.app.model.Mapper.mapToWeatherControllerDto;
import static com.weather.app.model.Mapper.mapToWeatherDbDto;
import static com.weather.web.client.common.ExceptionMessages.INCORRECT_DATE_RANGE_EXCEPTION_MESSAGE;
import static com.weather.web.client.utility.Checks.checkThat;

@RestController
class WeatherController {
    private final WeatherService weatherService;
    private final Clock clock;

    public WeatherController(WeatherService weatherService, Clock clock) {
        this.weatherService = weatherService;
        this.clock = clock;
    }

    @GetMapping("/weather/{date}")
    public List<WeatherControllerDto> getWeatherData(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        checkThat(validateDate(date), INCORRECT_DATE_RANGE_EXCEPTION_MESSAGE);
        final var visualCrossingApiResponse = weatherService.getWeather(date);
        final var responseToDb = mapToWeatherDbDto(visualCrossingApiResponse);
        weatherService.save(responseToDb);

        return mapToWeatherControllerDto(visualCrossingApiResponse);
    }

    private boolean validateDate(LocalDate date) {
        final var today = LocalDate.now(clock).minusDays(1);
        final var date16DaysAhead = today.plusDays(16);
        return date.isAfter(today) && date.isBefore(date16DaysAhead);
    }
}