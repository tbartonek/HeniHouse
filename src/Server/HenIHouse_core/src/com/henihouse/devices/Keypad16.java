package com.henihouse.devices;

import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;

public class Keypad16 {

    private int	    address;
    private i2cBitExpander keypadInterface;
    private String	 keys;
    private AD_MCP3221     lightMeasure;

    public boolean	 alarm_active     = false;
    public boolean	 alarm_alert      = false;
    public boolean	 alarm_servis     = false;
    public boolean	 keypad_buzzer    = false;
    public boolean	 house_active     = false;
    public boolean	 house_heating    = false;
    public boolean	 house_servis     = false;
    public boolean	 keypad_backlight = false;

    public boolean activateKeypad16(I2CthroughPin i2c, int address) {
	this.lightMeasure = new AD_MCP3221(i2c);

	this.address = address;
	this.keys = "";
	try {
	    keypadInterface = new i2cBitExpander();
	    return keypadInterface.initi2cBitExpander(i2c, address);
	} catch (InterruptedException e) {
	    Logs.addLog(new Log("Error", "Keypad",
		    "during initialization I2C Expander for Keypad."));
	    Logs.setKeypadError(true);
	    // e.printStackTrace();
	}
	return false;

    }

    public void LightMeasure() {
	double value = lightMeasure.readDigitalValue();
	System.out.println(value);
	if (value < 1 && keypad_backlight == false) {
	    keypad_backlight = true;
	    signalling();
	}
	if (value > 1 && keypad_backlight == true) {
	    keypad_backlight = false;
	    signalling();
	}
    }

    public String getKey() {
	String key = "";

	for (int i = 0; i < 4; i++) {
	    keypadInterface.writeRowKeypad((int) (15 - Math.pow(2, i)));
	    int data = 0;
	    if ((data = keypadInterface.readColumnKeypad()) != 15) {
		if ((data & (1)) == 0) {
		    switch (i) {
			case 0:
			    key = "1";
			    break;
			case 1:
			    key = "2";
			    break;
			case 2:
			    key = "3";
			    break;
			case 3:
			    key = "A";
			    break;
		    }
		};
		if ((data & (1 << 1)) == 0) {
		    switch (i) {
			case 0:
			    key = "4";
			    break;
			case 1:
			    key = "5";
			    break;
			case 2:
			    key = "6";
			    break;
			case 3:
			    key = "B";
			    break;
		    }
		};
		if ((data & (1 << 2)) == 0) {
		    switch (i) {
			case 0:
			    key = "7";
			    break;
			case 1:
			    key = "8";
			    break;
			case 2:
			    key = "9";
			    break;
			case 3:
			    key = "C";
			    break;
		    }
		};
		if ((data & (1 << 3)) == 0) {
		    switch (i) {
			case 0:
			    key = "*";
			    break;
			case 1:
			    key = "0";
			    break;
			case 2:
			    key = "#";
			    break;
			case 3:
			    key = "D";
			    break;
		    }
		};
	    }
	}
	return key;

    }

    public void signalling() {
	int data = 255;

	if (alarm_active) data -= 1;
	if (alarm_alert) data -= 2;
	if (alarm_servis) data -= 4;
	if (keypad_buzzer) data -= 8;
	if (keypad_backlight) data -= 16;
	if (house_servis) data -= 32;
	if (house_heating) data -= 64;
	if (house_active) data -= 128;
	keypadInterface.setOutputKeypad(data);
    }

    /**
     * Blink with parameters: number of blink, time of blink
     * 
     * @param number
     * @param time
     */
    public void blinkAlarmActive(int number, int time, boolean buzzer) {
	for (int i = 0; i < number; i++) {
	    try {
		alarm_active = true;
		if (buzzer) keypad_buzzer = true;
		signalling();
		Thread.sleep(time);
		alarm_active = false;
		if (buzzer) keypad_buzzer = false;
		signalling();
		Thread.sleep(time);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}
    }

    /**
     * Blink with parameters: number of blink, time of blink
     * 
     * @param number
     * @param time
     */
    public void blinkAlarmAlert(int number, int time, boolean buzzer) {
	for (int i = 0; i < number; i++) {

	    try {
		alarm_alert = true;
		if (buzzer) keypad_buzzer = true;
		signalling();
		Thread.sleep(time);
		alarm_alert = false;
		if (buzzer) keypad_buzzer = false;
		signalling();
		Thread.sleep(time);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}
    }

    /**
     * Blink with parameters: number of blink, time of blink
     * 
     * @param number
     * @param time
     */
    public void blinkAlarmServis(int number, int time, boolean buzzer) {
	for (int i = 0; i < number; i++) {
	    try {
		alarm_servis = true;
		if (buzzer) keypad_buzzer = true;
		signalling();
		Thread.sleep(time);
		alarm_servis = false;
		if (buzzer) keypad_buzzer = false;
		signalling();
		Thread.sleep(time);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    /**
     * Blink with parameters: number of blink, time of blink
     * 
     * @param number
     * @param time
     */
    public void blinkBuzzer(int number, int time) {
	for (int i = 0; i < number; i++) {

	    try {
		keypad_buzzer = true;
		signalling();
		Thread.sleep(time);
		keypad_buzzer = false;
		signalling();
		Thread.sleep(time);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    /**
     * Blink with parameters: number of blink, time of blink
     * 
     * @param number
     * @param time
     */
    public void blinkHouseActive(int number, int time, boolean buzzer) {
	for (int i = 0; i < number; i++) {

	    try {
		house_active = true;
		if (buzzer) keypad_buzzer = true;
		signalling();
		Thread.sleep(time);
		house_active = false;
		if (buzzer) keypad_buzzer = false;
		signalling();
		Thread.sleep(time);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    /**
     * Blink with parameters: number of blink, time of blink
     * 
     * @param number
     * @param time
     */
    public void blinkHouseHeating(int number, int time, boolean buzzer) {
	for (int i = 0; i < number; i++) {

	    try {
		house_heating = true;
		if (buzzer) keypad_buzzer = true;
		signalling();
		Thread.sleep(time);
		house_heating = false;
		if (buzzer) keypad_buzzer = false;
		signalling();
		Thread.sleep(time);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    /**
     * Blink with parameters: number of blink, time of blink
     * 
     * @param number
     * @param time
     */
    public void blinkHouseServis(int number, int time, boolean buzzer) {
	for (int i = 0; i < number; i++) {

	    try {
		house_servis = true;
		if (buzzer) keypad_buzzer = true;
		signalling();
		Thread.sleep(time);
		house_servis = false;
		if (buzzer) keypad_buzzer = false;
		signalling();
		Thread.sleep(time);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    public String getKeys() {
	return keys;
    }

    public void setKeys(String keys) {
	this.keys = keys;
    }

    public int getAddress() {
	return address;
    }
}
