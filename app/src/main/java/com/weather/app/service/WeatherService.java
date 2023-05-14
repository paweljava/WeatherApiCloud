package com.weather.app.service;

import com.weather.app.dto.ResponseWeatherDto;
import com.weather.app.repository.CityRepository;
import com.weather.web.client.WeatherClient;
import com.weather.web.client.dto.WeatherBitResponseDto;
import com.weather.web.client.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.weather.app.service.WeatherValidator.validateWeatherConditions;
import static com.weather.web.client.common.ExceptionMessages.CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE;
import static com.weather.web.client.utility.Checks.checkThat;
import static java.util.Comparator.comparingDouble;

@Service
@Slf4j
public class WeatherService {

    private final WeatherClient weatherClient;
    private final CityRepository cityRepository;

    public WeatherService(WeatherClient weatherClient, CityRepository cityRepository) {
        this.weatherClient = weatherClient;
        this.cityRepository = cityRepository;
    }

    //@Cacheable(cacheNames = "requestWeather")
    public List<WeatherBitResponseDto> getWeather(String date) {
        final var response = cityRepository.getCityList().stream().map(
                        city -> weatherClient.getForecastByCoordinates(city.getLat(), city.getLon()))
                .collect(Collectors.toList());
        return getCitiesWeathers(response, date);
    }

    private List<WeatherBitResponseDto> getCitiesWeathers(List<WeatherBitResponseDto> cityWeathers, String currentDate) {
        List<WeatherBitResponseDto> dataList = new ArrayList<WeatherBitResponseDto>();

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

                    var newCityData = new WeatherBitResponseDto(cityName, weatherList);
                    dataList.add(newCityData);
                }
            }
        }
        checkThat(dataList.size() > 0, CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE);
        dataList.sort(comparingDouble((WeatherBitResponseDto cityData) ->
                cityData.days().stream()
                        .mapToDouble(weather -> (weather.averageTemp() + weather.windSpeed() * 3))
                        .average()
                        .orElse(0))
                .reversed());
        // nie dziala
        /*dataList.sort(Comparator.comparingDouble(cityData ->
                        cityData.days().stream()
                                .mapToDouble(weather -> weather.averageTemp() + weather.windSpeed() * 3)
                                .average()
                                .orElse(0))
                .reversed());*/
        return dataList;
    }

    private String cityNameConversion(String cityName) {
        return cityRepository.getCityList().stream()
                .filter(a -> (a.getLat() + "," + a.getLon()).equals(cityName))
                .findFirst().get().getCityName();
    }

    private double applyFormula(double averageTemp, double windSpeed) {
        return averageTemp + windSpeed * 3;
    }

    public List<ResponseWeatherDto> save(List<ResponseWeatherDto> response) {
        return response;
    }
}

//jak technicznie mam powiedziec co sie dzieje w linijkach

/* dzialajacy stream
    cityWeathers.stream()
        .map(WeatherBitResponseDto::data)
        .flatMap(List::stream)
        .filter(weatherData -> weatherData.forecastDate().equals(currentDate))
        .forEach(weatherData -> {
        String cityName = cityWeathers.stream()
        .filter(cityWeather -> cityWeather.data().contains(weatherData))
        .findFirst()
        .map(WeatherBitResponseDto::cityName)
        .orElse("");
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(new Weather(weatherData.forecastDate(), weatherData.averageTemp(), weatherData.windSpeed()));
        WeatherBitResponseDto newCityData = new WeatherBitResponseDto(cityName, weatherList);
        dataList.add(newCityData);
        });

        System.out.println();
        System.out.println(dataList);
        checkThat(dataList.size() > 0, CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE);
        return dataList;*/
