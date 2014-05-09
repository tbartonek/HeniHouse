package com.henihouse.function;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.handler.filter.GZipFilter;
import lipermi.net.IServerListener;
import lipermi.net.Server;

import com.henihouse.interfaces.core.Interfaces;
import com.henihouse.interfaces.variables.Variables;
import com.henihouse.variables.AlarmVariables;
import com.henihouse.variables.HeatingVariables;
import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;
import com.henihouse.variables.OutputVariables;

public class ServerHeniHouse implements Interfaces {
    CallHandler callHandler;

    public ServerHeniHouse() {
	Logs.addLog(new Log("Info", "Server", "Creating."));
	Server server = new Server();
	callHandler = new CallHandler();

	try {
	    callHandler.registerGlobal(Interfaces.class, this);
	    server.addServerListener(new IServerListener() {
		public void clientConnected(Socket socket) {
		    Logs.addLog(new Log("Info", "Server", "Client connected: "
			    + socket.getInetAddress()));

		}

		public void clientDisconnected(Socket socket) {
		    Logs.addLog(new Log("Info", "Server",
			    "Client disconnected: " + socket.getInetAddress()));
		}
	    });
	    server.bind(Variables.getPort(), callHandler,
		    new GZipFilter());
	    InetAddress IP = InetAddress.getLocalHost();
	    Logs.addLog(new Log("Info", "Server", "Creating Server is done."));
	    // System.out.println("Creating Server is done with IP: "
	    // + IP.getLocalHost());
	} catch (LipeRMIException | IOException e) {
	    Logs.addLog(new Log("Error", "Server", "error during creating."));
	    Logs.setServerError(true);
	    e.printStackTrace();
	};
    }

    public void setChangeOutput(boolean value) {
	OutputVariables.setChange(value);
    }

    public void putValueOutput(String id, boolean value) {
	OutputVariables.putValue(id, value);
    }

    public boolean getValueOutput(String id) {
	return OutputVariables.getValue(id);
    }

    public boolean isActiveHeating() {
	return HeatingVariables.isActive();
    }

    public void setActiveHeating(boolean active) {
	HeatingVariables.setActive(active);
    }

    public double getTemperatureHeating() {
	return HeatingVariables.getTemperature();
    }

    public void setTemperatureHeating(double temperature) {
	HeatingVariables.setTemperature(temperature);
    }

    public double getActualTemperature() {
	return HeatingVariables.getActualeTemperature();
    }

    public Map<String, Boolean> getOutput() {
	return OutputVariables.getOutput();
    }

    public double getAverageTemperature() {
	return HeatingVariables.getAverageTemperature();
    }

    public Map<String, Double> getHeatingSetting() {
	Map<String, Double> heatingSetting = new Hashtable<String, Double>();

	heatingSetting.put("averageTemperature",
		HeatingVariables.getAverageTemperature());
	heatingSetting.put("actualTemperature",
		HeatingVariables.getActualeTemperature());
	heatingSetting.put("setTemperature", HeatingVariables.getTemperature());

	return heatingSetting;
    }

    public boolean verifyPassword(String password) {
	return AlarmVariables.verifyPassword(password);
    }

    public boolean changePassword(String oldPassword, String newPassword) {
	if (AlarmVariables.verifyPassword(oldPassword)) {
	    AlarmVariables.setPassword(newPassword);
	    return true;
	}
	return false;
    }

    public boolean changeServisPassword(String oldServisPassword,
	    String newServisPassword) {
	if (AlarmVariables.verifyServisPassword(oldServisPassword)) {
	    AlarmVariables.setServisPassword(newServisPassword);
	    return true;
	}
	return false;
    }

    public boolean isActiveAlarm() {
	if (AlarmVariables.isActive()) return true;
	return false;
    }

    public boolean activeAlarm() {
	AlarmVariables.setActive(true);
	return true;
    }

    public boolean deactiveAlarm(String password) {
	if (AlarmVariables.verifyPassword(password)) {
	    AlarmVariables.setActive(false);
	    return true;
	}
	return false;
    }

    @Override
    public boolean verifyServisPassword(String servisPassword) {
	return AlarmVariables.verifyServisPassword(servisPassword);
    }

    @Override
    public List<String> getAlarmNotice() {
	return AlarmVariables.getAlarmNotice();
    }

    public Map<String, Integer> getAlarmSetting() {
	Map<String, Integer> alarmSetting = new Hashtable<String, Integer>();

	alarmSetting.put("maxTryPassword", AlarmVariables.getMaxTryPassword());
	alarmSetting.put("alertTime", AlarmVariables.getAlertTime() / 1000);
	alarmSetting.put("startTimeAlarm",
		AlarmVariables.getStartTimeAlarm() / 1000);

	return alarmSetting;
    }

    public boolean setAlarmSetting(Map<String, Integer> alarmSetting,
	    String servisPassword) {
	if (AlarmVariables.verifyServisPassword(servisPassword)) {
	    AlarmVariables
		    .setMaxTryPassword(alarmSetting.get("maxTryPassword"));
	    AlarmVariables.setAlertTime(alarmSetting.get("alertTime") * 1000);
	    AlarmVariables
		    .setStartTimeAlarm(alarmSetting.get("startTimeAlarm") * 1000);

	    AlarmVariables.addNotice("Alarm settings are changed.");
	    return true;
	}
	return false;
    }

}
