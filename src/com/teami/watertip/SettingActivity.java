package com.teami.watertip;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
		setAll(false);
		
//		//天数输入框！！！我觉得是虚拟机的问题！！！肯定是的！！！！
//		final EditText waterInputText = (EditText)findViewById(R.id.water_input_editText);
//		waterInputText.setOnFocusChangeListener(new OnFocusChangeListener() {			
//			@SuppressLint("ResourceAsColor")
//			@Override
//			public void onFocusChange(View view, boolean hasFocus) {
//				if(hasFocus){
//					waterInputText.setBackgroundColor(Color.WHITE);
//					Toast.makeText(SettingActivity.this, "hasFocus", Toast.LENGTH_SHORT).show();
//				}else{
//					waterInputText.setBackgroundColor(Color.rgb(245, 245, 245));
//					Toast.makeText(SettingActivity.this, "lose Focus", Toast.LENGTH_SHORT).show();
//				}
//				
//			}
//		});
		
		
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
					setAll(true);
				}else{	    		//设置被关闭
					TextView on_off_text = (TextView)findViewById(R.id.on_off_text);
					on_off_text.setText("Off");	
					setAll(false);
				}
			}
		});
	}
	
	
	private void setAll(Boolean isOpen){
		if(isOpen){			//Switch开启，把那些东西都visible
			TextView t1 = (TextView)findViewById(R.id.everyday_text);
			t1.setVisibility(View.VISIBLE);
			TextView t2 = (TextView)findViewById(R.id.time_picker1);
			t2.setVisibility(View.VISIBLE);
			TextView t3 = (TextView)findViewById(R.id.to_text);
			t3.setVisibility(View.VISIBLE);
			TextView t4 = (TextView)findViewById(R.id.time_picker2);
			t4.setVisibility(View.VISIBLE);
			TextView t5 = (TextView)findViewById(R.id.group_text);
			t5.setVisibility(View.VISIBLE);
			RadioGroup t6 = (RadioGroup)findViewById(R.id.time_choose_group);
			t6.setVisibility(View.VISIBLE);			
		}else{
			TextView t1 = (TextView)findViewById(R.id.everyday_text);
			t1.setVisibility(View.INVISIBLE);
			TextView t2 = (TextView)findViewById(R.id.time_picker1);
			t2.setVisibility(View.INVISIBLE);
			TextView t3 = (TextView)findViewById(R.id.to_text);
			t3.setVisibility(View.INVISIBLE);
			TextView t4 = (TextView)findViewById(R.id.time_picker2);
			t4.setVisibility(View.INVISIBLE);
			TextView t5 = (TextView)findViewById(R.id.group_text);
			t5.setVisibility(View.INVISIBLE);
			RadioGroup t6 = (RadioGroup)findViewById(R.id.time_choose_group);
			t6.setVisibility(View.INVISIBLE);
		}
	}
}
