package com.weather.app.model;

import com.weather.app.dto.ResponseWeatherDto;
import com.weather.app.dto.WeatherDto;
import com.weather.web.client.dto.WeatherBitResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static ResponseWeatherDto mapRequestDtoToResponseDto(WeatherBitResponseDto weatherBitResponseDto) {
        return new ResponseWeatherDto(
                weatherBitResponseDto.cityName(),
                weatherBitResponseDto.data().stream().map(data -> new WeatherDto(
                                data.forecastDate(),
                                data.averageTemp(),
                                data.windSpeed()))
                        .collect(Collectors.toList()));
    }

    public static List<ResponseWeatherDto> mapListRequestDtoToListResponseDto(List<WeatherBitResponseDto> weatherBitResponseDto) {
        return weatherBitResponseDto.stream().map(
                        data -> new ResponseWeatherDto(
                                data.cityName(),
                                data.data().stream().map(
                                                weather -> new WeatherDto(
                                                        weather.forecastDate(),
                                                        weather.averageTemp(),
                                                        weather.windSpeed()))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}