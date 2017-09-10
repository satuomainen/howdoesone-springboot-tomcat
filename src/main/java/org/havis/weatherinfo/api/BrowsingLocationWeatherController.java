package org.havis.weatherinfo.api;

import org.havis.weatherinfo.feature.BrowsingLocationWeatherFeature;
import org.havis.weatherinfo.service.weather.WeatherInfoDTO.CurrentObservation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class BrowsingLocationWeatherController {

    @Resource
    private BrowsingLocationWeatherFeature browsingLocationWeatherFeature;

    @GetMapping(value = "/weather")
    @ResponseBody
    public CurrentObservation getWeather(HttpServletRequest request) {
        return browsingLocationWeatherFeature.getWeather(request);
    }
}
