package com.henihouse.devices;

import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;

public class i2cBitExpander {

    private int	   basicExpanderAddress = 64;
    private int	   addressDevice;
    private I2CthroughPin i2c;
    private boolean       portAoutput;		 // true = port A is
							// output, false = port
							// A is input
    private boolean       portBoutput;		 // true = port B is
							// output, false = port
							// B is input

    private boolean       portA_0_High	 = false; // false = L = 0V, true
							// = H
    private boolean       portA_1_High	 = false; // same ...
    private boolean       portA_2_High	 = false;
    private boolean       portA_3_High	 = false;
    private boolean       portA_4_High	 = false;
    private boolean       portA_5_High	 = false;
    private boolean       portA_6_High	 = false;
    private boolean       portA_7_High	 = false;

    private boolean       portB_0_High	 = false;
    private boolean       portB_1_High	 = false;
    private boolean       portB_2_High	 = false;
    private boolean       portB_3_High	 = false;
    private boolean       portB_4_High	 = false;
    private boolean       portB_5_High	 = false;
    private boolean       portB_6_High	 = false;
    private boolean       portB_7_High	 = false;

    public boolean initi2cBitExpander(I2CthroughPin i2c, int addressDevice,
	    boolean portAoutput1, boolean portBoutput1)
	    throws InterruptedException {
	this.i2c = i2c;
	this.addressDevice = addressDevice;
	boolean OK = true;
	Logs.addLog(new Log("Info", "Expander",
		"Start activation with address " + addressDevice + "."));
	if (portAoutput = portAoutput1) {
	    OK = setRegister(0, 0);
	    OK = setRegister(18, 255); // Set all bits of Port A on high
	} else {
	    OK = setRegister(0, 255);
	    OK = setRegister(2, 255); // change logical polarity on portA
	    OK = setRegister(12, 255);
	}
	// System.out.println("OK PortA");
	if (portBoutput = portBoutput1) {
	    OK = setRegister(1, 0);
	    OK = setRegister(19, 255); // Set all bits of Port B on high
	} else {
	    OK = setRegister(1, 255);
	    OK = setRegister(3, 255); // change logical polarity on portB
	    OK = setRegister(13, 255);
	}
	// System.out.println("OK PortB");
	if (OK) {
	    Logs.addLog(new Log("Info", "Expander",
		    "is activated with address " + addressDevice + "."));
	    return true;
	} else {
	    Logs.addLog(new Log("Warning", "Expander",
		    "isn't activated with address " + addressDevice + "."));
	    Logs.setExpanderWarning(true);
	    return false;
	}
    }

    /**
     * 
     * @param i2c
     * @param addressDevice
     * @throws InterruptedException
     */
    public boolean initi2cBitExpander(I2CthroughPin i2c, int addressDevice)
	    throws InterruptedException {
	this.i2c = i2c;
	this.addressDevice = addressDevice;
	boolean OK = true;
	Logs.addLog(new Log("Info", "Keypad", "Start activation with address "
		+ addressDevice + "."));
	// Keypad is connect on Port A I2C Expander.

	OK = setRegister(0, 240); // firts four bit are output, last four bit
				  // are
				  // input
	OK = setRegister(12, 240); // firts four bit are output, last four bit
				   // are
				   // input

	OK = setRegister(1, 0); // Port B is output
	OK = setRegister(19, 255); // Set all bits of Port B on high
	if (OK) {
	    Logs.addLog(new Log("Info", "Keypad", "is activated with address "
		    + addressDevice + "."));
	    return true;
	} else {
	    Logs.addLog(new Log("Warning", "Keypad",
		    "isn't activated with address " + addressDevice + "."));
	    Logs.setExpanderWarning(true);
	    return false;
	}
    }

    private boolean setRegister(int addressRegister, int data) {
	boolean OK = true;
	try {
	    i2c.start();
	    if (i2c.write(basicExpanderAddress + (addressDevice << 1))) {
		if (i2c.write(addressRegister)) { // register
		    if (!i2c.write(data)) OK = false;
		} else {
		    OK = false;
		};
	    } else {
		OK = false;
	    };
	    i2c.stop();
	    return OK;
	} catch (InterruptedException e) {
	    Logs.addLog(new Log("Warning", "Expander",
		    "during write register to device with addess: "
			    + (basicExpanderAddress + addressDevice)));
	    Logs.setExpanderWarning(true);
	    e.printStackTrace();
	}
	return false;
    }

    private int readRegister(int addressRegister) {
	int data = 0;
	boolean OK = true;
	try {
	    i2c.start();
	    if (i2c.write(basicExpanderAddress + (addressDevice << 1))) {
		if (i2c.write(addressRegister)) { // register
		    i2c.stop();

		    i2c.start();
		    if (i2c.write(basicExpanderAddress + (addressDevice << 1)
			    + 1)) {
			data = i2c.read(true);
			i2c.stop();
		    } else {
			OK = false;
		    }
		} else {
		    OK = false;
		}
	    } else {
		OK = false;
	    }
	    if (!OK) {
		Logs.addLog(new Log("Warning", "Expander",
			"during read register from expander with addess: "
				+ addressDevice));
		Logs.setExpanderWarning(true);
	    }

	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    Logs.addLog(new Log("Error", "Expander",
		    "during read register from device with addess: "
			    + addressDevice));
	    Logs.setExpanderError(true);
	    e.printStackTrace();
	}
	return data;
    }

    /**
     * Write data to device.
     * 
     * @param data
     */
    private boolean setPortA() {
	int data = 0;
	if (portAoutput) {
	    if (portA_0_High) data += 1;
	    if (portA_1_High) data += 2;
	    if (portA_2_High) data += 4;
	    if (portA_3_High) data += 8;
	    if (portA_4_High) data += 16;
	    if (portA_5_High) data += 32;
	    if (portA_6_High) data += 64;
	    if (portA_7_High) data += 128;

	    return setRegister(18, data);
	} else {
	    Logs.addLog(new Log("Warning", "Expander", "Port A isn't output"));
	    Logs.setExpanderWarning(true);
	    return false;
	}

    }

    public boolean setPortA(int data) {
	if (portAoutput) {
	    return setRegister(18, data);
	} else {
	    Logs.addLog(new Log("Warning", "Expander", "Port A isn't output"));
	    Logs.setExpanderWarning(true);
	    return false;
	}

    }

    /**
     * Read port A and return int data and set variables portA_?_High
     * 
     * @return int data
     */
    public int readPortA() {
	int data = 0;
	if (!portAoutput) {
	    data = readRegister(18);

	    portA_0_High = (data & (1)) != 0;
	    portA_1_High = (data & (1 << 1)) != 0;
	    portA_2_High = (data & (1 << 2)) != 0;
	    portA_3_High = (data & (1 << 3)) != 0;
	    portA_4_High = (data & (1 << 4)) != 0;
	    portA_5_High = (data & (1 << 5)) != 0;
	    portA_6_High = (data & (1 << 6)) != 0;
	    portA_7_High = (data & (1 << 7)) != 0;
	} else {
	    Logs.addLog(new Log("Warning", "Expander", "Port A is output"));
	    Logs.setExpanderWarning(true);
	}
	return data;
    }

    /**
     * Write data to device.
     */
    private boolean setPortB() {
	int data = 0;
	if (portBoutput) {
	    if (portB_0_High) data += 1;
	    if (portB_1_High) data += 2;
	    if (portB_2_High) data += 4;
	    if (portB_3_High) data += 8;
	    if (portB_4_High) data += 16;
	    if (portB_5_High) data += 32;
	    if (portB_6_High) data += 64;
	    if (portB_7_High) data += 128;

	    return setRegister(19, data);
	} else {
	    Logs.addLog(new Log("Warning", "Expander", "Port B isn't output"));
	    Logs.setExpanderWarning(true);
	    return false;
	}
    }

    public boolean setPortB(int data) {
	if (portBoutput) {
	    return setRegister(19, data);
	} else {
	    Logs.addLog(new Log("Warning", "Expander", "Port B isn't output"));
	    Logs.setExpanderWarning(true);
	    return false;
	}
    }

    /**
     * Read port B and return int data and set variables portB_?_High
     * 
     * @return int data
     */
    public int readPortB() {
	int data = 0;
	if (!portBoutput) {
	    data = readRegister(19);

	    portB_0_High = (data & (1)) != 0;
	    portB_1_High = (data & (1 << 1)) != 0;
	    portB_2_High = (data & (1 << 2)) != 0;
	    portB_3_High = (data & (1 << 3)) != 0;
	    portB_4_High = (data & (1 << 4)) != 0;
	    portB_5_High = (data & (1 << 5)) != 0;
	    portB_6_High = (data & (1 << 6)) != 0;
	    portB_7_High = (data & (1 << 7)) != 0;
	} else {
	    Logs.addLog(new Log("Warning", "Expander", "Port B is output"));
	    Logs.setExpanderWarning(true);
	}

	return data;
    }

    public void setPortApin0(boolean value) {
	portA_0_High = value;
	setPortA();
    }

    public void setPortApin1(boolean value) {
	portA_1_High = value;
	setPortA();
    }

    public void setPortApin2(boolean value) {
	portA_2_High = value;
	setPortA();
    }

    public void setPortApin3(boolean value) {
	portA_3_High = value;
	setPortA();
    }

    public void setPortApin4(boolean value) {
	portA_4_High = value;
	setPortA();
    }

    public void setPortApin5(boolean value) {
	portA_5_High = value;
	setPortA();
    }

    public void setPortApin6(boolean value) {
	portA_6_High = value;
	setPortA();
    }

    public void setPortApin7(boolean value) {
	portA_7_High = value;
	setPortA();
    }

    public void setPortBpin0(boolean value) {
	portB_0_High = value;
	setPortB();
    }

    public void setPortBpin1(boolean value) {
	portB_1_High = value;
	setPortB();
    }

    public void setPortBpin2(boolean value) {
	portB_2_High = value;
	setPortB();
    }

    public void setPortBpin3(boolean value) {
	portB_3_High = value;
	setPortB();
    }

    public void setPortBpin4(boolean value) {
	portB_4_High = value;
	setPortB();
    }

    public void setPortBpin5(boolean value) {
	portB_5_High = value;
	setPortB();
    }

    public void setPortBpin6(boolean value) {
	portB_6_High = value;
	setPortB();
    }

    public void setPortBpin7(boolean value) {
	portB_7_High = value;
	setPortB();
    }

    /**
     * Keypad is connect on Port B I2C Expander.
     */
    public boolean startExpanderAsKeypad() {
	boolean OK = true;
	if (setRegister(0, 240)) {
	    ; // firts four bit are output, last four bit are
	      // input
	    if (setRegister(12, 240)) {
		; // firts four bit are output, last four bit are
		  // input
		if (setRegister(1, 0)) {
		    ; // Port B is output
		    if (setRegister(2, 255)) {
			; // Change of polarity - program 1 is HW 0
			if (!setRegister(19, 0)) OK = false; // Set all bits of
							     // Port B on HW low
		    } else {
			OK = false;
		    }
		} else {
		    OK = false;
		}
	    } else {
		OK = false;
	    }
	} else {
	    OK = false;
	}
	return OK;
    }

    public boolean writeRowKeypad(int number) {
	return setRegister(18, number);
    }

    public int readColumnKeypad() {
	return readRegister(18) >> 4;
    }

    /**
     * Keypad used Port B as output pin for signaling - LED, buzzer, ...
     * 
     * @param data
     */
    public boolean setOutputKeypad(int data) {
	return setRegister(19, data);
    }

    public int getAddress() {
	return this.addressDevice;
    }

}