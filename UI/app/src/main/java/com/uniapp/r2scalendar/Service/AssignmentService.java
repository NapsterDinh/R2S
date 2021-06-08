package com.uniapp.r2scalendar.Service;

import android.util.Log;

import com.uniapp.r2scalendar.Controller.IAccountController;
import com.uniapp.r2scalendar.Controller.IAssignmentController;
import com.uniapp.r2scalendar.Model.AssignmentRequest;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.Model.MessageResponse;
import com.uniapp.r2scalendar.Model.Trainee;
import com.uniapp.r2scalendar.Repository.AssignmentRepository;
import com.uniapp.r2scalendar.Repository.TraineeRepository;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.IAddAssignmentVIew;
import com.uniapp.r2scalendar.View.IAssignmentView;
import com.uniapp.r2scalendar.View.ILoginView;
import com.uniapp.r2scalendar.View.ISignUpView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentService {
    private IAssignmentController iAssignmentController;
    private IAssignmentView iAssignmentView;
    private IAddAssignmentVIew iAddAssignmentVIew;
    HashMap<String,String> global_user;

    public AssignmentService(IAssignmentController iAssignmentController, IAssignmentView iAssignmentView) {
        this.iAssignmentController = iAssignmentController;
        this.iAssignmentView = iAssignmentView;
        global_user = GlobalUser.getInstance();
    }

    public AssignmentService(IAssignmentController iAssignmentController, IAddAssignmentVIew iAddAssignmentVIew) {
        this.iAssignmentController = iAssignmentController;
        this.iAddAssignmentVIew = iAddAssignmentVIew;
        global_user = GlobalUser.getInstance();
    }

    public void search(String str_search) {
        try {
            AssignmentRepository assignmentRepository = RetrofitClient.Client().create(AssignmentRepository.class);
            Call<List<AssignmentResponse>> assignmentResponseCallSearch = assignmentRepository.searchAssignment(global_user.get("idUser"),global_user.get("role"),str_search);
            assignmentResponseCallSearch.enqueue(new Callback<List<AssignmentResponse>>() {
                @Override
                public void onResponse(Call<List<AssignmentResponse>> call, Response<List<AssignmentResponse>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    iAssignmentController.displayItem(response.body());
                }

                @Override
                public void onFailure(Call<List<AssignmentResponse>> call, Throwable t) {
                    Log.e("Response Status", t.getMessage());
                    iAssignmentController.onFailureResponse(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAll() {
        try {
            AssignmentRepository assignmentRepository = RetrofitClient.Client().create(AssignmentRepository.class);
            Call<List<AssignmentResponse>> assignmentResponseCallGetAll = assignmentRepository.getAll(global_user.get("idUser"),global_user.get("role"));
            assignmentResponseCallGetAll.enqueue(new Callback<List<AssignmentResponse>>() {
                @Override
                public void onResponse(Call<List<AssignmentResponse>> call, Response<List<AssignmentResponse>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    iAssignmentController.displayItem(response.body());
                }

                @Override
                public void onFailure(Call<List<AssignmentResponse>> call, Throwable t) {
                    iAssignmentController.onFailureResponse(t.getMessage());
                    //iAssignmentController.onFailureResponse(assignmentRepository.getAll(global_user.get("idUser"),global_user.get("role")).request().url().toString());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getListSpinner()
    {

    }

    public void add(AssignmentRequest assignmentRequest)
    {
        try {
            AssignmentRepository assignmentRepository = RetrofitClient.Client().create(AssignmentRepository.class);
            Call<MessageResponse> assignmentResponseCallAdd = assignmentRepository.addAssignment(assignmentRequest);
            assignmentResponseCallAdd.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    if(response.body().getMessage().equals("Success"))
                    {
                        iAssignmentController.responseAddEditAssignment(true);
                    }
                    else
                    {
                        iAssignmentController.responseAddEditAssignment(false);
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    iAddAssignmentVIew.onResponseFailed(t.getMessage());
                    //iAssignmentController.onFailureResponse(assignmentRepository.getAll(global_user.get("idUser"),global_user.get("role")).request().url().toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit(AssignmentRequest new_assignment, AssignmentRequest old_assignment)
    {
        try {
            AssignmentRepository assignmentRepository = RetrofitClient.Client().create(AssignmentRepository.class);
            List<AssignmentRequest> assignmentRequestList = new ArrayList<>();

            assignmentRequestList.add(new_assignment);
            assignmentRequestList.add(old_assignment);

            Call<MessageResponse> assignmentResponseCallDelete = assignmentRepository.editAssignment(assignmentRequestList);
            assignmentResponseCallDelete.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    if(response.body().getMessage().equals("Success"))
                    {
                        iAssignmentController.responseAddEditAssignment(true);
                    }
                    else
                    {
                        iAssignmentController.responseAddEditAssignment(false);
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    iAssignmentController.onFailureResponse(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(boolean isFirstStep ,AssignmentResponse assignmentResponse)
    {
        try {
            AssignmentRepository assignmentRepository = RetrofitClient.Client().create(AssignmentRepository.class);
            Call<MessageResponse> assignmentResponseCallDelete = assignmentRepository.deleteAssignment(isFirstStep,assignmentResponse);
            assignmentResponseCallDelete.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    iAssignmentController.configDialogConstraint(response.body().getMessage());
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    iAssignmentController.onFailureResponse(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
