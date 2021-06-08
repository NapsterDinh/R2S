package com.uniapp.r2scalendar.Service;

import android.util.Log;
import android.view.View;

import com.uniapp.r2scalendar.Controller.IQuestionController;
import com.uniapp.r2scalendar.Controller.ITopicController;
import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;
import com.uniapp.r2scalendar.Repository.QuestionResponsitory;
import com.uniapp.r2scalendar.Repository.TopicRepository;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.IAddQuestionView;
import com.uniapp.r2scalendar.View.IQuestionView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopicService {

    public TopicService() {

    }


    public void getAll(IAddQuestionView addQuestionView, View view) {
        try {
            TopicRepository topicRepository = RetrofitClient.Client().create(TopicRepository.class);
            Call<List<Topic>> topicCall = topicRepository.getAll();
            topicCall.enqueue(new Callback<List<Topic>>() {
                @Override
                public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    addQuestionView.loadSpinner(response.body(), view);
                    addQuestionView.setTopicSelected();
                }

                @Override
                public void onFailure(Call<List<Topic>> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
