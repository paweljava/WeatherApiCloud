package com.weather.web.client;

import com.weather.web.client.model.VisualCrossingApiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static com.weather.web.client.common.ExceptionMessages.NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE;
import static com.weather.web.client.utility.Checks.checkThat;

@Slf4j
public class WeatherClient {
    @Value("${api_host}")
    private String apiHost;
    //private static final Logger logger = LoggerFactory.getLogger(WeatherClient.class);
    public static final String API_KEY = "E3BV74QMLJALSCLLLDEKW6UXS";
    private final RestTemplate restTemplate = new RestTemplate();

    public VisualCrossingApiDto getForecastByCoordinates(double lat, double lon) {
        final var response = callGetMethod(
                "/VisualCrossingWebServices/rest/services/timeline/{lat},{lon}?key={API_KEY}&include=days&unitGroup=metric",
                VisualCrossingApiDto.class, lat, lon, API_KEY);
        checkThat(!response.days().isEmpty(), NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE);
        log.info(String.valueOf(response));
        return response;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        //return restTemplate.getForObject(this.apiHost + url, responseType, objects);
        return restTemplate.getForEntity(this.apiHost + url, responseType, objects).getBody();

        //1. uzyc metody response entity
        //2. zalogowac odpowiedz z serwera zewnetrznego, kod odpowiedzi i cialo
        //3. z parsowac to, zamienic response entity na weatherbitresponsedto
    }
}