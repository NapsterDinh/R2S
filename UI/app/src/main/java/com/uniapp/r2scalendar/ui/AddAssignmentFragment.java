package com.uniapp.r2scalendar.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.internal.$Gson$Preconditions;
import com.uniapp.r2scalendar.Controller.AssignmentController;
import com.uniapp.r2scalendar.Controller.IAssignmentController;
import com.uniapp.r2scalendar.Model.AssignmentRequest;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.View.IAddAssignmentVIew;

import java.sql.Timestamp;
import java.util.List;

public class AddAssignmentFragment extends Fragment implements IAddAssignmentVIew {

    Spinner spinnerModuleName, spinnerClassName, spinnerTrainerID;
    Button btBack, btSave, btOk;
    Dialog dialog;
    ImageView imgIconDialog;
    TextView tvContent;
    IAssignmentController iAssignmentController;
    AssignmentResponse chooseAssignment = null;
    View view;
    ModuleResponse moduleResponseSelected = new ModuleResponse();
    ClassResponse classResponseSelected = new ClassResponse();
    TrainerResponse trainerResponseSelected = new TrainerResponse();
    ProgressDialog progressDialog;
    EditText edtClassID, edtClassName, edtModuleID, edtModuleName;
    Spinner spinnerTrainerIDEdit;
    private int countResponse =0;
    private AssignmentRequest old_assignment = new AssignmentRequest();

    public AddAssignmentFragment(AssignmentResponse chooseAssignment)
    {
        if(chooseAssignment!=null) this.chooseAssignment = chooseAssignment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(chooseAssignment!=null)
        {
            return inflater.inflate(R.layout.fragment_assignment_edit, container, false);
        }
        return inflater.inflate(R.layout.fragment_assignment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        iAssignmentController  = new AssignmentController(this,view);
        this.view = view;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Loading data.......");
        progressDialog.show();

        initView(view);

        if(chooseAssignment!=null)
        {
            edtClassID = view.findViewById(R.id.edtClassID);
            edtClassName = view.findViewById(R.id.edtClassName);
            edtModuleID = view.findViewById(R.id.edtModuleID);
            edtModuleName = view.findViewById(R.id.edtModuleName);
            spinnerTrainerIDEdit = view.findViewById(R.id.spinnerTrainerID);

            //edit
            btSave.setOnClickListener(v ->{
                progressDialog = new ProgressDialog(view.getContext());
                progressDialog.setTitle("Please wait...");
                progressDialog.setMessage("Loading data.......");
                progressDialog.show();

                TrainerResponse trainerResponse = (TrainerResponse) spinnerTrainerID.getSelectedItem();

                String registrationCode = "CL" + edtClassID.getText().toString() + "M" + edtModuleID.getText().toString() + "T" ;
                iAssignmentController.editAssignment(
                        new AssignmentRequest(Integer.parseInt(edtModuleID.getText().toString()),
                                            Integer.parseInt(edtClassID.getText().toString()),
                                            trainerResponse.getUserName(),
                                            registrationCode),
                        old_assignment);
            });
            btBack.setOnClickListener(v ->{
                GlobalFragment.backFragment(getActivity());
            });
            //loadSpinner
            iAssignmentController.getListSpinner();
        }
        else
        {
            //add
            btSave.setOnClickListener(v ->{
                progressDialog = new ProgressDialog(view.getContext());
                progressDialog.setTitle("Please wait...");
                progressDialog.setMessage("Loading data.......");
                progressDialog.show();

                ClassResponse classResponse = (ClassResponse) spinnerClassName.getSelectedItem();
                TrainerResponse trainerResponse = (TrainerResponse) spinnerTrainerID.getSelectedItem();
                ModuleResponse moduleResponse = (ModuleResponse) spinnerModuleName.getSelectedItem();

                String registrationCode = "CL" + classResponse.getClassID() + "M" + moduleResponse.getModuleId() + "T" ;
                iAssignmentController.addAssignment(new AssignmentRequest(moduleResponse.getModuleId(),
                        classResponse.getClassID(),
                        trainerResponse.getUserName(),
                        registrationCode));
            });
            btBack.setOnClickListener(v ->{
                GlobalFragment.backFragment(getActivity());
            });
            //loadSpinner
            iAssignmentController.getListSpinner();
        }


    }

    void initView(View view)
    {
        spinnerClassName = view.findViewById(R.id.spinnerClassName);
        spinnerModuleName = view.findViewById(R.id.spinnerModule);
        spinnerTrainerID = view.findViewById(R.id.spinnerTrainerID);

        btBack = view.findViewById(R.id.btBack);
        btSave = view.findViewById(R.id.btSave);
        dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialog_1_button);
        dialog.setCancelable(false);

        imgIconDialog = dialog.findViewById(R.id.imgIconDialog);
        btOk = dialog.findViewById(R.id.btOK);
        tvContent = dialog.findViewById(R.id.tvContent);
    }


    @Override
    public void onResponseFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void setListSpinner(String typeSpinner, List<TrainerResponse> trainerResponseList, List<ClassResponse> classResponseList, List<ModuleResponse> moduleResponseList) {
        countResponse++;
        switch (typeSpinner)
        {
            case "Module":
                if(chooseAssignment==null)
                {
                    ArrayAdapter<ModuleResponse> moduleResponseArrayAdapter = new ArrayAdapter<ModuleResponse>(view.getContext(),
                            android.R.layout.simple_spinner_item, moduleResponseList
                    );

                    moduleResponseArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerModuleName.setAdapter(moduleResponseArrayAdapter);
                }
                else
                {
                    this.moduleResponseSelected.setModuleName(chooseAssignment.getModuleName());
                    for (ModuleResponse module: moduleResponseList) {
                        if(module.equals(this.moduleResponseSelected))
                        {
                            edtModuleID.setText(String.valueOf(module.getModuleId()));
                            edtModuleID.setEnabled(false);
                            edtModuleName.setText(module.getModuleName());
                            edtModuleName.setEnabled(false);
                            old_assignment.setIdModule(module.getModuleId());
                        }
                    }
                }
                if(countResponse==3)
                {
                    progressDialog.dismiss();
                }
                break;
            case "Class":
                if(chooseAssignment==null)
                {
                    ArrayAdapter<ClassResponse> classResponseArrayAdapter = new ArrayAdapter<ClassResponse>(view.getContext(),
                            android.R.layout.simple_spinner_item, classResponseList
                    );

                    classResponseArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerClassName.setAdapter(classResponseArrayAdapter);
                }
                else
                {
                    this.classResponseSelected.setClassName(chooseAssignment.getClassName());
                    for (ClassResponse class_item: classResponseList) {
                        if(class_item.equals(this.classResponseSelected))
                        {
                            edtClassID.setText(String.valueOf(class_item.getClassID()));
                            edtClassID.setEnabled(false);
                            edtClassName.setText(class_item.getClassName());
                            edtClassName.setEnabled(false);
                            old_assignment.setIdClass(class_item.getClassID());
                        }
                    }

                }
                if(countResponse==3)
                {
                    progressDialog.dismiss();
                }
                break;
            case "Trainer":
                ArrayAdapter<TrainerResponse> trainerResponseArrayAdapter = new ArrayAdapter<TrainerResponse>(view.getContext(),
                        android.R.layout.simple_spinner_item, trainerResponseList
                );

                trainerResponseArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                if(chooseAssignment!=null)
                {
                    this.trainerResponseSelected.setName(chooseAssignment.getTrainerName());
                    spinnerTrainerIDEdit.setAdapter(trainerResponseArrayAdapter);
                    spinnerTrainerIDEdit.setSelection(trainerResponseArrayAdapter.getPosition(trainerResponseSelected));
                    TrainerResponse trainerResponse = (TrainerResponse) spinnerTrainerIDEdit.getSelectedItem();
                    old_assignment.setIdTrainer(trainerResponse.getUserName());
                }
                else
                {
                    spinnerTrainerID.setAdapter(trainerResponseArrayAdapter);
                    spinnerTrainerID.setSelection(trainerResponseArrayAdapter.getPosition(trainerResponseSelected));
                }

                if(countResponse==3)
                {
                    progressDialog.dismiss();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void configDialogAndShow(boolean isSuccess) {
            if(isSuccess)
            {
                imgIconDialog.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
                tvContent.setText(R.string.success);
                btOk.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.sky_blue));
                btOk.setOnClickListener( v -> {
                    //back screen
                    dialog.dismiss();
                    GlobalFragment.backFragment(getActivity());
                });
            }
            else
            {
                imgIconDialog.setImageResource(R.drawable.ic_baseline_error_outline_24);
                tvContent.setText(R.string.assignment_exist);
                btOk.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.red));
                btOk.setOnClickListener( v -> {
                    //no change
                    dialog.dismiss();
                });
            }
            progressDialog.dismiss();
            dialog.show();
    }
}
