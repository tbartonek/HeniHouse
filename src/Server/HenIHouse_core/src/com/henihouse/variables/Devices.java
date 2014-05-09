package com.henihouse.variables;

import java.util.ArrayList;
import java.util.List;

public class Devices {
    // Devices for alarm
    private static List<Device> moveDetectors     = new ArrayList<Device>();
    private static List<Device> soundDetectors    = new ArrayList<Device>();
    private static List<Device> fireDetectors     = new ArrayList<Device>();
    private static List<Device> magneticDetectors = new ArrayList<Device>();
    private static List<Device> tmpDetectors      = new ArrayList<Device>();
    private static List<Device> otherDetectors    = new ArrayList<Device>();
    private static List<Device> keypads	   = new ArrayList<Device>();
    private static List<Device> sirens	    = new ArrayList<Device>();

    private static List<Device> lights	    = new ArrayList<Device>();
    private static List<Device> sockets	   = new ArrayList<Device>();
    private static List<Device> others	    = new ArrayList<Device>();

    private static Device       heater;

    public static void addMoveDetector(Device moveDetector) {
	moveDetectors.add(moveDetector);
    }

    public static Device getMoveDetector(int index) {
	return moveDetectors.get(index);
    }

    public static List<Device> getMoveDetectors() {
	return moveDetectors;
    }

    public static void removeMoveDetector(int index) {
	moveDetectors.remove(index);
    }

    public static void addSoundDetector(Device soundDetector) {
	soundDetectors.add(soundDetector);
    }

    public static Device getSoundDetector(int index) {
	return soundDetectors.get(index);
    }

    public static List<Device> getSoundDetectors() {
	return soundDetectors;
    }

    public static void removeSoundDetector(int index) {
	soundDetectors.remove(index);
    }

    public static void addFireDetector(Device fireDetector) {
	fireDetectors.add(fireDetector);
    }

    public static Device getFireDetector(int index) {
	return fireDetectors.get(index);
    }

    public static List<Device> getFireDetectors() {
	return fireDetectors;
    }

    public static void removeFireDetector(int index) {
	fireDetectors.remove(index);
    }

    public static void addMagneticDetector(Device magneticDetector) {
	magneticDetectors.add(magneticDetector);
    }

    public static Device getMagneticDetector(int index) {
	return magneticDetectors.get(index);
    }

    public static List<Device> getMagneticDetectors() {
	return magneticDetectors;
    }

    public static void removeMagneticDetector(int index) {
	magneticDetectors.remove(index);
    }

    public static void addTmpDetector(Device tmpDetector) {
	tmpDetectors.add(tmpDetector);
    }

    public static Device getTmpDetector(int index) {
	return tmpDetectors.get(index);
    }

    public static List<Device> getTmpDetectors() {
	return tmpDetectors;
    }

    public static void removeTmpDetector(int index) {
	tmpDetectors.remove(index);
    }

    public static void addOtherDetector(Device otherDetector) {
	otherDetectors.add(otherDetector);
    }

    public static Device getOtherDetector(int index) {
	return otherDetectors.get(index);
    }

    public static List<Device> getOtherDetectors() {
	return otherDetectors;
    }

    public static void removeOtherDetector(int index) {
	otherDetectors.remove(index);
    }

    public static void addKeypad(Device keypad) {
	keypads.add(keypad);
    }

    public static Device getKeypad(int index) {
	return keypads.get(index);
    }

    public static List<Device> getKeypads() {
	return keypads;
    }

    public static void removeKeypad(int index) {
	keypads.remove(index);
    }

    public static void addSirens(Device siren) {
	sirens.add(siren);
    }

    public static Device getSiren(int index) {
	return sirens.get(index);
    }

    public static List<Device> getSirens() {
	return sirens;
    }

    public static void removeSiren(int index) {
	sirens.remove(index);
    }

    public static void addLight(Device light) {
	lights.add(light);
    }

    public static Device getLight(int index) {
	return lights.get(index);
    }

    public static List<Device> getLights() {
	return lights;
    }

    public static void removeLight(int index) {
	lights.remove(index);
    }

    public static void addSocket(Device socket) {
	sockets.add(socket);
    }

    public static Device getSocket(int index) {
	return sockets.get(index);
    }

    public static List<Device> getSockets() {
	return sockets;
    }

    public static void removeSocket(int index) {
	sockets.remove(index);
    }

    public static void addOther(Device other) {
	others.add(other);
    }

    public static Device getOther(int index) {
	return others.get(index);
    }

    public static List<Device> getOthers() {
	return others;
    }

    public static void removeOther(int index) {
	others.remove(index);
    }

    public static Device getHeater() {
	return heater;
    }

    public static void setHeater(Device heater) {
	Devices.heater = heater;
    }

}
