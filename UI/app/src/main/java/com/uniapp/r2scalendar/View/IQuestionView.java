package com.uniapp.r2scalendar.View;

import android.view.View;

import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;

import java.util.List;

public interface IQuestionView {

    void initVariable(View v);
    void displayItem(View v, List<QuestionResponse> questionResponseList);
    void loadSpinner(List<Topic> topicList, View view);
    void displayProgressDialog(View view);
    void disableProgressDialog();




}
