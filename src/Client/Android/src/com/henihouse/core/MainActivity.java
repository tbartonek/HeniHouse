package com.henihouse.core;

import java.io.IOException;

import lipermi.handler.CallHandler;
import lipermi.handler.filter.GZipFilter;
import lipermi.net.Client;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.henihouse.interfaces.core.Interfaces;


public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	    //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		/**
		TextView tv1 = (TextView) findViewById(R.id.teplota1);
		tv1.setText("25 °C");

		TextView tv2 = (TextView) findViewById(R.id.teplota2);
		tv2.setText("25 °C");

		TextView tv3 = (TextView) findViewById(R.id.alarm);
		tv3.setText("Deaktivovaný");


		String formattedDate = df.format(c.getTime());
		TextView tv5 = (TextView) findViewById(R.id.last_actual);
		tv5.setText(formattedDate);
*/
	}

	public void openAlarm(View button){
		 Intent intentApp = new Intent(MainActivity.this, 
                 AlarmActivity.class);
		 MainActivity.this.startActivity(intentApp);
	}
	
	public void openDevices(View button){
		 Intent intentApp = new Intent(MainActivity.this, 
                DevicesActivity.class);
		 MainActivity.this.startActivity(intentApp);
	}
	
	public void openHeater(View button){
		 Intent intentApp = new Intent(MainActivity.this, 
               HeaterActivity.class);
		 MainActivity.this.startActivity(intentApp);
	}
	
	public void openMeteo(View button){
		 Intent intentApp = new Intent(MainActivity.this, 
              MeteoActivity.class);
		 MainActivity.this.startActivity(intentApp);
	}
	
	public void test(View button){
		CallHandler callHandler = new CallHandler();
		Toast.makeText(this, "Svìtlo u dvìøí bylo aktivováno. ",
				Toast.LENGTH_SHORT).show();
		try {
			
			
			
			Client client = new Client("10.0.0.5", 1234, callHandler, new GZipFilter());
			//Client client = new Client("10.0.0.5", 1234, callHandler);
			
			
			
			Interfaces myServiceCaller = (Interfaces) client.getGlobal(Interfaces.class);
			
			
			System.out.println("return: " + myServiceCaller.isActiveHeating());

			

			
			client.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}


}
