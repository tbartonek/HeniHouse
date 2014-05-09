package com.henihouse.variables;

public class Device {
    private String  name;
    private String  id;
    private long    delay;
    private boolean basicValue;
    private boolean activateDuringAlert;

    /**
     * Constructor for detector, ...
     * 
     * @param name
     * @param id
     * @param delay
     * @param basicValue
     * @param activateDuringAlert
     */
    public Device(String name, String id, long delay, boolean basicValue) {
	this.name = name;
	this.id = id;
	this.delay = delay;
	this.basicValue = basicValue;
    }

    /**
     * Constructor for light, ...
     * 
     * @param name
     * @param id
     * @param activateDuringAlert
     */
    public Device(String name, String id, boolean activateDuringAlert) {
	this.name = name;
	this.id = id;
	this.activateDuringAlert = activateDuringAlert;
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
	// this.address = Integer.parseInt(id.split("_")[0]);
	// this.pin = Integer.parseInt(id.split("_")[1]);
	this.id = id;
    }

    public long getDelay() {
	return delay;
    }

    public void setDelay(long delay) {
	this.delay = delay;
    }

    public boolean isBasicValue() {
	return basicValue;
    }

    public void setBasicValue(boolean basicValue) {
	this.basicValue = basicValue;
    }

    public boolean isActivateDuringAlert() {
	return activateDuringAlert;
    }

    public void setActivateDuringAlert(boolean activateDuringAlert) {
	this.activateDuringAlert = activateDuringAlert;
    }

}
