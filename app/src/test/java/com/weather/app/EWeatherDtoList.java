package com.weather.app;

import com.weather.web.client.model.Weather;
import lombok.Getter;

import java.util.List;

@Getter
public class EWeatherDtoList {

    public static final Weather WEATHER_1 = new Weather("2022-11-01", 16, 20);
    public static final Weather WEATHER_2 = new Weather("2022-11-02", 15, 13);
    public static final Weather WEATHER_3 = new Weather("2022-11-03", 14, 20);
    public static final Weather WEATHER_4 = new Weather("2022-11-04", 13, 3);
    public static final Weather WEATHER_5 = new Weather("2022-11-05", 12, 20);
    public static final Weather WEATHER_6 = new Weather("2022-11-06", 11, 3);
    public static final Weather WEATHER_7 = new Weather("2022-11-07", 10, 20);
    public static final Weather WEATHER_8 = new Weather("2022-11-08", 9, 3);
    public static final Weather WEATHER_9 = new Weather("2022-11-09", 8, 20);
    public static final Weather WEATHER_10 = new Weather("2022-11-10", 7, 3);
    public static final Weather WEATHER_11 = new Weather("2022-11-11", 6, 20);
    public static final Weather WEATHER_12 = new Weather("2022-11-12", 5, 3);
    public static final Weather WEATHER_13 = new Weather("2022-11-13", 4, 20);
    public static final Weather WEATHER_14 = new Weather("2022-11-14", 3, 3);
    public static final Weather WEATHER_15 = new Weather("2022-11-15", 2, 20);
    public static final Weather WEATHER_16 = new Weather("2022-11-16", 1, 6);
    public static final Weather WEATHER_21 = new Weather("2022-11-01", 1, 20);
    public static final Weather WEATHER_22 = new Weather("2022-11-02", 2, 3);
    public static final Weather WEATHER_23 = new Weather("2022-11-03", 3, 20);
    public static final Weather WEATHER_24 = new Weather("2022-11-04", 4, 3);
    public static final Weather WEATHER_25 = new Weather("2022-11-05", 5, 20);
    public static final Weather WEATHER_26 = new Weather("2022-11-06", 6, 3);
    public static final Weather WEATHER_27 = new Weather("2022-11-07", 7, 20);
    public static final Weather WEATHER_28 = new Weather("2022-11-08", 8, 3);
    public static final Weather WEATHER_29 = new Weather("2022-11-09", 9, 20);
    public static final Weather WEATHER_30 = new Weather("2022-11-10", 10, 3);
    public static final Weather WEATHER_31 = new Weather("2022-11-11", 11, 20);
    public static final Weather WEATHER_32 = new Weather("2022-11-12", 12, 3);
    public static final Weather WEATHER_33 = new Weather("2022-11-13", 13, 20);
    public static final Weather WEATHER_34 = new Weather("2022-11-14", 14, 8);
    public static final Weather WEATHER_35 = new Weather("2022-11-15", 15, 20);
    public static final Weather WEATHER_36 = new Weather("2022-11-16", 16, 6);

    public static final List<Weather> WEATHER_LIST_1 = List.of(WEATHER_1, WEATHER_2, WEATHER_3, WEATHER_4,
            WEATHER_5, WEATHER_6, WEATHER_7, WEATHER_8, WEATHER_9, WEATHER_10, WEATHER_11,
            WEATHER_12, WEATHER_13, WEATHER_14, WEATHER_15, WEATHER_16);

    public static final List<Weather> WEATHER_LIST_2 = List.of(WEATHER_21, WEATHER_22, WEATHER_23, WEATHER_24,
            WEATHER_25, WEATHER_26, WEATHER_27, WEATHER_28, WEATHER_29, WEATHER_30, WEATHER_31,
            WEATHER_32, WEATHER_33, WEATHER_34, WEATHER_35, WEATHER_36);
}
