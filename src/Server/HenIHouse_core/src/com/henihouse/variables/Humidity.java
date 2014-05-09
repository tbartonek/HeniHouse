package com.henihouse.variables;

import java.util.ArrayList;
import java.util.List;

public class Humidity {

    private String       name;
    private String       id;
    private String       place;
    private String       address;
    private Double       humidity;
    private int	  index;

    private List<Double> humidities;

    public Humidity() {
	humidities = new ArrayList<Double>(20);
	index = 0;
    }

    public void addHumidity(double humidity) {
	if (index < 20) {
	    humidities.add(index, humidity);
	    index++;
	} else {
	    humidities.add(20, humidity);
	    index = 0;
	}
    }

    public List<Double> getHumidities() {
	return humidities;
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

    public Double getHumidity() {
	return humidity;
    }

    public void setHumidity(double humidity) {
	this.humidity = humidity;
    }

}
