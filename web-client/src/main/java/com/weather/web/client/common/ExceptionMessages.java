package com.weather.web.client.common;

public final class ExceptionMessages {

    public static final String INCORRECT_DATE_RANGE_EXCEPTION_MESSAGE =
            "The date is not within the range from today to 16 days ahead";

    public static final String CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE =
            "City not found for requested weather conditions" ;

    public static final String NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE =
            "No response from API";

    public static final String NO_CITY_NAME_MATCH_EXCEPTION_MESSAGE =
            "No match city name for geographical coordinates";

    public ExceptionMessages() {
        throw new AssertionError("No MenuManagerExceptionMessages instances for you!");
    }
}