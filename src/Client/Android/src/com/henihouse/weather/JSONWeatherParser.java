package com.henihouse.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class JSONWeatherParser {

        public static WeatherPrediction getWeather(String data, String days) throws JSONException  {
        		int numberDays = Integer.parseInt(days);
                WeatherPrediction weatherPrediction = new WeatherPrediction(numberDays);

                // We create out JSONObject from the data
                JSONObject jObj = new JSONObject(data);
                
                if(getInt("cnt", jObj) == numberDays){
                	weatherPrediction.setNumberDays(numberDays);
                	
                	// create object with information about city
                    JSONObject cityObj = getObject("city", jObj);
                    weatherPrediction.setCity(getString("name", cityObj));
                    weatherPrediction.setCountry(getString("country", cityObj));
      
                    // create object with information about prediction of weather
                    
                    JSONArray predictionArr = jObj.getJSONArray("list");
                    for (int index = 0;index < numberDays;index++){
                    	JSONObject weather = predictionArr.getJSONObject(index);
                    	// get date
                    	weatherPrediction.addDate(index, getInt("dt", weather));
                    	// get weather - day and night
                    	JSONObject tempObj = getObject("temp", weather);
                    		weatherPrediction.addTempDay(index, getFloat("day", tempObj));
                    		weatherPrediction.addTempNight(index, getFloat("night", tempObj));
                    		
                    	// get pressure
                    	weatherPrediction.addPressure(index, getFloat("pressure", weather));
                    	
                    	//get clouds
                    	weatherPrediction.addClouds(index, getInt("clouds", weather));
                    	
                    	//get weather icon name
                    	JSONArray weatherArr = weather.getJSONArray("weather");
                    		// weather item has only one child, therefore index = 0
                    		JSONObject weatherObj = weatherArr.getJSONObject(0);
                    		weatherPrediction.addWeatherIcon(index, "id"+getString("icon", weatherObj));
                    }               	
                } else {
                	//vypsat chybu
                }
                
                


                /**
                JSONObject coordObj = getObject("coord", jObj);
                loc.setLatitude(getFloat("lat", coordObj));
                loc.setLongitude(getFloat("lon", coordObj));
                
                JSONObject sysObj = getObject("sys", jObj);
                loc.setCountry(getString("country", sysObj));
                loc.setSunrise(getInt("sunrise", sysObj));
                loc.setSunset(getInt("sunset", sysObj));
                loc.setCity(getString("name", jObj));
                weather.location = loc;
                
                // We get weather info (This is an array)
                JSONArray jArr = jObj.getJSONArray("weather");
                
                // We use only the first value
                JSONObject JSONWeather = jArr.getJSONObject(0);
                weather.currentCondition.setWeatherId(getInt("id", JSONWeather));
                weather.currentCondition.setDescr(getString("description", JSONWeather));
                weather.currentCondition.setCondition(getString("main", JSONWeather));
                weather.currentCondition.setIcon(getString("icon", JSONWeather));
                
                JSONObject mainObj = getObject("main", jObj);
                weather.currentCondition.setHumidity(getInt("humidity", mainObj));
                weather.currentCondition.setPressure(getInt("pressure", mainObj));
                weather.temperature.setMaxTemp(getFloat("temp_max", mainObj));
                weather.temperature.setMinTemp(getFloat("temp_min", mainObj));
                weather.temperature.setTemp(getFloat("temp", mainObj));
                
                // Wind
                JSONObject wObj = getObject("wind", jObj);
                weather.wind.setSpeed(getFloat("speed", wObj));
                weather.wind.setDeg(getFloat("deg", wObj));
                
                // Clouds
                JSONObject cObj = getObject("clouds", jObj);
                weather.clouds.setPerc(getInt("all", cObj));
                
                // We download the icon to show
                
                */
                return weatherPrediction;
        }
        
        
        private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
                JSONObject subObj = jObj.getJSONObject(tagName);
                return subObj;
        }
        
        private static String getString(String tagName, JSONObject jObj) throws JSONException {
                return jObj.getString(tagName);
        }

        private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
                return (float) jObj.getDouble(tagName);
        }
        
        private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
                return jObj.getInt(tagName);
        }
        
}