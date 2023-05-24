package com.weather.app.model.controller;

import java.util.List;

public record WeatherControllerDto(String cityName, List<WeatherDto> weatherDtoList) {
}