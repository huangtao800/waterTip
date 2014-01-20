package com.teami.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HistoryModel {
	private static HistoryModel instanceModel;

	private HistoryModel() {

	}

	public static HistoryModel getInstance() {
		if (instanceModel == null) {
			instanceModel = new HistoryModel();
		}
		return instanceModel;
	}

	public XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles);
		return renderer;
	}

	public void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors,
			PointStyle[] styles) {
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(12); // ±ê×¢×Ö
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

	public XYMultipleSeriesDataset buildDataset(String[] titles,
			List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		addXYSeries(dataset, titles, xValues, yValues, 0);
		return dataset;
	}

	public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
			List<double[]> xValues, List<double[]> yValues, int scale) {
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

	public ArrayList<String[]> getRecordList(SQLiteDatabase db) {
		Cursor cursor=db.query("cups", new String[]{"year","month","day"},null , null, null, null, "year,month,day desc");
		ArrayList<String[]> resultList=new ArrayList<String[]>();
		while(cursor.moveToNext()){
			String date=cursor.getString(cursor.getColumnIndex("date"));
			int num=cursor.getInt(cursor.getColumnIndex("day"));
			resultList.add(new String[]{date,num+""});
		}
		return resultList;
	}

//	public ArrayList<String[]> parseRecordList(XmlPullParser parser)
//			throws XmlPullParserException, IOException {
//		ArrayList<String[]> resultList = new ArrayList<String[]>();
//
//		int eventType = -1;
//		while (eventType != XmlPullParser.END_DOCUMENT) {
//			if (eventType == XmlPullParser.START_TAG) {
//				String strName = parser.getName();
//				if (strName.equals("record")) {
//					String day = parser.getAttributeValue(null, "day");
//					String num = parser.getAttributeValue(null, "num");
//
//					resultList.add(new String[] { day, num });
//				}
//			}
//			eventType = parser.next();
//		}
//		return resultList;
//	}
}
