package com.weather.app.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class City {

    private Long id;
    private String cityName;
    private double lat;
    private double lon;

    public City(Long id, String cityName, double lat, double lon) {
        this.id = id;
        this.cityName = cityName;
        this.lat = lat;
        this.lon = lon;
    }
}
