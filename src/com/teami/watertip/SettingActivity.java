package com.teami.watertip;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SettingActivity extends BaseActivity {

	private boolean isOpen = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);	
		
		
		//天数输入框！！！
		final EditText waterInputText = (EditText)findViewById(R.id.water_input_editText);
		
		
		final TextView timePicker1 = (TextView)findViewById(R.id.time_picker1);
		timePicker1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new TimePickerDialog(SettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						String hours = ""+hourOfDay;
						String minutes = "" + minute;
						if(hourOfDay<10){
							hours="0"+hours;
						}
						if(minute<10){
							minutes="0"+minutes;
						}
						timePicker1.setText(hours+":"+minutes);					
					}
				}, 8,0 , true).show();
			}
		});

		final TextView timePicker2 = (TextView)findViewById(R.id.time_picker2);
		timePicker2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Calendar calendar = Calendar.getInstance();
				new TimePickerDialog(SettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						String hours = ""+hourOfDay;
						String minutes = "" + minute;
						if(hourOfDay<10){
							hours="0"+hours;
						}
						if(minute<10){
							minutes="0"+minutes;
						}
						timePicker2.setText(hours+":"+minutes);
					}
				}, 22, 0, true).show();
			}
		});
		
		//Switch的监听
		final Switch opneSwitch = (Switch)findViewById(R.id.open_switch);
		opneSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){		//设置被开启
					TextView on_off_text = (TextView)findViewById(R.id.on_off_text);
					on_off_text.setText("On");
				}else{	    		//设置被关闭
					TextView on_off_text = (TextView)findViewById(R.id.on_off_text);
					on_off_text.setText("Off");				}
			}
		});
	}
}
