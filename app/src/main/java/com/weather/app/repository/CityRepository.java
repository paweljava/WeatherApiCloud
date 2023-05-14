package com.weather.app.repository;

import com.weather.app.model.City;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Getter
@Repository
@RequiredArgsConstructor
public class CityRepository {

    private final City city1 = new City(3097421L, "Jastarnia", 54.7007, 18.67);
    private final City city2 = new City(3374036L, "Bridgetown", 13.112, -59.6127);
    private final City city3 = new City(3399415L, "Fortaleza", -3.71841, -38.5429);
    private final City city4 = new City(146150L, "Pissoúri", 34.667, 32.7004);
    private final City city5 = new City(146150L, "Mauritius", -20.1654, 57.5149);
    private final List<City> cityList = List.of(city1, city2, city3, city4, city5);
}
