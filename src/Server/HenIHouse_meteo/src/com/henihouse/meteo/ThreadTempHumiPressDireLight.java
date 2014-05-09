package com.henihouse.meteo;

import com.henihouse.devices.HumiditySensor;
import com.henihouse.devices.I2CthroughPin;
import com.henihouse.devices.Pressure_BMP085;
import com.henihouse.meteo.functions.ReadDirection;
import com.henihouse.meteo.functions.Round;
import com.henihouse.meteo.variables.Direction;
import com.henihouse.meteo.variables.Humidity;
import com.henihouse.meteo.variables.Temperature;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class ThreadTempHumiPressDireLight implements Runnable {

	private HumiditySensor humiditySensor;
	private Pressure_BMP085 pressureSensor;
	private GpioPinDigitalInput dire_pin;
	private GpioPinDigitalOutput volt_pin;
	private int measureTimeMINUS = 2000; 
	
	private int measureTime = 10; //[s] default 60s
	
	public void init(I2CthroughPin i2cHumiditySensor, GpioPinDigitalInput direction_pin, GpioPinDigitalOutput voltage_pin) {
			this.humiditySensor = new HumiditySensor(i2cHumiditySensor, "3.5V");
			this.pressureSensor = new Pressure_BMP085(i2cHumiditySensor);
			this.dire_pin = direction_pin;
			this.volt_pin = voltage_pin;
	    }
	
	public void run() {
		for (;;) {
			try {
			Temperature.addValue(Round.roundDouble(humiditySensor.readTemperature(), 2));
			System.out.println(Temperature.getActualValue());
			Humidity.addValue(Round.roundDouble(humiditySensor.readHumidity(), 1));
			System.out.println(Humidity.getActualValue());
			Direction.addValue(ReadDirection.readDirection(dire_pin, volt_pin));
			System.out.println(Direction.getActualValue());
			System.out.println(pressureSensor.getTemperature());
			System.out.println(pressureSensor.getPressure(pressureSensor.getTemperature(), 506));
			
			
			
				Thread.sleep(measureTime * 1000 -measureTimeMINUS);
			    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}
	}
	
	
}
