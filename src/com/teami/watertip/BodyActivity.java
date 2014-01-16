package com.teami.watertip;

import java.util.Calendar;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class BodyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_layout);
		TextView titleView = (TextView) findViewById(R.id.app_title);
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/centurygothic.ttf");
		titleView.setTypeface(typeface);
		
		
		//设置日期
		Calendar instance = Calendar.getInstance();
		TextView dateView = (TextView) findViewById(R.id.date_textview);
		dateView.setText(instance.get(Calendar.YEAR)
				+ "/"
				+ ((instance.get(Calendar.MONTH)+1) + "/" + instance
						.get(Calendar.DAY_OF_MONTH)));
		dateView.setTypeface(typeface);
		
		TextView countView=(TextView) findViewById(R.id.count_textview);
		countView.setTypeface(typeface);
		
		//设置剩余杯数
		TextView leftNumberView=(TextView)findViewById(R.id.left_number_textview);
		leftNumberView.setTypeface(typeface);
		leftNumberView.setText("3 to go!");
		
	}
}
