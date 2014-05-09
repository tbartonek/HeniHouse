package com.henihouse.variables;

import java.util.Hashtable;
import java.util.Map;

/**
 * Class for definy active/deactive output
 */

public class OutputVariables {
    private static boolean	      change = false;
    private static final Object	 lock   = new Object();
    private static Map<String, Boolean> output = new Hashtable<String, Boolean>();

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

    public static Map<String, Boolean> getOutput() {
	return output;
    }

    public static void putValue(String id, boolean value) {
	output.put(id, value);
    }

    public static boolean getValue(String id) {
	return output.get(id);
    }

    public static boolean existID(String id) {
	return output.containsKey(id);
    }

}
