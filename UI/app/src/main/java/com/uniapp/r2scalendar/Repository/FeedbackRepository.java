package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.Admin;
import com.uniapp.r2scalendar.Model.Feedback;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedbackRepository {
    @GET("feedback")
    Call<List<Feedback>> getAll();
}
