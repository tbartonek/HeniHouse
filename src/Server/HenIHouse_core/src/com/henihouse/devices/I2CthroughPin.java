package com.henihouse.devices;

import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

public class I2CthroughPin {

    private GpioPinDigitalMultipurpose SDA;
    private GpioPinDigitalOutput       SCL;

    /**
     * Constructor for setting communication - GPIO, pin SDA and pin SCL.
     * 
     * @param GPIO
     * @param pin_SDA
     * @param pin_SCL
     */

    public I2CthroughPin(GpioController GPIO, Pin pin_SDA, Pin pin_SCL) {
	SDA = GPIO.provisionDigitalMultipurposePin(pin_SDA,
		PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_UP);
	SDA.high();
	SCL = GPIO.provisionDigitalOutputPin(pin_SCL, PinState.LOW);
	try {
	    stop();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    Logs.addLog(new Log("Error", "Expander", "during stop procedure."));
	    e.printStackTrace();
	}
    }

    /**
     * Set SDA as Input.
     */
    private void pinSDAasInput() {
	SDA.setMode(PinMode.DIGITAL_INPUT);
    }

    /**
     * Set SDA as Output.
     */
    private void pinSDAasOutput() {
	SDA.setMode(PinMode.DIGITAL_OUTPUT);
	SDA.low();

    }

    /**
     * Start sequence for communication with I2C device.
     * 
     * @throws InterruptedException
     */

    public void start() throws InterruptedException {
	SCL.high();
	SDA.low();
	SCL.low();
    }

    public void start_humidity() throws InterruptedException {
	SCL.low();
	SDA.high();

	SCL.high();
	SDA.low();
	SCL.low();

	SCL.high();
	SDA.high();
	SCL.low();
	SDA.low();
    }

    /**
     * Stop sequence for stop communication with I2C device.
     * 
     * @throws InterruptedException
     */
    public void stop() throws InterruptedException {
	SDA.low();
	SCL.high();

	// Stop
	SDA.high();
	SCL.low();
    }

    /**
     * Write sequence for write 8 bits to I2C device. If device answered, return
     * true, other way false.
     * 
     * @param data
     * @throws InterruptedException
     * @return OK
     */
    public boolean write(int data) throws InterruptedException {
	boolean OK;
	for (int i = 0; i < 8; i++) {
	    if ((data & (1 << 7 - i)) > 0) {
		SDA.high();
		// System.out.println(1);
	    } else {
		SDA.low();
		// System.out.println(0);
	    }
	    // write bit
	    SCL.high();
	    // Thread.sleep(1);
	    SCL.low();
	    // Thread.sleep(1);
	}
	pinSDAasInput();

	int i = 0;
	while (!SDA.isLow() && i < 100) {
	    ++i;
	}
	if (SDA.isLow()) {
	    OK = true;
	} else {
	    OK = false;
	}
	// SDA.low();
	// System.out.println("ACK ok");
	// ACK OK
	SCL.high();
	SCL.low();
	pinSDAasOutput();
	return OK;
    }

    /**
     * Read sequence for read 8 bits from I2C device.
     * 
     * @param lastByte
     * @return
     * @throws InterruptedException
     */
    public int read(boolean lastByte) throws InterruptedException {
	int data = 0;
	pinSDAasInput();
	for (int i = 0; i < 8; i++) {
	    if (SDA.isHigh()) data += (int) (128 / Math.pow(2, i));
	    // ok read bit
	    SCL.high();
	    SCL.low();

	}
	// ACK byte OK
	pinSDAasOutput();
	if (lastByte) {
	    SDA.high();
	} else {
	    SDA.low();
	};
	// write bit
	SCL.high();
	SCL.low();

	return data;
    }
}
