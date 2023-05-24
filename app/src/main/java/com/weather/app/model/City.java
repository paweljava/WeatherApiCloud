package com.weather.app.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Table(name = "cityrepository")
@RequiredArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String cityName;
    private double lat;
    private double lon;

    public City(String cityName, double lat, double lon) {
        this.id = UUID.randomUUID();
        this.cityName = cityName;
        this.lat = lat;
        this.lon = lon;
    }
}