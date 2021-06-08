package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.AssignmentRequest;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.Model.MessageResponse;
import com.uniapp.r2scalendar.Model.Trainee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AssignmentRepository {
    @GET("assignment/{idUser}/{role}")
    Call<List<AssignmentResponse>> getAll(@Path("idUser") String idUser, @Path("role") String roleUser);

    @GET("assignment/search/{idUser}/{role}/{str_search}")
    Call<List<AssignmentResponse>> searchAssignment(@Path("idUser") String idUser, @Path("role") String roleUser,@Path("str_search") String str_search);

    @HTTP(method = "DELETE", path = "assignment/delete/{isFirstStep}", hasBody = true)
    //@DELETE("assignment/delete/{isFirstStep}")
    Call<MessageResponse> deleteAssignment(@Path("isFirstStep") boolean isFirstStep,@Body AssignmentResponse assignmentResponse);

    @POST("assignment/add")
    Call<MessageResponse> addAssignment(@Body AssignmentRequest assignmentRequest);

    //@PUT("assignment/edit/{new}/{old}")
    @HTTP(method = "PUT", path = "assignment/edit", hasBody = true)
    Call<MessageResponse> editAssignment(@Body List<AssignmentRequest> assignmentRequests);


}
