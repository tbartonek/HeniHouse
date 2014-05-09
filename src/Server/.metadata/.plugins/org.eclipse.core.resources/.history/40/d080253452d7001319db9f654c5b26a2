package com.henihouse.control;

import java.util.ArrayList;
import java.util.List;

import com.henihouse.devices.I2CthroughPin;
import com.henihouse.devices.i2cBitExpander;
import com.henihouse.variables.Expanders;
import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;
import com.henihouse.variables.OutputVariables;

public class Output {

    private List<i2cBitExpander> i2cBitExpanders = new ArrayList<i2cBitExpander>();
    private i2cBitExpander       i2cBitExpander;

    public void initOutput(I2CthroughPin i2c) {
	try {
	    for (int i = 0; i < Expanders.getAdressOutputExpanders().size(); i++) {
		if (Expanders.getAddressOutputExpanders(i) != null) {
		    i2cBitExpander = new i2cBitExpander();
		    if (i2cBitExpander.initi2cBitExpander(i2c,
			    Expanders.getAddressOutputExpanders(i), true, true)) {
			i2cBitExpanders.add(i, i2cBitExpander);

			Logs.addLog(new Log("Info", "Output expander",
				"with address "
					+ Expanders
						.getAddressOutputExpanders(i)
					+ " was add."));
		    } else {
			Logs.addLog(new Log("Warning", "Output expander",
				"with address "
					+ Expanders
						.getAddressOutputExpanders(i)
					+ " wasn't add."));
			Logs.setOutputWarning(true);
		    }
		}

	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    Logs.addLog(new Log("Error", "Output expander",
		    "during initialization."));
	    Logs.setOutputError(true);
	}
    }

    public void controlOutput() {
	if (OutputVariables.isChange()) {
	    // System.out.println("Change on output expander.");
	    for (int index = 0; index < i2cBitExpanders.size(); index++) {
		if (i2cBitExpanders.get(index) != null) {
		    int data = 255;
		    int addressExpander = i2cBitExpanders.get(index)
			    .getAddress();
		    System.out.println(addressExpander);
		    for (int pin = 0; pin < 8; pin++) {
			if (OutputVariables
				.existID(addressExpander + "_" + pin)) {
			    if (OutputVariables.getValue(addressExpander + "_"
				    + pin)) {
				data -= Math.pow(2, pin);
			    }
			}
		    }
		    if (!i2cBitExpanders.get(index).setPortA(data)) {
			Logs.addLog(new Log("Warning", "Output expander",
				"with address " + addressExpander
					+ " don't communicate."));
			Logs.setOutputWarning(true);
		    }

		    data = 255;
		    for (int pin = 8; pin < 16; pin++) {
			if (OutputVariables
				.existID(addressExpander + "_" + pin)) {
			    if (OutputVariables.getValue(addressExpander + "_"
				    + pin)) {
				data -= Math.pow(2, pin);
			    }
			}
		    }
		    if (i2cBitExpanders.get(index).setPortB(data)) {
			Logs.addLog(new Log("Warning", "Output expander",
				"with address " + addressExpander
					+ " don't communicate."));
			Logs.setOutputWarning(true);
		    }
		}
	    }
	    OutputVariables.setChange(false);
	}
    }
}
