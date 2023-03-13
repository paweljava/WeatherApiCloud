package com.weather.app.service;

public class Test {
    int n = 5;

    @org.junit.jupiter.api.Test
    void test() {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            final var q = ((i % 3 == 0) || (i % 5 == 0)) ? sum = sum + i : sum;
        }

        System.out.println(sum);
        System.out.println(n);
    }
}
