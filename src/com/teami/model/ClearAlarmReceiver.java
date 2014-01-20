package com.teami.model;

import java.util.Calendar;

import com.teami.watertip.BaseActivity;
import com.teami.watertip.BodyActivity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;

public class ClearAlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent it=new Intent(context,BodyActivity.class);
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		clearDrunkNumber(context);

		DatabaseHelper helper=new DatabaseHelper(context, "water.db");
		SQLiteDatabase db=helper.getWritableDatabase();
		saveTodayCups(db, context);
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
	
	/**
	 * 将当天喝过的水保存至历史记录
	 * @param db
	 * @param context
	 */
	private void saveTodayCups(SQLiteDatabase db,Context context){
		SharedPreferences sharedPreferences=context.getSharedPreferences(BaseActivity.PREFERENCE, Context.MODE_PRIVATE);
		int drunkNumber=sharedPreferences.getInt(BaseActivity.TODAY_CUPS, 0);
		
		ContentValues values = new ContentValues();
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int month=calendar.get(Calendar.MONTH);
		month++;
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		values.put("date", month+"."+day);
		values.put("num", drunkNumber);
		db.insert("cups", null, values);
	}

}
