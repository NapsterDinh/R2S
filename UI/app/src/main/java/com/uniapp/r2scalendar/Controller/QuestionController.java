package com.uniapp.r2scalendar.Controller;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Service.QuestionService;
import com.uniapp.r2scalendar.View.IAddQuestionView;
import com.uniapp.r2scalendar.View.IQuestionView;

import java.util.List;
import java.util.Map;

public class QuestionController implements IQuestionController {
    private IQuestionView iQuestionView;
    private View view;
    private QuestionService questionService;
    private IAddQuestionView addQuestionView;



    public QuestionController(IQuestionView iQuestionView, IAddQuestionView addQuestionView, View view) {
        this.iQuestionView = iQuestionView;
        this.addQuestionView = addQuestionView;
        this.view = view;
        questionService = new QuestionService(this, iQuestionView,view);
    }

    @Override
    public void searchQuestion(Map<String,Object> params) {

        iQuestionView.displayProgressDialog(view);
        questionService.searchQuestion((String) params.get("topicName"));
    }

    @Override
    public void getAll()
    {
        iQuestionView.displayProgressDialog(view);
        questionService.getAll();
    }

    @Override
    public void displayItem(List<QuestionResponse> questionResponseList) {

        iQuestionView.displayItem(view, questionResponseList);
    }

    @Override
    public void onFailureResponse(String message) {

    }


    @Override
    public void updateQuestion(Map<String, Object> params,IAddQuestionView addQuestionView,View view) {
        addQuestionView.displayProgressDialog(view);
        questionService.updateQuestion((Integer.parseInt(params.get("questionID").toString())), (String) params.get("questionContext"),addQuestionView);
    }


    @Override
    public void deleteQuestion(Map<String, Object> params) {
        iQuestionView.displayProgressDialog(view);
        QuestionResponse question = (QuestionResponse) params.get("questionResponse");
        questionService.deleteQuestion(question.getQuestionID());
    }

    @Override
    public void createQuestion(Map<String, Object> params, IAddQuestionView addQuestionView) {
        addQuestionView.displayProgressDialog(view);
        questionService.createQuestion((String) params.get("topicID"), (String) params.get("questionContext"), addQuestionView);
    }



    @Override
    public void getList()
    {
        questionService.getAll( iQuestionView,view);
    }

    @Override
    public void showSearchQuestion(List<QuestionResponse> questionResponseList) {
        iQuestionView.displayProgressDialog(view);
        iQuestionView.displayItem(view, questionResponseList);
    }
}
