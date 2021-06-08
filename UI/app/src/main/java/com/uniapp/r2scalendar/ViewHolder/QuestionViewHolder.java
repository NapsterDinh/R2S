package com.uniapp.r2scalendar.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniapp.r2scalendar.R;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    public TextView txtTopicID, txtTopicName, txtQuestionID, txtQuestionContent;
    public ImageButton btDelete, btEdit;


    public QuestionViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTopicID = itemView.findViewById(R.id.txtTopID);
        txtTopicName = itemView.findViewById(R.id.txtTopName);
        txtQuestionID = itemView.findViewById(R.id.txtQuestionID);
        txtQuestionContent = itemView.findViewById(R.id.txtQuestionContent);
        btDelete = itemView.findViewById(R.id.btDelete);
        btEdit = itemView.findViewById(R.id.btEdit);
    }
}
