package com.weather.app;

import com.weather.web.client.model.Weather;
import lombok.Getter;

import java.util.List;

import static java.time.LocalDate.parse;

@Getter
public class EWeatherDtoList {

    public static final Weather WEATHER_1 = new Weather(parse("2023-05-21"), 16, 20);
    public static final Weather WEATHER_2 = new Weather(parse("2023-05-22"), 15, 13);
    public static final Weather WEATHER_3 = new Weather(parse("2023-05-23"), 14, 20);
    public static final Weather WEATHER_4 = new Weather(parse("2023-05-24"), 13, 3);
    public static final Weather WEATHER_5 = new Weather(parse("2023-05-25"), 12, 15);
    public static final Weather WEATHER_6 = new Weather(parse("2023-05-26"), 11, 3);
    public static final Weather WEATHER_7 = new Weather(parse("2023-05-27"), 10, 20);
    public static final Weather WEATHER_8 = new Weather(parse("2023-05-28"), 9, 3);
    public static final Weather WEATHER_9 = new Weather(parse("2023-05-29"), 8, 20);
    public static final Weather WEATHER_10 = new Weather(parse("2023-05-10"), 7, 3);
    public static final Weather WEATHER_11 = new Weather(parse("2023-05-11"), 6, 20);
    public static final Weather WEATHER_12 = new Weather(parse("2023-05-12"), 5, 3);
    public static final Weather WEATHER_13 = new Weather(parse("2023-05-13"), 4, 20);
    public static final Weather WEATHER_14 = new Weather(parse("2023-05-14"), 3, 3);
    public static final Weather WEATHER_15 = new Weather(parse("2023-05-15"), 2, 20);
    public static final Weather WEATHER_16 = new Weather(parse("2023-05-16"), 1, 6);
    public static final Weather WEATHER_21 = new Weather(parse("2023-05-21"), 1, 20);
    public static final Weather WEATHER_22 = new Weather(parse("2023-05-22"), 2, 3);
    public static final Weather WEATHER_23 = new Weather(parse("2023-05-23"), 3, 20);
    public static final Weather WEATHER_24 = new Weather(parse("2023-05-24"), 4, 3);
    public static final Weather WEATHER_25 = new Weather(parse("2023-05-25"), 25, 15);
    public static final Weather WEATHER_26 = new Weather(parse("2023-05-26"), 6, 3);
    public static final Weather WEATHER_27 = new Weather(parse("2023-05-27"), 7, 20);
    public static final Weather WEATHER_28 = new Weather(parse("2023-05-28"), 8, 3);
    public static final Weather WEATHER_29 = new Weather(parse("2023-05-29"), 9, 20);
    public static final Weather WEATHER_30 = new Weather(parse("2023-05-10"), 10, 3);
    public static final Weather WEATHER_31 = new Weather(parse("2023-05-11"), 11, 20);
    public static final Weather WEATHER_32 = new Weather(parse("2023-05-12"), 12, 3);
    public static final Weather WEATHER_33 = new Weather(parse("2023-05-13"), 13, 20);
    public static final Weather WEATHER_34 = new Weather(parse("2023-05-14"), 14, 8);
    public static final Weather WEATHER_35 = new Weather(parse("2023-05-15"), 15, 20);
    public static final Weather WEATHER_36 = new Weather(parse("2023-05-16"), 16, 6);

    public static final List<Weather> WEATHER_LIST_1 = List.of(WEATHER_1, WEATHER_2, WEATHER_3, WEATHER_4,
            WEATHER_5, WEATHER_6, WEATHER_7, WEATHER_8, WEATHER_9, WEATHER_10, WEATHER_11,
            WEATHER_12, WEATHER_13, WEATHER_14, WEATHER_15, WEATHER_16);

    public static final List<Weather> WEATHER_LIST_2 = List.of(WEATHER_21, WEATHER_22, WEATHER_23, WEATHER_24,
            WEATHER_25, WEATHER_26, WEATHER_27, WEATHER_28, WEATHER_29, WEATHER_30, WEATHER_31,
            WEATHER_32, WEATHER_33, WEATHER_34, WEATHER_35, WEATHER_36);
}