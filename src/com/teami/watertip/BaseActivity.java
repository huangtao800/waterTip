package com.teami.watertip;

import android.R.integer;
import android.app.Activity;

public class BaseActivity extends Activity{
	public static final String PREFERENCE="app_preference";
	public static final String TODAY_CUPS ="today_cups";	//�����Ѿ��ȹ��ı���:int
	public static final String GOAL_NUMBER="goal_number";	//�趨Ŀ��ı�����int
	
	
	//setting�����õ������ݣ���������Щ����δ����ʱ���Ĭ��ֵ����������
	public static final Boolean ISREMIND = true;		//boolean�Ƿ�����
	public static final int STARTHOUR = 8;
	public static final int STARTMINUTE = 30;
	public static final int ENDHOUR = 22;
	public static final int ENDMINUTE = 30;
	public static final int INTERVAL = 0;  //int�ͣ����0��ʾ��Сʱ��1��ʾ1Сʱ��2��ʾ2Сʱ������

	//setting�����õ������ݽ�����������

}
