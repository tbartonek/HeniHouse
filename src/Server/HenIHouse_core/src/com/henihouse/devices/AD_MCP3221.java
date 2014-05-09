package com.henihouse.devices;

import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;

public class AD_MCP3221 {
    private I2CthroughPin i2c;
    private double	digitalValue;

    public AD_MCP3221(I2CthroughPin i2c) {
	this.i2c = i2c;
    }

    public double readDigitalValue() {
	int dataMSB = 0;
	int dataLSB = 0;

	try {
	    i2c.start();
	    i2c.stop();
	    i2c.start();
	    if (i2c.write(155)) {
		dataMSB = i2c.read(false);
		dataLSB = i2c.read(true);
		i2c.stop();

		digitalValue = (((dataMSB & 0x0F) << 8) + dataLSB);
		digitalValue = (5.05 / 4096) * digitalValue;
	    } else {
		Logs.addLog(new Log("Warning", "Sensor",
			"A/D sensor didn't sent answer."));
		Logs.setSensorWarning(true);
	    }
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    Logs.addLog(new Log("Error", "Sensor", "A/D sensor."));
	    Logs.setSensorError(true);
	    e.printStackTrace();
	}

	return digitalValue;
    }
}
