package com.project.todolist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.project.todolist.R;
import com.project.todolist.callback.Authenticator;
import com.project.todolist.callback.DataBaseWriter;
import com.project.todolist.datamodel.User;
import com.project.todolist.firebase.FireBaseAuthModel;
import com.project.todolist.firebase.FireBaseDatabaseModel;
import com.project.todolist.firebase.FirebaseDataRefrences;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        if (FirebaseDataRefrences.getInstance().getFirebaseUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @OnClick(R.id.btn_login)
    public void onLoginPreesed() {
        String email = InputEmial.getText().toString();
        String pass = InputPass.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(SignInActivity.this, "empty field", Toast.LENGTH_LONG).show();
        } else {
            Authenticator authenticator = new FireBaseAuthModel();
            authenticator.signIn(email, pass).subscribe(authResult -> {

                if (authResult.getUser() != null) {
                    Log.i("checkuser", authResult.getUser().getEmail());
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                }
            }, throwable ->
            {
                Toast.makeText(SignInActivity.this, "authentication Failed", Toast.LENGTH_LONG).show();
            });
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void onRegisterPressed() {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }

}

