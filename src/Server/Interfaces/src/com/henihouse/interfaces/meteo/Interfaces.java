package com.henihouse.interfaces.meteo;

import java.util.List;

public interface Interfaces {

	public int getDirection(int items);
	
	public double getHumidity(int items);
	
	public double getRainFall(int items);
	
	public double getTemperature(int items);
	
	public List<Double> getTemperatures(int start, int end);
	
	public double getWind(int items);
	
}
