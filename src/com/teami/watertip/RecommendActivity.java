package com.teami.watertip;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
		
		//Drawable drawable="@drawable-hdpi/recommend/百合花";
//		Uri uri= Uri.parse("drawable-hdpi/recommend/百合花");
//		image.setImageResource(R.drawable.recommend.百合花);
		XmlResourceParser allScores=getResources().getXml(R.xml.recommend);
//		XmlResourceParser mockFriendScores=getResources().getXml(R.xml.recommend);
		int eventType=-1;
		boolean bFoundScores=false;
		ArrayList<Recommend> recommends=new ArrayList<Recommend>();
		
		while(eventType!=XmlResourceParser.END_DOCUMENT){
			if(eventType==XmlResourceParser.START_DOCUMENT){
				String strname=allScores.getName();
				if(strname.equals("score")){
					String str_title=allScores.getAttributeValue(0);
					String str_image=allScores.getAttributeName(1);
					String str_detail=allScores.getAttributeName(2);
					Recommend recommend=new Recommend(str_title, str_image, str_detail);
					recommends.add(recommend);
					
				}
			}
		}
		title.setText(recommends.get(0).getTitle());
		detail.setText(recommends.get(0).getDetail());
	}
	
	class Recommend{
		private String title;
		private String image;
		private String detail;
		
		public Recommend(String title,String image,String detail){
			this.title=title;
			this.image=image;
			this.detail=detail;			
		}
		public String getTitle(){
			return title;
		}
		public String getImage(){
			return image;
		}
		public String getDetail(){
			return detail;
		}
	}
	

}
