package com.weather.web.client.common;

public final class ExceptionMessages {

    public static final String INCORRECT_DATE_FORMAT_EXCEPTION_MESSAGE =
            "Incorrect date format";

    public static final String CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE =
            "City not found for requested weather conditions" ;

    public static final String NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE =
            "No response from API";

    private ExceptionMessages() {
        throw new AssertionError("No MenuManagerExceptionMessages instances for you!");
    }
}
