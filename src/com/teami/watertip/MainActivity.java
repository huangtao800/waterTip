package com.teami.watertip;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startAnimating();
		//test
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		ImageView logoView=(ImageView)findViewById(R.id.ImageViewLogo);
		logoView.clearAnimation();
	}

	private void startAnimating(){
		View imageView=findViewById(R.id.ImageViewLogo);
		Animation fadeAnimation=AnimationUtils.loadAnimation(this, R.anim.fadein);
		imageView.startAnimation(fadeAnimation);
	}
}
