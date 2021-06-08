package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;
import com.uniapp.r2scalendar.Repository.TopicRepository;
import com.uniapp.r2scalendar.View.IAddQuestionView;

import java.util.List;

public interface ITopicController {
    void getList();
}
