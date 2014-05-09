package com.henihouse.devices;

import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;

public class Pressure_BMP085 {
    private I2CthroughPin i2c;

    private short	 AC1;
    private short	 AC2;
    private short	 AC3;
    private int	   AC4;
    private int	   AC5;
    private int	   AC6;
    private short	 B1;
    private short	 B2;
    private short	 MB;
    private short	 MC;
    private short	 MD;
    private long	  B5;

    public Pressure_BMP085(I2CthroughPin i2c) {
	this.i2c = i2c;
	Logs.addLog(new Log("Info", "Sensor",
		"Init. - Pressure-Temperature sensor read coeficients."));
	AC1 = (short) getCoefficient(0xAA, 0xAB);
	// System.out.println("AC1 " + AC1);

	AC2 = (short) getCoefficient(0xAC, 0xAD);
	// System.out.println("AC2 " + AC2);

	AC3 = (short) getCoefficient(0xAE, 0xAF);
	// System.out.println("AC3 " + AC3);

	AC4 = getCoefficient(0xB0, 0xB1);
	// System.out.println("AC4 " + AC4);

	AC5 = getCoefficient(0xB2, 0xB3);
	// System.out.println("AC5 " + AC5);

	AC6 = getCoefficient(0xB4, 0xB5);
	// System.out.println("AC6 " + AC6);

	B1 = (short) getCoefficient(0xB6, 0xB7);
	// System.out.println("B1 " + B1);

	B2 = (short) getCoefficient(0xB8, 0xB9);
	// System.out.println("B2 " + B2);

	MB = (short) getCoefficient(0xBA, 0xBB);
	// System.out.println("MB " + MB);

	MC = (short) getCoefficient(0xBC, 0xBD);
	// System.out.println("MC " + MC);

	MD = (short) getCoefficient(0xBE, 0xBF);
	// System.out.println("MD " + MD);

	Logs.addLog(new Log("Info", "Sensor",
		"Init. - Pressure-Temperature is OK."));
    }

    public float getTemperature() {
	long temperature = 0;
	boolean OK = true;
	int dataMSB = 0;
	int dataLSB = 0;
	try {
	    i2c.start();
	    if (i2c.write(0xEE)) {
		if (i2c.write(0xF4)) {
		    if (i2c.write(0x2E)) {
			i2c.stop();

			Thread.sleep(6);
			i2c.start();
			if (i2c.write(0xEE)) {
			    if (i2c.write(0xF6)) {
				i2c.stop();
				i2c.start();
				if (i2c.write(0xEF)) {
				    dataMSB = i2c.read(true);
				    i2c.stop();
				    i2c.start();
				    if (i2c.write(0xEE)) {
					if (i2c.write(0xF7)) {
					    i2c.stop();
					    i2c.start();
					    if (i2c.write(0xEF)) {
						dataLSB = i2c.read(true);
						temperature = ((dataMSB << 8) + dataLSB);
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
	    } else {
		OK = false;
	    };
	    i2c.stop();
	    if (!OK) {
		Logs.addLog(new Log("Warning", "Sensor",
			"Pressure-Temperature sensor didn't sent answer during read temperature."));
		Logs.setSensorWarning(true);
		return 0;
	    }

	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    Logs.addLog(new Log("Error", "Sensor",
		    "Pressure-Temperature sensor didn't sent answer."));
	    Logs.setSensorWarning(true);
	}
	float temperatureTrue = countTemperature(temperature);
	temperatureTrue = temperatureTrue / 10;
	return temperatureTrue;
    }

    private long countTemperature(long temperature) {
	long X1, X2;
	// X1 = (long) ((temperature - AC6) * AC5 / Math.pow(2, 15));
	// X1 = (long) ((temperature - AC6) * AC5 / 32768);
	X1 = (long) ((temperature - AC6) * AC5 >> 15);
	// X2 = (long) (MC * Math.pow(2, 11) / (X1 + MD));
	// X2 = (long) (MC * 2048 / (X1 + MD));
	X2 = (long) ((MC << 11) / (X1 + MD));

	B5 = (long) (X1 + X2);
	// temperature = (long) ((B5 + 8) / Math.pow(2, 4));
	temperature = (long) ((B5 + 8) >> 4);
	return temperature;
    }

    /**
     * Arguments - actual temperature and altitude
     * @return pressure [Pa]
     */
    public float getPressure(float temperature, int altitude) {
	int pressure = 0;
	boolean OK = true;
	int dataMSB = 0;
	int dataLSB = 0;
	int dataXLSB = 0;
	try {
	    i2c.start();
	    if (i2c.write(0xEE)) {
		if (i2c.write(0xF4)) {
		    if (i2c.write(0xB4)) {
			i2c.stop();

			Thread.sleep(16);
			i2c.start();
			if (i2c.write(0xEE)) {
			    if (i2c.write(0xF6)) {
				i2c.stop();
				i2c.start();
				if (i2c.write(0xEF)) {
				    dataMSB = i2c.read(false);
				    dataLSB = i2c.read(false);
				    dataXLSB = i2c.read(true);
				    pressure = ((dataMSB << 16)
					    + (dataLSB << 8) + dataXLSB) >> 7;
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
	    i2c.stop();
	    if (!OK) {
		Logs.addLog(new Log("Warning", "Sensor",
			"Pressure-Temperature sensor didn't sent answer during read pressure."));
		Logs.setSensorWarning(true);
		return 0;
	    }

	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    Logs.addLog(new Log("Error", "Sensor",
		    "Pressure-Temperature sensor didn't sent answer."));
	    Logs.setSensorWarning(true);
	}
	return countPressure(pressure, temperature, altitude);

    }

    private int getCoefficient(int adr1, int adr2) {
	int coefficient = 0;
	boolean OK = true;
	int dataMSB = 0;
	int dataLSB = 0;
	try {
	    i2c.start();
	    if (i2c.write(0xEE)) {
		if (i2c.write(adr1)) {
		    i2c.stop();
		    i2c.start();
		    if (i2c.write(0xEF)) {
			dataMSB = i2c.read(true);
			i2c.stop();
			i2c.start();
			if (i2c.write(0xEE)) {
			    if (i2c.write(adr2)) {
				i2c.stop();
				i2c.start();
				if (i2c.write(0xEF)) {
				    dataLSB = i2c.read(true);
				    coefficient = ((dataMSB << 8) + dataLSB);
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
	    i2c.stop();
	    if (!OK) {
		Logs.addLog(new Log("Warning", "Sensor",
			"Pressure-Temperature sensor didn't sent answer."));
		Logs.setSensorWarning(true);
	    }

	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    Logs.addLog(new Log("Error", "Sensor",
		    "Pressure-Temperature sensor didn't sent answer."));
	    Logs.setSensorWarning(true);
	}
	return coefficient;
    }

    /**
     * @return pressure [Pa]
     */
    private float countPressure(int up, float temperature, int altitude) {
	int b6 = (int) (B5 - 4000);

	int x1 = (((b6 * b6) >> 12) * B2) >> 11;
	int x2 = (AC2 * b6) >> 11;

	int x3 = x1 + x2;

	int b3 = (((AC1 * 4 + x3) << 1) + 2) >> 2;

	x1 = (AC3 * b6) >> 13;
	x2 = (B1 * ((b6 * b6) >> 12)) >> 16;
	x3 = (x1 + x2 + 2) >> 2;
	int b4 = (AC4 * (x3 + 32768)) >> 15;

	int b7 = ((up - b3) * (50000 >> 1));

	int pressure;
	if (b7 < 0x80000000)
	    pressure = (b7 << 1) / b4;
	else
	    pressure = (b7 / b4) << 1;

	x1 = pressure >> 8;
	x1 *= x1;

	x1 = (x1 * 3038) >> 16;
	x2 = (-7357 * pressure) >> 16;

	pressure = pressure + ((x1 + x2 + 3791) >> 4);
	float pressureTrue = pressure / 100;
	pressureTrue = (float) (pressureTrue + ((pressureTrue * 10.80665 * altitude) / (287 * (273 + temperature + (altitude / 400)))));
	return (float) (Math.round(pressureTrue * 10.0) / 10.0);
    }
}
