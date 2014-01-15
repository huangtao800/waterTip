package com.teami.watertip;

import java.io.File;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class BodyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_layout);
		TextView titleView = (TextView) findViewById(R.id.app_title);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/centurygothic.ttf");
		titleView.setTypeface(typeface);
	}

}
