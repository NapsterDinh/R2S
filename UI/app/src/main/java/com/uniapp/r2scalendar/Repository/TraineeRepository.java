package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.Trainee;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TraineeRepository {
    @GET("trainee")
    Call<Trainee> getAll();

    @POST("trainee")
    Call<Trainee> insertTrainee(@Body Trainee trainee);
}
