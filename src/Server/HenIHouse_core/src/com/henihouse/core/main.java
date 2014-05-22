package com.henihouse.core;

import java.io.IOException;

import com.henihouse.control.Keypads;
import com.henihouse.control.MainControl;
import com.henihouse.devices.I2CthroughPin;
import com.henihouse.devices.Pressure_BMP085;
import com.henihouse.function.Alarm;
import com.henihouse.gui.SetVariables;
import com.henihouse.variables.Device;
import com.henihouse.variables.Logs;
import com.henihouse.variables.Variables;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;

public final class main {
    private static I2CthroughPin i2cExpanders;
    private static I2CthroughPin i2cKeypads;
    private static I2CthroughPin i2cHumiditySensor;
    private static I2CthroughPin i2cADconverter;
    private static Device	device;

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException,
	    InterruptedException {

	// get I2C bus instance
	final I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_0);
	final GpioController gpio = GpioFactory.getInstance();

	// Define I2C devices
	i2cKeypads = new I2CthroughPin(gpio, RaspiPin.GPIO_02, RaspiPin.GPIO_03);
	i2cExpanders = new I2CthroughPin(gpio, RaspiPin.GPIO_00,
		RaspiPin.GPIO_07);
	// i2cHumiditySensor = new I2CthroughPin(gpio, RaspiPin.GPIO_13,
	// RaspiPin.GPIO_12);

	i2cHumiditySensor = new I2CthroughPin(gpio, RaspiPin.GPIO_12,
		RaspiPin.GPIO_13);
	System.out.println("Start");
	/**
	GpioPinDigitalOutput SDA = gpio.provisionDigitalOutputPin(
		RaspiPin.GPIO_08, PinState.LOW);
	SDA.high();
	SDA.low();

	SDA.high();
	SDA.low();

	SDA.high();
	SDA.low();

	SDA.setMode(PinMode.DIGITAL_INPUT);
	*/
	/*
	 * GpioPinDigitalMultipurpose SDA =
	 * gpio.provisionDigitalMultipurposePin( RaspiPin.GPIO_12,
	 * PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_UP);
	 * SDA.setMode(PinMode.DIGITAL_OUTPUT); SDA.high(); Thread.sleep(500);
	 * SDA.low(); Thread.sleep(500); SDA.high(); Thread.sleep(500);
	 * SDA.low(); Thread.sleep(500);
	 * 
	 * SDA.high(); Thread.sleep(500); SDA.low(); Thread.sleep(500);
	 * 
	 * SDA.setMode(PinMode.DIGITAL_INPUT); //
	 * SDA.setPullResistance(PinPullResistance.OFF);
	 * 
	 * System.out.println("konec");
	 */
	Pressure_BMP085 pressure = new Pressure_BMP085(i2cHumiditySensor);

	GpioPinDigitalMultipurpose reset_pin = gpio
		.provisionDigitalMultipurposePin(RaspiPin.GPIO_15,
			PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_UP);

	// Declaration of instance

	SetVariables.setVariables();

	MainControl MainControl = new MainControl();
	Keypads Keypads = new Keypads();
	// ClimaticControl ClimaticControl = new ClimaticControl();

	// MainControl.initMainControl(i2cExpanders);
	// Keypads.initKeypads(i2cKeypads);
	// ClimaticControl.initClimaticControl(i2cHumiditySensor);

	final Thread control = new Thread(MainControl);
	final Thread keypads = new Thread(Keypads);

	final Thread alarm = new Thread(new Alarm());
	// final Thread climaticControl = new Thread(ClimaticControl);

	// Start of Threads
	control.start();
	// keypads.start();
	// alarm.start();
	// ServerHeniHouse server = new ServerHeniHouse();
	// climaticControl.start();

	for (;;) {
	    reset_pin.high();
	    Variables.setHouse_active(true);
	    Thread.sleep(150);
	    reset_pin.low();
	    Variables.setHouse_active(false);
	    Thread.sleep(10000);
	    if (Logs.isAlarmError()) {
		alarm.stop();
		alarm.start();
	    }
	}
    }
}
