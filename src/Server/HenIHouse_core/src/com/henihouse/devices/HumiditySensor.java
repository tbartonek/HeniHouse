package com.henihouse.devices;

import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;

public class HumiditySensor {
    // sensor set to 12bit RH and 14bit Temp
    private I2CthroughPin i2c;
    private double	temperature = 0;
    private double	humidity    = 0;

    // variables for SOrh 12bit
    private final double  c1	  = -2.0468;
    private final double  c2	  = 0.0367;
    private final double  c3	  = -0.0000015955;

    // variables for RHtrue 12bit
    private final double  t1	  = 0.01;
    private final double  t2	  = 0.00008;

    private double	d1;
    private final double  d2	  = 0.01;

    public HumiditySensor(I2CthroughPin i2c, String VDD) {
	setD1(VDD);
	this.i2c = i2c;
    }

    // 3 = read temperature
    // 5 = read humidity
    // 30 = soft reset

    public double readHumidity() {
	int dataMSB = 0;
	int dataLSB = 0;
	boolean OK = true;
	this.humidity = 0;
	try {

	    i2c.start_humidity();
	    if (i2c.write(3)) {
		Thread.sleep(500);
		dataMSB = i2c.read(false);
		dataLSB = i2c.read(true);

		temperature = countTemperature((dataMSB << 8) + dataLSB);

		dataMSB = 0;
		dataLSB = 0;

		i2c.start_humidity();
		if (i2c.write(5)) {
		    Thread.sleep(300);
		    dataMSB = i2c.read(false);
		    dataLSB = i2c.read(true);
		    this.humidity = countHumidity((dataMSB << 8) + dataLSB,
			    temperature);
		} else {
		    OK = false;
		}
	    } else {
		OK = false;
	    }
	    if (!OK) {
		Logs.addLog(new Log("Warning", "Sensor",
			"Humidity sensor didn't sent answer."));
		Logs.setSensorWarning(true);
	    }
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    Logs.addLog(new Log("Error", "Sensor", "Humidity sensor."));
	    Logs.setSensorError(true);
	    e.printStackTrace();
	}
	return this.humidity;
    }

    public double readTemperature() {
	int dataMSB = 0;
	int dataLSB = 0;
	try {

	    i2c.start_humidity();
	    if (i2c.write(3)) {
		Thread.sleep(500);
		dataMSB = i2c.read(false);
		dataLSB = i2c.read(true);

		this.temperature = countTemperature((dataMSB << 8) + dataLSB);
	    } else {
		Logs.addLog(new Log("Warning", "Sensor",
			"Humidity-Temperature sensor didn't sent answer."));
		Logs.setSensorWarning(true);
	    }
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    Logs.addLog(new Log("Error", "Sensor",
		    "Humidity-Temperature sensor."));
	    Logs.setSensorError(true);
	    e.printStackTrace();
	}
	return temperature;
    }

    /**
     * Return TRUE humidity - compensation of temperature.
     * 
     * @param SOrh
     * @param temperature
     * @return
     */
    private double countHumidity(double SOrh, double temperature) {
	double RHlinear = c1 + (c2 * SOrh) + (c3 * (SOrh) * (SOrh));
	return ((temperature - 25) * (t1 + t2 * SOrh)) + RHlinear;
    }

    /**
     * Return temperature.
     * 
     * @param SOt
     * @return
     */
    private double countTemperature(double SOt) {
	return d1 + (d2 * SOt);
    }

    /**
     * Set d1 according VDD.
     * 
     * @param VDD
     */
    private void setD1(String VDD) {
	switch (VDD) {
	    case "5V":
		d1 = -40.1;
		break;
	    case "4V":
		d1 = -39.8;
		break;
	    case "3.5V":
		d1 = -39.7;
		break;
	    case "3V":
		d1 = -39.6;
		break;
	    case "2.5V":
		d1 = -39.4;
		break;
	    default:
		d1 = -39.7;
		break;
	}

    }
}
