package com.weather.app.model;

import com.weather.app.dto.ResponseWeatherDto;
import com.weather.app.dto.WeatherDto;
import com.weather.web.client.dto.WeatherBitResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static List<ResponseWeatherDto> mapListRequestDtoToListResponseDto(List<WeatherBitResponseDto> weatherBitResponseDto) {
        return weatherBitResponseDto.stream().map(
                        data -> new ResponseWeatherDto(
                                data.cityName(),
                                data.days().stream().map(
                                                weather -> new WeatherDto(
                                                        weather.forecastDate(),
                                                        weather.averageTemp(),
                                                        weather.windSpeed()))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}