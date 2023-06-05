package com.weather.app;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

import static java.time.Clock.fixed;
import static java.time.Instant.parse;
import static java.time.ZoneId.of;

@TestConfiguration
public class TestConfig {

    @Bean
    public Clock clock() {
        return fixed(parse("2023-05-30T12:34:56Z"), of("UTC"));
    }
}