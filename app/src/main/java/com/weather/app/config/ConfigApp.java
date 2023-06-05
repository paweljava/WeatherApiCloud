package com.weather.app.config;

import com.weather.web.client.WeatherClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ConfigApp {
    @Bean
    public WeatherClient getWeatherClient() {
        return new WeatherClient();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}