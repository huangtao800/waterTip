package com.teami.watertip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.xmlpull.v1.XmlPullParserException;

import com.teami.model.ClearAlarmReceiver;
import com.teami.model.TipModel;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.XmlResourceParser;
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

public class BodyActivity extends BaseActivity {

	private Typeface typeface = null;
	private Handler handler;
	private TipModel tipModel;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedPreferences=getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
		setContentView(R.layout.body_layout);
		typeface = Typeface.createFromAsset(getAssets(),
				"fonts/centurygothic.ttf");
		tipModel=TipModel.getInstance();
		setDate();
		setDrunkNumber();
		setLeftNumber();
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
	 * 设置已经喝过的杯数
	 */
	private void setDrunkNumber(){
		TextView countTextView=(TextView)findViewById(R.id.count_textview);
		int drunkNumber=sharedPreferences.getInt(TODAY_CUPS, 0);
		countTextView.setText(""+drunkNumber);
		
	}
	
	/**
	 * 设置剩余杯数
	 */
	private void setLeftNumber() {
		TextView leftNumberView = (TextView) findViewById(R.id.left_number_textview);
		int drunkNumber=sharedPreferences.getInt(TODAY_CUPS, 0);
		int goalNumber=sharedPreferences.getInt(GOAL_NUMBER, 8);
		int leftNumber=goalNumber-drunkNumber;
		if(leftNumber>0){
			leftNumberView.setText("还差"+leftNumber+"杯");
		}else{
			leftNumberView.setText("任务完成！");
		}
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
		XmlResourceParser tipParser=getResources().getXml(R.xml.tips);
		ArrayList<String> tipList=new ArrayList<String>();
		try {
			tipList=tipModel.parseTipList(tipParser);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		startTipAnimation(tipSwitcher, tipList);
		
	}
	
	private void startTipAnimation(final TextSwitcher tipSwitcher,final ArrayList<String> tipList){
		Animation in = AnimationUtils.loadAnimation(this,  
                android.R.anim.fade_in);  
        Animation out = AnimationUtils.loadAnimation(this,  
                android.R.anim.fade_out); 
        tipSwitcher.setInAnimation(in);
        tipSwitcher.setOutAnimation(out);
        
		handler = new Handler();
		Runnable runnable = new Runnable() {
			int index = 0;
			

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(index==tipList.size()){
					index=0;
				}
				tipSwitcher.setText(tipList.get(index));
				index++;
				handler.postDelayed(this, 6000);
			}
		};
		handler.postDelayed(runnable, 6000);
	}
	
	private void setAlarmWork(){
		Intent intent=new Intent(this,ClearAlarmReceiver.class);
		PendingIntent pi=PendingIntent.getBroadcast(this, 0, intent,0);
		AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND,1);
		
		am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 86400000, pi);
	}

	public void onSetClick(View view){
		startActivity(new Intent(BodyActivity.this,SettingActivity.class));
	}
	
	public void onAddClick(View view){
		int drunkNumber=sharedPreferences.getInt(TODAY_CUPS, 0);
		drunkNumber++;
		Editor editor=sharedPreferences.edit();
		editor.putInt(TODAY_CUPS, drunkNumber);
		editor.commit();
		setDrunkNumber();
		setLeftNumber();
	}
	
	public void onHistoryClick(View view){
		startActivity(new Intent(BodyActivity.this,HistoryActivity.class));
	}
}
