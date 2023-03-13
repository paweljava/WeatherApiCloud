package com.weather.app.dto;

import java.util.List;

public record ResponseWeatherDto(String cityName, List<WeatherDto> weatherDtoList) {
}
