package com.weather.web.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Weather(@JsonProperty("datetime") String forecastDate,
                      @JsonProperty("temp") double averageTemp,
                      @JsonProperty("windspeed") double windSpeed) {
}
