package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;
import com.uniapp.r2scalendar.Repository.TopicRepository;
import com.uniapp.r2scalendar.Service.QuestionService;
import com.uniapp.r2scalendar.Service.TopicService;
import com.uniapp.r2scalendar.View.IAddQuestionView;
import com.uniapp.r2scalendar.View.IQuestionView;

import java.util.List;

public class TopicController implements ITopicController {
    private TopicService topicService;
    private IAddQuestionView addQuestionView;
    private View view;

    public TopicController(IAddQuestionView addQuestionView, View view) {
        this.addQuestionView = addQuestionView;
        this.view = view;
        topicService = new TopicService();
    }

    @Override
    public void getList () {
        topicService.getAll(addQuestionView, view);
    }
}
