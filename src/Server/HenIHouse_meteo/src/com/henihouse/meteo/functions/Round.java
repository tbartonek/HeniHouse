package com.henihouse.meteo.functions;

import java.text.DecimalFormat;

public class Round {
	public static String roundString(double number, int decimalNumber){
		DecimalFormat df = null;
		switch (decimalNumber) {
		case 0:
			df = new DecimalFormat("###");
			break;
		case 1:
			df = new DecimalFormat("###.#");
			break;
		case 2:
			df = new DecimalFormat("###.##");
			break;
		case 3:
			df = new DecimalFormat("###.###");
			break;
		default:
			System.out.println("Invalid decimal number.");
			df = new DecimalFormat("###");
		}
		return df.format(number);
	}
	
	public static double roundDouble (double number, int decimalNumber){
		double round;
		switch (decimalNumber) {
		case 0:
			round = Math.round(number);
			return round;
		case 1:
			round = Math.round((number*10));
			return round /10;
		case 2:
			round = Math.round((number*100));
			return round /100;
		case 3:
			round = Math.round((number*1000));
			return round /1000;
		default:
			System.out.println("Invalid decimal number.");
			return 0;
		}
	}
}
