package com.henihouse.function;

import java.util.List;

import com.henihouse.variables.AlarmVariables;
import com.henihouse.variables.Device;
import com.henihouse.variables.Devices;
import com.henihouse.variables.InputVariables;
import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;
import com.henihouse.variables.OutputVariables;

public class Alarm implements Runnable {

    private long    timeForSleep       = 2000;
    private long    timeStartAlert     = 0;
    private long    timeStopAlert      = 0;
    private boolean prepareAlert       = false;
    private boolean alert	      = false;
    private boolean alertWithoutActive = false;

    @Override
    public void run() {
	for (;;) {
	    // We don't check devices, when isn't change
	    // InputVariables.setChange(true);
	    if (InputVariables.isChange()) {
		// TMP is checked during deactive alarm
		if (!AlarmVariables.isServis_mode()) {
		    checkDevices(Devices.getTmpDetectors(), true);
		    checkDevices(Devices.getFireDetectors(), true);
		}

	    }
	    if (AlarmVariables.isActive()) {
		checkDevices(Devices.getMoveDetectors(), false);
		checkDevices(Devices.getSoundDetectors(), false);
		checkDevices(Devices.getMagneticDetectors(), false);

		if (prepareAlert && System.currentTimeMillis() > timeStartAlert) {
		    alert(true);
		}

	    } else if (prepareAlert) prepareAlert = false;

	    if (alert) {
		if (!AlarmVariables.isAlert()
			|| timeStopAlert < System.currentTimeMillis()) {
		    alert(false);
		    alert = false;
		}
	    }

	    try {
		Thread.sleep(timeForSleep);
	    } catch (InterruptedException e) {
		Logs.addLog(new Log("Error", "Alarm", "during sleeping."));
		Logs.setAlarmError(true);
	    }
	}

    }

    private void checkDevices(List<Device> devices, boolean alertWithoutActive) {
	for (int index = 0; index < devices.size(); index++) {
	    if (InputVariables.getValue(devices.get(index).getId()) != devices
		    .get(index).isBasicValue()) {
		if (alertWithoutActive) this.alertWithoutActive = true;
		Logs.addLog(new Log("Info", "Alarm", "Change on sensor: "
			+ devices.get(index).getName() + "."));
		if (devices.get(index).getDelay() == 0) {
		    AlarmVariables.setAlert(true);
		    alert(true);
		} else if (!prepareAlert) {
		    timeStartAlert = System.currentTimeMillis()
			    + devices.get(index).getDelay();
		    prepareAlert = true;
		}
	    }
	}
    }

    private void alert(boolean activate) {
	if (!alert || !activate) {
	    if (activate) {
		Logs.addLog(new Log("Info", "Alarm", "Start alert..."));
		timeStopAlert = AlarmVariables.getAlertTime()
			+ System.currentTimeMillis();
	    } else {
		Logs.addLog(new Log("Info", "Alarm", "Stop alert..."));
	    }
	    if (!AlarmVariables.isAlert() && activate)
		AlarmVariables.setAlert(true);
	    for (int index = 0; index < Devices.getSirens().size(); index++) {
		if (OutputVariables.existID(Devices.getSiren(index).getId())) {
		    OutputVariables.putValue(Devices.getSiren(index).getId(),
			    activate);
		    OutputVariables.setChange(true);
		} else {
		    Logs.addLog(new Log("Info", "Alarm", "Siren with id: "
			    + Devices.getSiren(index).getId() + " isn't exist."));
		}
	    }
	    for (int index = 0; index < Devices.getLights().size(); index++) {
		if (Devices.getLight(index).isActivateDuringAlert()) {
		    if (OutputVariables
			    .existID(Devices.getLight(index).getId())) {
			OutputVariables.putValue(Devices.getLight(index)
				.getId(), activate);
			OutputVariables.setChange(true);
		    } else {
			Logs.addLog(new Log("Info", "Alarm", "Light with id: "
				+ Devices.getLight(index).getId()
				+ " isn't exist."));

		    }
		}
	    }
	    prepareAlert = false;
	    alert = true;
	}
    }

}
