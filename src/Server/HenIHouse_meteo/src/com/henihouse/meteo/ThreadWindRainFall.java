package com.henihouse.meteo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.henihouse.meteo.functions.GetCurrentTime;
import com.henihouse.meteo.functions.Round;
import com.henihouse.meteo.variables.RainFall;
import com.henihouse.meteo.variables.Wind;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ThreadWindRainFall implements Runnable {

	private GpioPinDigitalInput wind;
	private GpioPinDigitalInput rainFall;
	private int numberWind;
	private int numberRainFall;
	private double lenghtArm = 0.096; //[m] default 96 mm
	private int measureTime = 10; //[s] default 60s
	private double rainFallRadius = 4.5; //[cm] default 4.5 cm, d=9cm  
	private double rainFallVolume;
	private double windVolume;

	public void init(GpioPinDigitalInput wind, GpioPinDigitalInput rainFall) {
		this.wind = wind;
		this.rainFall = rainFall;

		numberWind = 0;
		numberRainFall = 0;

		GpioPinListenerDigital listenerWind = new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent event) {
				if (event.getState() == PinState.HIGH) {
					++numberWind;
					//System.out.println("Rychlost vetru: " + numberWind);
				}
			}
		};

		wind.addListener(listenerWind);

		GpioPinListenerDigital listenerRainFall = new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent event) {
				if (event.getState() == PinState.HIGH) {
					++numberRainFall;
					//System.out.println("Srazky: " + numberRainFall);
				}
			}
		};

		rainFall.addListener(listenerRainFall);

		windVolume = (6 * Math.PI * lenghtArm * 3.6) / (measureTime);
		rainFallVolume = 0.004 / ((Math.PI * rainFallRadius*rainFallRadius)/10000);
	}

	public void run() {
		for (;;) {
			try {
				double windSpeed = windVolume*numberWind;
				numberWind = 0;

				double rainFall = rainFallVolume * numberRainFall;
				numberRainFall = 0;

				Wind.addValue(Round.roundDouble(windSpeed, 1));
				RainFall.addValue(Round.roundDouble(rainFall, 2));
				
				Thread.sleep((measureTime * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
