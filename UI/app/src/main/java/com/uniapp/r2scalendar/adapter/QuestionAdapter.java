package com.uniapp.r2scalendar.adapter;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.uniapp.r2scalendar.Controller.IQuestionController;
import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.ViewHolder.QuestionViewHolder;
import com.uniapp.r2scalendar.ui.AddQuestionFragment;
import com.uniapp.r2scalendar.ui.GlobalFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {

    View view;
    List<QuestionResponse> questionResponseList;
    IQuestionController iQuestionController;
    public RecyclerView recyclerView;

    private Dialog dialogDeleteSuccess;
    private Button buttonYes;
    private Button buttonCancel;
    private TextView textViewTitle;
    private TextView textViewContent;
    ImageView imgIconDialog;

    FragmentActivity fragmentActivity;


    public QuestionAdapter(View view, List<QuestionResponse> questionResponseList,RecyclerView recyclerView, IQuestionController iQuestionController,FragmentActivity fragmentActivity)
    {
        this.view=view;
        this.questionResponseList=questionResponseList;
        this.recyclerView=recyclerView;
        this.iQuestionController=iQuestionController;
        this.fragmentActivity=fragmentActivity;
    }
    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);

        return new QuestionViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        System.out.println(questionResponseList.get(0).getTopicName());

        holder.txtTopicID.setText(holder.txtTopicID.getText().toString() + questionResponseList.get(position).getTopicID());
        holder.txtTopicName.setText(holder.txtTopicName.getText().toString() + questionResponseList.get(position).getTopicName());
        holder.txtQuestionID.setText(holder.txtQuestionID.getText().toString() + questionResponseList.get(position).getQuestionID());
        holder.txtQuestionContent.setText(holder.txtQuestionContent.getText().toString() + questionResponseList.get(position).getQuestionContent());


        holder.btDelete.setOnClickListener(v -> {
            dialogDeleteSuccess=new Dialog(view.getContext());
            dialogDeleteSuccess.setContentView(R.layout.dialog_2_button);
            dialogDeleteSuccess.setCancelable(false);
            buttonYes=dialogDeleteSuccess.findViewById(R.id.btOK);
            buttonCancel=dialogDeleteSuccess.findViewById(R.id.btCancel);
            textViewTitle=dialogDeleteSuccess.findViewById(R.id.tvContent);
            textViewContent=dialogDeleteSuccess.findViewById(R.id.tvContent3);

            textViewTitle.setText("Are You Sure?");
            textViewContent.setText("Do you want to delete this Question?");
            buttonCancel.setText("Cancel!");
           buttonYes.setText("Yes!");
            dialogDeleteSuccess.show();

            buttonYes.setOnClickListener(v1->{

                deleteQuestion(view, position);
                dialogDeleteSuccess.dismiss();

            });

            buttonCancel.setOnClickListener(v2->{
                dialogDeleteSuccess.dismiss();
            });


        });

        holder.btEdit.setOnClickListener(v -> {
            QuestionResponse questionResponse=new QuestionResponse(questionResponseList.get(position).getTopicID(),
                    questionResponseList.get(position).getQuestionContent(),
                    questionResponseList.get(position).getTopicName(),
                    questionResponseList.get(position).getQuestionID());

            GlobalFragment.replaceFragment(new AddQuestionFragment(questionResponse), fragmentActivity);
        });
    }

    public void deleteQuestion(View view, int position) {
        Map<String, Object> params = new HashMap<>();

        QuestionResponse questionResponse =new QuestionResponse();
        questionResponse.setQuestionID(questionResponseList.get(position).getQuestionID());
        questionResponse.setTopicID(questionResponseList.get(position).getTopicID());
        questionResponse.setTopicName(questionResponseList.get(position).getTopicName());
        questionResponse.setQuestionContent(questionResponseList.get(position).getQuestionContent());
        params.put("questionResponse", questionResponse);

        iQuestionController.deleteQuestion(params);
    }

    @Override
    public int getItemCount() {
        return questionResponseList.size();
    }
}
