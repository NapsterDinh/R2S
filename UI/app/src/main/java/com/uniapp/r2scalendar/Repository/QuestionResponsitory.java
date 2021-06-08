package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface QuestionResponsitory {
    @GET("question")
    Call<List<QuestionResponse>> getAll();

    @GET("question/search")
    Call<List<QuestionResponse>> searchQuestion(@Query("TopicName") String topicName);

   @PUT("question")
    Call<QuestionResponse> updateQuestion(@Query("QuestionID") int questionID, @Query("QuestionContent") String questionContent);

   @POST("question")
   Call<QuestionResponse> createQuestion(@Query("TopicID") int topicID, @Query("QuestionContent") String questionContent);

   @DELETE("question")
    Call<QuestionResponse> deleteQuestion(@Query("QuestionID") int questionID);



}
