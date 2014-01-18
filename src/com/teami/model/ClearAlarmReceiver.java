package com.teami.model;

import com.teami.watertip.BaseActivity;
import com.teami.watertip.BodyActivity;
import com.teami.watertip.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.XmlResourceParser;

public class ClearAlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent it=new Intent(context,BodyActivity.class);
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		clearDrunkNumber(context);
//		Log.i(BaseActivity.APP_TAG,"alarm");
	}
	
	/**
	 * 清空当天已经喝过的水
	 * @param context
	 */
	private void clearDrunkNumber(Context context){
		SharedPreferences sharedPreferences=context.getSharedPreferences(BaseActivity.PREFERENCE, Context.MODE_PRIVATE);
		Editor editor=sharedPreferences.edit();
		editor.putInt(BaseActivity.TODAY_CUPS, 0);
		editor.commit();
	}
	
	private void saveTodayCups(Context context){
		XmlResourceParser recordParser= context.getResources().getXml(R.xml.record);
		
	}

}
