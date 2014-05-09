package com.henihouse.control;

import java.util.ArrayList;
import java.util.List;

import com.henihouse.devices.I2CthroughPin;
import com.henihouse.devices.i2cBitExpander;
import com.henihouse.variables.Expanders;
import com.henihouse.variables.InputVariables;
import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;

public class Input {

    private List<i2cBitExpander> i2cBitExpanders;
    private i2cBitExpander       i2cBitExpander;
    private List<Integer>	data;
    private int		  portA;
    private int		  portB;
    private int		  addressExpander;

    public void initInput(I2CthroughPin i2c) {
	i2cBitExpanders = new ArrayList<i2cBitExpander>();
	data = new ArrayList<Integer>();
	try {
	    for (int i = 0; i < Expanders.getAddressInputExpanders().size(); i++) {
		if (Expanders.getAddressInputExpanders(i) != null) {
		    i2cBitExpander = new i2cBitExpander();
		    if (i2cBitExpander
			    .initi2cBitExpander(i2c,
				    Expanders.getAddressInputExpanders(i),
				    false, false)) {
			i2cBitExpanders.add(i, i2cBitExpander);
			data.add(0);
			data.add(0);
			for (int pin = 0; pin < 16; pin++) {
			    InputVariables.putValue(
				    Expanders.getAddressInputExpanders(i) + "_"
					    + pin, false);
			}
			Logs.addLog(new Log("Info", "Input expander",
				"with address "
					+ Expanders.getAddressInputExpanders(i)
					+ " was add."));
		    } else {
			Logs.addLog(new Log("Warning", "Input expander",
				"with address "
					+ Expanders.getAddressInputExpanders(i)
					+ " wasn't add."));
			Logs.setInputWarning(true);
		    }
		}
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    Logs.addLog(new Log("Error", "Input expander",
		    "during initialization."));
	    Logs.setInputError(true);
	}
    }

    public void controlInput() {
	int indexData = 0;
	for (int index = 0; index < i2cBitExpanders.size(); index++) {
	    if (i2cBitExpanders.get(index) != null) {
		portA = i2cBitExpanders.get(index).readPortA();
		portB = i2cBitExpanders.get(index).readPortB();
		addressExpander = i2cBitExpanders.get(index).getAddress();

		if (portA != data.get(indexData)) {
		    InputVariables.setChange(true);
		    data.set(indexData, portA);
		    for (int pin = 0; pin < 8; pin++) {
			if ((portA & (1 << pin)) == 0) {
			    if (InputVariables.getValue(addressExpander + "_"
				    + pin) != false) {
				// System.out.println("Bit " + addressExpander
				// + "_" + pin + " set false.");
				InputVariables.putValue(addressExpander + "_"
					+ pin, false);
			    }
			} else {
			    if (InputVariables.getValue(addressExpander + "_"
				    + pin) != true) {
				// System.out.println("Bit " + addressExpander
				// + "_" + pin + " set true.");
				InputVariables.putValue(addressExpander + "_"
					+ pin, true);
			    }
			}
		    }
		}
		++indexData;
		if (portB != data.get(indexData)) {
		    InputVariables.setChange(true);
		    data.set(indexData, portB);
		    for (int pin = 0; pin < 8; pin++) {
			if ((portB & (1 << pin)) == 0) {
			    if (InputVariables.getValue(addressExpander + "_"
				    + (pin + 8)) != false) {
				// System.out.println("Bit " + addressExpander
				// + "_" + (pin + 8) + " set false.");
				InputVariables.putValue(addressExpander + "_"
					+ (pin + 8), false);
			    }
			} else {
			    if (InputVariables.getValue(addressExpander + "_"
				    + (pin + 8)) != true) {
				// System.out.println("Bit " + addressExpander
				// + "_" + (pin + 8) + " set true.");
				InputVariables.putValue(addressExpander + "_"
					+ (pin + 8), true);
			    }
			}
		    }
		}
		++indexData;
	    }
	}
    }
}