package com.zeotap.assigntment2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    
    @JsonProperty("main")
    private MainWeather main;

    @JsonProperty("weather")
    private WeatherCondition[] weather;

    @JsonProperty("dt")
    private long dt; // Unix timestamp for the data update

    @JsonProperty("name") // This is where you get the city name from the API response
    private String name; // Add this line for the city name

    @JsonProperty("wind")
    private Wind wind; // Add this for wind speed

    // Getters and Setters
    public MainWeather getMain() {
        return main;
    }

    public void setMain(MainWeather main) {
        this.main = main;
    }

    public WeatherCondition[] getWeather() {
        return weather;
    }

    public void setWeather(WeatherCondition[] weather) {
        this.weather = weather;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public static class MainWeather {
        private double temp;
        private double feels_like;
        private double humidity; // Add humidity

        // Getters and Setters
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        public double getHumidity() { // Add getter for humidity
            return humidity;
        }

        public void setHumidity(double humidity) { // Add setter for humidity
            this.humidity = humidity;
        }
    }

    public static class WeatherCondition {
        private String main;

        // Getters and Setters
        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }

    public static class Wind { // Add a class for wind speed
        private double speed;

        // Getters and Setters
        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }
}
