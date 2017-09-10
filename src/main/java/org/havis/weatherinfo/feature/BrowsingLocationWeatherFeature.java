package org.havis.weatherinfo.feature;

import org.havis.weatherinfo.service.iplocation.IPAddressLocationResolver;
import org.havis.weatherinfo.service.iplocation.LocationInfoDTO;
import org.havis.weatherinfo.service.weather.WeatherInfoDTO.CurrentObservation;
import org.havis.weatherinfo.service.weather.WeatherInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Service
public class BrowsingLocationWeatherFeature {
    private final static Logger logger = LoggerFactory.getLogger(BrowsingLocationWeatherFeature.class);

    @Resource
    private IPAddressLocationResolver ipAddressLocationResolver;

    @Resource
    private WeatherInfoService weatherInfoService;

    public CurrentObservation getWeather(HttpServletRequest request) {
        final LocationInfoDTO location = ipAddressLocationResolver.resolveFrom(request);
        logger.debug("Location: {}, {}", location.getCity(), location.getCountry());
        final Locale locale = request.getLocale();
        final CurrentObservation currentObservation = weatherInfoService.getWeather(
                location.getCountry(), location.getCity(), locale);

        logger.debug("Got current observation: {}", currentObservation);

        return currentObservation;
    }
}
