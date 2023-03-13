package com.weather.app.controller;

import com.weather.app.dto.ResponseWeatherDto;
import com.weather.app.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.weather.app.model.Mapper.mapListRequestDtoToListResponseDto;
import static com.weather.web.client.common.ExceptionMessages.INCORRECT_DATE_FORMAT_EXCEPTION_MESSAGE;
import static com.weather.web.client.utility.Checks.checkThat;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{date}")
    public List<ResponseWeatherDto> getWeatherData(@PathVariable("date") String date) {
        checkThat(validateDateFormat(date), INCORRECT_DATE_FORMAT_EXCEPTION_MESSAGE);
        return mapListRequestDtoToListResponseDto(weatherService.getWeather(date));
    }

    private boolean validateDateFormat(String dateToValidate) {
        final var formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        final Date parsedData;
        try {
            parsedData = formatter.parse(dateToValidate);
            System.out.println("Date format yyyy-MM-dd " + formatter.format(parsedData));
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*private boolean validateDateFormat(String dateToValidate) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            var formattedDate = df.format(dateToValidate);
            var parsedDate = df.parse(formattedDate);

            return formattedDate.equals(df.format(parsedDate));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd'");
        }
    }*/





}

