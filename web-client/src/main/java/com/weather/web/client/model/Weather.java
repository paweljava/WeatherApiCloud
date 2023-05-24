package com.weather.web.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public record Weather(@JsonProperty("datetime") LocalDate forecastDate,
                      @JsonProperty("temp") double averageTemp,
                      @JsonProperty("windspeed") double windSpeed) {
}
