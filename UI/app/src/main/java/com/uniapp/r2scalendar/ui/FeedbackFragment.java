package com.uniapp.r2scalendar.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.uniapp.r2scalendar.Controller.FeedBackController;
import com.uniapp.r2scalendar.Controller.IFeedbackController;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.View.IFeedbackView;
import com.uniapp.r2scalendar.adapter.FeedbackPagerAdapter;

import java.util.List;

public class FeedbackFragment extends Fragment implements IFeedbackView {

    FragmentActivity fragmentActivity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    IFeedbackController iFeedbackController;
    Spinner spinnerClass, spinnerModuleStatistics;
    Button btShowOverview, btShowComment, btViewDetail;
    ProgressDialog progressDialog;
    View view;

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private static int countResponse =0;

    public FeedbackFragment(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        fragmentManager = fragmentActivity.getSupportFragmentManager();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Loading data.......");
        progressDialog.show();

        this.view = view;

        iFeedbackController = new FeedBackController(this,view);

        initVariable(view);

        iFeedbackController.getListSpinner();

    }

    @Override
    public void initVariable(View v) {
        spinnerClass = v.findViewById(R.id.spinnerClass);
        spinnerModuleStatistics = v.findViewById(R.id.spinnerModuleStatistic);
        btShowOverview = v.findViewById(R.id.btShowOverview);
        btShowComment = v.findViewById(R.id.btshowConmment);
        btViewDetail = v.findViewById(R.id.btShowDetail);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = v.findViewById(R.id.vpPager);
        pagerAdapter = new FeedbackPagerAdapter(fragmentManager);
        mPager.setAdapter(pagerAdapter);

        btShowComment.setVisibility(View.INVISIBLE);


        btViewDetail.setOnClickListener(v1 ->{
            countResponse =0;
            btShowComment.setVisibility(View.VISIBLE);
            mPager.setCurrentItem(2,false);
        });

        btShowOverview.setOnClickListener(v1 -> {
            countResponse =0;
            btShowComment.setVisibility(View.INVISIBLE);
            mPager.setCurrentItem(0,false);
        });

        btShowComment.setOnClickListener(v1-> {
            pagerAdapter = new FeedbackPagerAdapter(fragmentManager);
            mPager.setAdapter(pagerAdapter);
            pagerAdapter.notifyDataSetChanged();
            countResponse =0;
            btShowComment.setVisibility(View.INVISIBLE);
            mPager.setCurrentItem(3,false);
        });

    }

    @Override
    public void setListSpinner(boolean isModule, List<ClassResponse> classResponseList, List<ModuleResponse> moduleResponseList) {
        countResponse++;
        if(isModule)
        {
            //module
            ArrayAdapter<ModuleResponse> moduleResponseArrayAdapter = new ArrayAdapter<ModuleResponse>(view.getContext(),
                    android.R.layout.simple_spinner_item, moduleResponseList
            );

            moduleResponseArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerModuleStatistics.setAdapter(moduleResponseArrayAdapter);
        }
        else
        {
            //class
            ArrayAdapter<ClassResponse> classResponseArrayAdapter = new ArrayAdapter<ClassResponse>(view.getContext(),
                    android.R.layout.simple_spinner_item, classResponseList
            );

            classResponseArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerClass.setAdapter(classResponseArrayAdapter);
        }
        if(countResponse==2)
        {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onFailureResponse(String message) {
        Toast.makeText(view.getContext(),message,Toast.LENGTH_LONG).show();
    }


}