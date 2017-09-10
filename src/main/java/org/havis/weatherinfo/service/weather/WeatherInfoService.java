package org.havis.weatherinfo.service.weather;

import org.havis.weatherinfo.service.weather.WeatherInfoDTO.CurrentObservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class WeatherInfoService {

    private static final String WEATHER_URI =
            "http://api.wunderground.com/api/{wundergroundApiKey}/conditions/lang:{language}/q/{country}/{city}.json";

    @Value("#{environment['WUNDERGROUND_API_KEY']}")
    private String wundergroundApiKey;

    @Value("#{environment['WUNDERGROUND_FALLBACK_LANGUAGE']?:'en'}")
    private String defaultLanguage;

    public CurrentObservation getWeather(String country, String city, Locale locale) {
        return new RestTemplate()
                .getForEntity(
                        WEATHER_URI,
                        WeatherInfoDTO.class,
                        createVariables(country, city, getLanguage(locale)))
                .getBody()
                .getCurrentObservation();
    }

    private Map<String, String> createVariables(String country, String city, String language) {
        final Map<String, String> variables = new HashMap<>();
        variables.put("wundergroundApiKey", wundergroundApiKey);
        variables.put("language", language);
        variables.put("country", country);
        variables.put("city", city);
        return variables;
    }

    private String getLanguage(Locale locale) {
        return (locale != null ? locale.getLanguage() : defaultLanguage).toUpperCase();
    }
}
