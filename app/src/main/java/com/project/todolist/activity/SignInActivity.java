package com.project.todolist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.project.todolist.R;
import com.project.todolist.firebase.FireBaseAuthModel;
import com.project.todolist.view.AuthView;
import com.project.todolist.presenter.AuthPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements AuthView {

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

    AuthPresenter authPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        authPresenter = new FireBaseAuthModel(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        authPresenter.checkUserSession();
    }


    @OnClick(R.id.btn_login)
    public void onLoginPreesed() {
        String email = InputEmial.getText().toString();
        String pass = InputPass.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(SignInActivity.this, "empty field", Toast.LENGTH_LONG).show();
        }
        else {
            authPresenter.signIn(email,pass);
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void onRegisterPressed() {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }

    @Override
    public void navigateActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(this,"go Main Activity",Toast.LENGTH_LONG).show();
        startActivity(intent);
        SignInActivity.this.finish();
    }

    @Override
    public void authFailed() {

    }
}

