package com.uniapp.r2scalendar.ui;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniapp.r2scalendar.Controller.IQuestionController;
import com.uniapp.r2scalendar.Controller.QuestionController;
import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.ProgressDialogaa;
import com.uniapp.r2scalendar.View.IQuestionView;
import com.uniapp.r2scalendar.adapter.QuestionAdapter;
import com.uniapp.r2scalendar.adapter.TopicSpinnerAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionFragment extends Fragment implements IQuestionView {

    private ImageButton imgAddQuestions;
    private Spinner spinnerTopicName;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    QuestionAdapter questionAdapter;
    IQuestionController IQuestionController;
    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IQuestionController = new QuestionController(this, null, view);
        initVariable(view);


        IQuestionController.getList();
        IQuestionController.getAll();

        spinnerTopicName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here
                Map<String, Object> params = new HashMap<>();
                params.put("topicName", ((Topic) spinnerTopicName.getSelectedItem()).getTopicName());

                IQuestionController.searchQuestion(params);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    @Override
    public void initVariable(View v) {
        imgAddQuestions = v.findViewById(R.id.imgAddQuestion);
        spinnerTopicName = v.findViewById(R.id.spinnerTopicName);
        recyclerView = v.findViewById(R.id.rvQuestion);

        imgAddQuestions.setOnClickListener(view -> {
            //change fragment
            GlobalFragment.replaceFragment(new AddQuestionFragment(), getActivity());
        });

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        registerForContextMenu(recyclerView);
    }

    @Override
    public void displayItem(View v, List<QuestionResponse> questionResponseList) {
        questionAdapter = new QuestionAdapter(v, questionResponseList, recyclerView, IQuestionController, null);
        recyclerView.setAdapter(questionAdapter);
        questionAdapter.notifyDataSetChanged();
    }


    @Override
    public void displayProgressDialog(View view) {
        progressDialog = new android.app.ProgressDialog(view.getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Question is loading...");
        progressDialog.show();
    }

    @Override
    public void disableProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void loadSpinner(List<Topic> topicList, View view) {
        TopicSpinnerAdapter topicSpinnerAdapter = new TopicSpinnerAdapter(this.getContext(), android.R.layout.simple_spinner_item, topicList);
        spinnerTopicName.setAdapter(topicSpinnerAdapter);
    }
}
