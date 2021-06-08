package com.uniapp.r2scalendar.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.uniapp.r2scalendar.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassViewHolder extends RecyclerView.ViewHolder{
    public TextView classID, className, capacity, startTime, endTime;
    public ImageButton btnEdit, btnDelete;

    public ClassViewHolder(@NonNull View itemView) {
        super(itemView);

        classID = itemView.findViewById(R.id.txtClassID);
        className = itemView.findViewById(R.id.txtClassName);
        capacity = itemView.findViewById(R.id.txtCapacity);
        startTime = itemView.findViewById(R.id.txtStartDate);
        endTime = itemView.findViewById(R.id.txtEndDate);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }
}
