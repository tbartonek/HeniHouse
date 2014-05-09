package com.henihouse.variables;

import java.util.ArrayList;
import java.util.List;

public class Climatic {
    private List<Temperature> temperatures;
    private List<Humidity>    humidities;
    private List<Double>      pressures;

    public Climatic() {
	setTemperatures(new ArrayList<Temperature>());
	setHumidities(new ArrayList<Humidity>());
	setPressures(new ArrayList<Double>());
    }

    public List<Temperature> getTemperatures() {
	return temperatures;
    }

    public Temperature getTemperature(int index) {
	return this.temperatures.get(index);
    }

    public void setTemperatures(List<Temperature> temperatures) {
	this.temperatures = temperatures;
    }

    public List<Humidity> getHumidities() {
	return humidities;
    }

    public Humidity getHumidity(int index) {
	return humidities.get(index);
    }

    public void setHumidities(List<Humidity> humidities) {
	this.humidities = humidities;
    }

    public List<Double> getPressures() {
	return pressures;
    }

    public void setPressures(List<Double> pressures) {
	this.pressures = pressures;
    }

}
