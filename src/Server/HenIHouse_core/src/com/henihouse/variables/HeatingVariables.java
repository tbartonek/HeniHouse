package com.henihouse.variables;

public class HeatingVariables {

    private static boolean active      = false;
    private static double  temperature = 21.5;
    private static double  actualTemperature = 21.0;
    private static double averageTemperature = 25.0;
    private static double  hysterez    = 0.5;

    public static boolean isActive() {
	return active;
    }

    public static void setActive(boolean active) {
	HeatingVariables.active = active;
    }

    public static double getTemperature() {
	return temperature;
    }

    public static void setTemperature(double temperature) {
	HeatingVariables.temperature = temperature;
    }

    public static double getHysterez() {
	return hysterez;
    }

    public static void setHysterez(double hysterez) {
	HeatingVariables.hysterez = hysterez;
    }

	public static void setActualeTemperature(double averageTemperature) {
		actualTemperature = averageTemperature;
	}
	
	public static double getActualeTemperature() {
		return actualTemperature;
	}

	public static double getAverageTemperature() {
		return averageTemperature;
	}

	public static void setAverageTemperature(double averageTemperature) {
		HeatingVariables.averageTemperature = averageTemperature;
	}

}
