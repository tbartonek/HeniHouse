package com.henihouse.gui;

import com.henihouse.variables.Device;
import com.henihouse.variables.Devices;
import com.henihouse.variables.Expanders;
import com.henihouse.variables.OutputVariables;

public class SetVariables {
    private static Device device;

    public static void setVariables() {
	// Expanders
	Expanders.addAddressInputExpanders(1);

	Expanders.addAddressOutputExpanders(0); // office Partyzanska 2

	// Keypad
	device = new Device("Keypad - dooor", "0", false);
	Devices.addKeypad(device);

	// Output
	// Light
	device = new Device("Light - dooor", "0_0", true);
	Devices.addLight(device);
	OutputVariables.putValue("0_0", false);

	device = new Device("Light - rear", "0_1", true);
	Devices.addLight(device);
	OutputVariables.putValue("0_1", false);

	device = new Device("Light - show", "0_2", false);
	Devices.addLight(device);
	OutputVariables.putValue("0_2", false);

	device = new Device("Light - out table", "0_3", false);
	Devices.addLight(device);
	OutputVariables.putValue("0_3", false);

	// Heater
	device = new Device("Electric heater", "0_4", false);
	Devices.setHeater(device);
	OutputVariables.putValue("0_4", false);

	// Alarm - siren
	device = new Device("Alarm - out siren", "0_5", true);
	Devices.addSirens(device);
	OutputVariables.putValue("0_5", false);

	device = new Device("Alarm - in siren", "0_6", true);
	Devices.addSirens(device);
	OutputVariables.putValue("0_6", false);

	// Input
	// Alarm - detector - move
	device = new Device("Door", "1_0", 30000, true);
	Devices.addMoveDetector(device);

	device = new Device("Rear", "1_2", 30000, true);
	Devices.addMoveDetector(device);

	device = new Device("Corridor", "1_5", 0, true);
	Devices.addMoveDetector(device);

	// Alarm - detector - sound
	device = new Device("Rear", "1_3", 0, true);
	Devices.addSoundDetector(device);

	device = new Device("Corridor", "1_6", 0, true);
	Devices.addSoundDetector(device);

	// Alarm - detector - TMP
	device = new Device("Door", "1_1", 0, true);
	Devices.addTmpDetector(device);

	device = new Device("Rear", "1_4", 0, true);
	Devices.addTmpDetector(device);

	device = new Device("Corridor", "1_7", 0, true);
	Devices.addTmpDetector(device);

	device = new Device("Siren", "1_9", 0, true);
	Devices.addTmpDetector(device);

	device = new Device("Backup accumulator", "1_10", 0, true);
	Devices.addTmpDetector(device);

	device = new Device("Fire", "1_12", 0, true);
	Devices.addTmpDetector(device);

	// Alarm - magnetic
	device = new Device("Door - rear", "1_8", 0, true);
	Devices.addMagneticDetector(device);

	// Fire detector
	device = new Device("Fire", "1_11", 0, true);
	Devices.addFireDetector(device);

    }
}
