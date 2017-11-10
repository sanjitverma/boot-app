package com.example.verma.bootapp.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.client.RestTemplate;

/**
 * Created by SANJIT on 10/11/17.
 */
public class AmazonHealth implements HealthIndicator {
    @Override
    public Health health() {
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject("http://www.amazon.com", String.class);
            return Health.up().build();
        } catch (Exception e) {
            return Health.down().build();
        }
    }
}
