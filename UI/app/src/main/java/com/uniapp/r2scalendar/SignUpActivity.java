package com.uniapp.r2scalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uniapp.r2scalendar.Controller.IAccountController;
import com.uniapp.r2scalendar.Controller.AccountController;
import com.uniapp.r2scalendar.Model.Trainee;
import com.uniapp.r2scalendar.View.ISignUpView;

public class SignUpActivity extends AppCompatActivity implements ISignUpView {
    IAccountController accountController;

    EditText email,password,name,confirm;
    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        accountController = new AccountController(null, this);

        OnSignUp();
    }

    public void OnSignUp() {
        email = (EditText) findViewById(R.id.textEmailSignUp);
        password = (EditText) findViewById(R.id.textPasswordSignUp);
        confirm = (EditText) findViewById(R.id.textConfirmSignUp);
        name = (EditText) findViewById(R.id.textName);
        signUpButton = findViewById(R.id.signUpButton);

        email.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        signUpButton.setOnClickListener(v-> {
            if (!password.getText().toString().equals(confirm.getText().toString())) {
                Toast.makeText(this, "Password doesn't match!", Toast.LENGTH_LONG).show();
            }

            Trainee user = new Trainee();
            user.setName(name.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            accountController.OnSignUp(user);
        });
    }

    @Override
    public void OnSignUpSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}