package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.AssignmentRequest;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.Model.ClassResponse;
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

public interface ModuleRepository {
    @GET("module/{isAll}")
    Call<List<ModuleResponse>> getAll(@Path("isAll") String isAll);

    @GET("module")
    Call<List<ModuleResponse>> getAll();

    @DELETE("module")
    Call<ModuleResponse> deleteModule(@Query("Id") String moduleID);

    @POST("module")
    Call<ModuleResponse> insertModule(@Body ModuleResponse moduleResponse);

    @PUT("module")
    Call<ModuleResponse> editModule(@Body ModuleResponse moduleResponse);


}
