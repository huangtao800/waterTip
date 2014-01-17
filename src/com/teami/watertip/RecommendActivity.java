package com.teami.watertip;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendActivity extends Activity {
	private int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommend_layout);
		TextView title=(TextView)findViewById(R.id.recommend_title);
		ImageView image=(ImageView)findViewById(R.id.recommend_image);
		TextView detail=(TextView)findViewById(R.id.recommend_detail);
	}
	

}
