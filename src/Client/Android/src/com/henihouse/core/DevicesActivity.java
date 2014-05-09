package com.henihouse.core;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import lipermi.handler.CallHandler;
import lipermi.handler.filter.GZipFilter;
import lipermi.net.Client;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.henihouse.interfaces.core.Interfaces;
import com.henihouse.interfaces.variables.Variables;

public class DevicesActivity extends Activity {

	Calendar c = Calendar.getInstance();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String lightDoor = "1_0";
	private String lightRear = "1_1";
	private String lightTable = "1_2";
	private String lightShop = "1_3";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devices);
		CallHandler callHandler = new CallHandler();
		try {

			Client client = new Client("10.0.0.9",
					Variables.getPort(), callHandler,
					new GZipFilter());

			Interfaces interfaces = (Interfaces) client
					.getGlobal(Interfaces.class);
			Map<String, Boolean> output = interfaces.getOutput();
			client.close();
			ImageButton imgButton;

			imgButton = (ImageButton) findViewById(R.id.ibLightDoor);
			if (output.get(lightDoor)) {
				imgButton.setImageResource(R.drawable.light_on);
			} else {
				imgButton.setImageResource(R.drawable.light_off);
			}

			imgButton = (ImageButton) findViewById(R.id.ibLightRear);
			if (output.get(lightRear)) {
				imgButton.setImageResource(R.drawable.light_on);
			} else {
				imgButton.setImageResource(R.drawable.light_off);
			}

			imgButton = (ImageButton) findViewById(R.id.ibLightTable);
			if (output.get(lightTable)) {
				imgButton.setImageResource(R.drawable.light_on);
			} else {
				imgButton.setImageResource(R.drawable.light_off);
			}

			imgButton = (ImageButton) findViewById(R.id.ibLightShop);
			if (output.get(lightShop)) {
				imgButton.setImageResource(R.drawable.light_on);
			} else {
				imgButton.setImageResource(R.drawable.light_off);
			}

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

	public void buttonLight(View button) {
		CallHandler callHandler = new CallHandler();
		try {

			Client client = new Client("10.0.0.9",
					Variables.getPort(), callHandler,
					new GZipFilter());

			Interfaces interfaces = (Interfaces) client
					.getGlobal(Interfaces.class);

			ImageButton imgButton = (ImageButton) button;

			switch (button.getId()) {
			case R.id.ibLightDoor:
				if (!interfaces.getValueOutput(lightDoor)) {
					imgButton.setImageResource(R.drawable.light_on);
					Toast.makeText(this, "Svìtlo u dvìøí bylo aktivováno.",
							Toast.LENGTH_SHORT).show();
					interfaces.putValueOutput(lightDoor, true);
				} else {
					imgButton.setImageResource(R.drawable.light_off);
					Toast.makeText(this, "Svìtlo u dvìøí bylo deaktivováno.",
							Toast.LENGTH_SHORT).show();
					interfaces.putValueOutput(lightDoor, false);
				}
				break;
			case R.id.ibLightRear:
				if (!interfaces.getValueOutput(lightRear)) {
					imgButton.setImageResource(R.drawable.light_on);
					Toast.makeText(this, "Svìtlo vzadu bylo aktivováno.",
							Toast.LENGTH_SHORT).show();
					interfaces.putValueOutput(lightRear, true);
				} else {
					imgButton.setImageResource(R.drawable.light_off);
					Toast.makeText(this, "Svìtlo vzadu bylo deaktivováno.",
							Toast.LENGTH_SHORT).show();
					interfaces.putValueOutput(lightRear, false);
				}
				break;
			case R.id.ibLightShop:
				if (!interfaces.getValueOutput(lightShop)) {
					imgButton.setImageResource(R.drawable.light_on);
					Toast.makeText(this,
							"Svìtlo ve vitrínách bylo aktivováno.",
							Toast.LENGTH_SHORT).show();
					interfaces.putValueOutput(lightShop, true);
				} else {
					imgButton.setImageResource(R.drawable.light_off);
					Toast.makeText(this,
							"Svìtlo ve vitrínách bylo deaktivováno.",
							Toast.LENGTH_SHORT).show();
					interfaces.putValueOutput(lightShop, false);
				}
				break;
			case R.id.ibLightTable:
				if (!interfaces.getValueOutput(lightTable)) {
					imgButton.setImageResource(R.drawable.light_on);
					Toast.makeText(this,
							"Svìtlo na venkovní tabuli bylo aktivováno.",
							Toast.LENGTH_SHORT).show();
					interfaces.putValueOutput(lightTable, true);
				} else {
					imgButton.setImageResource(R.drawable.light_off);
					Toast.makeText(this,
							"Svìtlo na venkovní tabuli bylo deaktivováno.",
							Toast.LENGTH_SHORT).show();
					interfaces.putValueOutput(lightTable, false);
				}
				break;
			}

			client.close();

			// String formattedDate = df.format(c.getTime());
			// TextView tv5 = (TextView) findViewById(R.id.last_actual);
			// tv5.setText(formattedDate);

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
}
