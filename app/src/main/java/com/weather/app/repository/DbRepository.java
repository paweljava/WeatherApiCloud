package com.weather.app.repository;

import com.weather.app.model.db.WeatherDbDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DbRepository extends JpaRepository<WeatherDbDto, UUID> {
}