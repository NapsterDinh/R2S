package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;
import com.uniapp.r2scalendar.View.IAddQuestionView;

import java.util.List;
import java.util.Map;

public interface IQuestionController {

    void searchQuestion(Map<String,Object> params);
    void getAll();
    void displayItem(List<QuestionResponse> questionResponseList);
    void onFailureResponse(String message);
    void updateQuestion(Map<String,Object> params,IAddQuestionView addQuestionView,View view);
    void deleteQuestion(Map<String,Object> params);
    void createQuestion(Map<String,Object> params, IAddQuestionView addQuestionView);
    void getList();
    void showSearchQuestion(List<QuestionResponse> questionResponseList);

}
