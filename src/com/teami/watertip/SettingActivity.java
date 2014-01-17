package com.teami.watertip;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.Switch;

public class SettingActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);	
		
		
		//天数输入框！！！
		final EditText waterInputText = (EditText)findViewById(R.id.water_input_editText);
		waterInputText.setOnFocusChangeListener(new OnFocusChangeListener() {			
			@SuppressLint("ResourceAsColor")
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					waterInputText.selectAll();
					waterInputText.setBackgroundColor(Color.WHITE);
				}else{
					waterInputText.setBackgroundColor(R.color.section_backgroud_color);
				}
				
			}
		});
		
		
		//Switch的监听
		final Switch settingSwitch = (Switch)findViewById(R.id.water_switch);
	
	}
}
