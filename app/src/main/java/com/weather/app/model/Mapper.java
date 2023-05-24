package com.weather.app.model;

import com.weather.app.model.controller.WeatherDto;
import com.weather.app.model.controller.WeatherControllerDto;
import com.weather.app.model.db.WeatherDbDto;
import com.weather.web.client.model.VisualCrossingApiDto;
import com.weather.web.client.model.Weather;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static List<WeatherControllerDto> mapToWeatherControllerDto(List<VisualCrossingApiDto> visualCrossingApiDto) {
        return visualCrossingApiDto.stream().map(
                        data -> new WeatherControllerDto(
                                data.cityName(),
                                data.days().stream().map(
                                                weather -> new WeatherDto(
                                                        weather.forecastDate(),
                                                        weather.averageTemp(),
                                                        weather.windSpeed()))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public static List<WeatherDbDto> mapToWeatherDbDto(List<VisualCrossingApiDto> visualCrossingApiDto) {
        return visualCrossingApiDto.stream().map(
                data -> new WeatherDbDto (
                        data.cityName(),
                        data.days().stream().map(Weather::forecastDate).findFirst().get(),
                        data.days().stream().map(Weather::averageTemp).findFirst().get()))
                .collect(Collectors.toList());
    }
}