package com.weather.web.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record Weather(@JsonProperty("datetime") LocalDate forecastDate,
                      @JsonProperty("temp") double averageTemp,
                      @JsonProperty("windspeed") double windSpeed) {
}