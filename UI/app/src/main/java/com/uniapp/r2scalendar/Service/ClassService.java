package com.uniapp.r2scalendar.Service;

import android.util.Log;
import android.view.View;

import com.uniapp.r2scalendar.Controller.IAssignmentController;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.MessageResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;
import com.uniapp.r2scalendar.Repository.ClassRepository;
import com.uniapp.r2scalendar.Repository.TrainerRepository;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.IAddClassView;
import com.uniapp.r2scalendar.View.IClassView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassService {
    private IAssignmentController iAssignmentController;
    private IClassView classView;
    private View view;

    HashMap<String,String> global_user;

    public ClassService(IAssignmentController iAssignmentController){
        this.iAssignmentController = iAssignmentController;
        global_user = GlobalUser.getInstance();
    }

    public ClassService(IClassView classView, View view){
        this.classView = classView;
        this.view = view;
    }


    public void getSpinnerClassForAssignment() {
        try {
            ClassRepository classRepository = RetrofitClient.Client().create(ClassRepository.class);
            Call<List<ClassResponse>> listCall = classRepository.getAll("false");
            listCall.enqueue(new Callback<List<ClassResponse>>() {
                @Override
                public void onResponse(Call<List<ClassResponse>> call, Response<List<ClassResponse>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    iAssignmentController.setListSpinner("Class",null,response.body(),null);
                }

                @Override
                public void onFailure(Call<List<ClassResponse>> call, Throwable t) {
                    iAssignmentController.onFailureResponseAddEdit(t.getMessage());
                    //iAssignmentController.onFailureResponse(assignmentRepository.getAll(global_user.get("idUser"),global_user.get("role")).request().url().toString());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllClass() {
        try {
            ClassRepository classRepository = RetrofitClient.Client().create(ClassRepository.class);
            Call<List<ClassResponse>> listCall = classRepository.getAll();
            listCall.enqueue(new Callback<List<ClassResponse>>() {
                @Override
                public void onResponse(Call<List<ClassResponse>> call, Response<List<ClassResponse>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    classView.displayItem(view, response.body());
                    classView.disableProgressDialog();
                }

                @Override
                public void onFailure(Call<List<ClassResponse>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleAddClass(ClassResponse classResponse, IAddClassView addClassView) {
        try {
            ClassRepository classRepository = RetrofitClient.Client().create(ClassRepository.class);
            Call<MessageResponse> listCall = classRepository.insertClass(classResponse);
            listCall.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    addClassView.disableProgressDialog();
                    addClassView.onSuccess("Add Success!", response.body());
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleEditClass(ClassResponse classResponse, IAddClassView addClassView) {
        try {
            ClassRepository classRepository = RetrofitClient.Client().create(ClassRepository.class);
            Call<ClassResponse> listCall = classRepository.editClass(classResponse);
            listCall.enqueue(new Callback<ClassResponse>() {
                @Override
                public void onResponse(Call<ClassResponse> call, Response<ClassResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    addClassView.disableProgressDialog();
                    addClassView.onSuccess("Update success!", null);
                }
                @Override
                public void onFailure(Call<ClassResponse> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteClass(String ClassID, IClassView classView) {
        try {
            ClassRepository classRepository = RetrofitClient.Client().create(ClassRepository.class);
            Call<ClassResponse> listCall = classRepository.deleteClass(ClassID);
            listCall.enqueue(new Callback<ClassResponse>() {
                @Override
                public void onResponse(Call<ClassResponse> call, Response<ClassResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    classView.disableProgressDialog();
                    classView.onSuccess("Delete success!");
                }
                @Override
                public void onFailure(Call<ClassResponse> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
