package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.MessageResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClassRepository {
    @GET("class/{isAll}")
    Call<List<ClassResponse>> getAll(@Path("isAll") String isAll);

    @GET("class")
    Call<List<ClassResponse>> getAll();

    @POST("class")
    Call<MessageResponse> insertClass(@Body ClassResponse classResponse);

    @PUT("class")
    Call<ClassResponse> editClass(@Body ClassResponse classResponse);

    @DELETE("class")
    Call<ClassResponse> deleteClass(@Query("Id") String ClassID);
}
