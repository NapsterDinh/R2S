package com.uniapp.r2scalendar.Service;

import android.util.Log;

import com.uniapp.r2scalendar.Controller.IAssignmentController;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;
import com.uniapp.r2scalendar.Repository.AssignmentRepository;
import com.uniapp.r2scalendar.Repository.TrainerRepository;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.IAddAssignmentVIew;
import com.uniapp.r2scalendar.View.IAssignmentView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerService {
    private IAssignmentController iAssignmentController;

    HashMap<String,String> global_user;

    public TrainerService(IAssignmentController iAssignmentController){
        this.iAssignmentController = iAssignmentController;
        global_user = GlobalUser.getInstance();
    }


    public void getSpinnerTrainerForAssignment() {
        try {
            TrainerRepository trainerRepository = RetrofitClient.Client().create(TrainerRepository.class);
            Call<List<TrainerResponse>> listCall = trainerRepository.getAll();
            listCall.enqueue(new Callback<List<TrainerResponse>>() {
                @Override
                public void onResponse(Call<List<TrainerResponse>> call, Response<List<TrainerResponse>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    iAssignmentController.setListSpinner("Trainer",response.body(),null,null);
                }

                @Override
                public void onFailure(Call<List<TrainerResponse>> call, Throwable t) {
                    iAssignmentController.onFailureResponseAddEdit(t.getMessage());
                    //iAssignmentController.onFailureResponse(assignmentRepository.getAll(global_user.get("idUser"),global_user.get("role")).request().url().toString());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
