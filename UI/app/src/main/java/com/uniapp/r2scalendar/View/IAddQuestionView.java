package com.uniapp.r2scalendar.View;

import android.view.View;

import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;

import java.util.List;

public interface IAddQuestionView {
    void loadSpinner(List<Topic> topicList, View view);
    void setTopicSelected();
    void onSuccess(String message);
    void displayProgressDialog(View view);
    void disableProgressDialog();
}
