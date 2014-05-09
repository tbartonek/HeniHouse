package com.henihouse.meteo.functions;

import java.util.ArrayList;
import java.util.List;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class ReadDirection {
	
	public static int readDirection(GpioPinDigitalInput dire_pin, GpioPinDigitalOutput volt_pin) throws InterruptedException {
		List<Integer> values = new ArrayList<Integer>();

		volt_pin.high();
		Thread.sleep(600);

		for (int i = 0; i < 1000; i++) {
			if (dire_pin.isHigh() == true) {
				values.add(1);
			} else {
				values.add(0);
			}

		}

		volt_pin.low();

		boolean one = false;
		int numberOne = 0;
		int numberIndex = 0;
		int data = 0;
		for (int index = 0; index < values.size(); index++) {
			if (!one && values.get(index) == 1) {
				one = true;
			}
			if (one) {
				if (values.get(index) == 1) {
					++numberOne;
				} else {
					if (numberOne > 3) {

						data += (int) (128 / Math.pow(2, numberIndex));
						++numberIndex;
					} else {
						++numberIndex;
					}
					numberOne = 0;
					one = false;
				}
			}

		}

		if ((data >> 4) == 10) { // 10D = B1010
			return data & 15;
		} else {
			return 16;
		}
	}
}
