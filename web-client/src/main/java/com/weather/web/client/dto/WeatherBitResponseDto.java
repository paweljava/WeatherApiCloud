package com.weather.web.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.web.client.model.Weather;

import java.util.List;

public record WeatherBitResponseDto(@JsonProperty("address") String cityName, List<Weather> days) {
}