package com.teami.watertip;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.teami.model.DatabaseHelper;
import com.teami.model.HistoryModel;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.View;

public class HistoryActivity extends BaseActivity{
	private HistoryModel historyModel ;
    private String title = "";
    //折线所表示的含义
    private String lineInfoString = "";
    private String dateString = "";
    private String numOfWaterString = "";  
    private ArrayList<String[]> list;
	private double maxValue = 0; //数据的最大值
	private int color;
 	
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		historyModel = HistoryModel.getInstance();
		init(); //初始化组件
		//表示目前就一条折线信息
		String[] titles = new String[] {lineInfoString};
		//List有几条数据
		List<double[]> x = new ArrayList<double[]>();
		//X轴所显示的数目 1,2,3,4,5.... 1条数据有几个点信息
		double[] xInfo = new double[list.size()];
		int count = 1;
		for (int i = 0; i < list.size(); i++){
			xInfo[i] = count;
			count++;
		}
 
		//折线数量 1
		for (int i = 0; i < titles.length; i++) {
			x.add(xInfo);
		}
		//折线各个点的值
		double[] xValue = new double[list.size()];
		for (int i = 0; i < xValue.length; i++){
			xValue[i] = Double.parseDouble((list.get(i))[1]);
			//把数据最大值赋给maxValue
			if (xValue[i] > maxValue){
				maxValue = xValue[i];
			}
			}
		maxValue = maxValue + (maxValue/8);
 
		List<double[]> values = new ArrayList<double[]>();
		values.add(xValue);
		
		color = getResources().getColor(R.color.font_color);
		int[] colors = new int[] {color};//折线的颜色
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE}; //折线的样式
		XYMultipleSeriesRenderer renderer = historyModel.buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
		}
		
		//图标上的简单属性，标题，横坐标，纵坐标，横坐标起始和终止位置，纵坐标起始和终止为位置
		setChartSettings(renderer, title, "", "", 0.5, 12.5, -10, 40, Color.DKGRAY, Color.DKGRAY);
		renderer.setZoomButtonsVisible(true);
		View view = ChartFactory.getLineChartView(this, historyModel.buildDataset(titles, x, values), renderer);
		view.setBackgroundColor(Color.WHITE);
		setContentView(view);
	}
 
    
    private void init() throws IndexOutOfBoundsException{
    	
		
		InputStream inputStream=getResources().openRawResource(R.raw.record);
		XmlPullParserFactory factory=null;
		XmlPullParser recordParser=null;
		try {
			factory=XmlPullParserFactory.newInstance();
			recordParser=factory.newPullParser();
			recordParser.setInput(inputStream, "utf-8");
		} catch (XmlPullParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DatabaseHelper dbHelper=new DatabaseHelper(HistoryActivity.this, "water.db",2);
		SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
		list=historyModel.getRecordList(sqliteDatabase);
		
		title =  getResources().getString(R.string.historyTitle);
        lineInfoString = getResources().getString(R.string.historyaLineInfo);
        dateString = getResources().getString(R.string.date);
        numOfWaterString = getResources().getString(R.string.numOfWater);

		
	}
    
    public void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
			String yTitle, double xMin, double xMax, double yMin, double yMax, 
			int axesColor, int labelsColor) {
		renderer.setChartTitle(title);
    	renderer.setYTitle(numOfWaterString);// 设置Y轴名称
		renderer.setXTitle(dateString);
		renderer.setXAxisMin(0.5);// 设置X轴的最小值为0.5
		renderer.setXAxisMax(7.5);// 设置X轴的最大值为5
		renderer.setYAxisMin(0);// 设置Y轴的最小值为0
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.WHITE);
		renderer.setMarginsColor(Color.WHITE);
		renderer.setYAxisMax(maxValue);// 设置Y轴最大值
		//设置显示对应位置上的数值
//		renderer.setDisplayChartValues(true);
		renderer.getSeriesRendererAt(0).setDisplayChartValues(true); 
		// 设置x,y轴显示的排列,默认是 Align.CENTER
        renderer.setXLabelsAlign(Align.CENTER);
        renderer.setYLabelsAlign(Align.RIGHT);
        // 设置坐标轴,轴的颜色
//        renderer.setAxesColor(Color.DKGRAY);
        // 显示网格
        renderer.setShowGrid(true);
        // 设置x,y轴上的刻度的颜色
//        renderer.setLabelsColor(Color.DKGRAY);
	    renderer.setXLabels(0);// 设置X轴显示的刻度标签的个数
	    renderer.setBarSpacing(1);
	    renderer.setXLabels(0);// 设置X轴显示的刻度标签的个数
	    int tempNum = 1;
	    for (String[] dataString : list){
	    	renderer.addXTextLabel(tempNum,dataString[0]); 
	    	tempNum++;
	    }
	}
}
