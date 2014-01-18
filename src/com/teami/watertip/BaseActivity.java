package com.teami.watertip;

import android.R.integer;
import android.app.Activity;

public class BaseActivity extends Activity{
	public static final String PREFERENCE="app_preference";
	public static final String TODAY_CUPS ="today_cups";	//今天已经喝过的杯数:int
	public static final String GOAL_NUMBER="goal_number";	//设定目标的杯数：int
	
	
	//setting部分用到的数据！！！！这些都是未设置时候的默认值！！！！！
	public static final Boolean ISREMIND = true;		//boolean是否提醒
	public static final int STARTHOUR = 8;
	public static final int STARTMINUTE = 30;
	public static final int ENDHOUR = 22;
	public static final int ENDMINUTE = 30;
	public static final int INTERVAL = 0;  //int型！间隔0表示半小时，1表示1小时，2表示2小时！！！

	//setting部分用到的数据结束！！！！

}
