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
    //��������ʾ�ĺ���
    private String lineInfoString = "";
    private String dateString = "";
    private String numOfWaterString = "";  
    private ArrayList<String[]> list;
	private double maxValue = 0; //���ݵ����ֵ
	private int color;
 	
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		historyModel = HistoryModel.getInstance();
		init(); //��ʼ�����
		//��ʾĿǰ��һ��������Ϣ
		String[] titles = new String[] {lineInfoString};
		//List�м�������
		List<double[]> x = new ArrayList<double[]>();
		//X������ʾ����Ŀ 1,2,3,4,5.... 1�������м�������Ϣ
		double[] xInfo = new double[list.size()];
		int count = 1;
		for (int i = 0; i < list.size(); i++){
			xInfo[i] = count;
			count++;
		}
 
		//�������� 1
		for (int i = 0; i < titles.length; i++) {
			x.add(xInfo);
		}
		//���߸������ֵ
		double[] xValue = new double[list.size()];
		for (int i = 0; i < xValue.length; i++){
			xValue[i] = Double.parseDouble((list.get(i))[1]);
			//���������ֵ����maxValue
			if (xValue[i] > maxValue){
				maxValue = xValue[i];
			}
			}
		maxValue = maxValue + (maxValue/8);
 
		List<double[]> values = new ArrayList<double[]>();
		values.add(xValue);
		
		color = getResources().getColor(R.color.font_color);
		int[] colors = new int[] {color};//���ߵ���ɫ
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE}; //���ߵ���ʽ
		XYMultipleSeriesRenderer renderer = historyModel.buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
		}
		
		//ͼ���ϵļ����ԣ����⣬�����꣬�����꣬��������ʼ����ֹλ�ã���������ʼ����ֹΪλ��
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
    	renderer.setYTitle(numOfWaterString);// ����Y������
		renderer.setXTitle(dateString);
		renderer.setXAxisMin(0.5);// ����X�����СֵΪ0.5
		renderer.setXAxisMax(7.5);// ����X������ֵΪ5
		renderer.setYAxisMin(0);// ����Y�����СֵΪ0
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.WHITE);
		renderer.setMarginsColor(Color.WHITE);
		renderer.setYAxisMax(maxValue);// ����Y�����ֵ
		//������ʾ��Ӧλ���ϵ���ֵ
//		renderer.setDisplayChartValues(true);
		renderer.getSeriesRendererAt(0).setDisplayChartValues(true); 
		// ����x,y����ʾ������,Ĭ���� Align.CENTER
        renderer.setXLabelsAlign(Align.CENTER);
        renderer.setYLabelsAlign(Align.RIGHT);
        // ����������,�����ɫ
//        renderer.setAxesColor(Color.DKGRAY);
        // ��ʾ����
        renderer.setShowGrid(true);
        // ����x,y���ϵĿ̶ȵ���ɫ
//        renderer.setLabelsColor(Color.DKGRAY);
	    renderer.setXLabels(0);// ����X����ʾ�Ŀ̶ȱ�ǩ�ĸ���
	    renderer.setBarSpacing(1);
	    renderer.setXLabels(0);// ����X����ʾ�Ŀ̶ȱ�ǩ�ĸ���
	    int tempNum = 1;
	    for (String[] dataString : list){
	    	renderer.addXTextLabel(tempNum,dataString[0]); 
	    	tempNum++;
	    }
	}
}
