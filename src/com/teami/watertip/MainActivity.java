package com.teami.watertip;

import com.teami.model.DatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startAnimating();
		DatabaseHelper helper=new DatabaseHelper(MainActivity.this, "water.db");
		helper.getReadableDatabase();
		helper.close();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		//stop animating
		ImageView logoView=(ImageView)findViewById(R.id.ImageViewLogo);
		logoView.clearAnimation();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		startAnimating();
	}

	private void startAnimating(){
		View imageView=findViewById(R.id.ImageViewLogo);
		Animation fadeAnimation=AnimationUtils.loadAnimation(this, R.anim.fadein);
		imageView.startAnimation(fadeAnimation);
		fadeAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,BodyActivity.class));
				MainActivity.this.finish();
			}
		});
	}
}
