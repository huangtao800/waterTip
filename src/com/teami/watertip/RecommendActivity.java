package com.teami.watertip;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import com.teami.watertip.R.drawable;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecommendActivity extends Activity {
	private static int count;
	private ArrayList<Recommend> recommends=new ArrayList<Recommend>();
	private int sizeOfRecommends;
//	public RecommendActivity(){
//		XmlResourceParser allScores=getResources().getXml(R.xml.recommend);
//		ArrayList<Recommend> recommends=new ArrayList<Recommend>();
//		int eventType=-1;
//		try {
//			while(eventType!=XmlResourceParser.END_DOCUMENT){
//				if(eventType==XmlResourceParser.START_TAG){
//					String strname=allScores.getName();
//					if(strname.equals("score")){
//						String str_title=allScores.getAttributeValue(null,"title");
//						String str_image=allScores.getAttributeValue(null,"image");
//						String str_detail=allScores.getAttributeValue(null,"detail");
//						
//						recommends.add(new Recommend(str_title, str_image, str_detail));			
//					}
//				}
//				eventType=allScores.next();
//			}
//		} catch (XmlPullParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		sizeOfRecommends=recommends.size();
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommend_layout);
		TextView title=(TextView)findViewById(R.id.recommend_title);
		ImageView image=(ImageView)findViewById(R.id.recommend_image);
		TextView detail=(TextView)findViewById(R.id.recommend_detail);
		
		XmlResourceParser allScores=getResources().getXml(R.xml.recommend);
		ArrayList<Recommend> recommends=new ArrayList<Recommend>();
		int eventType=-1;
		try {
			while(eventType!=XmlResourceParser.END_DOCUMENT){
				if(eventType==XmlResourceParser.START_TAG){
					String strname=allScores.getName();
					if(strname.equals("score")){
						String str_title=allScores.getAttributeValue(null,"title");
						String str_image=allScores.getAttributeValue(null,"image");
						String str_detail=allScores.getAttributeValue(null,"detail");
						
						recommends.add(new Recommend(str_title, str_image, str_detail));			
					}
				}
				eventType=allScores.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		sizeOfRecommends=recommends.size();
				
		title.setText(recommends.get(count).getTitle());
		detail.setText(recommends.get(count).getDetail());
		
		int resId;
		try {
			resId = (Integer) R.drawable.class.getField(recommends.get(count).getImage()).get(null);
			image.setImageResource(resId);  
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        

		
		
	}
	
	public void onLastRecommendClick(View view){
		count--;
		if(count<0){
			count=0;
			Toast toast = Toast.makeText(getApplicationContext(),
				     "当前已是第一页", Toast.LENGTH_LONG);
		    toast.setGravity(Gravity.CENTER, 0, 0);
		    toast.show();
			startActivity(new Intent(RecommendActivity.this,BodyActivity.class));
			
		}else{
			onCreate(null);
		}
			
		
	}
	
	public void onNextRecommendClick(View view){
		count++;
		if(count>=sizeOfRecommends)
		{
			count=sizeOfRecommends-1;
			Toast toast = Toast.makeText(getApplicationContext(),
				     "当前已是最后一页", Toast.LENGTH_LONG);
		    toast.setGravity(Gravity.CENTER, 0, 0);
		    toast.show();
		}else{
			onCreate(null);
		}
			
		
	}
	
	class Recommend{
		private String r_title;
		private String r_image;
		private String r_detail;
		
		public Recommend(String title,String image,String detail){
			this.r_title=title;
			this.r_image=image;
			this.r_detail=detail;			
		}
		public String getTitle(){
			return r_title;
		}
		public String getImage(){
			return r_image;
		}
		public String getDetail(){
			return r_detail;
		}
	}
	

}
