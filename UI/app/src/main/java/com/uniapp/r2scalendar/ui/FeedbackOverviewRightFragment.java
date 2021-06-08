package com.uniapp.r2scalendar.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.Chart;

import java.util.ArrayList;

public class FeedbackOverviewRightFragment extends Fragment {
    PieChart pieChart1, pieChart2, pieChart3, pieChart4;

    public FeedbackOverviewRightFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_statistics_overview_right, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieChart1 = view.findViewById(R.id.piechart1);
        pieChart2 = view.findViewById(R.id.piechart2);
        pieChart3 = view.findViewById(R.id.piechart3);
        pieChart4 = view.findViewById(R.id.piechart4);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        pieChart1 = Chart.getInstance(pieChart1,list,true);
        pieChart2 = Chart.getInstance(pieChart2,list,true);
        pieChart3 = Chart.getInstance(pieChart3,list,true);
        pieChart4 = Chart.getInstance(pieChart4,list,true);

    }
}
