package com.uniapp.r2scalendar.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uniapp.r2scalendar.Controller.IClassController;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.MessageResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.ProgressDialogaa;
import com.uniapp.r2scalendar.View.IAddClassView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddClassFragment extends Fragment implements IAddClassView {
    Button btnEndDate, btnStartDate, btnSave, btnBack, startTime, endTime;
    TextView validateClassName, validateCapacity, validateStartDate, validateEndDate;
    TextView className, capacity, AddModuleTitle;
    IClassController classController;
    ProgressDialog progressDialog;
    ClassResponse classResponse;

    public AddClassFragment(IClassController classController) {
        this.classController = classController;
    }
    public AddClassFragment(IClassController classController, ClassResponse classResponse) {
        this.classController = classController;
        this.classResponse = classResponse;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmemt_add_class, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariables(view);
        initDatePicker(view);
        handleButtons(view);
        initData();
    }

    private void initDatePicker(View v) {
        btnEndDate = v.findViewById(R.id.btn_EndDate);
        btnStartDate = v.findViewById(R.id.btn_StartDate);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (this.classResponse != null) {
            btnStartDate.setText(classResponse.getStartTime());
            btnStartDate.setEnabled(false);
        } else {
            btnStartDate.setOnClickListener(y -> {
                @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth
                        , (view, year1, month1, dayOfMonth) -> {
                    btnStartDate.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
                },year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                datePickerDialog.show();
            });
        }

        btnEndDate.setOnClickListener(t -> {
            @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth
                    , (view, year1, month1, dayOfMonth) -> {
                btnEndDate.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
            },year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
            datePickerDialog.show();
        });
    }

    private void initData() {
        if (this.classResponse != null) {
            AddModuleTitle.setText("Edit Class");
            String startDate = this.classResponse.getStartTime() == null ? "" : this.classResponse.getStartTime().replace("-", "/");
            String EndDate = this.classResponse.getEndTime() == null ? "" : this.classResponse.getEndTime().replace("-", "/");

            className.setText(this.classResponse.getClassName());
            capacity.setText(String.valueOf(this.classResponse.getCapacity()));
            startTime.setText(startDate.isEmpty() ? "" :startDate.split("/")[2] + "/" + startDate.split("/")[1] + "/" + startDate.split("/")[0]);
            endTime.setText(EndDate.isEmpty() ? "" : EndDate.split("/")[2] + "/" + EndDate.split("/")[1] + "/" + EndDate.split("/")[0]);
        }
    }

    private void initVariables(View view) {
        className = view.findViewById(R.id.edt_ClassName);
        capacity = view.findViewById(R.id.edt_Capacity);
        startTime = view.findViewById(R.id.btn_StartDate);
        endTime = view.findViewById(R.id.btn_EndDate);
        AddModuleTitle = view.findViewById(R.id.AddModuleTitle);

        validateClassName = view.findViewById(R.id.validateClassName);
        validateCapacity = view.findViewById(R.id.validateCapacity);
        validateEndDate = view.findViewById(R.id.validateEndDate);
        validateStartDate = view.findViewById(R.id.validateStartDate);

    }

    private void handleButtons(View view) {
        btnSave = view.findViewById(R.id.btnSaveClass);
        btnBack = view.findViewById(R.id.btnBackClass);

        btnSave.setOnClickListener(v -> {
            try {
                if (validationData(className.getText().toString(), capacity.getText().toString(), startTime.getText().toString(),
                        endTime.getText().toString())) {
                    if (this.classResponse != null) {
                        this.classResponse.setClassName(className.getText().toString());
                        this.classResponse.setCapacity(Integer.parseInt(capacity.getText().toString()));
                        this.classResponse.setStartTime(startTime.getText().toString());
                        this.classResponse.setEndTime(endTime.getText().toString());

                        classController.handleClassItem(this.classResponse, this, view);
                    } else {
                        ClassResponse classResponse = new ClassResponse(className.getText().toString(), Integer.parseInt(capacity.getText().toString()),
                                startTime.getText().toString(), endTime.getText().toString());
                        classController.handleClassItem(classResponse, this, view);
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        btnBack.setOnClickListener(t -> {
            GlobalFragment.replaceFragment(new ClassFragment(),getActivity());
        });
    }

    @Override
    public void onSuccess(String message, MessageResponse messageResponse) {
        Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_1_button);
        dialog.setCancelable(false);
        ((TextView) dialog.findViewById(R.id.tvContent)).setText(message);

        if (messageResponse != null && messageResponse.getMessage().equals("Duplicated")) {
            dialog.setContentView(R.layout.dialog_2_button);
            ((TextView) dialog.findViewById(R.id.tvContent)).setText("Failed");
            ((TextView) dialog.findViewById(R.id.tvContent3)).setText("Class is duplicated!");
            ((TextView) dialog.findViewById(R.id.btCancel)).setText("Close");
        }

        dialog.findViewById(R.id.btOK).setOnClickListener(v -> {
            dialog.dismiss();
            GlobalFragment.replaceFragment(new ClassFragment(),getActivity());
        });
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private Boolean validationData(String className, String capacity, String startTime, String EndTime) throws ParseException {
        Boolean checkValidation = true;

        if (className == null || className.isEmpty() || className.length() > 255) {
            validateClassName.setText("Please enter class name and less than 255 character");
            checkValidation = false;
        } else {
            validateClassName.setText("");
        }

        if (capacity == null || !TextUtils.isDigitsOnly(capacity) || capacity.isEmpty() || Integer.parseInt(capacity) <= 0) {
            validateCapacity.setText("Please enter capacity is integer and larger than 0");
            checkValidation = false;
        } else {
            validateCapacity.setText("");
        }

        String today = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "/"
                + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1)  + "/"
                + String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

        if (this.classResponse == null && (startTime == null || startTime.equals("dd/mm/yyyy") ||
                new SimpleDateFormat("dd/MM/yyyy").parse(startTime).before(new SimpleDateFormat("dd/MM/yyyy").parse(today)))) {
            validateStartDate.setText("Please choose date or fill full dd/mm/yyyy");
            checkValidation = false;
        } else {
            validateStartDate.setText("");
        }

        if (EndTime == null || EndTime.equals("dd/mm/yyyy") ||
            new SimpleDateFormat("dd/MM/yyyy").parse(EndTime).before(new SimpleDateFormat("dd/MM/yyyy").parse(startTime)) ||
            new SimpleDateFormat("dd/MM/yyyy").parse(EndTime).equals(new SimpleDateFormat("dd/MM/yyyy").parse(startTime))) {
            validateEndDate.setText("Please choose date or fill full dd/mm/yyyy");
            checkValidation = false;
        } else {
            validateEndDate.setText("");
        }

        return checkValidation;
    }

    @Override
    public void displayProgressDialog(View view) {
        progressDialog = new android.app.ProgressDialog(view.getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Your class is adding to database...");
        progressDialog.show();
    }

    @Override
    public void disableProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }
}
