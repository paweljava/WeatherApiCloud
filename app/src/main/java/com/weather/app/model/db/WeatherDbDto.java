package com.weather.app.model.db;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "weatherdbdto")
@AllArgsConstructor
public class WeatherDbDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String cityName;
    private LocalDate date;
    private double temperature;

    WeatherDbDto() {
    }

    public WeatherDbDto(String cityName, LocalDate date, Double temperature) {
        this.id = UUID.randomUUID();
        this.cityName = cityName;
        this.date = date;
        this.temperature = temperature;
    }
}