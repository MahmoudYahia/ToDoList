package com.project.todolist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.project.todolist.R;
import com.project.todolist.login.LoginContract;
import com.project.todolist.login.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements LoginContract.LoginView {

    @Bind(R.id.txt_email)
    EditText InputEmial;
    @Bind(R.id.txt_pass)
    EditText InputPass;
    @Bind(R.id.btn_login)
    Button loginBtn;
    @Bind(R.id.btn_sign_up)
    Button RegisterBtn;
    @Bind(R.id.sign_in_google)
    SignInButton mSignInButton;

    LoginContract.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loginPresenter.onActivityStarted();
    }

    @OnClick(R.id.btn_login)
    public void onLoginPressed() {
        loginPresenter.onLoginButtonClicked(InputEmial.getText().toString(), InputPass.getText().toString());
    }

    @OnClick(R.id.btn_sign_up)
    public void onRegisterButtonPressed() {
        loginPresenter.onRegisterButtonClicked();
    }

    @Override
    public void navigateToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        SignInActivity.this.finish();
    }

    @Override
    public void showFailedMessage() {
        Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyFieldMessage() {
        Toast.makeText(this,"Empty Field",Toast.LENGTH_LONG).show();
    }


    @Override
    public void navigateToSignUpActivity() {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }



    @Override
    public void notLoggedUser() {

    }
}

