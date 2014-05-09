package com.henihouse.variables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Log {
	private Date time;
	private String device;
	private String text;
	private boolean error = false;
	private boolean warning = false;
	private boolean info = false;

	/**
	 * Constructor for new log with parameters device, text and type - ERROR,Warning,Info
	 * @param device
	 * @param text
	 * @param type
	 */
	public Log(String type, String device, String text) {
		this.time = Calendar.getInstance().getTime();
		this.device = device;
		this.text = text;
		if(type.equalsIgnoreCase("ERROR"))this.error = true;
		if(type.equalsIgnoreCase("Warning")) this.warning = true;
		if(type.equalsIgnoreCase("Info")) this.info = true;
		// muze se smazat po odskouseni
		if(!error&&!warning&&!info)System.out.println("Wrong type of log");
		System.out.println(getLog());
	}

	public String getLog() {
		String log = "";
		String typeLog = "";
		if (error) {
			typeLog = "ERROR - ";
		} else if (warning) {
			typeLog = "WARNING - ";
		} else {
			typeLog = "Info - ";
		}
		log = typeLog + getTimeLog() + ": " + this.device + " - " + this.text;      
		return log;
	}
	
	private String getTimeLog(){
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy HH:mm:ss");
		return dateFormat.format(this.time);
		
	}

	public Date getTime() {
		return time;
	}

	public String getDevice() {
		return device;
	}

	public String getText() {
		return text;
	}

	public boolean isError() {
		return error;
	}

	public boolean isWarning() {
		return warning;
	}

	public boolean isInfo() {
		return info;
	}

}
