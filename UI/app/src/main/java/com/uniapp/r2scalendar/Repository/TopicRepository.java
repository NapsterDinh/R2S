package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TopicRepository {
    @GET("topic")
    Call<List<Topic>> getAll();
}
