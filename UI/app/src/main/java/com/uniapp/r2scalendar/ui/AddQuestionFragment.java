package com.uniapp.r2scalendar.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.uniapp.r2scalendar.Controller.IQuestionController;
import com.uniapp.r2scalendar.Controller.ITopicController;
import com.uniapp.r2scalendar.Controller.QuestionController;
import com.uniapp.r2scalendar.Controller.TopicController;
import com.uniapp.r2scalendar.Model.QuestionResponse;
import com.uniapp.r2scalendar.Model.Topic;
import com.uniapp.r2scalendar.R;
import com.uniapp.r2scalendar.Utils.ProgressDialogaa;
import com.uniapp.r2scalendar.View.IAddQuestionView;
import com.uniapp.r2scalendar.adapter.TopicSpinnerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddQuestionFragment extends Fragment implements IAddQuestionView {

    private Spinner spinnerTopicName;
    private EditText textViewQuestionContent;
    private Button buttonSave, buttonBack;
    private TextView textViewError;
    TextView txtQuestionTitle;
    View view;
    ProgressDialog progressDialog;

    QuestionResponse chooseQuestion = null;
    IQuestionController iQuestionController;
    ITopicController topicController;


    public AddQuestionFragment(QuestionResponse chooseQuestion) {
         this.chooseQuestion = chooseQuestion;
    }

    public AddQuestionFragment() {
        this.chooseQuestion = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragmemt_add_question, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iQuestionController = new QuestionController(null, this, view);
        topicController = new TopicController(this, view);
        this.view = view;

        init(view);
        topicController.getList();
        handleItem();


    }

    private void init(View v) {
        spinnerTopicName = v.findViewById(R.id.spinner);
        textViewQuestionContent = v.findViewById(R.id.multiAutoCompleteTextView);
        buttonSave = v.findViewById(R.id.btSave);
        buttonBack = v.findViewById(R.id.btBack);
        textViewError = v.findViewById(R.id.txtError);
        txtQuestionTitle=view.findViewById(R.id.tvAddQuestion);
    }

    private void handleItem() {
        if (chooseQuestion != null) {
            textViewQuestionContent.setText(chooseQuestion.getQuestionContent());
            buttonSave.setOnClickListener(v -> {
                if (textViewQuestionContent.getText().toString().isEmpty()||validation()) {
                    textViewError.setText("Please enter the question");
                    return;
                }

                else {
                    textViewError.setText("");
                    Map<String, Object> params = new HashMap<>();
                    params.put("questionID", chooseQuestion.getQuestionID());
                    params.put("questionContext", textViewQuestionContent.getText().toString());
                    iQuestionController.updateQuestion(params,this,view);
                }


            });

            buttonBack.setOnClickListener(v -> {
                GlobalFragment.replaceFragment(new QuestionFragment(),getActivity());
            });

        } else {
            buttonSave.setOnClickListener(v1 -> {

                if (textViewQuestionContent.getText().toString().isEmpty()||validation()) {
                    textViewError.setText("Please enter the question");
                    return;
                }

                else
                {
                    textViewError.setText("");
                    Map<String, Object> params = new HashMap<>();
                    params.put("topicID", ((Topic) spinnerTopicName.getSelectedItem()).getTopicID());
                    params.put("questionContext", textViewQuestionContent.getText().toString());
                    iQuestionController.createQuestion(params, this);
                }


            });

            buttonBack.setOnClickListener(v -> {
                GlobalFragment.replaceFragment(new QuestionFragment(),getActivity());
            });
        }
    }

    private boolean validation() {
        return textViewQuestionContent.getText().toString().equals(null);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void loadSpinner(List<Topic> topicList, View view) {
        List<Topic> topic = new ArrayList<Topic>();
        for (Topic a : topicList) {
            if (a.getTopicName().equals("all"))
                topic.add(a);
        }
        topicList.removeAll(topic);
        TopicSpinnerAdapter topicSpinnerAdapter = new TopicSpinnerAdapter(this.getContext(), android.R.layout.simple_spinner_item, topicList);
        spinnerTopicName.setAdapter(topicSpinnerAdapter);
    }


    @Override
    public void onSuccess(String message) {
        Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_1_button);
        dialog.setCancelable(false);
        ((TextView) dialog.findViewById(R.id.tvContent)).setText(message);
        dialog.findViewById(R.id.btOK).setOnClickListener(v -> {
            dialog.dismiss();
            GlobalFragment.replaceFragment(new QuestionFragment(), getActivity());
        });
        dialog.show();
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

    @Override
    public void setTopicSelected() {
        if (chooseQuestion == null) return;

        for (int i = 0; i < spinnerTopicName.getAdapter().getCount(); i++) {
            if (((ArrayAdapter<Topic>) spinnerTopicName.getAdapter()).getItem(i).getTopicID().equals(String.valueOf(chooseQuestion.getTopicID()))) {
                spinnerTopicName.setSelection(i);
                spinnerTopicName.setEnabled(false);
                textViewQuestionContent.setText(chooseQuestion.getQuestionContent());
                txtQuestionTitle.setText("Edit Question");

                return;
            }
        }
    }
}
