package com.weather.web.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Weather(@JsonProperty("valid_date") String forecastDate,
                      @JsonProperty("temp") double averageTemp,
                      @JsonProperty("wind_spd") double windSpeed) {
}
