package com.uniapp.r2scalendar.Service;

import android.util.Log;
import android.view.View;

import com.uniapp.r2scalendar.Controller.IQuestionController;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;
import com.uniapp.r2scalendar.Repository.QuestionResponsitory;
import com.uniapp.r2scalendar.Repository.TopicRepository;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.IAddQuestionView;
import com.uniapp.r2scalendar.View.IQuestionView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionService {
    private IQuestionController iQuestionController;
    private IQuestionView iQuestionView;
    HashMap<String,String> global_user;
    View view;

    public QuestionService(IQuestionController iQuestionController, IQuestionView iQuestionView, View view) {
        this.iQuestionController = iQuestionController;
        this.iQuestionView = iQuestionView;
        this.view = view;
        global_user = GlobalUser.getInstance();
    }

    public void searchQuestion(String topicName) {
        try {
            if (topicName.equals("all")) {
                getAll();
            } else {
                QuestionResponsitory questionResponsitory = RetrofitClient.Client().create(QuestionResponsitory.class);
                Call<List<QuestionResponse>> questionResponseCall = questionResponsitory.searchQuestion(topicName);
                questionResponseCall.enqueue(new Callback<List<QuestionResponse>>() {

                    @Override
                    public void onResponse(Call<List<QuestionResponse>> call, Response<List<QuestionResponse>> response) {
                        Log.e("Response Status", String.valueOf(response.code()));
                        iQuestionView.disableProgressDialog();
                        iQuestionController.displayItem(response.body());

                    }

                    @Override
                    public void onFailure(Call<List<QuestionResponse>> call, Throwable t) {
                        iQuestionController.onFailureResponse(t.getMessage());
                    }
                });

            }
            } catch(Exception e){
                e.printStackTrace();
            }

    }

    public void getAll() {
        try {
            QuestionResponsitory questionResponsitory = RetrofitClient.Client().create(QuestionResponsitory.class);
            Call<List<QuestionResponse>> questionResponseCall = questionResponsitory.getAll();
            questionResponseCall.enqueue(new Callback<List<QuestionResponse>>() {
                @Override
                public void onResponse(Call<List<QuestionResponse>> call, Response<List<QuestionResponse>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    iQuestionView.disableProgressDialog();
                    iQuestionController.displayItem(response.body());

                                   }

                @Override
                public void onFailure(Call<List<QuestionResponse>> call, Throwable t) {
                    iQuestionController.onFailureResponse(t.getMessage());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteQuestion(int questionID) {
            try {
                QuestionResponsitory questionResponsitory = RetrofitClient.Client().create(QuestionResponsitory.class);
                Call<QuestionResponse> questionResponseCall = questionResponsitory.deleteQuestion(questionID);
                questionResponseCall.enqueue(new Callback<QuestionResponse>() {
                    @Override
                    public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                        Log.e("Response Status", String.valueOf(response.code()));
                            System.out.println(response.body());

                        iQuestionView.disableProgressDialog();
                        iQuestionController.getAll();

                    }

                    @Override
                    public void onFailure(Call<QuestionResponse> call, Throwable t) {
                        iQuestionController.onFailureResponse(t.getMessage());
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void createQuestion(String topicID, String questionContent, IAddQuestionView addQuestionView) {
        try {
            QuestionResponsitory questionResponsitory = RetrofitClient.Client().create(QuestionResponsitory.class);
            Call<QuestionResponse> questionResponseCall = questionResponsitory.createQuestion(Integer.parseInt(topicID),questionContent);
            questionResponseCall.enqueue(new Callback<QuestionResponse>() {
                @Override
                public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    System.out.println(response.body());

                    addQuestionView.disableProgressDialog();
                    addQuestionView.onSuccess("Add Question Success!");

                }

                @Override
                public void onFailure(Call<QuestionResponse> call, Throwable t) {
                    iQuestionController.onFailureResponse(t.getMessage());
                }


            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateQuestion(int questionID, String questionContent,IAddQuestionView addQuestionView) {
        try {
            QuestionResponsitory questionResponsitory = RetrofitClient.Client().create(QuestionResponsitory.class);
            Call<QuestionResponse> questionResponseCall = questionResponsitory.updateQuestion(questionID,questionContent);
            questionResponseCall.enqueue(new Callback<QuestionResponse>() {
                @Override
                public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    System.out.println(response.body());

                    addQuestionView.disableProgressDialog();
                    addQuestionView.onSuccess("Edit Question Success!");
                }

                @Override
                public void onFailure(Call<QuestionResponse> call, Throwable t) {
                    iQuestionController.onFailureResponse(t.getMessage());
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAll(IQuestionView iQuestionView, View view) {
        try {
            TopicRepository topicRepository = RetrofitClient.Client().create(TopicRepository.class);
            Call<List<Topic>> topicCall = topicRepository.getAll();
            topicCall.enqueue(new Callback<List<Topic>>() {
                @Override
                public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    iQuestionView.disableProgressDialog();
                    iQuestionView.loadSpinner(response.body(), view);
                }

                @Override
                public void onFailure(Call<List<Topic>> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
