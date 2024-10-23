package com.zeotap.assigntment2.model;

import java.util.List;

public class MaxTemperatureSummary {
    private String city;
    private List<Double> maxTemperatures;

    public MaxTemperatureSummary(String city, List<Double> maxTemperatures) {
        this.city = city;
        this.maxTemperatures = maxTemperatures;
    }

    public String getCity() {
        return city;
    }

    public List<Double> getMaxTemperatures() {
        return maxTemperatures;
    }
}
