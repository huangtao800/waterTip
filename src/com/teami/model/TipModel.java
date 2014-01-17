package com.teami.model;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;

public class TipModel {
	private static TipModel instanceModel;
	private TipModel(){
		
	}
	public static TipModel getInstance(){
		if(instanceModel==null){
			instanceModel=new TipModel();
		}
		return instanceModel;
	}
	
	public ArrayList<String> parseTipList(XmlResourceParser parser) throws XmlPullParserException, IOException{
		ArrayList<String> resultList=new ArrayList<String>();
		int eventType=-1;
		while(eventType!=XmlResourceParser.END_DOCUMENT){
			if(eventType==XmlResourceParser.START_TAG){
				String strName=parser.getName();
				if(strName.equals("tip")){
					String tipContent=parser.getAttributeValue(null, "content");
					resultList.add(tipContent);
				}
			}
			eventType=parser.next();
		}
		
		return resultList;
	}
	
}
