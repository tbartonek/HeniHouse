package com.henihouse.core;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lipermi.handler.CallHandler;
import lipermi.handler.filter.GZipFilter;
import lipermi.net.Client;

import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.henihouse.interfaces.meteo.Interfaces;
import com.henihouse.interfaces.variables.Variables;
import com.henihouse.weather.JSONWeatherParser;
import com.henihouse.weather.WeatherHttpClient;
import com.henihouse.weather.WeatherPrediction;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class MeteoActivity extends Activity {

	private TextView location;
	private TextView date;
	private TextView tempDay;
	private TextView tempNight;
	private TextView pressure;
	private TextView clouds;

	public enum imageWeatherId {
		id01d, id02d, id03d, id04d, id09d, id10d, id11d, id13d, id50d, id01n, id02n, id03n, id04n, id09n, id10n, id11n, id13n, id50n;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meteo);



		// draw sin curve
		GraphViewData[] data = new GraphViewData[6];
		data[0] = new GraphViewData(1, 25);
		data[1] = new GraphViewData(2, 24);
		data[2] = new GraphViewData(3, 20);
		data[3] = new GraphViewData(4, 18);
		data[4] = new GraphViewData(5, 20);
		data[5] = new GraphViewData(6, 19);
		// data[6] = new GraphViewData(16,18 );
		// data[7] = new GraphViewData(17,17 );
		// data[8] = new GraphViewData(18,16 );
		// data[9] = new GraphViewData(19,15 );
		// data[10] = new GraphViewData(20,16 );
		// data[11] = new GraphViewData(21,16 );
		// graph with dynamically genereated horizontal and vertical labels
		GraphView graphView;

		graphView = new LineGraphView(this, "");
		// add data
		GraphViewSeriesStyle seriesStyle = new GraphViewSeriesStyle();
		/**
		 * seriesStyle.setValueDependentColor(new ValueDependentColor() {
		 * 
		 * @Override public int get(GraphViewDataInterface data) { // the higher
		 *           the more red return
		 *           Color.rgb((int)(150+((data.getY()/3)*100)),
		 *           (int)(150-((data.getY()/3)*150)),
		 *           (int)(150-((data.getY()/3)*150))); } });
		 * 
		 *           graphView.addSeries(new GraphViewSeries("aaaa",
		 *           seriesStyle, data));
		 */
		graphView.addSeries(new GraphViewSeries(data));
		// set view port, start=2, size=40

		graphView.setScrollable(true);
		graphView.setScalable(false);

		graphView.getGraphViewStyle().setNumHorizontalLabels(6);
		graphView.getGraphViewStyle().setTextSize(20);
		graphView.getGraphViewStyle().setNumVerticalLabels(3);

		graphView.setHorizontalLabels(new String[] { "10:00", "10:30", "11:00",
				"11:30", "12:00", "12:30" });
		graphView.setViewPort(1, 5);
		// set manual Y axis bounds
		graphView.setManualYAxisBounds(26, 17);
		LinearLayout layout_temperature = (LinearLayout) findViewById(R.id.graph_temperature);
		layout_temperature.addView(graphView);

		// draw sin curve
		GraphViewData[] data2 = new GraphViewData[12];
		data2[0] = new GraphViewData(0, 25);
		data2[1] = new GraphViewData(1, 24);
		data2[2] = new GraphViewData(2, 20);
		data2[3] = new GraphViewData(3, 18);
		data2[4] = new GraphViewData(4, 20);
		data2[5] = new GraphViewData(5, 19);
		data2[6] = new GraphViewData(6, 21);
		data2[7] = new GraphViewData(7, 17);
		data2[8] = new GraphViewData(8, 16);
		data2[9] = new GraphViewData(9, 15);
		data2[10] = new GraphViewData(10, 16);
		data2[11] = new GraphViewData(11, 16);
		// graph with dynamically genereated horizontal and vertical labels
		GraphView graphView2;

		graphView2 = new LineGraphView(this, "");
		// add data

		graphView2.addSeries(new GraphViewSeries(data2));
		// set view port, start=2, size=40

		graphView2.setScrollable(true);
		graphView2.setScalable(true);

		graphView2.getGraphViewStyle().setTextSize(20);
		graphView2.getGraphViewStyle().setNumVerticalLabels(3);

		graphView2.setHorizontalLabels(new String[] { "10:00", "10:30",
				"11:00", "11:30", "12:00", "12:30" });
		graphView2.setHorizontalScrollBarEnabled(true);
		graphView2.getGraphViewStyle().setNumHorizontalLabels(6);

		graphView2.setViewPort(1, 4);
		// set manual Y axis bounds
		graphView2.setManualYAxisBounds(26, 17);

		LinearLayout layout_pressure = (LinearLayout) findViewById(R.id.graph_pressure);
		layout_pressure.addView(graphView2);

		String city = "Ceske Budejovice, CZ";
		String days = "4";
		

		// cityText = (TextView) findViewById(R.id.cityText);
		// condDescr = (TextView) findViewById(R.id.condDescr);
		// temp = (TextView) findViewById(R.id.tvMeteoTemperatureDay1);
		// hum = (TextView) findViewById(R.id.hum);
		// press = (TextView) findViewById(R.id.press);
		// windSpeed = (TextView) findViewById(R.id.windSpeed);
		// windDeg = (TextView) findViewById(R.id.windDeg);
		// imgView = (ImageView) findViewById(R.id.condIcon);

		JSONWeatherTask task = new JSONWeatherTask();
		task.execute(new String[] { city, days });
		
		CallHandler callHandler = new CallHandler();
		try {

			Client client = new Client("10.0.0.9",
					Variables.getPort(), callHandler,
					new GZipFilter());

			Interfaces interfaces = (Interfaces) client
					.getGlobal(Interfaces.class);
			
			TextView temperature = (TextView) findViewById(R.id.tvMeteoTemperatureValue);
			temperature.setText(String.valueOf(interfaces.getTemperature(0)));
			
			TextView humidity = (TextView) findViewById(R.id.tvMeteoHumidityValue);
			humidity.setText(String.valueOf(interfaces.getHumidity(0)));
			
			TextView rainFall = (TextView) findViewById(R.id.tvMeteoRainFallValue);
			rainFall.setText(String.valueOf(interfaces.getRainFall(0)));
			
			TextView rainFallUnit = (TextView) findViewById(R.id.tvMeteoRainFallUnit);
			rainFallUnit.setText("mm/1min");
			
			TextView wind = (TextView) findViewById(R.id.tvMeteoWinValue);
			wind.setText(String.valueOf(interfaces.getWind(0)));
			
			ImageView compass = (ImageView) findViewById(R.id.ivMeteoCompass);
			
			
			compass.setImageResource(getResources()
					.getIdentifier(
							"compass_"
									+ Integer.toString(interfaces.getDirection(0)),
							"drawable", getPackageName()));
			
			
			client.close();
			
			

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, R.string.errorConnectServer, Toast.LENGTH_LONG)
					.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, R.string.errorGeneral, Toast.LENGTH_LONG)
					.show();
		}
		
		
	}

	private class JSONWeatherTask extends
			AsyncTask<String, Void, WeatherPrediction> {

		@Override
		protected WeatherPrediction doInBackground(String... params) {
			WeatherPrediction weather = new WeatherPrediction(
					Integer.parseInt(params[1]));
			String data = ((new WeatherHttpClient()).getWeatherData(params[0],
					params[1]));
			// System.out.println("Poslana data: " + data );
			if (data != null) {
				System.out.println("OK");
				try {
					weather = JSONWeatherParser.getWeather(data, params[1]);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {

			}
			return weather;

		}

		@Override
		protected void onPostExecute(WeatherPrediction weather) {

			super.onPostExecute(weather);
			if (weather.getCity() != null) {
				location = (TextView) findViewById(R.id.tvMeteoLocationValue);
				location.setText(" " + weather.getCity() + ", "
						+ weather.getCountry());

				for (int index = 0; index < weather.getNumberDays(); index++) {
					String dateAsText = new SimpleDateFormat("dd.MM.")
							.format(new Date(weather.getDate(index) * 1000L));
					date = (TextView) findViewById(getResources()
							.getIdentifier(
									"tvMeteoDate" + Integer.toString(index + 1),
									"id", getPackageName()));
					date.setText(dateAsText);

					ImageView image = (ImageView) findViewById(getResources()
							.getIdentifier(
									"ivMeteoStatus"
											+ Integer.toString(index + 1),
									"id", getPackageName()));

					switch (imageWeatherId.valueOf(weather
							.getWeatherIcon(index))) {
					case id01d:
						image.setImageResource(R.drawable.weather_clear);
						break;
					case id01n:
						image.setImageResource(R.drawable.weather_clear);
						break;	
					case id02d:
						image.setImageResource(R.drawable.weather_clouds);
						break;
					case id02n:
						image.setImageResource(R.drawable.weather_clouds);
						break;
					case id03d:
						image.setImageResource(R.drawable.weather_many_clouds);
						break;
					case id03n:
						image.setImageResource(R.drawable.weather_many_clouds);
						break;
					case id04d:
						image.setImageResource(R.drawable.weather_showers_scattered);
						break;
					case id04n:
						image.setImageResource(R.drawable.weather_showers_scattered);
						break;
					case id09d:
						image.setImageResource(R.drawable.weather_showers);
						break;
					case id09n:
						image.setImageResource(R.drawable.weather_showers);
						break;
					case id10d:
						image.setImageResource(R.drawable.weather_showers_day);
						break;
					case id10n:
						image.setImageResource(R.drawable.weather_showers_day);
						break;
					case id11d:
						image.setImageResource(R.drawable.weather_storm);
						break;
					case id11n:
						image.setImageResource(R.drawable.weather_storm);
						break;
					case id13d:
						image.setImageResource(R.drawable.weather_snow);
						break;
					case id13n:
						image.setImageResource(R.drawable.weather_snow);
						break;
					case id50d:
						break;
					case id50n:
						break;
					default:
						break;
					}

					tempDay = (TextView) findViewById(getResources()
							.getIdentifier(
									"tvMeteoTemperatureDay"
											+ Integer.toString(index + 1),
									"id", getPackageName()));
					tempDay.setText(Math.round((weather.getTempDay(index)))
							+ "°C");

					tempNight = (TextView) findViewById(getResources()
							.getIdentifier(
									"tvMeteoTemperatureNight"
											+ Integer.toString(index + 1),
									"id", getPackageName()));
					tempNight.setText(Math.round((weather.getTempNight(index)))
							+ "°C");

					clouds = (TextView) findViewById(getResources()
							.getIdentifier(
									"tvMeteoCloudy"
											+ Integer.toString(index + 1),
									"id", getPackageName()));
					clouds.setText(weather.getClouds(index) + "%");

					pressure = (TextView) findViewById(getResources()
							.getIdentifier(
									"tvMeteoPressure"
											+ Integer.toString(index + 1),
									"id", getPackageName()));
					pressure.setText(Math.round((weather.getPressure(index)))
							+ "hPa");
				}
			}

		}

	}

}
