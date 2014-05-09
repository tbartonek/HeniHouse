package com.henihouse.weather;

import java.util.List;
import java.util.Vector;

public class WeatherPrediction {
	
    private String city;
    private String country;
    private int	numberDays; 
	
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	public int getNumberDays() {
		return numberDays;
	}


	public void setNumberDays(int numberDays) {
		this.numberDays = numberDays;
	}

	private List<Integer> date;
	private List<Float> tempDay;
    private List<Float> tempNight;
    private List<Integer> clouds;
    private List<Float> pressure;
    private List<String> weatherIcon;
    
    public WeatherPrediction (int numberDays){
    	date = new Vector<Integer>(numberDays);
    	tempDay = new Vector<Float>(numberDays);
    	tempNight = new Vector<Float>(numberDays);
    	clouds = new Vector<Integer>(numberDays);
    	pressure = new Vector<Float>(numberDays);
    	weatherIcon = new Vector<String>(numberDays);
    }
    
    public void addDate (int index, int date){
    	this.date.add(index, date);
    }
    
    public int getDate (int index) {
    	return this.date.get(index);
    }
    
    public void addTempDay (int index, float tempDay){
    	this.tempDay.add(index, tempDay);
    }
    
    public float getTempDay (int index) {
    	return this.tempDay.get(index);
    }
    
    public void addTempNight (int index, float tempNight){
    	this.tempNight.add(index, tempNight);
    }
    
    public float getTempNight (int index) {
    	return this.tempNight.get(index);
    }
    
    public void addClouds (int index, int clouds){
    	this.clouds.add(index, clouds);
    }
    
    public int getClouds (int index) {
    	return this.clouds.get(index);
    }
    
    public void addPressure (int index, float pressure){
    	this.pressure.add(index, pressure);
    }
    
    public float getPressure (int index) {
    	return this.pressure.get(index);
    }
    
    public void addWeatherIcon (int index, String weatherIcon){
    	this.weatherIcon.add(index, weatherIcon);
    }
    
    public String getWeatherIcon (int index) {
    	return this.weatherIcon.get(index);
    }
}
