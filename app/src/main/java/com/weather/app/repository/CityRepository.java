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

    private final City city1 = new City(3097421L, "Jastarnia", "82", "PL", 54.69606, 18.67873);
    private final City city2 = new City(3374036L, "Bridgetown", "08", "BB", "Barbados", 13.10732, -59.62021);
    private final City city3 = new City(3399415L, "Fortaleza", "06,", "BR", "Brazil", -3.71722, -38.54306);
    private final City city4 = new City(146150L, "Pissoúri", "05", "CY", "Cyprus", 34.66942, 32.70132);
    // City Le Morne (Mauritius) not exist in database www.weatherbit.io
    private final List<City> cityList = List.of(city1, city2, city3, city4);
}
