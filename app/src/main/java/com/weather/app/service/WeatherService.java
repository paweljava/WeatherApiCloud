package com.weather.app.service;

import com.weather.app.model.db.WeatherDbDto;
import com.weather.app.repository.CityRepository;
import com.weather.app.repository.DbRepository;
import com.weather.web.client.WeatherClient;
import com.weather.web.client.model.VisualCrossingApiDto;
import com.weather.web.client.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.weather.app.service.WeatherValidator.validateWeatherConditions;
import static com.weather.web.client.common.ExceptionMessages.CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE;
import static com.weather.web.client.common.ExceptionMessages.NO_CITY_NAME_MATCH_EXCEPTION_MESSAGE;
import static com.weather.web.client.utility.Checks.checkThat;
import static java.util.Comparator.comparingDouble;

@Service
@Slf4j
public class WeatherService {

    private final WeatherClient weatherClient;
    private final CityRepository cityRepository;
    private final DbRepository dbRepository;

    public WeatherService(WeatherClient weatherClient, CityRepository cityRepository, DbRepository dbRepository) {
        this.weatherClient = weatherClient;
        this.cityRepository = cityRepository;
        this.dbRepository = dbRepository;
    }

    @Cacheable(cacheNames = "requestWeather")
    public List<VisualCrossingApiDto> getWeather(LocalDate date) {
        final var response = cityRepository.findAll().stream().map(
                        city -> weatherClient.getForecastByCoordinates(city.getLat(), city.getLon()))
                .collect(Collectors.toList());
        return getCitiesWeathersLambda(response, date);
    }

    // not in use
    public List<VisualCrossingApiDto> getCitiesWeathers(List<VisualCrossingApiDto> cityWeathers, LocalDate currentDate) {
        List<VisualCrossingApiDto> dataList = new ArrayList<>();

        for (final var cityData : cityWeathers) {
            final var cityName = cityNameConversion(cityData.cityName());
            for (final var weatherData : cityData.days()) {
                if ((weatherData.forecastDate().equals(currentDate)) &&
                        (validateWeatherConditions(weatherData.averageTemp(), weatherData.windSpeed()))) {
                    final var newWeatherData = new Weather(
                            weatherData.forecastDate(),
                            weatherData.averageTemp(),
                            weatherData.windSpeed());
                    List<Weather> weatherList = new ArrayList<>();
                    weatherList.add(newWeatherData);

                    var newCityData = new VisualCrossingApiDto(cityName, weatherList);
                    dataList.add(newCityData);
                }
            }
        }
        checkThat(dataList.size() > 0, CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE);
        dataList.sort(comparingDouble((VisualCrossingApiDto cityData) ->
                cityData.days().stream()
                        .mapToDouble(weather -> (weather.averageTemp() + weather.windSpeed() * 3))
                        .average()
                        .orElse(0))
                .reversed());
        return dataList;
    }

    private String cityNameConversion(String cityName) {

        /*try {
            return cityRepository.findAll().stream()
                    .filter(a -> ((a.getLat() + "," + a.getLon()).equals(cityName)) || a.getCityName().equals(cityName))
                    .findFirst().orElseThrow(() -> new Throwable(NO_CITY_NAME_MATCH_EXCEPTION_MESSAGE)).getCityName();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }*/

        for (final var cityData : cityRepository.findAll()) {
            var a = (cityData.getLat() + "," + cityData.getLon()).equals(cityName);
            var b = cityData.getCityName().equals(cityName);
            if (((cityData.getLat() + "," + cityData.getLon()).equals(cityName)) || cityData.getCityName().equals(cityName))
            {
                return cityData.getCityName();
            }
        }
        return null;
    }

    public void save(List<WeatherDbDto> response) {
        response.forEach(dbRepository::save);
    }

    private List<VisualCrossingApiDto> getCitiesWeathersLambda(List<VisualCrossingApiDto> cityWeathers, LocalDate currentDate) {
        List<VisualCrossingApiDto> dataList = new ArrayList<>();

        cityWeathers.stream()
                .map(VisualCrossingApiDto::days)
                .flatMap(List::stream)
                .filter(weatherData -> weatherData.forecastDate().equals(currentDate))
                .forEach(weatherData -> {
                    var cityName = cityNameConversion(cityWeathers.stream()
                            .filter(cityWeather -> cityWeather.days().contains(weatherData))
                            .findFirst()
                            .map(VisualCrossingApiDto::cityName)
                            .orElseThrow());
                    List<Weather> weatherList = new ArrayList<>();
                    weatherList.add(new Weather(
                            weatherData.forecastDate(),
                            weatherData.averageTemp(),
                            weatherData.windSpeed()));
                    var newCityData = new VisualCrossingApiDto(cityName, weatherList);
                    dataList.add(newCityData);
                });

        checkThat(dataList.size() > 0, CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE);
        dataList.sort(comparingDouble((VisualCrossingApiDto cityData) ->
                cityData.days().stream()
                        .mapToDouble(weather -> (weather.averageTemp() + weather.windSpeed() * 3))
                        .average()
                        .orElse(0))
                .reversed());
        return dataList;
    }
}