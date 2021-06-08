package com.uniapp.r2scalendar.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.uniapp.r2scalendar.Controller.IAdminController;
import com.uniapp.r2scalendar.Controller.IModuleController;
import com.uniapp.r2scalendar.Model.Admin;
import com.uniapp.r2scalendar.Model.Feedback;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.ProgressDialogaa;
import com.uniapp.r2scalendar.View.IAddModuleView;
import com.uniapp.r2scalendar.adapter.AdminSpinerAdapter;
import com.uniapp.r2scalendar.adapter.FeedbackSpinerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddModuleFragment extends Fragment implements IAddModuleView {
    Button btnStartDate, btnEndDate, btnFeedbackStartDate, btnFeedbackEndDate, btnSaveModule, btnBackModule;
    Spinner spinnerAdminID, spinnerFeedbackTitle;
    EditText ModuleName;
    TextView AddModuleTitle, validateModuleName, validateStartDate, validateEndDate, validateFeedbackStartDate, validateFeedbackEndDate;
    IModuleController moduleController;
    ModuleResponse moduleResponse;
    ProgressDialog progressDialog;

    IAdminController adminController;
    Admin chooseAdmin = null;
    Feedback chooseFeedback = null;

    public AddModuleFragment(IModuleController moduleController) {
        this.moduleController = moduleController;
    }
    public AddModuleFragment(IModuleController moduleController, ModuleResponse moduleResponse) {
        this.moduleController = moduleController;
        this.moduleResponse = moduleResponse;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmemt_add_module, container, false);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariables(view);
        initDatePicker(view);
        handleButtons(view);
        initData();


        moduleController.getList(this, view);
        spinnerAdminID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here
                Map<String, Object> params = new HashMap<>();
                params.put("adminName", ((Admin) spinnerAdminID.getSelectedItem()).getUserName());

            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        moduleController.getListFeedback(this, view);
        spinnerFeedbackTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here
                Map<String, Object> params = new HashMap<>();
                params.put("FeedbackTitle", ((Feedback) spinnerFeedbackTitle.getSelectedItem()).getTitle());

            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    private void initData() {
        if (this.moduleResponse != null) {
            AddModuleTitle.setText("Edit Module");
            String startDate = this.moduleResponse.getStartTime() == null ? "" : this.moduleResponse.getStartTime().replace("-", "/");
            String EndDate = this.moduleResponse.getEndTime() == null ? "" : this.moduleResponse.getEndTime().replace("-", "/");

            ModuleName.setText(this.moduleResponse.getModuleName());
            //capacity.setText(String.valueOf(this.classResponse.getCapacity()));
            btnStartDate.setText(startDate.isEmpty() ? "" :startDate.split("/")[2] + "/" + startDate.split("/")[1] + "/" + startDate.split("/")[0]);
            btnEndDate.setText(EndDate.isEmpty() ? "" : EndDate.split("/")[2] + "/" + EndDate.split("/")[1] + "/" + EndDate.split("/")[0]);
        }
    }

    private void handleButtons(View view) {
        btnSaveModule = view.findViewById(R.id.btnSaveModule);
        btnBackModule = view.findViewById(R.id.btnBackModule);
        btnSaveModule.setOnClickListener(v -> {

        });
        btnBackModule.setOnClickListener(t -> {
            GlobalFragment.replaceFragment(new ModuleFragment(),getActivity());
        });

        btnSaveModule.setOnClickListener(v -> {
            try {
                if (validationData(ModuleName.getText().toString(), btnStartDate.getText().toString(), btnEndDate.getText().toString(),
                        btnFeedbackStartDate.getText().toString(), btnFeedbackEndDate.getText().toString())) {
                    if (this.moduleResponse != null) {
                        this.moduleResponse.setModuleName(ModuleName.getText().toString());
                        this.moduleResponse.setAdminId(spinnerAdminID.getSelectedItem().toString());
                        this.moduleResponse.setFeedbackTitle(spinnerFeedbackTitle.getSelectedItem().toString());
                        this.moduleResponse.setStartTime(btnStartDate.getText().toString());
                        this.moduleResponse.setEndTime(btnEndDate.getText().toString());
                        this.moduleResponse.setStartTime(btnFeedbackStartDate.getText().toString());
                        this.moduleResponse.setEndTime(btnFeedbackEndDate.getText().toString());
                        moduleController.handleModuleItem(this.moduleResponse, this, view);
                    } else {
                        ModuleResponse moduleResponse = new ModuleResponse(ModuleName.getText().toString(),
                                btnStartDate.getText().toString(), btnEndDate.getText().toString(),
                                spinnerAdminID.getSelectedItem().toString(), spinnerFeedbackTitle.getSelectedItem().toString(),
                                btnFeedbackStartDate.getText().toString(), btnFeedbackEndDate.getText().toString());
                        moduleController.handleModuleItem(moduleResponse, this, view);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private void initDatePicker(View v) {
        btnEndDate = v.findViewById(R.id.btn_EndDate);
        btnStartDate = v.findViewById(R.id.btn_StartDate);
        btnFeedbackStartDate = v.findViewById(R.id.btn_FeedbackStartDate);
        btnFeedbackEndDate = v.findViewById(R.id.btn_FeedbackEndDate);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (this.moduleResponse != null) {
            btnStartDate.setText(moduleResponse.getStartTime());
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
        btnFeedbackStartDate.setOnClickListener(t -> {
            @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth
                    , (view, year1, month1, dayOfMonth) -> {
                btnFeedbackStartDate.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
            },year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
            datePickerDialog.show();

        });
        btnFeedbackEndDate.setOnClickListener(t -> {
            @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth
                    , (view, year1, month1, dayOfMonth) -> {
                btnFeedbackEndDate.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
            },year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
            datePickerDialog.show();

        });



    }

    private void initVariables(View view) {
        AddModuleTitle = view.findViewById(R.id.AddModuleTitle);
        ModuleName = view.findViewById(R.id.edt_ModuleName);
        validateModuleName = view.findViewById(R.id.validateModuleName);
        btnStartDate = view.findViewById(R.id.btn_StartDate);
        validateStartDate = view.findViewById(R.id.validateStartDate);
        btnEndDate = view.findViewById(R.id.btn_EndDate);
        validateEndDate = view.findViewById(R.id.validateEndDate);
        spinnerAdminID = view.findViewById(R.id.spinnerAdminID);
        spinnerFeedbackTitle = view.findViewById(R.id.spinnerFeedbackTitle);

        btnFeedbackStartDate = view.findViewById(R.id.btn_FeedbackStartDate);
        validateFeedbackStartDate = view.findViewById(R.id.validateFeedbackStartDate);
        btnFeedbackEndDate = view.findViewById(R.id.btn_FeedbackEndDate);
        validateFeedbackEndDate = view.findViewById(R.id.validateFeedbackEndDate);

        btnSaveModule = view.findViewById(R.id.btnSaveModule);
        btnBackModule = view.findViewById(R.id.btnBackModule);
    }

    @Override
    public void displayItem(View v, List<ModuleResponse> moduleResponseList) {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void loadSpinner(List<Admin> adminList, View view) {
        AdminSpinerAdapter adminSpinerAdapter = new AdminSpinerAdapter(this.getContext(), android.R.layout.simple_spinner_item, adminList);
        spinnerAdminID.setAdapter(adminSpinerAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void loadSpinnerFeedback(List<Feedback> adminList, View view) {
        FeedbackSpinerAdapter feedbackSpinerAdapter = new FeedbackSpinerAdapter(this.getContext(), android.R.layout.simple_spinner_item, adminList);
        spinnerFeedbackTitle.setAdapter(feedbackSpinerAdapter);
    }

    @Override
    public void setAdminSelected() {
        if (chooseAdmin == null) return;

        for (int i = 0; i < spinnerAdminID.getAdapter().getCount(); i++) {
            if (((ArrayAdapter<Admin>) spinnerAdminID.getAdapter()).getItem(i).getUserName().equals(String.valueOf(chooseAdmin.getUserName()))) {
                spinnerAdminID.setSelection(i);
                spinnerAdminID.setEnabled(false);
                AddModuleTitle.setText("Edit Module");

                return;
            }
        }
    }
    @Override
    public void setFeedbackTitleSelected() {
        if (chooseFeedback == null) return;

        for (int i = 0; i < spinnerFeedbackTitle.getAdapter().getCount(); i++) {
            if (((ArrayAdapter<Feedback>) spinnerFeedbackTitle.getAdapter()).getItem(i).getTitle().equals(String.valueOf(chooseFeedback.getTitle()))) {
                spinnerFeedbackTitle.setSelection(i);
                spinnerFeedbackTitle.setEnabled(false);
                AddModuleTitle.setText("Edit Module");
                return;
            }
        }
    }

    @Override
    public void onSuccess(String message) {
        Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_1_button);
        dialog.setCancelable(false);
        ((TextView) dialog.findViewById(R.id.tvContent)).setText(message);
        dialog.findViewById(R.id.btOK).setOnClickListener(v -> {
            dialog.dismiss();
            GlobalFragment.replaceFragment(new ModuleFragment(),getActivity());
        });
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private Boolean validationData(String ModuleName, String btnStartDate, String btnEndDate, String btnFeedbackStartDate, String btnFeedbackEndDate) throws ParseException {
        Boolean checkValidation = true;

        if (ModuleName == null || ModuleName.toString().isEmpty() || ModuleName.length() > 255) {
            validateModuleName.setText("Please enter class name and less than 255 character");
            checkValidation = false;
        } else {
            validateModuleName.setText("");
        }
        String today = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "/"
                + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1)  + "/"
                + String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

        if (this.moduleResponse == null && (btnStartDate == null || btnStartDate.equals("dd/mm/yyyy") ||
                new SimpleDateFormat("dd/MM/yyyy").parse(btnStartDate).before(new SimpleDateFormat("dd/MM/yyyy").parse(today)))) {
            validateStartDate.setText("Please choose date or fill full dd/mm/yyyy");
            checkValidation = false;
        } else {
            validateStartDate.setText("");
        }

        if (btnEndDate == null || btnEndDate.equals("dd/mm/yyyy") ||
                new SimpleDateFormat("dd/MM/yyyy").parse(btnEndDate).before(new SimpleDateFormat("dd/MM/yyyy").parse(btnStartDate)) ||
                new SimpleDateFormat("dd/MM/yyyy").parse(btnEndDate).equals(new SimpleDateFormat("dd/MM/yyyy").parse(btnStartDate))) {
            validateEndDate.setText("Please choose date or fill full dd/mm/yyyy");
            checkValidation = false;
        } else {
            validateEndDate.setText("");
        }

        if (this.moduleResponse == null && (btnFeedbackStartDate == null || btnFeedbackStartDate.equals("dd/mm/yyyy") ||
                new SimpleDateFormat("dd/MM/yyyy").parse(btnStartDate).before(new SimpleDateFormat("dd/MM/yyyy").parse(today)))) {
            validateStartDate.setText("Please choose date or fill full dd/mm/yyyy");
            checkValidation = false;
        } else {
            validateFeedbackStartDate.setText("");
        }

        if (btnFeedbackEndDate == null || btnFeedbackEndDate.equals("dd/mm/yyyy") ||
                new SimpleDateFormat("dd/MM/yyyy").parse(btnFeedbackEndDate).before(new SimpleDateFormat("dd/MM/yyyy").parse(btnFeedbackStartDate)) ||
                new SimpleDateFormat("dd/MM/yyyy").parse(btnFeedbackEndDate).equals(new SimpleDateFormat("dd/MM/yyyy").parse(btnFeedbackStartDate))) {
            validateFeedbackEndDate.setText("Please choose date or fill full dd/mm/yyyy");
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
        progressDialog.setMessage("New Module is adding to database...");
        progressDialog.show();
    }
    @Override
    public void disableProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }
}
