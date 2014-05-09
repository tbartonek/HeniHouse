package com.henihouse.interfaces.core;

import java.util.List;
import java.util.Map;

public interface Interfaces {
	// Output
	public void setChangeOutput(boolean value);

	public void putValueOutput(String id, boolean value);

	public boolean getValueOutput(String id);
	
	public Map<String, Boolean> getOutput();

	// Heating
	public boolean isActiveHeating();

	public void setActiveHeating(boolean active);

	public double getTemperatureHeating();
	
	public double getAverageTemperature();
	
	public double getActualTemperature();
	
	public void setTemperatureHeating(double temperature);
	
	public Map<String, Double> getHeatingSetting();
	
	// Alarm
	public boolean verifyPassword (String password);
	
	public boolean verifyServisPassword(String password);
	
	public List<String> getAlarmNotice();
	
	public boolean changePassword (String oldPassword, String newPassword);
	
	public boolean changeServisPassword(String oldServisPassword, String newServisPassword);
	
	public boolean isActiveAlarm ();
	
	public boolean activeAlarm ();
	
	public boolean deactiveAlarm(String password);
	
	public Map<String, Integer> getAlarmSetting();
	
	public boolean setAlarmSetting(Map<String, Integer> alarmSetting, String servisPassword);
	
}
