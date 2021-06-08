package com.uniapp.r2scalendar.Utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.MPPointF;
import com.uniapp.r2scalendar.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Chart {
    static String[] setLabel = {"Strongly disagree", "Disagree", "Neutral", "Agree", "Strongly agree"};

    //static final int[] MY_COLORS = {R.color.strongly_disagree,R.color.disagree, R.color.neutral, R.color.agree,R.color.strongly_agree};
    static final  int[] MY_COLORS = {Color.rgb(247, 193, 181), Color.rgb(245, 144, 122), Color.rgb(255, 103, 69),
            Color.rgb(253, 125, 85), Color.rgb(252, 60, 0)};

    static ArrayList<Integer> colors = new ArrayList<>();

    public static PieChart getInstance(PieChart pieChart, ArrayList<Integer> list,boolean isHide) {
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 10);
        pieChart.setDragDecelerationFrictionCoef(0.92f);
        pieChart.setCenterText("");

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);

        //pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        pieChart.getLegend().setEnabled(false);


        pieChart.setEntryLabelColor(Color.WHITE);

        pieChart.setEntryLabelTextSize(11f);

        /* chart.setDrawMarkers(false); // To remove markers when click
        chart.setDrawEntryLabels(false); // To remove labels from piece of pie
        chart.getDescription().setEnabled(false); // To remove description of pie*/

        setData(pieChart, list);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        pieChart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);
        pieChart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);

        //Rkt pls comment below value if u dont need any Animate in pie chart
        pieChart.animateXY(1400, 1400);;

        hideLabel(isHide,pieChart);

        return pieChart;
    }

    private static void setData(PieChart pieChart, ArrayList<Integer> list) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        int sum=0;

        for(int j=0; j<list.size();j++)
        {
            sum = sum+list.get(j);
        }

        for(int i=0; i<list.size();i++)
        {
            entries.add(new PieEntry( ((float) list.get(i)/sum)*100, setLabel[i]));
        }


        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 0));
        dataSet.setSelectionShift(5f);

        for(int c: MY_COLORS) colors.add(c);

        dataSet.setColors(colors);


        PieData data = new PieData(dataSet);
        data.setValueFormatter(new MyDecimalFormator(new DecimalFormat("###,###,##0.0")));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);


        pieChart.setData(data);


        pieChart.highlightValues(null);

        pieChart.invalidate();
    }
    public static void hideLabel(boolean isHide,PieChart pieChart)
    {
        if(isHide)
        {
            pieChart.setDrawSliceText(false);
        }
    }
}
