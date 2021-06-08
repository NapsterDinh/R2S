package com.uniapp.r2scalendar.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniapp.r2scalendar.R;

public class ModuleViewHolder extends RecyclerView.ViewHolder {
    public TextView ModuleID, ModuleName, AdminID, StartTime, EndTime, FeedbackTitle, FeedbackStartTime, FeedEndTime;
    public ImageButton btnEdit, btnDelete;


    public ModuleViewHolder(@NonNull View itemView) {
        super(itemView);

        ModuleID = itemView.findViewById(R.id.txtModuleID);
        ModuleName = itemView.findViewById(R.id.txtModuleName);
        AdminID = itemView.findViewById(R.id.txtAdminID);
        StartTime = itemView.findViewById(R.id.txtStartTime);
        EndTime = itemView.findViewById(R.id.txtEndTime);
        FeedbackTitle = itemView.findViewById(R.id.txtFeedbackTitle);
        FeedbackStartTime = itemView.findViewById(R.id.txtFeedbackStartTime);
        FeedEndTime = itemView.findViewById(R.id.txtFeedEndTime);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }
}