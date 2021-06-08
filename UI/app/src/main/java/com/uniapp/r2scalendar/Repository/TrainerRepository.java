package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrainerRepository {
    @GET("trainer")
    Call<List<TrainerResponse>> getAll();
}
