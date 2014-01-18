package com.teami.watertip;

import java.net.ContentHandler;
import java.util.Calendar;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SettingActivity extends BaseActivity {

	private SharedPreferences preferences;
	private Editor editor;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
		
		preferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
		editor = preferences.edit();
		
		final EditText goalInput = (EditText)findViewById(R.id.water_input_editText);
		goalInput.setText(preferences.getString("GOAL_NUMBER", "8"));
		
		goalInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "设定目标成功", Toast.LENGTH_SHORT).show();
				editor.putString("GOAL_NUMBER", ""+goalInput.getText());
				editor.commit();
			}
		});
		
		//Switch的相关内容
		final Switch openSwitch = (Switch)findViewById(R.id.open_switch);
		//根据设置决定是否开提醒，默认是否
		
		boolean isRemind = preferences.getBoolean("ISREMIND", false);
				
		if(isRemind){
			setAll(true);
			openSwitch.setChecked(true);
		}else{
			setAll(false);
			openSwitch.setChecked(false);			
		}
		
		openSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){		//设置被开启
					TextView on_off_text = (TextView)findViewById(R.id.on_off_text);
					editor.putBoolean("ISREMIND", true);
					editor.commit();
					on_off_text.setText("On");		
					setAll(true);
				}else{	    		//设置被关闭
					TextView on_off_text = (TextView)findViewById(R.id.on_off_text);
					editor.putBoolean("ISREMIND", false);
					editor.commit();
					on_off_text.setText("Off");	
					setAll(false);
				}
			}
		});
		
		
		
		int startHour = preferences.getInt("STARTHOUR", 8);
		int startMin = preferences.getInt("STARTMINUTE", 30);
		int endHour = preferences.getInt("ENDHOUR", 22);
		int endMin = preferences.getInt("ENDMINUTE", 22);		
		
		final TextView timePicker1 = (TextView)findViewById(R.id.time_picker1);
		
		//登陆的时候根据设置内容设置起始时间！
		String startTime;
		if(startHour<10){
			startTime = "0"+startHour;
		}else{
			startTime = ""+startHour;
		}
		startTime+=":";
		if(startMin<10){
			startTime += ("0"+startMin);
		}else{
			startTime += startMin;
		}		
		timePicker1.setText(startTime);
		
		timePicker1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new TimePickerDialog(SettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						String hours = ""+hourOfDay;
						String minutes = "" + minute;
						editor.putInt("STARTHOUR", hourOfDay);
						editor.putInt("STARTMINUTE", minute);
						editor.commit();
						
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
		
		//登陆的时候根据设置内容设置终止时间！
		String endTime;
		if(endHour<10){
			endTime = "0"+endHour;
		}else{
			endTime = ""+endHour;
		}
		endTime+=":";
		if(endMin<10){
			endTime += ("0"+endMin);
		}else{
			endTime += endMin;
		}		
		timePicker2.setText(endTime);
		
		timePicker2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new TimePickerDialog(SettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						String hours = ""+hourOfDay;
						String minutes = "" + minute;
						editor.putInt("ENDHOUR", hourOfDay);
						editor.putInt("ENDMINUTE", minute);
						editor.commit();
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
		

		
			
		int interval = preferences.getInt("INTERVAL", 1);
		RadioGroup group = (RadioGroup)findViewById(R.id.time_choose_group);
		group.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Log.i("[test]", ""+checkedId);
				int interval = 1;
				switch (checkedId) {
				case R.id.radio0:
					interval = 0;
					break;
				case R.id.radio1:
					interval = 1;
					break;
				case R.id.radio2:
					interval = 2;
					break;
				default:
					interval = 0;
					break;
				}
				editor.putInt("INTERVAL", interval);
				editor.commit();
			}
		});
		
		switch (interval) {
		case 0:
			group.check(R.id.radio0);
			break;
		case 1:
			group.check(R.id.radio1);
			break;
		case 2:
			group.check(R.id.radio2);
			break;
		default:
			group.check(R.id.radio0);
			break;
		}
		
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
























////天数输入框！！！我觉得是虚拟机的问题！！！肯定是的！！！！所以先留着它！！！！
//final EditText waterInputText = (EditText)findViewById(R.id.water_input_editText);
//waterInputText.setOnFocusChangeListener(new OnFocusChangeListener() {			
//	@SuppressLint("ResourceAsColor")
//	@Override
//	public void onFocusChange(View view, boolean hasFocus) {
//		if(hasFocus){
//			waterInputText.setBackgroundColor(Color.WHITE);
//			Toast.makeText(SettingActivity.this, "hasFocus", Toast.LENGTH_SHORT).show();
//		}else{
//			waterInputText.setBackgroundColor(Color.rgb(245, 245, 245));
//			Toast.makeText(SettingActivity.this, "lose Focus", Toast.LENGTH_SHORT).show();
//		}
//		
//	}
//});
