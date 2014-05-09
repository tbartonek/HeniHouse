package com.henihouse.control;

import java.util.ArrayList;
import java.util.List;

import com.henihouse.devices.I2CthroughPin;
import com.henihouse.devices.Keypad16;
import com.henihouse.variables.AlarmVariables;
import com.henihouse.variables.Devices;
import com.henihouse.variables.HeatingVariables;
import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;
import com.henihouse.variables.OutputVariables;
import com.henihouse.variables.Variables;

public class Keypads implements Runnable {

    private List<Keypad16> keypads;
    private long	   timeRepeat	 = 100;
    private int	    repeatMeasureLight = 0;
    private double	 maxTimeForPassword = 0;
    private String	 key;
    private boolean	servis_mode;
    private long	   timeStartAlarm     = 0;
    private boolean	startAlarm	 = false;

    public void initKeypads(I2CthroughPin i2c) {
	Keypad16 keypad;
	keypads = new ArrayList<Keypad16>();
	for (int index = 0; index < Devices.getKeypads().size(); index++) {
	    keypad = new Keypad16();
	    if (keypad.activateKeypad16(i2c,
		    Integer.parseInt(Devices.getKeypad(index).getId()))) {
		this.keypads.add(keypad);
	    } else {
		Logs.addLog(new Log("Warning", "Keypad", " with address "
			+ Devices.getKeypad(index).getId() + " wasn't add."));
		Logs.setKeypadWarning(true);
	    }
	}
    }

    public void run() {

	for (;;) {
	    for (int index = 0; index < keypads.size(); index++) {
		controlKeypad(keypads.get(index));
		if (repeatMeasureLight == 100) {
		    repeatMeasureLight = 0;
		    keypads.get(index).LightMeasure();
		} else {
		    ++repeatMeasureLight;
		}
		keypads.get(index).alarm_active = AlarmVariables.isActive();
		keypads.get(index).alarm_alert = AlarmVariables.isAlert();
		if (servis_mode) {
		    keypads.get(index).alarm_servis = !keypads.get(index).alarm_servis;
		} else {
		    keypads.get(index).alarm_servis = AlarmVariables.isServis();
		}
		keypads.get(index).house_active = Variables.isHouse_active();
		keypads.get(index).house_heating = HeatingVariables.isActive();
		keypads.get(index).signalling();

	    }
	    try {
		Thread.sleep(timeRepeat);
	    } catch (InterruptedException e) {
		Logs.addLog(new Log("Error", "Keypad", "during sleeping."));
		Logs.setKeypadError(true);
	    }
	}
    }

    private void controlKeypad(Keypad16 keypad) {
	if ((key = keypad.getKey()) != "") {
	    maxTimeForPassword = System.currentTimeMillis()
		    + AlarmVariables.getMaxTimePassword();
	    switch (key) {
		case "A":
		    keypad.blinkBuzzer(3, 30);
		    timeStartAlarm = System.currentTimeMillis()
			    + AlarmVariables.getStartTimeAlarm();
		    startAlarm = true;
		    keypad.setKeys("");
		    break;
		case "B":
		    if (!AlarmVariables.isActive()) {
			keypad.blinkBuzzer(1, 30);
			keypad.setKeys("");
			Logs.addLog(new Log("Info", "Alarm", "change password."));
			changePassword(keypad);
		    } else {
			keypad.blinkAlarmAlert(3, 300, true);
			Logs.addLog(new Log("Info", "Alarm",
				"change password during active alarm - reject."));
		    }
		    break;
		case "D":
		    keypad.blinkBuzzer(1, 30);
		    HeatingVariables.setActive(!HeatingVariables.isActive());
		    OutputVariables
			    .putValue("0_4", HeatingVariables.isActive());
		    OutputVariables.setChange(true);
		    Logs.addLog(new Log("Info", "Climatic control",
			    "Heating - " + HeatingVariables.isActive() + "."));
		    break;
		case "C":
		    keypad.blinkAlarmAlert(1, 600, true);
		    keypad.setKeys("");
		    if (servis_mode) {
			Logs.addLog(new Log("Info", "Alarm",
				"deactive servis mode."));
			AlarmVariables.setServis_mode(false);
			servis_mode = false;
		    } else {
			Logs.addLog(new Log("Info", "Alarm",
				"delete password on keypad with address "
					+ keypad.getAddress() + "."));
		    }

		    break;
		case "*":
		    if (HeatingVariables.getTemperature() > 10) {
			HeatingVariables.setTemperature(HeatingVariables
				.getTemperature() - 0.5);
			Logs.addLog(new Log("Info", "Climatic control",
				"Temperature is set on: "
					+ HeatingVariables.getTemperature()
					+ "C."));
			keypad.blinkHouseHeating(1, 30, true);
		    } else {
			Logs.addLog(new Log("Info", "Climatic control",
				"Minimal temperature is set - 10C"));
			keypad.blinkHouseHeating(2, 100, true);
		    }
		    break;
		case "#":
		    if (HeatingVariables.getTemperature() < 25) {
			HeatingVariables.setTemperature(HeatingVariables
				.getTemperature() + 0.5);
			Logs.addLog(new Log("Info", "Climatic control",
				"Temperature is set on: "
					+ HeatingVariables.getTemperature()
					+ "C."));
			keypad.blinkHouseHeating(1, 30, true);
		    } else {
			Logs.addLog(new Log("Info", "Climatic control",
				"Maximal temperature is set - 25C"));
			keypad.blinkHouseHeating(2, 100, true);
		    }
		    break;
		default:
		    keypad.blinkBuzzer(1, 30);
		    keypad.setKeys(keypad.getKeys() + key);
		    break;
	    }

	    if (AlarmVariables.verifyPassword(keypad.getKeys())) {
		if (AlarmVariables.isActive() || startAlarm) {
		    keypad.blinkBuzzer(2, 30);
		    Logs.addLog(new Log("Info", "Alarm", "is deactive."));
		    startAlarm = false;
		    AlarmVariables.setActive(false);
		    AlarmVariables.setAlert(false);
		} else if (AlarmVariables.isAlert()) {
		    keypad.blinkBuzzer(2, 30);
		    Logs.addLog(new Log("Info", "Alarm", "delete alert."));
		    AlarmVariables.setAlert(false);
		} else {
		    keypad.blinkBuzzer(3, 30);
		    startAlarm = true;
		    timeStartAlarm = System.currentTimeMillis()
			    + AlarmVariables.getStartTimeAlarm();

		}
		keypad.setKeys("");
	    } else if (AlarmVariables.verifyServisPassword(keypad.getKeys())) {
		Logs.addLog(new Log("Info", "Alarm", "active servis mode."));
		AlarmVariables.setServis_mode(true);
		servis_mode = true;
		keypad.setKeys("");
	    } else if (AlarmVariables.getLengthPassword() == keypad.getKeys()
		    .length()) {
		AlarmVariables.verifyServisPassword(keypad.getKeys());
		Logs.addLog(new Log("Info", "Alarm",
			"Password was written worse, try againt."));
		keypad.setKeys("");
		keypad.blinkAlarmAlert(3, 300, true);
	    }

	}
	if (!keypad.getKeys().equals("")
		&& System.currentTimeMillis() >= maxTimeForPassword) {
	    Logs.addLog(new Log("Info", "Alarm",
		    "maximum time for write password was exceed."));
	    keypad.setKeys("");
	    keypad.blinkAlarmAlert(1, 600, true);
	}
	if (startAlarm) keypad.blinkAlarmActive(1, 50, false);
	if (startAlarm && timeStartAlarm < System.currentTimeMillis()) {
	    keypad.blinkBuzzer(5, 50);
	    startAlarm = false;
	    AlarmVariables.setActive(true);
	    AlarmVariables.setAlert(false);
	    Logs.addLog(new Log("Info", "Alarm", "is activated"));
	}
    }

    private void changePassword(Keypad16 keypad) {
	boolean changePassword = true;
	double maxTimeChangePassword = System.currentTimeMillis()
		+ AlarmVariables.getMaxTimePassword();
	boolean oldPasswordRight = false;
	boolean newPasswordEnd1 = false;
	boolean newPasswordEnd2 = false;
	String keys = "";
	String newPassword1 = "";

	do {
	    keypad.alarm_servis = true;
	    keypad.signalling();
	    if ((key = keypad.getKey()) != "") {
		maxTimeChangePassword = System.currentTimeMillis()
			+ AlarmVariables.getMaxTimePassword();
		switch (key) {
		    case "A":
			break;
		    case "B":
			if (oldPasswordRight & !newPasswordEnd1) {
			    newPasswordEnd1 = true;
			    newPassword1 = keys;
			    Logs.addLog(new Log("Info", "Alarm",
				    "first password is ok."));
			    keys = "";
			    keypad.blinkAlarmActive(2, 250, true);
			} else if (newPasswordEnd1) {
			    if (newPassword1.equals(keys)) {
				newPasswordEnd2 = true;
				Logs.addLog(new Log("Info", "Alarm",
					"first and second new password are same."));
			    } else {
				Logs.addLog(new Log("Info", "Alarm",
					"first and second new password aren't same."));
				keypad.blinkAlarmAlert(3, 300, true);
				changePassword = false;
			    }
			}
			break;
		    case "D":
			break;
		    case "C":
			Logs.addLog(new Log("Info", "Alarm",
				"stop change password without save."));
			keypad.blinkAlarmAlert(3, 300, true);
			changePassword = false;
			break;
		    case "*":
			break;
		    case "#":
			break;
		    default:
			keypad.blinkBuzzer(1, 30);
			keys = keys + key;
			break;
		}

		if (AlarmVariables.verifyPassword(keys)) {
		    Logs.addLog(new Log("Info", "Alarm",
			    "right enter old password."));
		    keypad.blinkAlarmActive(2, 250, true);
		    keys = "";
		    oldPasswordRight = true;
		} else if (oldPasswordRight & newPasswordEnd2) {
		    AlarmVariables.setPassword(keys);
		    changePassword = false;
		    keypad.blinkAlarmActive(1, 800, true);
		}
	    }
	    keypad.alarm_servis = false;
	    keypad.signalling();
	    if (System.currentTimeMillis() >= maxTimeChangePassword) {
		Logs.addLog(new Log("Info", "Alarm",
			"maximum time for change password was exceed."));
		changePassword = false;
		keypad.blinkAlarmAlert(3, 300, true);
	    }

	    try {
		Thread.sleep(timeRepeat - 100);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		Logs.addLog(new Log("Error", "Keypad", "during sleeping."));
		Logs.setKeypadError(true);
	    }
	} while (changePassword);
    }
}
