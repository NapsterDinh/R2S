package com.uniapp.r2scalendar.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniapp.r2scalendar.R;

public class AssignmentViewHolder extends RecyclerView.ViewHolder {
    public TextView txtNo, txtModuleName, txtClassName, txtTrainerName, txtRegistrationCode;
    public ImageButton btDelete, btEdit;

    ItemClickListener itemClickListener;

    public AssignmentViewHolder(@NonNull View itemView) {
        super(itemView);

        txtNo = itemView.findViewById(R.id.txtNo);
        txtModuleName = itemView.findViewById(R.id.txtTraineeID);
        txtClassName = itemView.findViewById(R.id.txtContent);
        txtTrainerName = itemView.findViewById(R.id.txtTrainerName);
        txtRegistrationCode = itemView.findViewById(R.id.txtRegistrationCode);
        btDelete = itemView.findViewById(R.id.btnDelete);
        btEdit = itemView.findViewById(R.id.btnEdit);

        btDelete.setOnClickListener(v ->{
            itemClickListener.onClick(v,getAdapterPosition(),true);
        });
        btEdit.setOnClickListener(v -> {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        });
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

}
