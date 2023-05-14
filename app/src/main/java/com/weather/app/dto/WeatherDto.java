package com.weather.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherDto(@JsonProperty("datetime") String data,
                         @JsonProperty("temp") double averageTemp,
                         @JsonProperty("windspeed") double windSpeed) {
}
