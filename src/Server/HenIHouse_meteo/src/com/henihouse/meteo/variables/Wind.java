package com.henihouse.meteo.variables;

import java.util.ArrayList;
import java.util.List;

public class Wind {
	private static int maxValues = 60 * 24; // 60 minut. 24 hodin = max. number
											// values is for 1 day
	private static List<Double> values = new ArrayList<Double>(maxValues);

	public static void addValue(Double value) {
		if (values.size() < maxValues) {
			values.add(0, value);
		} else {
			values.add(0, value);
			values.remove(maxValues);
		}
	}

	public static Double getActualValue() {
		return values.get(0);
	}

	public static Double getAverageValue(int items) {
		if(items == 0) return values.get(0);
		double average = 0;
		if (values.size() != items && values.size() < items) {
			items = values.size();
			System.out.println("Average count only from " + values.size()
					+ " values.");
		}

		for (int i = 0; i < items; i++) {
			average += values.get(i);
		}
		return average / items;
	}
}
