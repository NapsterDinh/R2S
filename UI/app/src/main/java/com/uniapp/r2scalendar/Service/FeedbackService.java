package com.uniapp.r2scalendar.Service;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.uniapp.r2scalendar.Controller.IFeedbackController;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.Feedback;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Repository.AssignmentRepository;
import com.uniapp.r2scalendar.Repository.ClassRepository;
import com.uniapp.r2scalendar.Repository.FeedbackRepository;
import com.uniapp.r2scalendar.Repository.ModuleRepository;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.IAddModuleView;
import com.uniapp.r2scalendar.View.IFeedbackView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackService {
    IFeedbackController iFeedbackController;
    IFeedbackView iFeedbackView;
    HashMap<String,String> global_user;

    public FeedbackService(IFeedbackController iFeedbackController,  IFeedbackView iFeedbackView) {
        this.iFeedbackController = iFeedbackController;
        this.iFeedbackView = iFeedbackView;
        global_user = GlobalUser.getInstance();
    }

    public FeedbackService() {

    }

    public FeedbackService(IAddModuleView addModuleView, View view) {
    }

    public void getAllFeedbackTitleforSpiner(IAddModuleView addModuleView, View view) {
        try {
            FeedbackRepository feedbackRepository = RetrofitClient.Client().create(FeedbackRepository.class);
            Call<List<Feedback>> feedbackCall = feedbackRepository.getAll();
            feedbackCall.enqueue(new Callback<List<Feedback>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    addModuleView.loadSpinnerFeedback(response.body(), view);
                    addModuleView.setFeedbackTitleSelected();
                }

                @Override
                public void onFailure(Call<List<Feedback>> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAll() {

            try {
                ClassRepository classRepository = RetrofitClient.Client().create(ClassRepository.class);
                Call<List<ClassResponse>> listCall = classRepository.getAll("true");
                listCall.enqueue(new Callback<List<ClassResponse>>() {
                    @Override
                    public void onResponse(Call<List<ClassResponse>> call, Response<List<ClassResponse>> response) {
                        Log.e("Response Status", String.valueOf(response.code()));

                        iFeedbackController.setListSpinner(false,response.body(),null);
                    }

                    @Override
                    public void onFailure(Call<List<ClassResponse>> call, Throwable t) {
                        iFeedbackController.onFailureResponse(t.getMessage());

                    }
                });
                ModuleRepository moduleRepository = RetrofitClient.Client().create(ModuleRepository.class);
                Call<List<ModuleResponse>> listCall1 = moduleRepository.getAll("true");
                listCall1.enqueue(new Callback<List<ModuleResponse>>() {
                    @Override
                    public void onResponse(Call<List<ModuleResponse>> call, Response<List<ModuleResponse>> response) {
                        Log.e("Response Status", String.valueOf(response.code()));

                        iFeedbackController.setListSpinner(true,null,response.body());
                    }

                    @Override
                    public void onFailure(Call<List<ModuleResponse>> call, Throwable t) {
                        iFeedbackController.onFailureResponse(t.getMessage());

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
