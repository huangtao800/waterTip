package com.teami.watertip;

import java.util.Calendar;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class BodyActivity extends Activity {

	private Typeface typeface = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_layout);
		typeface = Typeface.createFromAsset(getAssets(),
				"fonts/centurygothic.ttf");
		setDate();
		setNumberLeft();
		setFont();
	}

	/**
	 * 设置日期
	 */
	private void setDate() {
		Calendar instance = Calendar.getInstance();
		TextView dateView = (TextView) findViewById(R.id.date_textview);
		dateView.setText(instance.get(Calendar.YEAR)
				+ "/"
				+ ((instance.get(Calendar.MONTH) + 1) + "/" + instance
						.get(Calendar.DAY_OF_MONTH)));
	}

	/**
	 * 设置剩余杯数
	 */
	private void setNumberLeft() {
		TextView leftNumberView = (TextView) findViewById(R.id.left_number_textview);
		leftNumberView.setText("3 to go!");
	}

	/**
	 * 设置字体
	 */
	private void setFont() {
		TextView countView = (TextView) findViewById(R.id.count_textview);
		countView.setTypeface(typeface);

		TextView titleView = (TextView) findViewById(R.id.app_title);
		titleView.setTypeface(typeface);

		TextView leftNumberView = (TextView) findViewById(R.id.left_number_textview);
		leftNumberView.setTypeface(typeface);

		TextView dateView = (TextView) findViewById(R.id.date_textview);
		dateView.setTypeface(typeface);

		TextView tipView = (TextView) findViewById(R.id.tip_textview);
		tipView.setTypeface(typeface);
	}
}
