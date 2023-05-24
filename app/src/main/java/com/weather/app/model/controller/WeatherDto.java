package com.weather.app.model.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record WeatherDto(@JsonProperty("datetime") LocalDate data,
                         @JsonProperty("temp") double averageTemp,
                         @JsonProperty("windspeed") double windSpeed) {
}