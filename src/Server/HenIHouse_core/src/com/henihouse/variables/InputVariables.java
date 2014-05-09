package com.henihouse.variables;

import java.util.Hashtable;
import java.util.Map;

public class InputVariables {
    private static boolean	      change = false;
    private static final Object	 lock   = new Object();
    private static Map<String, Boolean> input  = new Hashtable<String, Boolean>();

    public static boolean isChange() {
	synchronized (lock) {
	    return change;
	}
    }

    public static void setChange(boolean value) {
	synchronized (lock) {
	    change = value;
	}
    }

    public static Map<String, Boolean> getInput() {
	return input;
    }

    public static void putValue(String id, boolean value) {
	input.put(id, value);
    }

    public static boolean getValue(String id) {
	return input.get(id);
    }

}
