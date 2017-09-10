package org.havis.weatherinfo.service.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherInfoDTO {

    public class CurrentObservation {

        class DisplayLocation {

            public DisplayLocation() {}

            @JsonProperty("full")
            public String name;
        }

        public CurrentObservation() {}

        @JsonProperty("display_location")
        public DisplayLocation displayLocation;

        @JsonProperty("weather")
        public String weather;

        @JsonProperty("temp_c")
        public String temperature;

        @JsonProperty("wind_dir")
        public String windDirection;

        @JsonProperty("relative_humidity")
        public String relativeHumidity;

        @JsonProperty("wind_kph")
        public String windSpeedKph;

        @JsonProperty("pressure_mb")
        public String pressureMilliBar;
    }

    @JsonProperty("current_observation")
    private CurrentObservation currentObservation;

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }
}
