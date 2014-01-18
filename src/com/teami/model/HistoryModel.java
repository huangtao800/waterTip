package com.teami.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.xmlpull.v1.XmlPullParserException;
import android.content.res.XmlResourceParser;

public class HistoryModel {
	private static HistoryModel instanceModel;
	
	private HistoryModel(){
		
	}
	
	public static HistoryModel getInstance(){
		if(instanceModel==null){
			instanceModel=new HistoryModel();
		}
		return instanceModel;
	}
	
	public XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles);
		return renderer;
	}
	
	public void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, 
			PointStyle[] styles) {
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(12); //±ê×¢×Ö
		renderer.setLegendTextSize(15);
		renderer.setPointSize(5f);
		renderer.setMargins(new int[] { 20, 30, 15, 20 });
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			r.setDisplayChartValuesDistance(25);
			renderer.addSeriesRenderer(r);
		}
	}

	

	public XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		addXYSeries(dataset, titles, xValues, yValues, 0);
		return dataset;
	}
	
	public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues, List<double[]> yValues, int scale) {
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			XYSeries series = new XYSeries(titles[i], scale);
			double[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
	}
	    
	public ArrayList<String[]> parseRecordList(XmlResourceParser parser) throws XmlPullParserException, IOException{
			ArrayList<String[]> resultList=new ArrayList<String[]>();
			
			int eventType=-1;
			while(eventType!=XmlResourceParser.END_DOCUMENT){
				if(eventType==XmlResourceParser.START_TAG){
					String strName=parser.getName();
					if(strName.equals("record")){
						String day = parser.getAttributeValue(null,"day");
						String num = parser.getAttributeValue(null, "num");
						
						resultList.add(new String[]{day,num});
					}
				}
				eventType=parser.next();
			}
			return resultList;
	}
}
