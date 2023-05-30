package com.weather.app;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;


// TODO naprawic test integracyjny uzywajac tego mechanizmu ponizej tak, aby dzialal nie dynamicznie
@TestConfiguration
public class TestConfig {

    @Bean
    public Clock clock(){
        return Clock.fixed(Instant.parse("2023-05-30T12:34:56Z"), ZoneId.of("UTC"));
    }
}
