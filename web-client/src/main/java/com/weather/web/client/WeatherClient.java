package com.weather.web.client;

import com.weather.web.client.dto.WeatherBitResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static com.weather.web.client.common.ExceptionMessages.NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE;
import static com.weather.web.client.utility.Checks.checkThat;

//@Component
@Slf4j
public class WeatherClient {
    @Value("${api_host}")
    private String apiHost;
    //private static final Logger logger = LoggerFactory.getLogger(WeatherClient.class);

    // not valid API_KEY
    // public static final String API_KEY = "f1cf185d994f48bc8ece50716d0ade8d";
    //public static final String API_KEY = "bf6acaa02f9b4bbea4b7325e06f70d43";
    //public static final String API_KEY = "58e1c50472eb4ba2ac16ae29bf5fe3c0";
    // valid API_KEY
    public static final String API_KEY = "6da79a3bd40847feb9f14dd2938739ce";
    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherBitResponseDto getForecastByCoordinates(double lat, double lon) {
        final var response = callGetMethod(
                "/v2.0/forecast/daily?lat={lat}&lon={lon}&key={API_KEY}&lang=pl&",
                WeatherBitResponseDto.class, lat, lon, API_KEY);
        checkThat(!response.data().isEmpty(), NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE);
        //logger.info(String.valueOf(response));
        log.info(String.valueOf(response));
        return response;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        //return restTemplate.getForObject(this.apiHost + url, responseType, objects);
        return restTemplate.getForEntity(this.apiHost + url, responseType, objects).getBody();

        //1. uzyc metody response entity
        //2. zalogowac odpowiedz z serwera zewnetrznego,  kod odpowiedz i cialo
        //3. z parsowac to, zamienic response entity na weatherbitresponsedto
    }
}
