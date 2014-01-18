package com.teami.model;

import com.teami.watertip.BaseActivity;
import com.teami.watertip.BodyActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ClearAlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent it=new Intent(context,BodyActivity.class);
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		SharedPreferences sharedPreferences=context.getSharedPreferences("app_preference", Context.MODE_PRIVATE);
		Editor editor=sharedPreferences.edit();
		editor.putInt(BaseActivity.TODAY_CUPS, 0);
		editor.commit();
	}

}
