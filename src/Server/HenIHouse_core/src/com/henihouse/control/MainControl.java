package com.henihouse.control;

import com.henihouse.devices.I2CthroughPin;
import com.henihouse.variables.Variables;

public class MainControl implements Runnable {

    private Input  input;
    private Output output;
    private long   timeRepeat = Variables.getTimeRepeatForControl();

    public void initMainControl(I2CthroughPin i2cExpander) {
	this.input = new Input();
	this.input.initInput(i2cExpander);
	this.output = new Output();
	this.output.initOutput(i2cExpander);
    }

    public void run() {
	for (;;) {
	    input.controlInput();
	    output.controlOutput();
	    try {
		Thread.sleep(timeRepeat);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

    }

}
