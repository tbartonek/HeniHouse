package com.henihouse.core;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lipermi.handler.CallHandler;
import lipermi.handler.filter.GZipFilter;
import lipermi.net.Client;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.henihouse.interfaces.core.Interfaces;
import com.henihouse.interfaces.variables.Variables;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class AlarmActivity extends Activity {

	private String pushNumber = "";
	private CallHandler callHandler = new CallHandler();
	private Client client;
	private String alarmNoticeText = "";
	private boolean settingMode = false;
	private boolean servisPasswordOK = false;
	private String servisPassword = "";
	private boolean saveSettings = false;
	private String serverIp = "192.168.2.105";
	private Map<String, Integer> alarmSetting = new HashMap<String, Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_alarm);
		TextView alarmNoticeT = (TextView) findViewById(R.id.alarmNotice);
		alarmNoticeT.setMovementMethod(new ScrollingMovementMethod());

		try {

			client = new Client(serverIp, Variables.getPort(),
					callHandler, new GZipFilter());
			Interfaces interfaces = (Interfaces) client
					.getGlobal(Interfaces.class);

			if (interfaces.isActiveAlarm()) {
				ImageButton imgButton;
				imgButton = (ImageButton) findViewById(R.id.ibStateAlarm);
				imgButton.setImageResource(R.drawable.lock);
			}

			loadAlarmNotice(interfaces);
			client.close();
		} catch (IOException e) {
			Toast.makeText(this, R.string.errorConnectServer, Toast.LENGTH_LONG)
					.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				client.close();
			} catch (IOException e1) {
			}
			Toast.makeText(this, R.string.errorGeneral, Toast.LENGTH_LONG)
					.show();
		}
	}

	public void buttonNumber(View button) {
		animButton(button);

		switch (button.getId()) {
		case R.id.ibOne:
			pushNumber = pushNumber + "1";
			break;
		case R.id.ibTwo:
			pushNumber = pushNumber + "2";
			break;
		case R.id.ibThree:
			pushNumber = pushNumber + "3";
			break;
		case R.id.ibFour:
			pushNumber = pushNumber + "4";
			break;
		case R.id.ibFive:
			pushNumber = pushNumber + "5";
			break;
		case R.id.ibSix:
			pushNumber = pushNumber + "6";
			break;
		case R.id.ibSeven:
			pushNumber = pushNumber + "7";
			break;
		case R.id.ibEight:
			pushNumber = pushNumber + "8";
			break;
		case R.id.ibNine:
			pushNumber = pushNumber + "9";
			break;
		case R.id.ibZero:
			pushNumber = pushNumber + "0";
			break;
		case R.id.ibCancel:
			pushNumber = "";
			Toast.makeText(this, R.string.alarm_deleteNumber, Toast.LENGTH_LONG)
					.show();
			break;
		}
	}

	public void buttonSetting(View button) {
		if (servisPasswordOK) {
			if (settingMode) {
				saveSettings = true;
				switchLayout(true);
			} else {
				switchLayout(false);
			}
		} else {
			animButton(button);
			initiatePopupWindow();
		}
	}

	public void buttonSettingCancel(View button) {
		switchLayout(true);
	}

	private void switchLayout(boolean alarmNotice) {
		TextView tvAlarmNotice = (TextView) findViewById(R.id.alarmNotice);
		TextView tvAlarmTitle = (TextView) findViewById(R.id.tvAlarmTitle);
		TextView tvSettings = (TextView) findViewById(R.id.tvSettings);
		TextView tvSettingsCancel = (TextView) findViewById(R.id.tvSettingsCancel);

		TextView tvMaxTryValue = (TextView) findViewById(R.id.tvMaxTryValue);
		TextView tvAlertTimeValue = (TextView) findViewById(R.id.tvAlertTimeValue);
		TextView tvStartTimeValue = (TextView) findViewById(R.id.tvStartTimeValue);

		TextView tvPasswordValue = (TextView) findViewById(R.id.tvPasswordValue);
		TextView tvPasswordNewValue1 = (TextView) findViewById(R.id.tvPasswordNewValue1);
		TextView tvPasswordNewValue2 = (TextView) findViewById(R.id.tvPasswordNewValue2);

		TextView tvServisPasswordValue = (TextView) findViewById(R.id.tvServisPasswordValue);
		TextView tvServisPasswordNewValue1 = (TextView) findViewById(R.id.tvServisPasswordNewValue1);
		TextView tvServisPasswordNewValue2 = (TextView) findViewById(R.id.tvServisPasswordNewValue2);

		ScrollView layoutSettings = (ScrollView) findViewById(R.id.alarmSettingLayout);
		LinearLayout layoutNumbers = (LinearLayout) findViewById(R.id.layoutNumbers);

		ImageButton ibSettingAlarm = (ImageButton) findViewById(R.id.ibSettingsAlarm);
		ImageButton ibLock = (ImageButton) findViewById(R.id.ibStateAlarm);
		ImageButton ibSettingsCancel = (ImageButton) findViewById(R.id.ibSettingsCancel);

		CheckBox cbPassword = (CheckBox) findViewById(R.id.cbPassword);
		CheckBox cbServisPassword = (CheckBox) findViewById(R.id.cbServisPassword);

		try {
			client = new Client(serverIp, Variables.getPort(),
					callHandler, new GZipFilter());
			Interfaces interfaces = (Interfaces) client
					.getGlobal(Interfaces.class);

			if (alarmNotice) {
				if (saveSettings) {
					boolean change = false;

					if (!String.valueOf(alarmSetting.get("maxTryPassword"))
							.equals(tvMaxTryValue.getText().toString()) && Integer.valueOf(tvMaxTryValue.getText().toString()) > 1)
						change = true;
					if (!String.valueOf(alarmSetting.get("alertTime")).equals(
							tvAlertTimeValue.getText().toString()))
						change = true;
					if (!String.valueOf(alarmSetting.get("startTimeAlarm"))
							.equals(tvStartTimeValue.getText().toString()))
						change = true;
					if (change) {
						alarmSetting.put("maxTryPassword", Integer
								.valueOf(tvMaxTryValue.getText().toString()));
						alarmSetting
								.put("alertTime", Integer
										.valueOf(tvAlertTimeValue.getText()
												.toString()));
						alarmSetting
								.put("startTimeAlarm", Integer
										.valueOf(tvStartTimeValue.getText()
												.toString()));

						if (interfaces.setAlarmSetting(alarmSetting,
								servisPassword)) {
							Toast.makeText(this, R.string.saveValues,
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(this, R.string.saveValuesWrong,
									Toast.LENGTH_LONG).show();
						}
					}

					if (cbPassword.isChecked()) {
						if (tvPasswordNewValue2
								.getText()
								.toString()
								.equals(tvPasswordNewValue1.getText()
										.toString())
								&& !tvPasswordNewValue2.getText().toString()
										.equals("")) {
							if (interfaces.changePassword(tvPasswordValue
									.getText().toString(), tvPasswordNewValue2
									.getText().toString())) {
								Toast.makeText(this,
										R.string.alarm_PasswordSave,
										Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(this,
										R.string.alarm_PasswordWrong,
										Toast.LENGTH_LONG).show();
							}

						} else {
							Toast.makeText(this, R.string.alarm_PasswordCheck,
									Toast.LENGTH_LONG).show();
						}
					}

					if (cbServisPassword.isChecked()) {
						if (tvServisPasswordNewValue2
								.getText()
								.toString()
								.equals(tvServisPasswordNewValue1.getText()
										.toString())
								&& !tvServisPasswordNewValue2.getText()
										.toString().equals("")) {
							if (interfaces.changeServisPassword(
									tvServisPasswordValue.getText().toString(),
									tvServisPasswordNewValue2.getText()
											.toString())) {
								Toast.makeText(this,
										R.string.alarm_ServisPasswordSave,
										Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(this,
										R.string.alarm_ServisPasswordWrong,
										Toast.LENGTH_LONG).show();
							}

						} else {
							Toast.makeText(this,
									R.string.alarm_ServisPasswordCheck,
									Toast.LENGTH_LONG).show();
						}
					}
				}
				
				tvAlarmNotice.setVisibility(View.VISIBLE);
				tvAlarmTitle.setText(R.string.alarm_title);
				tvSettings.setText(R.string.alarm_setting);
				tvSettingsCancel.setVisibility(View.GONE);

				layoutSettings.setVisibility(View.GONE);
				layoutNumbers.setVisibility(View.VISIBLE);

				ibSettingsCancel.setVisibility(View.GONE);
				ibSettingAlarm.setImageResource(R.drawable.settings);
				ibLock.setVisibility(View.VISIBLE);

				tvPasswordValue.setText("");
				tvPasswordNewValue1.setText("");
				tvPasswordNewValue2.setText("");

				tvServisPasswordValue.setText("");
				tvServisPasswordNewValue1.setText("");
				tvServisPasswordNewValue2.setText("");

				settingMode = false;
				saveSettings = false;
				servisPasswordOK = false;
				servisPassword = "";

				cbPassword.setChecked(false);
				cbServisPassword.setChecked(false);

				loadAlarmNotice(interfaces);
			} else {

				tvAlarmNotice.setVisibility(View.GONE);
				tvAlarmTitle.setText(R.string.alarm_setting_title);
				tvSettings.setText(R.string.save);
				tvSettingsCancel.setVisibility(View.VISIBLE);

				layoutSettings.setVisibility(View.VISIBLE);
				layoutNumbers.setVisibility(View.INVISIBLE);

				ibSettingAlarm.setImageResource(R.drawable.save);
				ibLock.setVisibility(View.GONE);
				ibSettingsCancel.setVisibility(View.VISIBLE);
				settingMode = true;
				alarmSetting = interfaces.getAlarmSetting();

				tvMaxTryValue.setText(String.valueOf(alarmSetting
						.get("maxTryPassword")));
				
				
				tvAlertTimeValue.setText(String.valueOf(alarmSetting
						.get("alertTime")));
				
				
				tvStartTimeValue.setText(String.valueOf(alarmSetting
						.get("startTimeAlarm")));
				
			}

			client.close();
		} catch (IOException e) {
			Toast.makeText(this, R.string.errorConnectServer, Toast.LENGTH_LONG)
					.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				client.close();
			} catch (IOException e1) {
			}
			Toast.makeText(this, R.string.errorGeneral, Toast.LENGTH_LONG)
					.show();
		}
	}

	private PopupWindow pwindo;
	private View popUp;

	private void initiatePopupWindow() {
		try {
			// We need to get the instance of the LayoutInflater
			LayoutInflater inflater = (LayoutInflater) AlarmActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			popUp = inflater.inflate(R.layout.activity_pop_up_servis_password,
					(ViewGroup) findViewById(R.id.popup_element));
			pwindo = new PopupWindow(popUp, 750, 180, true);
			pwindo.showAtLocation(popUp, Gravity.CENTER, 0, -50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void popUpClose(View v) {
		pwindo.dismiss();

	}

	public void popUpVerifyServisPassword(View v) {
		try {
			EditText tvSettingsPassword = (EditText) popUp
					.findViewById(R.id.tvServisPasswordValue2);
			servisPassword = tvSettingsPassword.getText().toString();

			if (!"".equals(servisPassword)) {
				client = new Client(serverIp, Variables.getPort(),
						callHandler, new GZipFilter());
				Interfaces interfaces = (Interfaces) client
						.getGlobal(Interfaces.class);

				if (interfaces.verifyServisPassword(servisPassword)) {
					servisPasswordOK = true;
					pwindo.dismiss();
					client.close();
					switchLayout(false);
				} else {
					Toast.makeText(this, R.string.alarm_wrongPassword,
							Toast.LENGTH_LONG).show();
					tvSettingsPassword.setText("");
					loadAlarmNotice(interfaces);
					client.close();
				}

			} else {
				Toast.makeText(this, R.string.alarm_popUp_wrong_pass,
						Toast.LENGTH_LONG).show();
			}
		} catch (IOException e) {
			Toast.makeText(this, R.string.errorConnectServer, Toast.LENGTH_LONG)
					.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, R.string.errorGeneral, Toast.LENGTH_LONG)
					.show();
		}

	}

	public void buttonOK(View button) {
		if (pushNumber != "") {
			animButton(button);
			try {

				client = new Client(serverIp, Variables.getPort(),
						callHandler, new GZipFilter());
				Interfaces interfaces = (Interfaces) client
						.getGlobal(Interfaces.class);
				if (interfaces.isActiveAlarm()) {
					if (interfaces.deactiveAlarm(pushNumber)) {
						Toast.makeText(this, R.string.alarm_deactivate,
								Toast.LENGTH_LONG).show();
						ImageButton imgButton;
						imgButton = (ImageButton) findViewById(R.id.ibStateAlarm);
						imgButton.setImageResource(R.drawable.unlock);
					} else {
						Toast.makeText(this, R.string.alarm_wrongPassword,
								Toast.LENGTH_LONG).show();
					}

				} else {
					if (interfaces.verifyPassword(pushNumber)) {
						if (interfaces.activeAlarm()) {
							Toast.makeText(this, R.string.alarm_activate,
									Toast.LENGTH_LONG).show();
							ImageButton imgButton;
							imgButton = (ImageButton) findViewById(R.id.ibStateAlarm);
							imgButton.setImageResource(R.drawable.lock);
						}
					} else {
						Toast.makeText(this, R.string.alarm_wrongPassword,
								Toast.LENGTH_LONG).show();
					}

				}
				pushNumber = "";
				loadAlarmNotice(interfaces);
				client.close();
			} catch (IOException e) {
				Toast.makeText(this, R.string.errorConnectServer,
						Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					client.close();
				} catch (IOException e1) {
				}
				Toast.makeText(this, R.string.errorGeneral, Toast.LENGTH_LONG)
						.show();
			}

		}
	}

	public void buttonLock(View button) {
		try {

			client = new Client(serverIp, Variables.getPort(),
					callHandler, new GZipFilter());
			Interfaces interfaces = (Interfaces) client
					.getGlobal(Interfaces.class);
			if (!interfaces.isActiveAlarm()) {
				ImageButton imgButton;
				imgButton = (ImageButton) findViewById(R.id.ibStateAlarm);
				imgButton.setImageResource(R.drawable.lock);
				if (interfaces.activeAlarm()) {
					Toast.makeText(this, R.string.alarm_activate,
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(this, R.string.alarm_requestDeactivate,
						Toast.LENGTH_LONG).show();
			}
			loadAlarmNotice(interfaces);
			client.close();
		} catch (IOException e) {
			Toast.makeText(this, R.string.errorConnectServer, Toast.LENGTH_LONG)
					.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				client.close();
			} catch (IOException e1) {
			}
			Toast.makeText(this, R.string.errorGeneral, Toast.LENGTH_LONG)
					.show();
		}

	}

	private void loadAlarmNotice(Interfaces interfaces) {
		alarmNoticeText = "";
		List<String> alarmNotice = interfaces.getAlarmNotice();
		for (int i = 0; i < alarmNotice.size(); i++) {
			alarmNoticeText = alarmNoticeText + alarmNotice.get(i)
					+ System.getProperty("line.separator");
		}

		TextView alarmNoticeT = (TextView) findViewById(R.id.alarmNotice);
		alarmNoticeT.setText(alarmNoticeText);
	}

	private void animButton(View button) {
		final Animation animAlpha = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);
		button.startAnimation(animAlpha);
	}

}
