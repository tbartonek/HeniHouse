package com.henihouse.core;

import java.io.IOException;
import java.util.Map;

import lipermi.handler.CallHandler;
import lipermi.handler.filter.GZipFilter;
import lipermi.net.Client;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.henihouse.interfaces.core.Interfaces;
import com.henihouse.interfaces.variables.Variables;

public class HeaterActivity extends Activity {
	CallHandler callHandler = new CallHandler();;
	Client client;
	Double maxTemperature = 30.0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heater);

		try {

			client = new Client("10.0.0.9", Variables.getPort(),
					callHandler, new GZipFilter());

			Interfaces interfaces = (Interfaces) client
					.getGlobal(Interfaces.class);

			Map<String, Double> heatingSetting = interfaces.getHeatingSetting();

			TextView actualTemperature = (TextView) findViewById(R.id.heater_temp_1);
			actualTemperature.setText(String.valueOf(heatingSetting
					.get("actualTemperature")));

			EditText setTemperature = (EditText) findViewById(R.id.heater_temp_set_1);
			setTemperature.setText(String.valueOf(heatingSetting
					.get("setTemperature")));

			client.close();

			setTemperature
					.setOnEditorActionListener(new OnEditorActionListener() {

						@Override
						public boolean onEditorAction(TextView editText,
								int actionId, KeyEvent event) {
							if(Double
									.parseDouble(editText.getText()
											.toString()) < maxTemperature){
							if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
									|| (actionId == EditorInfo.IME_ACTION_DONE)) {
								try {

									client = new Client("10.0.0.9",
											Variables.getPort(),
											callHandler, new GZipFilter());

									Interfaces interfaces = (Interfaces) client
											.getGlobal(Interfaces.class);
									interfaces.setTemperatureHeating(Double
											.parseDouble(editText.getText()
													.toString()));
									client.close();
									Toast.makeText(HeaterActivity.this,
											R.string.saveValue,
											Toast.LENGTH_LONG).show();
								} catch (IOException e) {
									Toast.makeText(HeaterActivity.this,
											R.string.errorConnectServer,
											Toast.LENGTH_LONG).show();
								} catch (Exception e) {
									Toast.makeText(HeaterActivity.this,
											R.string.errorDouble,
											Toast.LENGTH_LONG).show();
									editText.setText("");
									try {
										client.close();
									} catch (IOException e1) {
										Toast.makeText(HeaterActivity.this,
												R.string.errorConnectServer,
												Toast.LENGTH_LONG).show();
									}
								}
							}} else{
								editText.setText("");
								Toast.makeText(HeaterActivity.this,
										R.string.heater_max_value,
										Toast.LENGTH_LONG).show();
							}
							return true;
						}
					});

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
	
	public void saveValue(View button){
		EditText setTemperature = (EditText) findViewById(R.id.heater_temp_set_1);
		
		try {

			client = new Client("10.0.0.9",
					Variables.getPort(),
					callHandler, new GZipFilter());

			Interfaces interfaces = (Interfaces) client
					.getGlobal(Interfaces.class);
			interfaces.setTemperatureHeating(Double
					.parseDouble(setTemperature.getText()
							.toString()));
			client.close();
			Toast.makeText(HeaterActivity.this,
					R.string.saveValue,
					Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Toast.makeText(HeaterActivity.this,
					R.string.errorConnectServer,
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(HeaterActivity.this,
					R.string.errorDouble,
					Toast.LENGTH_LONG).show();
			setTemperature.setText("");
			try {
				client.close();
			} catch (IOException e1) {
				Toast.makeText(HeaterActivity.this,
						R.string.errorConnectServer,
						Toast.LENGTH_LONG).show();
			}
		}
	}
	
}
