package com.henihouse.variables;

import java.util.ArrayList;
import java.util.List;

public class Temperature {
    private String       name;
    private String       id;
    private String       place;
    private String       address;
    private Double       temperature;
    private int	  index;

    private List<Double> temperatures;

    public Temperature() {
	temperatures = new ArrayList<Double>(20);
	index = 0;
    }

    public void addTemperature(double temperature) {
	if (index < 20) {
	    temperatures.add(index, temperature);
	    index++;
	} else {
	    temperatures.add(20, temperature);
	    index = 0;
	}
    }

    public List<Double> getTemperatures() {
	return temperatures;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getPlace() {
	return place;
    }

    public void setPlace(String place) {
	this.place = place;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public Double getTemperature() {
	return temperature;
    }

    public void setTemperature(double temperature) {
	this.temperature = temperature;
    }

}
