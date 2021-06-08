package com.uniapp.r2scalendar;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.uniapp.r2scalendar.Controller.ILoginController;
import com.uniapp.r2scalendar.Controller.LoginController;
import com.uniapp.r2scalendar.Model.LoginRequest;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.Utils.ProgressDialogaa;
import com.uniapp.r2scalendar.View.ILoginView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements ILoginView,View.OnClickListener {
    private EditText edt_username, edt_password;
    private Button btn_login;
    private TextView txtUSN,txtPW;
    private Spinner spn_choose;
    private ILoginController loginController;
    private CheckBox cb_rememberme;
    private Dialog dialog;
    private SharedPreferences sharedPreferences;

    private Button btOk;
    private ImageView imgIconDialog;
    private TextView tvContent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("DataLogin", MODE_PRIVATE);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_1_button);
        dialog.setCancelable(false);

        mapping();

        imgIconDialog.setImageResource(R.drawable.ic_baseline_error_outline_24);
        tvContent.setText("Login Failed, Check Your Login Details");
        btOk.setBackgroundTintList(this.getResources().getColorStateList(R.color.sky_blue));
        btOk.setTextSize(11);
        btOk.setText("Yes");

        txtUSN.setVisibility(View.INVISIBLE);
        txtPW.setVisibility(View.INVISIBLE);

        loginController = new LoginController(this);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Admin");
        arrayList.add("Trainer");
        arrayList.add("Trainee");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_choose.setAdapter(arrayAdapter);


        edt_username.setText(sharedPreferences.getString("username",""));
        edt_password.setText(sharedPreferences.getString("password",""));
        cb_rememberme.setChecked(sharedPreferences.getBoolean("checked",false));
        ArrayAdapter myAdap = (ArrayAdapter) spn_choose.getAdapter();
        int spinnerPosition = myAdap.getPosition(sharedPreferences.getString("role","Admin"));
        spn_choose.setSelection(spinnerPosition);

        btn_login.setOnClickListener(this);

    }

    private void mapping()
    {
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        spn_choose = (Spinner) findViewById(R.id.spn_choose);
        txtUSN = (TextView) findViewById(R.id.txtUSN);
        txtPW = (TextView) findViewById(R.id.txtPW);
        cb_rememberme = (CheckBox) findViewById(R.id.checkbox_rememberpassword);

        imgIconDialog = dialog.findViewById(R.id.imgIconDialog);
        tvContent = dialog.findViewById(R.id.tvContent);
        btOk = dialog.findViewById(R.id.btOK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if (spn_choose.getSelectedItem().toString().equals("Admin"))
                {
                    if (check()== true) loginController.getAllAdmin(edt_username.getText().toString().trim(), edt_password.getText().toString().trim());
                }
                else if (spn_choose.getSelectedItem().toString().equals("Trainer"))
                {
                    if(check()== true) loginController.getAllTrainer(edt_username.getText().toString().trim(), edt_password.getText().toString().trim());
                }
                else
                {
                    if (check()==true) loginController.getAllTrainee(edt_username.getText().toString().trim(), edt_password.getText().toString().trim());
                }
        }
    }

    @Override
    public void onResult(LoginRequest response) {
        txtUSN.setVisibility(View.INVISIBLE);
        txtPW.setVisibility(View.INVISIBLE);
        GlobalUser.getInstance().put("idUser",response.getUserName());
        if (spn_choose.getSelectedItem().toString().equals("Admin"))
        {
            handlePreferences();
            GlobalUser.getInstance().put("role", "Admin");
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else if (spn_choose.getSelectedItem().toString().equals("Trainer"))
        {
            handlePreferences();
            GlobalUser.getInstance().put("role", "Trainer");
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        else
        {
            handlePreferences();
            GlobalUser.getInstance().put("role", "Trainee");
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

        }
    }



    @Override
    public void onResponseFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        dialog.show();
        btOk.setOnClickListener(v ->{
            dialog.dismiss();
            edt_username.setText("");
            edt_password.setText("");
        });
    }

    public int checkWhiteSpace(String str)
    {
        int spaceCount = 0;
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                spaceCount++;
            }
        }
        return spaceCount;
    }

    protected void handlePreferences() {
        if(cb_rememberme.isChecked()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", edt_username.getText().toString());
            editor.putString("password",edt_password.getText().toString());
            editor.putString("role",spn_choose.getSelectedItem().toString());
            editor.putBoolean("checked",true);
            editor.apply();
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("username");
            editor.remove("password");
            editor.remove("role");
            editor.remove("checked");
            editor.apply();
        }
    }


    private boolean check()
    {
        if (checkWhiteSpace(edt_username.getText().toString())>0)
        {
            txtUSN.setVisibility(View.VISIBLE);
            txtPW.setVisibility(View.VISIBLE);
            txtUSN.setText("Username must have at blank space!");
            txtPW.setText("Password must have at least 1 character!");
            return false;

        }
        else if(edt_username.getText().toString().trim().equals("") || edt_password.getText().toString().trim().equals(""))
        {
            txtUSN.setVisibility(View.VISIBLE);
            txtPW.setVisibility(View.VISIBLE);
            txtUSN.setText("Username must have at least 1 character!");
            txtPW.setText("Password must have at least 1 character!");
            return false;
        }
        return true;
    }

    @Override
    public void displayProgressDialog() {
        ProgressDialogaa.getProgressDialog().displayProgressDialog(getWindow().getDecorView().getRootView(), "Please wait...", "Just a second...");
    }

    @Override
    public void disableProgressDialog() {
        ProgressDialogaa.getProgressDialog().disableProgressDialog();
    }


}
