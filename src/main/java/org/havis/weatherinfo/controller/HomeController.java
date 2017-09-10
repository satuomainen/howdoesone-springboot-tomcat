package org.havis.weatherinfo.controller;

import org.havis.weatherinfo.feature.BrowsingLocationWeatherFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Resource
    private BrowsingLocationWeatherFeature browsingLocationWeatherFeature;

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("currentObservation", browsingLocationWeatherFeature.getWeather(request));
        return "index";
    }
}
