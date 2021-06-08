package com.uniapp.r2scalendar.ui;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniapp.r2scalendar.Controller.AssignmentController;
import com.uniapp.r2scalendar.Controller.IAssignmentController;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.View.IAssignmentView;
import com.uniapp.r2scalendar.adapter.AssignmentAdapter;

import java.util.List;

public class AssignmentFragment extends Fragment implements IAssignmentView {
    AssignmentAdapter assignmentAdapter;
    TextView tvAssignmentTitle;
    Button  btSearch;
    RecyclerView rvAssignment;
    EditText edtSearch;
    ImageButton imageButtonAdd;
    LinearLayoutManager layoutManager;
    ProgressDialog progressDialog;

    IAssignmentController iAssignmentController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assignment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        iAssignmentController  = new AssignmentController(this,view);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Loading data.......");
        progressDialog.show();

        //* Generate item on view *//*
        initVariable(view);

        if(GlobalUser.getInstance().get("role").equals("Trainer"))
        {
            imageButtonAdd.setVisibility(View.INVISIBLE);
        }

        iAssignmentController.getAll();
    }

    @Override
    public void initVariable(View v) {
        tvAssignmentTitle = v.findViewById(R.id.tvAssignmentTitle);
        btSearch = v.findViewById(R.id.btSave);
        rvAssignment = v.findViewById(R.id.rvAssignment);
        edtSearch = v.findViewById(R.id.edtSearch);
        imageButtonAdd = v.findViewById(R.id.imageButtonAdd);

        imageButtonAdd.setOnClickListener(view -> {
            //change fragment
            GlobalFragment.replaceFragment(new AddAssignmentFragment(null),getActivity());
        });

        btSearch.setOnClickListener(view2 -> {
            progressDialog.show();
            iAssignmentController.searchAssignment(edtSearch.getText().toString().trim());
        });

        rvAssignment.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(v.getContext());
        rvAssignment.setLayoutManager(layoutManager);
        registerForContextMenu(rvAssignment);
    }

    @Override
    public void displayItem(View v, List<AssignmentResponse> assignmentResponseList) {
        assignmentAdapter=new AssignmentAdapter(v, assignmentResponseList,rvAssignment,iAssignmentController,getActivity());
        rvAssignment.setAdapter(assignmentAdapter);
        assignmentAdapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    @Override
    public void onSearchSuccess(List<AssignmentResponse> assignmentResponseList) {

    }

    @Override
    public void onResponseFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void errorEmptyEdtSearch() {
        edtSearch.setError("This field must not be empty!!!");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void configDialogConstraint(boolean isViolateConstraint) {
        assignmentAdapter.configDialogConstraint(isViolateConstraint);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
