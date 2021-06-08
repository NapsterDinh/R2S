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

import com.uniapp.r2scalendar.Controller.IModuleController;
import com.uniapp.r2scalendar.Controller.ModuleController;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.ProgressDialogaa;
import com.uniapp.r2scalendar.View.IModuleView;
import com.uniapp.r2scalendar.adapter.ModuleAdapter;

import java.util.List;

public class ModuleFragment extends Fragment implements IModuleView {
    IModuleController moduleController;
    RecyclerView recyclerView;
    ImageButton imageButtonAdd;
    LinearLayoutManager layoutManager;
    ModuleAdapter moduleAdapter;
    ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_module_dasboard, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moduleController = new ModuleController(this, view);

        initVariables(view);
        moduleController.displayItem();
    }

    private void initVariables(View v) {
        recyclerView = v.findViewById(R.id.rvModule);
        imageButtonAdd = v.findViewById(R.id.imgAddModule);
        imageButtonAdd.setOnClickListener(view -> {
            //change fragment
            GlobalFragment.replaceFragment(new AddModuleFragment(moduleController),getActivity());
        });

        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        registerForContextMenu(recyclerView);
    }
    @Override
    public void displayItem(View v, List<ModuleResponse> moduleResponseList) {
        moduleAdapter =new ModuleAdapter(moduleResponseList, v, moduleController, getActivity(), this);
        recyclerView.setAdapter(moduleAdapter);
        moduleAdapter.notifyDataSetChanged();
    }

    @Override
    public void disableProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }

    @Override
    public void displayProgressDialog(View view) {
        progressDialog = new android.app.ProgressDialog(this.getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Module is loading...");
        progressDialog.show();
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


}
