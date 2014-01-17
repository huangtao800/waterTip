package com.teami.watertip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.xmlpull.v1.XmlPullParserException;

import com.teami.model.HistoryModel;
import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class HistoryActivity extends BaseActivity{
	private String title = "喝水历史统计";
	private double[] xContent;
	private double[] yContent;
	private double minXStart=0.5; private double maxXStart=7.5;
	private double minYStart=0;   private double maxYStart=10;
	
	private final int xNumber=7;
	private HistoryModel barCharModel;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        barCharModel=HistoryModel.getInstance();
        //可以是多条折线混合，titles是对每条折线的说明
        //当前情况下只有一天折线，表示每日喝水量
        String[] titles = new String[] { title };
        
        //横坐标显示的内容,显示日期
        getHistory();
        List<double[]> x = new ArrayList<double[]>();
        for (int i = 0; i < titles.length; i++) {
            x.add(xContent);
        }
        
        //纵坐标 图标上的数值，每个对应title中的一个
        List<double[]> values = new ArrayList<double[]>();
        for(int i=0;i<yContent.length;i++){
        	if(yContent[i]>maxYStart){
        		maxYStart = yContent[i];
        	}
        }
        values.add(yContent);
        
        
        //每条线的颜色
        int[] colors = new int[] {getResources().getColor(R.color.font_color) };
        //每个线上点的形状
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE };
        
        //渲染器
        XYMultipleSeriesRenderer renderer = barCharModel.buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        
        //设置图上的点为实心 并且显示数值
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
            ((XYSeriesRenderer)renderer.getSeriesRendererAt(i)).setDisplayChartValues(true);
        }
        
        //图标上的简单属性，标题，横坐标，纵坐标，横坐标起始和终止位置，纵坐标起始和终止为位置
        barCharModel.setChartSettings(renderer, "Average temperature", "Month", "Temperature", minXStart, maxXStart,
        		minYStart, maxYStart, Color.LTGRAY, Color.LTGRAY);
        
        //横纵坐标显示多少个点，根据最大最小值自动分配
        renderer.setXLabels(xNumber);
        renderer.setYLabels((int)(maxYStart-minYStart+1));
        
        //是否显示网格
        renderer.setShowGrid(true);
        //设置刻度线与刻度线之间的相对位置
        renderer.setXLabelsAlign(Align.RIGHT);
        renderer.setYLabelsAlign(Align.RIGHT);

        //是否显示放大缩小按钮
        renderer.setZoomButtonsVisible(true);
        //设置拖动时的最大最小值
        renderer.setPanLimits(new double[] { -10, 20, minYStart-10, maxYStart+10 });
        //设置放大缩小时的最大最小值
        renderer.setZoomLimits(new double[] { -10, 20, minYStart-10, maxYStart+10 });

        View view = ChartFactory.getLineChartView(this, barCharModel.buildDataset(titles, x, values), renderer);
        view.setBackgroundColor(Color.WHITE);
        setContentView(view);
    }

	private void getHistory(){
		XmlResourceParser tipParser=getResources().getXml(R.xml.tips);
		ArrayList<ArrayList<Double>> xy=new ArrayList<ArrayList<Double>>();
		try {
			xy=barCharModel.parseTipList(tipParser);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<Double> xArrayList = new ArrayList<Double>();
		xArrayList = xy.get(0);
		xContent = new double[xArrayList.size()];
		for(int i=0;i<xArrayList.size();i++){
			xContent[i] = xArrayList.get(i);
		}
		
		ArrayList<Double> yArrayList = new ArrayList<Double>();
		yArrayList = xy.get(1);
		yContent = new double[yArrayList.size()];
		for(int i=0;i<yArrayList.size();i++){
			yContent[i] = yArrayList.get(i);
		}
		
	}
   
}
