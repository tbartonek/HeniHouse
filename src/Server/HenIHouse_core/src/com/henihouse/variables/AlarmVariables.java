package com.henihouse.variables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmVariables {
    private static boolean      active		 = false;
    private static boolean      alert		  = false;
    private static boolean      servis		 = false;
    private static boolean      servis_mode	    = false;
    private static String       password	       = "1234";
    private static String       servisPassword	 = "9876";
    private static int	  maxTryPassword	 = 5;
    private static int	  tryPassword	    = 5;
    private static long	 maxTimePassword	= 30000;
    private static int	  alertTime	      = 15000;
    private static int	  startTimeAlarm	 = 45000;
    private static List<Device> keypads;
    private static boolean      checkMoveDetectors     = true;
    private static boolean      checkSoundDetectors    = true;
    private static boolean      checkTMPDetectors      = true;
    private static boolean      checkOtherDetectors    = true;
    private static boolean      checkMagneticDetectors = true;
    private static int	  maxNotice	      = 50;
    private static List<String> alarmNotice	    = new ArrayList<String>(
							       maxNotice);

    public AlarmVariables() {
	keypads = new ArrayList<Device>(4);
    }

    public static boolean isActive() {
	return active;
    }

    public static void setActive(boolean active2) {
	if (active2) {
	    addNotice("Alarm is activated.");
	} else {
	    addNotice("Alarm is deactivated.");
	}
	active = active2;
    }

    public static boolean isAlert() {
	return alert;
    }

    public static void setAlert(boolean alert2) {
	if (alert2) {
	    addNotice("Alert is activated.");
	} else {
	    addNotice("Alert is deactivated.");
	}
	alert = alert2;
    }

    public static boolean isServis() {
	return servis;
    }

    public static void setServis(boolean servis2) {
	if (servis2) {
	    addNotice("Servis mode is activated.");
	} else {
	    addNotice("Servis mode is deactivated.");
	}
	servis = servis2;
    }

    public static int getLengthPassword() {
	return password.length();
    }

    public static boolean verifyPassword(String password2) {
	if (password.equalsIgnoreCase(password2)) {
	    tryPassword = 0;
	    AlarmVariables.setAlert(false);
	    return true;
	} else {
	    ++tryPassword;
	    if (tryPassword == maxTryPassword) {
		AlarmVariables.setAlert(true);
	    }
	    addNotice("Wrong password.");
	    return false;
	}
    }

    public static boolean isServis_mode() {
	return servis_mode;
    }

    public static void setServis_mode(boolean servis_mode) {
	AlarmVariables.servis_mode = servis_mode;
    }

    public static void setPassword(String password2) {
	addNotice("Alarm password is changed.");
	password = password2;
    }

    public static String getServisPassword() {
	return servisPassword;
    }

    public static void setServisPassword(String servisPassword) {
	AlarmVariables.servisPassword = servisPassword;
	addNotice("Servis password is changed.");
    }

    public static boolean verifyServisPassword(String servisPassword2) {
	if (servisPassword.equalsIgnoreCase(servisPassword2)) {
	    return true;
	} else {
	    addNotice("Wrong servis password.");
	    return false;
	}
    }

    public static void addKeypad(Device keypad2) {
	keypads.add(keypad2);
    }

    public static Device getKeypad(int index) {
	return keypads.get(index);
    }

    public static List<Device> getKeypads() {
	return keypads;
    }

    public static void removeKeypad(int index) {
	keypads.remove(index);
    }

    public static long getMaxTimePassword() {
	return maxTimePassword;
    }

    public static void setMaxTimePassword(long maxTimePassword2) {
	maxTimePassword = maxTimePassword2;
    }

    public static boolean isCheckMoveDetectors() {
	return checkMoveDetectors;
    }

    public static void setCheckMoveDetectors(boolean checkMoveDetectors2) {
	checkMoveDetectors = checkMoveDetectors2;
    }

    public static boolean isCheckSoundDetectors() {
	return checkSoundDetectors;
    }

    public static void setCheckSoundDetectors(boolean checkSoundDetectors2) {
	checkSoundDetectors = checkSoundDetectors2;
    }

    public static boolean isCheckTMPDetectors() {
	return checkTMPDetectors;
    }

    public static void setCheckTMPDetectors(boolean checkTMPDetectors2) {
	checkTMPDetectors = checkTMPDetectors2;
    }

    public static boolean isCheckOtherDetectors() {
	return checkOtherDetectors;
    }

    public static void setCheckOtherDetectors(boolean checkOtherDetectors2) {
	checkOtherDetectors = checkOtherDetectors2;
    }

    public static boolean isCheckMagneticDetectors() {
	return checkMagneticDetectors;
    }

    public static void setCheckMagneticDetectors(boolean checkMagneticDetectors2) {
	checkMagneticDetectors = checkMagneticDetectors2;
    }

    public static int getAlertTime() {
	return alertTime;
    }

    public static void setAlertTime(int alertTime2) {
	alertTime = alertTime2;
    }

    public static int getMaxTryPassword() {
	return maxTryPassword;
    }

    public static void setMaxTryPassword(int maxTryPassword2) {
	maxTryPassword = maxTryPassword2;
    }

    public static int getStartTimeAlarm() {
	return startTimeAlarm;
    }

    public static void setStartTimeAlarm(int startTimeAlarm) {
	AlarmVariables.startTimeAlarm = startTimeAlarm;
    }

    public static List<String> getAlarmNotice() {
	return alarmNotice;
    }

    private static String getCurrentTime() {
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	return dateFormat.format(cal.getTime());

    }

    public static void addNotice(String notice) {
	Logs.addLog(new Log("Info", "Alarm", notice));

	notice = getCurrentTime() + " - " + notice;
	if (alarmNotice.size() < maxNotice) {
	    alarmNotice.add(0, notice);
	} else {
	    alarmNotice.add(0, notice);
	    alarmNotice.remove(maxNotice);
	}
    }

}
