package com.uniapp.r2scalendar.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniapp.r2scalendar.Controller.ClassController;
import com.uniapp.r2scalendar.Controller.IClassController;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.ProgressDialogaa;
import com.uniapp.r2scalendar.View.IClassView;
import com.uniapp.r2scalendar.adapter.ClassAdapter;

import java.util.List;

public class ClassFragment extends Fragment implements IClassView {
    IClassController classController;
    RecyclerView recyclerView;
    ImageButton imageButtonAdd;
    LinearLayoutManager layoutManager;
    ClassAdapter classAdapter;
    ProgressDialog progressDialog;
    boolean result = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_class_dasboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        classController = new ClassController(this, view);

        initVariables(view);
        classController.displayAllItem();
    }

    private void initVariables(View v) {
        recyclerView = v.findViewById(R.id.rvClass);
        imageButtonAdd = v.findViewById(R.id.imgAddClass);

        imageButtonAdd.setOnClickListener(view -> {
            //change fragment
            GlobalFragment.replaceFragment(new AddClassFragment(classController),getActivity());
        });

        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        registerForContextMenu(recyclerView);
    }

    @Override
    public void displayItem(View v, List<ClassResponse> classResponseList) {
        classAdapter =new ClassAdapter(classResponseList, v, classController, getActivity(), this);
        recyclerView.setAdapter(classAdapter);
        classAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayProgressDialog(View view) {
        progressDialog = new android.app.ProgressDialog(view.getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Class is loading...");
        progressDialog.show();
    }

    @Override
    public void disableProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }

    @Override
    public void onSuccess(String message) {
        Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_1_button);
        dialog.setCancelable(false);
        ((TextView) dialog.findViewById(R.id.tvContent)).setText(message);
        dialog.findViewById(R.id.btOK).setOnClickListener(v -> {
            dialog.dismiss();
            GlobalFragment.replaceFragment(new ClassFragment(),getActivity());
        });
        dialog.show();
    }
}