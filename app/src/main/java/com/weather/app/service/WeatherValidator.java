package com.weather.app.service;

final class WeatherValidator {

    public static final double MIN_TEMP = 1;
    public static final double MAX_TEMP = 35;
    public static final double MIN_WIND = 1;
    public static final double MAX_WIND = 18;

    public static boolean validateWeatherConditions(double averageTemp, double windSpeed) {
        return averageTemp > MIN_TEMP  &&
                averageTemp < MAX_TEMP &&
                windSpeed > MIN_WIND &&
                windSpeed < MAX_WIND;
    }
}
