package com.henihouse.variables;

public class Variables {
    private static double  levelLight_keypad    = 1;
    private static double  levelLight_front     = 2;
    private static double  levelLight_rear      = 1.5;
    private static long    timeRepeatForControl = 1000;
    private static boolean house_active	 = false;

    public static double getLevelLight_keypad() {
	return levelLight_keypad;
    }

    public static void setLevelLight_keypad(double levelLight_keypad) {
	Variables.levelLight_keypad = levelLight_keypad;
    }

    public static double getLevelLight_front() {
	return levelLight_front;
    }

    public static void setLevelLight_front(double levelLight_front) {
	Variables.levelLight_front = levelLight_front;
    }

    public static double getLevelLight_rear() {
	return levelLight_rear;
    }

    public static void setLevelLight_rear(double levelLight_rear) {
	Variables.levelLight_rear = levelLight_rear;
    }

    public static long getTimeRepeatForControl() {
	return timeRepeatForControl;
    }

    public static void setTimeRepeatForControl(long timeRepeatForControl) {
	Variables.timeRepeatForControl = timeRepeatForControl;
    }

    public static boolean isHouse_active() {
	return house_active;
    }

    public static void setHouse_active(boolean house_active) {
	Variables.house_active = house_active;
    }

}
