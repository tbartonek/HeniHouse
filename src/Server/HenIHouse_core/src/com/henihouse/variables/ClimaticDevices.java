package com.henihouse.variables;

import java.util.ArrayList;
import java.util.List;

public class ClimaticDevices {
    private List<Device> temperatureDevices;
    private List<Device> humidityDevices;
    private Device       pressureDevice;

    public ClimaticDevices() {
	setTemperatureDevices(new ArrayList<Device>());
	setHumidityDevices(new ArrayList<Device>());
    }

    public List<Device> getTemperatureDevices() {
	return temperatureDevices;
    }

    public Device getTemperatureDevice(int index) {
	return this.temperatureDevices.get(index);
    }

    public void addTemperatureDevice(Device temperatureDevice) {
	this.temperatureDevices.add(temperatureDevice);
    }

    public void setTemperatureDevices(List<Device> temperatureDevices) {
	this.temperatureDevices = temperatureDevices;
    }

    public List<Device> getHumidityDevices() {
	return humidityDevices;
    }

    public Device getHumidityDevice(int index) {
	return this.humidityDevices.get(index);
    }

    public void addHumidityDevice(Device humidityDevice) {
	this.humidityDevices.add(humidityDevice);
    }

    public void setHumidityDevices(List<Device> humidityDevices) {
	this.humidityDevices = humidityDevices;
    }

    public Device getPressureDevice() {
	return pressureDevice;
    }

    public void setPressureDevice(Device pressureDevice) {
	this.pressureDevice = pressureDevice;
    }
}
