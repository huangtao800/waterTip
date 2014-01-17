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
	private String title = "��ˮ��ʷͳ��";
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
        //�����Ƕ������߻�ϣ�titles�Ƕ�ÿ�����ߵ�˵��
        //��ǰ�����ֻ��һ�����ߣ���ʾÿ�պ�ˮ��
        String[] titles = new String[] { title };
        
        //��������ʾ������,��ʾ����
        getHistory();
        List<double[]> x = new ArrayList<double[]>();
        for (int i = 0; i < titles.length; i++) {
            x.add(xContent);
        }
        
        //������ ͼ���ϵ���ֵ��ÿ����Ӧtitle�е�һ��
        List<double[]> values = new ArrayList<double[]>();
        for(int i=0;i<yContent.length;i++){
        	if(yContent[i]>maxYStart){
        		maxYStart = yContent[i];
        	}
        }
        values.add(yContent);
        
        
        //ÿ���ߵ���ɫ
        int[] colors = new int[] {getResources().getColor(R.color.font_color) };
        //ÿ�����ϵ����״
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE };
        
        //��Ⱦ��
        XYMultipleSeriesRenderer renderer = barCharModel.buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        
        //����ͼ�ϵĵ�Ϊʵ�� ������ʾ��ֵ
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
            ((XYSeriesRenderer)renderer.getSeriesRendererAt(i)).setDisplayChartValues(true);
        }
        
        //ͼ���ϵļ����ԣ����⣬�����꣬�����꣬��������ʼ����ֹλ�ã���������ʼ����ֹΪλ��
        barCharModel.setChartSettings(renderer, "Average temperature", "Month", "Temperature", minXStart, maxXStart,
        		minYStart, maxYStart, Color.LTGRAY, Color.LTGRAY);
        
        //����������ʾ���ٸ��㣬���������Сֵ�Զ�����
        renderer.setXLabels(xNumber);
        renderer.setYLabels((int)(maxYStart-minYStart+1));
        
        //�Ƿ���ʾ����
        renderer.setShowGrid(true);
        //���ÿ̶�����̶���֮������λ��
        renderer.setXLabelsAlign(Align.RIGHT);
        renderer.setYLabelsAlign(Align.RIGHT);

        //�Ƿ���ʾ�Ŵ���С��ť
        renderer.setZoomButtonsVisible(true);
        //�����϶�ʱ�������Сֵ
        renderer.setPanLimits(new double[] { -10, 20, minYStart-10, maxYStart+10 });
        //���÷Ŵ���Сʱ�������Сֵ
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
