package com.henihouse.function;

import java.util.ArrayList;
import java.util.List;

import com.henihouse.devices.HumiditySensor;
import com.henihouse.devices.I2CthroughPin;
import com.henihouse.variables.Devices;
import com.henihouse.variables.HeatingVariables;
import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;
import com.henihouse.variables.OutputVariables;

public class ClimaticControl implements Runnable {

    private HumiditySensor humiditySensor;
    private long	   timeForSleep       = 60000;
    private List<Double>   temperatures       = new ArrayList<Double>(5);
    private int	    index	      = 0;
    private double	 averageTemperature = 0;

    public void initClimaticControl(I2CthroughPin i2cHumiditySensor) {
	this.humiditySensor = new HumiditySensor(i2cHumiditySensor, "5V");
	for (int i = 0; i < 5; i++) {
	    temperatures.add((double) 23);
	}
    }

    public void run() {
	for (;;) {
	    temperatures
		    .set(index, (double) (humiditySensor.readTemperature()));
	    HeatingVariables.setActualeTemperature(temperatures.get(index));
	    ++index;
	    if (index == 5) index = 0;

	    averageTemperature = 0;
	    for (int i = 0; i < temperatures.size(); i++) {
		averageTemperature += temperatures.get(i);
	    }
	    averageTemperature = (averageTemperature / temperatures.size());
	    HeatingVariables.setAverageTemperature(averageTemperature);

	    if (averageTemperature < HeatingVariables.getTemperature()
		    - HeatingVariables.getHysterez()
		    && !HeatingVariables.isActive()) {
		HeatingVariables.setActive(true);
		if (OutputVariables.existID(Devices.getHeater().getId())) {
		    OutputVariables.putValue(Devices.getHeater().getId(), true);
		    OutputVariables.setChange(true);
		} else {
		    Logs.addLog(new Log("Warning", "Climatic control",
			    "Heater with id: " + Devices.getHeater().getId()
				    + " wasn't find."));
		    Logs.setClimaticControlWarning(true);

		}
	    } else if (averageTemperature > HeatingVariables.getTemperature()
		    + HeatingVariables.getHysterez()
		    && HeatingVariables.isActive()) {
		HeatingVariables.setActive(false);
		if (OutputVariables.existID(Devices.getHeater().getId())) {
		    OutputVariables
			    .putValue(Devices.getHeater().getId(), false);
		    OutputVariables.setChange(true);
		} else {
		    Logs.addLog(new Log("Warning", "Climatic control",
			    "Heater with id: " + Devices.getHeater().getId()
				    + " wasn't find."));
		    Logs.setClimaticControlWarning(true);
		}
	    }
	    try {
		Thread.sleep(timeForSleep);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Logs.addLog(new Log("Error", "Climatic control",
			"during sleeping."));
		Logs.setClimaticControlError(true);
	    }
	}
    }

}
