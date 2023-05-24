package com.weather.web.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.web.client.model.Weather;

import java.util.List;

public record VisualCrossingApiDto(@JsonProperty("address") String cityName, List<Weather> days) {
}