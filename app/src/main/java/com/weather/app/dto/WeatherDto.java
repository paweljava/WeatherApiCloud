package com.weather.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherDto(@JsonProperty("valid_date") String data,
                         @JsonProperty("temp") double averageTemp,
                         @JsonProperty("wind_spd") double windSpeed) {
}
