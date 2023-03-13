package com.weather.app.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class City {

    private Long id;
    private String cityName;
    private String stateCode;
    private String countryCode;
    private String countryFullName;
    private double lat;
    private double lon;

    public City(Long id, String cityName, String stateCode, String countryCode, double lat, double lon) {
        this.id = id;
        this.cityName = cityName;
        this.stateCode = stateCode;
        this.countryCode = countryCode;
        this.lat = lat;
        this.lon = lon;
    }

    public City(Long id, String cityName, String stateCode, String countryCode,
                String countryFullName, double lat, double lon) {
        this.id = id;
        this.cityName = cityName;
        this.stateCode = stateCode;
        this.countryCode = countryCode;
        this.countryFullName = countryFullName;
        this.lat = lat;
        this.lon = lon;
    }
}
