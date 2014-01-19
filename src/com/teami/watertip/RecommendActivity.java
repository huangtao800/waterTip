package com.teami.watertip;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import com.teami.watertip.R.drawable;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendActivity extends Activity {
	private static int count;

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
		//int eventType=-1;
		ArrayList<Recommend> recommends=new ArrayList<Recommend>();
		int eventType=-1;
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
//				allScores.next();
//			}
//		} catch (XmlPullParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//				
//		title.setText(recommends.get(0).getTitle());
//		image.setImageDrawable(getResources().getDrawable(R.drawable.clear));
//		detail.setText(recommends.get(0).getDetail());
		
		
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
