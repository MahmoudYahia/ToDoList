package com.project.todolist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.todolist.R;
import com.project.todolist.signup.SignUpContract;
import com.project.todolist.signup.SignUpPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.SignupView {
    @Bind(R.id.reg_email)
    EditText RegUserEmail;
    @Bind(R.id.reg_password)
    EditText RegUserPassword;
    @Bind(R.id.reg_confirm_pass)
    EditText RegUserConfirmPass;
    @Bind(R.id.btnRegister)
    Button RegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRegister)
    public void addAccount() {
        SignUpContract.Presenter Presenter =new SignUpPresenter(this);

        Presenter.onRegisterButtonClicked(RegUserEmail.getText().toString(),
                RegUserPassword.getText().toString(),
                RegUserConfirmPass.getText().toString());
    }


    @Override
    public void navigateToSignInActivity() {
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
        finish();
    }

    @Override
    public void showSignUpFailedMessage() {
        Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void ResetConfirmPass() {
        RegUserConfirmPass.setText("");
    }

    @Override
    public void showNotValidPassword() {
        Toast.makeText(this,"size must > 5",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyFieldsMessage() {
        Toast.makeText(this, "empty field", Toast.LENGTH_LONG).show();

    }
}
