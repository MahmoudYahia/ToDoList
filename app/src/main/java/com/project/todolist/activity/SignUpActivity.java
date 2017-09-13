package com.project.todolist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.todolist.R;
import com.project.todolist.firebase.FireBaseAuthModel;
import com.project.todolist.view.AuthView;
import com.project.todolist.presenter.AuthPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements AuthView{
    @Bind(R.id.reg_email)
    EditText RegUserEmail;
    @Bind(R.id.reg_password)
    EditText  RegUserPassword;
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
        String UserEmail, UserPass, UserConfirmPass;

        UserEmail = RegUserEmail.getText().toString();
        UserPass = RegUserPassword.getText().toString();
        UserConfirmPass = RegUserConfirmPass.getText().toString();

        if (TextUtils.isEmpty(UserEmail)
                || TextUtils.isEmpty(UserPass) || TextUtils.isEmpty(UserConfirmPass)) {

            Toast.makeText(this, "Empty Fields", Toast.LENGTH_LONG).show();
        } else {

            if (UserPass.length() > 5) {
                if (UserPass.equals(UserConfirmPass)) {
                    // matched pass
                    AuthPresenter authPresenter = new FireBaseAuthModel(this);
                    authPresenter.signUp(UserEmail,UserPass);
                } else {
                    RegUserConfirmPass.setText("");
                }
            } else {
                RegUserPassword.setError("minimum size 6 ");
            }

        }
    }

    @Override
    public void navigateActivity() {
        startActivity( new Intent(SignUpActivity.this,SignInActivity.class));
        finish();
    }

    @Override
    public void authFailed() {

    }
}
