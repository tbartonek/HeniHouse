package com.henihouse.weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {
	//private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
 /**
    api.openweathermap.org/data/2.5/forecast/daily?q=London&mode=xml&units=metric&cnt=7
    		Seaching 10 days forecast by geographic coordinats at JSON format 
    		api.openweathermap.org/data/2.5/forecast/daily?lat=35&lon=139&cnt=10&mode=json
    
    */ 
    public String getWeatherData(String location, String days) {
        HttpURLConnection con = null ;
        InputStream is = null;
 
        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + location + "&mode=json&units=metric&cnt=" + days)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
             
            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");
             
            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;
                 
    }
}
