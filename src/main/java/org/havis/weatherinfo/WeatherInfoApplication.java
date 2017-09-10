package org.havis.weatherinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class WeatherInfoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WeatherInfoApplication.class, args);
    }
}
