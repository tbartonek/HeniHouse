package com.henihouse.meteo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.handler.filter.GZipFilter;
import lipermi.net.IServerListener;
import lipermi.net.Server;

import com.henihouse.interfaces.meteo.Interfaces;
import com.henihouse.interfaces.variables.Variables;
import com.henihouse.meteo.variables.Direction;
import com.henihouse.meteo.variables.Humidity;
import com.henihouse.meteo.variables.RainFall;
import com.henihouse.meteo.variables.Temperature;
import com.henihouse.meteo.variables.Wind;
import com.henihouse.variables.Log;
import com.henihouse.variables.Logs;

public class ThreadServer implements Runnable, Interfaces{

	private int measureTime = 60; //[s] default 60s
	CallHandler callHandler;
	
	public void init(){
	    
		Logs.addLog(new Log("Info", "Server", "Creating."));
		Server server = new Server();
		callHandler = new CallHandler();

		try {
		    callHandler.registerGlobal(Interfaces.class, this);
		    server.addServerListener(new IServerListener() {
			public void clientConnected(Socket socket) {
			    Logs.addLog(new Log("Info", "Server", "Client connected: "
				    + socket.getInetAddress()));

			}

			public void clientDisconnected(Socket socket) {
			    Logs.addLog(new Log("Info", "Server",
				    "Client disconnected: " + socket.getInetAddress()));
			}
		    });
		    server.bind(Variables.getPort(), callHandler,
			    new GZipFilter());
		    InetAddress IP = InetAddress.getLocalHost();
		    Logs.addLog(new Log("Info", "Server", "Creating Server is done."));
		    // System.out.println("Creating Server is done with IP: "
		    // + IP.getLocalHost());
		} catch (LipeRMIException | IOException e) {
		    Logs.addLog(new Log("Error", "Server", "error during creating."));
		    Logs.setServerError(true);
		    e.printStackTrace();
		};
	    
	}
	
	public void run() {
		for (;;) {
			try {
				
				Thread.sleep((measureTime * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public int getDirection(int items) {
		return Direction.getAverageValue(items);
	}


	public double getHumidity(int items) {
		// TODO Auto-generated method stub
		return Humidity.getAverageValue(items);
	}


	public double getRainFall(int items) {
		// TODO Auto-generated method stub
		return RainFall.getSumValue(items);
	}


	public double getTemperature(int items) {
		// TODO Auto-generated method stub
		return Temperature.getAverageValue(items);
	}


	public List<Double> getTemperatures(int start, int end) {
		// TODO Auto-generated method stub
		return Temperature.getTemperatures(start, end);
	}


	public double getWind(int items) {
		// TODO Auto-generated method stub
		return Wind.getAverageValue(items);
	}
}
