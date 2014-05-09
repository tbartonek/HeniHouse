package com.henihouse.variables;

import java.util.ArrayList;
import java.util.List;

public class Expanders {
    private static List<Integer> addressInputExpanders  = new ArrayList<Integer>();
    private static List<Integer> addressOutputExpanders = new ArrayList<Integer>();

    public static Integer getAddressInputExpanders(int index) {
	return addressInputExpanders.get(index);
    }

    public static List<Integer> getAddressInputExpanders() {
	return addressInputExpanders;
    }

    public static void addAddressInputExpanders(int address) {
	addressInputExpanders.add(address);
    }

    public static Integer getAddressOutputExpanders(int index) {
	return addressOutputExpanders.get(index);
    }

    public static void addAddressOutputExpanders(int address) {
	addressOutputExpanders.add(address);
    }

    public static List<Integer> getAdressOutputExpanders() {
	return addressOutputExpanders;
    }
}
