package com.henihouse.meteo.variables;

import java.util.ArrayList;
import java.util.List;

public class Logs {
    private static List<Log> logs		   = new ArrayList<Log>();

    private static boolean   warning		= false;
    private static boolean   inputWarning	   = false;
    private static boolean   outputWarning	  = false;
    private static boolean   expanderWarning	= false;
    private static boolean   keypadWarning	  = false;
    private static boolean   sensorWarning	  = false;

    private static boolean   alarmWarning	   = false;
    private static boolean   climaticControlWarning = false;
    private static boolean   devicesControlWarning  = false;
    private static boolean   serverWarning	  = false;

    private static boolean   error		  = false;
    private static boolean   inputError	     = false;
    private static boolean   outputError	    = false;
    private static boolean   expanderError	  = false;
    private static boolean   keypadError	    = false;
    private static boolean   sensorError	    = false;

    private static boolean   alarmError	     = false;
    private static boolean   climaticControlError   = false;
    private static boolean   devicesControlError    = false;
    private static boolean   serverError	    = false;

    public static synchronized void addLog(Log log) {
	logs.add(log);
    }

    public static synchronized List<Log> getLogs() {
	return logs;
    }

    // Warnings

    public static boolean isWarning() {
	return warning;
    }

    public static synchronized boolean isInputWarning() {
	return inputWarning;
    }

    public static synchronized void setInputWarning(boolean inputWarning) {
	Logs.warning = inputWarning;
	Logs.inputWarning = inputWarning;
    }

    public static synchronized boolean isOutputWarning() {
	return outputWarning;
    }

    public static synchronized void setOutputWarning(boolean outputWarning) {
	Logs.warning = outputWarning;
	Logs.outputWarning = outputWarning;
    }

    public static boolean isExpanderWarning() {
	return expanderWarning;
    }

    public static void setExpanderWarning(boolean expanderWarning) {
	Logs.warning = expanderWarning;
	Logs.expanderWarning = expanderWarning;
    }

    public static synchronized boolean isKeypadWarning() {
	return keypadWarning;
    }

    public static synchronized void setKeypadWarning(boolean keypadWarning) {
	Logs.warning = keypadWarning;
	Logs.keypadWarning = keypadWarning;
    }

    public static synchronized boolean isSensorWarning() {
	return sensorWarning;
    }

    public static synchronized void setSensorWarning(boolean sensorWarning) {
	Logs.warning = sensorWarning;
	Logs.sensorWarning = sensorWarning;
    }

    public static synchronized boolean isAlarmWarning() {
	return alarmWarning;
    }

    public static synchronized void setAlarmWarning(boolean alarmWarning) {
	Logs.warning = alarmWarning;
	Logs.alarmWarning = alarmWarning;
    }

    public static synchronized boolean isClimaticControlWarning() {
	return climaticControlWarning;
    }

    public static synchronized void setClimaticControlWarning(
	    boolean climaticControlWarning) {
	Logs.warning = climaticControlWarning;
	Logs.climaticControlWarning = climaticControlWarning;
    }

    public static synchronized boolean isDevicesControlWarning() {
	return devicesControlWarning;
    }

    public static synchronized void setDevicesControlWarning(
	    boolean devicesControlWarning) {
	Logs.warning = devicesControlWarning;
	Logs.devicesControlWarning = devicesControlWarning;
    }

    public static synchronized boolean isServerWarning() {
	return serverWarning;
    }

    public static synchronized void setServerWarning(boolean serverWarning) {
	Logs.warning = serverWarning;
	Logs.serverWarning = serverWarning;
    }

    // Errors

    public static synchronized boolean isInputError() {
	return inputError;
    }

    public static synchronized void setInputError(boolean inputError) {
	Logs.error = inputError;
	Logs.inputError = inputError;
    }

    public static synchronized boolean isOutputError() {
	return outputError;
    }

    public static synchronized void setOutputError(boolean outputError) {
	Logs.error = outputError;
	Logs.outputError = outputError;
    }

    public static boolean isExpanderError() {
	return expanderError;
    }

    public static void setExpanderError(boolean expanderError) {
	Logs.expanderError = expanderError;
    }

    public static synchronized boolean isKeypadError() {
	return keypadError;
    }

    public static synchronized void setKeypadError(boolean keypadError) {
	Logs.error = keypadError;
	Logs.keypadError = keypadError;
    }

    public static synchronized boolean isSensorError() {
	return sensorError;
    }

    public static synchronized void setSensorError(boolean sensorError) {
	Logs.error = sensorError;
	Logs.sensorError = sensorError;
    }

    public static synchronized boolean isAlarmError() {
	return alarmError;
    }

    public static synchronized void setAlarmError(boolean alarmError) {
	Logs.error = alarmError;
	Logs.alarmError = alarmError;
    }

    public static synchronized boolean isClimaticControlError() {
	return climaticControlError;
    }

    public static synchronized void setClimaticControlError(
	    boolean climaticControlError) {
	Logs.error = climaticControlError;
	Logs.climaticControlError = climaticControlError;
    }

    public static synchronized boolean isDevicesControlError() {
	return devicesControlError;
    }

    public static synchronized void setDevicesControlError(
	    boolean devicesControlError) {
	Logs.error = devicesControlError;
	Logs.devicesControlError = devicesControlError;
    }

    public static synchronized boolean isServerError() {
	return serverError;
    }

    public static synchronized void setServerError(boolean serverError) {
	Logs.error = serverError;
	Logs.serverError = serverError;
    }

    public static synchronized boolean isError() {
	return Logs.error;
    }
}
