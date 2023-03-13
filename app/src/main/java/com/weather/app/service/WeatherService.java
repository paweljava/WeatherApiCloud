package com.weather.app.service;

import com.weather.app.repository.CityRepository;
import com.weather.web.client.WeatherClient;
import com.weather.web.client.dto.WeatherBitResponseDto;
import com.weather.web.client.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(cacheNames = "requestWeather")
    public List<WeatherBitResponseDto> getWeather(String date) {
        final var response = cityRepository.getCityList().stream().map(
                        city -> weatherClient.getForecastByCoordinates(city.getLat(), city.getLon()))
                .collect(Collectors.toList());
        return getCitiesWeathers2(response, date);
        //return getCityWeather(getCitiesWeathers2(response, date));
    }

    private List<WeatherBitResponseDto> getCitiesWeathers2(List<WeatherBitResponseDto> cityWeathers, String currentDate) {
        List<WeatherBitResponseDto> dataList = new ArrayList<WeatherBitResponseDto>();

        for (final var cityData : cityWeathers) {
            final var cityName = cityData.cityName();
            for (final var weatherData : cityData.data()) {
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
        dataList.sort(comparingDouble(cityData ->
                cityData.data().stream()
                        .mapToDouble(weather -> (weather.averageTemp() + weather.windSpeed()) * 3)
                        .average()
                        .orElse(0)));
        //dataList.sort(Comparator.comparingDouble(sort -> (sort.data().get(0).windSpeed() + sort.data().get(0).windSpeed()) * 3));
        return dataList;
    }


    public WeatherBitResponseDto getCityWeather(List<WeatherBitResponseDto> cityWeathers) {
        WeatherBitResponseDto response = null;
        double formulaValue = 0;
        for (final var cityData : cityWeathers) {
            for (final var weather : cityData.data()) {
                if (applyFormula(weather.averageTemp(), weather.windSpeed()) > formulaValue) {
                    formulaValue = (applyFormula(weather.averageTemp(), weather.windSpeed()));
                    response = new WeatherBitResponseDto(cityData.cityName(), cityData.data());
                }
            }
        }
        return response;
    }

    /*public WeatherBitResponseDto getCityWeather(List<WeatherBitResponseDto> cityWeathers) {
        WeatherBitResponseDto response = null;
        double formulaValue = 0;
        for (final var cityData : cityWeathers) {
            for (final var weather : cityData.data()) {
                if (applyFormula(weather.averageTemp(), weather.windSpeed()) > formulaValue) {
                    formulaValue = (applyFormula(weather.averageTemp(), weather.windSpeed()));
                    response = new WeatherBitResponseDto(cityData.cityName(), cityData.data());
                }
            }
        }
        return response;
    }*/

    private double applyFormula(double averageTemp, double windSpeed) {
        return averageTemp + windSpeed * 3;
    }
}
        /*var weathersListFilteredByDate = cityWeathers.stream()
                .map(a -> a.data().stream()
                        .filter(b -> b.forecastDate().equals(currentDate))
                        //.flatMap(list -> list.stream())
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .toList();
        return dataList;*/


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
