package com.henihouse.meteo.variables;

import java.util.ArrayList;
import java.util.List;

import com.henihouse.meteo.functions.Round;

public class Direction {
	private static int maxValues = 60 * 24; // 60 minut. 24 hodin = max. number
	// values is for 1 day
	private static List<Integer> values = new ArrayList<Integer>(maxValues);

	public static void addValue(int value) {
		if (values.size() < maxValues) {
			values.add(0, value);
		} else {
			values.add(0, value);
			values.remove(maxValues);
		}
	}

	public static int getActualValue() {
		return values.get(0);
	}

	public static int getAverageValue(int items) {
		if(items == 0) return values.get(0);
		int average = 0;
		if (values.size() != items && values.size() < items) {
			items = values.size();
			System.out.println("Average count only from " + values.size()
					+ " values.");
		}

		for (int i = 0; i < items; i++) {
			average += values.get(i);
		}
		return (int) Round.roundDouble((average / items), 0);
	}
}
