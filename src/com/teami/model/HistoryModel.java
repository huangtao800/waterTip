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
import android.graphics.Color;

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

	    private void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
	        //设置坐标轴标题大小
	    	renderer.setAxisTitleTextSize(16);
	    	//设置图标标题大小
	        renderer.setChartTitleTextSize(20);
	        //设置轴标签文本大小
	        renderer.setLabelsTextSize(15);
	        //设置图例文本大小
	        renderer.setLegendTextSize(15);
	        renderer.setPointSize(5f);
	        //设置四边留白
	        renderer.setMargins(new int[] { 20, 30, 15, 20 });
	        int length = colors.length;
	        for (int i = 0; i < length; i++) {
	            XYSeriesRenderer r = new XYSeriesRenderer();
	            r.setColor(colors[i]);
	            r.setPointStyle(styles[i]);
	            renderer.addSeriesRenderer(r);
	        }
	    }

	    public void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle, String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor) {
	        renderer.setChartTitle(title);
	        renderer.setXTitle(xTitle);
	        renderer.setYTitle(yTitle);
	        renderer.setXAxisMin(xMin);
	        renderer.setXAxisMax(xMax);
	        renderer.setYAxisMin(yMin);
	        renderer.setYAxisMax(yMax);
	        renderer.setAxesColor(axesColor);
	        renderer.setLabelsColor(labelsColor);
	        renderer.setApplyBackgroundColor(true);
	        renderer.setBackgroundColor(Color.WHITE);
	        renderer.setMarginsColor(Color.WHITE);
	    }

	    public XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues, List<double[]> yValues) {
	        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	        addXYSeries(dataset, titles, xValues, yValues, 0);
	        return dataset;
	    }

	    public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, 
	    		List<double[]> xValues,List<double[]> yValues, int scale) {
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
	    
	    public ArrayList<ArrayList<Double>> parseTipList(XmlResourceParser parser) throws XmlPullParserException, IOException{
			ArrayList<ArrayList<Double>> resultList=new ArrayList<ArrayList<Double>>();
            ArrayList<Double> x = new ArrayList<Double>();
            ArrayList<Double> y = new ArrayList<Double>();
 			
			int eventType=-1;
			while(eventType!=XmlResourceParser.END_DOCUMENT){
				if(eventType==XmlResourceParser.START_TAG){
					String strName=parser.getName();
					if(strName.equals("record")){
						String month=parser.getAttributeValue(null, "month");
						String day = parser.getAttributeValue(null,"day");
						String temp = month+"."+day;
						double tempX = Double.parseDouble(temp);
						x.add(tempX);
						
						String num = parser.getAttributeValue(null, "num");
						double tempY = Double.parseDouble(num);
						y.add(tempY);
					}
				}
				eventType=parser.next();
			}
			
			resultList.add(x);
			resultList.add(y);
			
			return resultList;
		}
}
