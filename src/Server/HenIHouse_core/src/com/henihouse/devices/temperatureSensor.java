package com.henihouse.devices;

import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;

public class temperatureSensor {

    private int	   basicTemperatureAddress  = 144;
    private int	   addressDevice;
    private int	   register;
    private int	   resolution;
    private double	temperature;
    private int	   alarmTemperature;
    private int	   alarmHysteresis;
    private boolean       alarm_polarity_activHigh = true;
    private I2CthroughPin i2c;

    public temperatureSensor(I2CthroughPin i2c, int addressDevice1)
	    throws InterruptedException {
	this.i2c = i2c;
	addressDevice = addressDevice1;
	i2c.stop();
    }

    /**
     * Method for setting register and write to device.
     * 
     * @param one_shot
     * @param resolution1
     * @param alarm_polarity_activHigh
     * @param shutDown
     */
    public boolean setRegister(boolean one_shot, int resolution1,
	    boolean alarm_polarity_activHigh1, boolean shutDown) {
	register = 0;
	boolean OK = true;
	if (one_shot) register += 128;

	switch (resolution = resolution1) {
	    case 9:
		register += 0;
		break;
	    case 10:
		register += 32;
		break;
	    case 11:
		register += 64;
		break;
	    case 12:
		register += 96;
		break;
	    default:
		System.out
			.println("Wrong number of resolution, set default 9.");
		register += 0;
		resolution = 9;
		break;
	}

	if (alarm_polarity_activHigh = alarm_polarity_activHigh1)
	    register += 4;

	if (shutDown) register += 1;

	// System.out.println ("Device with address: " + addressDevice +
	// " has init. register: " + Byte.toString(initRegister) + ".");

	try {
	    i2c.start();
	    if (i2c.write(basicTemperatureAddress + addressDevice)) {
		if (i2c.write(1)) { // configuration register
		    if (i2c.write(register)) {
			i2c.stop();
		    } else {
			OK = false;
		    };
		} else {
		    OK = false;
		};
	    } else {
		OK = false;
	    }
	    if (!OK) {
		Logs.addLog(new Log("Warning", "Climatic control",
			"Mistake during write register to temperature sensor with addess: "
				+ addressDevice));
		Logs.setSensorWarning(true);
	    }
	    return OK;
	} catch (InterruptedException e) {
	    Logs.addLog(new Log("Error", "Climatic control",
		    "Mistake during write init register to temperature sensor with address: "
			    + addressDevice + "."));
	    Logs.setSensorError(true);
	    // e.printStackTrace();
	}
	return false;
    }

    /**
     * Method for reading temperature from device.
     * 
     * @return temperature
     */
    public double readTemperature() {
	int dataMSB = 0;
	int dataLSB = 0;
	boolean OK = true;

	try {
	    i2c.start();
	    if (i2c.write(basicTemperatureAddress + addressDevice)) {
		if (i2c.write(0)) { // temperature register
		    i2c.stop();

		    i2c.start();
		    if (i2c.write(basicTemperatureAddress + addressDevice + 1)) {
			dataMSB = i2c.read(false);
			dataLSB = i2c.read(true);
			i2c.stop();
		    } else {
			OK = false;
		    };
		} else {
		    OK = false;
		};
	    } else {
		OK = false;
	    }
	    if (!OK) {
		Logs.addLog(new Log("Warning", "Climatic control",
			"Mistake during read data from temperature sensor with address: "
				+ addressDevice + "."));
		Logs.setSensorWarning(true);
	    }
	} catch (InterruptedException e) {
	    Logs.addLog(new Log("Error", "Climatic control",
		    "Mistake during read data from temperature sensor with address: "
			    + addressDevice + "."));
	    Logs.setSensorError(true);
	    // e.printStackTrace();
	}

	switch (resolution) {
	    case 9:
		temperature = (((dataMSB & 0x7F) << 1) + (dataLSB >> 7))
			* Math.pow(2, -1);
		break;
	    case 10:
		temperature = (((dataMSB & 0x7F) << 2) + (dataLSB >> 6))
			* Math.pow(2, -2);
		break;
	    case 11:
		temperature = (((dataMSB & 0x7F) << 3) + (dataLSB >> 5))
			* Math.pow(2, -3);
		break;
	    case 12:
		temperature = (((dataMSB & 0x7F) << 4) + (dataLSB >> 4))
			* Math.pow(2, -4);
		break;
	}
	// Check SIGN bit and set sign
	if ((dataMSB & 0x80) == 128) temperature -= 2 * temperature;
	return temperature;
    }

    /**
     * Method for seting temperature and hysteresis for alarm.
     * 
     * @param alarmHysteresis1
     * @param alarmTemperature1
     */
    public void setAlarmTempAndHyst(int alarmHysteresis1, int alarmTemperature1) {
	boolean OK = true;
	try {
	    i2c.start();
	    if (i2c.write(basicTemperatureAddress + addressDevice)) {
		if (i2c.write(2)) { // hysteresis register
		    if (i2c.write(alarmHysteresis = alarmHysteresis1)) {
			if (i2c.write(0)) {
			    i2c.stop();
			    i2c.start();
			    if (i2c.write(basicTemperatureAddress
				    + addressDevice)) {
				if (i2c.write(3)) { // alarm rtemperature
						    // register
				    if (i2c.write(alarmTemperature = alarmTemperature1)) {
					if (i2c.write(0)) {
					    i2c.stop();
					} else {
					    OK = false;
					};
				    } else {
					OK = false;
				    };
				} else {
				    OK = false;
				};
			    } else {
				OK = false;
			    };
			} else {
			    OK = false;
			};
		    } else {
			OK = false;
		    };
		} else {
		    OK = false;
		};
	    } else {
		OK = false;
	    };

	    if (!OK) {
		Logs.addLog(new Log("Warning", "Climatic control",
			"Mistake during write register to temperature sensor with addess: "
				+ addressDevice));
		Logs.setSensorWarning(true);
	    }

	} catch (InterruptedException e) {
	    Logs.addLog(new Log(
		    "Error",
		    "Climatic control",
		    "Mistake during write temperature or hysteresis for alarm to device with address: "
			    + addressDevice + "."));
	    Logs.setSensorError(true);
	    // e.printStackTrace();
	}

    }

    /**
     * Method for reading temperature in shutdown. It is usefull for low-power
     * aplication.
     * 
     * @param resolution1
     * @return temperature
     * @throws InterruptedException
     */
    public double oneShotTemperature(int resolution1)
	    throws InterruptedException {
	setRegister(false, resolution1, alarm_polarity_activHigh, true);
	setRegister(true, resolution1, alarm_polarity_activHigh, true);
	switch (resolution = resolution1) {
	    case 10:
		Thread.sleep(40);
		break;
	    case 11:
		Thread.sleep(100);
		break;
	    case 12:
		Thread.sleep(220);
		break;
	}
	return readTemperature();
    }

}
