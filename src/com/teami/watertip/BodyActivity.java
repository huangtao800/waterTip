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
		
		
		//…Ë÷√»’∆⁄
		Calendar instance = Calendar.getInstance();
		TextView dateView = (TextView) findViewById(R.id.date_textview);
		dateView.setText(instance.get(Calendar.YEAR)
				+ "-"
				+ ((instance.get(Calendar.MONTH)+1) + "-" + instance
						.get(Calendar.DAY_OF_MONTH)));
		dateView.setTypeface(typeface);
	}
}
