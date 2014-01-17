package com.teami.watertip;

import java.util.Calendar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class BodyActivity extends Activity {

	private Typeface typeface = null;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_layout);
		typeface = Typeface.createFromAsset(getAssets(),
				"fonts/centurygothic.ttf");
		setDate();
		setNumberLeft();
		setFont();
		setWaterTip();
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

	}

	/**
	 * 设置喝水小提示，每隔一段时间更换提示内容
	 */
	private void setWaterTip() {
		TextSwitcher tipSwitcher = (TextSwitcher) findViewById(R.id.tip_textswitcher);
		tipSwitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				TextView textView = new TextView(BodyActivity.this);
				textView.setGravity(Gravity.CENTER_HORIZONTAL
						| Gravity.CENTER_VERTICAL);
				textView.setTextColor(getResources().getColor(R.color.font_color));
				textView.setTextSize(getResources().getDimension(R.dimen.tip_size));
				return textView;
			}
		});
		
		Animation in = AnimationUtils.loadAnimation(this,  
                android.R.anim.fade_in);  
        Animation out = AnimationUtils.loadAnimation(this,  
                android.R.anim.fade_out); 
        tipSwitcher.setInAnimation(in);
        tipSwitcher.setOutAnimation(out);
        
		handler = new Handler();
		Runnable runnable = new Runnable() {
			int number = 0;
			TextSwitcher tipSwitcher = (TextSwitcher) findViewById(R.id.tip_textswitcher);

			@Override
			public void run() {
				// TODO Auto-generated method stub
				number++;
				tipSwitcher.setText(number + "");
				handler.postDelayed(this, 1000);
			}

		};
		handler.postDelayed(runnable, 1000);
	}

}
