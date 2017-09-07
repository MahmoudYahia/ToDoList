package com.project.todolist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.todolist.R;
import com.project.todolist.callback.Authenticator;
import com.project.todolist.callback.DataBaseWriter;
import com.project.todolist.datamodel.User;
import com.project.todolist.firebase.FireBaseAuthModel;
import com.project.todolist.firebase.FireBaseDatabaseModel;
import com.project.todolist.firebase.FirebaseDataRefrences;

import durdinapps.rxfirebase2.RxFirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    EditText RegUserEmail, RegUserPassword, RegUserConfirmPass;
    Button RegisterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        RegUserEmail = (EditText) findViewById(R.id.reg_email);
        RegUserPassword = (EditText) findViewById(R.id.reg_password);
        RegUserConfirmPass = (EditText) findViewById(R.id.reg_confirm_pass);
        RegisterButton = (Button) findViewById(R.id.btnRegister);

        RegisterButton.setOnClickListener(v -> {
            addAccount();
        });
    }


    public void addAccount() {
        String UserEmail, UserPass, UserConfirmPass;

        UserEmail = RegUserEmail.getText().toString();
        UserPass = RegUserPassword.getText().toString();
        UserConfirmPass = RegUserConfirmPass.getText().toString();

        if (TextUtils.isEmpty(UserEmail)
                || TextUtils.isEmpty(UserPass) || TextUtils.isEmpty(UserConfirmPass)) {

            Toast.makeText(this, "Empty Fields", Toast.LENGTH_LONG).show();
            // make Snackbar >> Empty Fields
        } else {

            if (UserPass.length() > 5) {
                if (UserPass.equals(UserConfirmPass)) {
                    // matched pass

                    Authenticator authenticator= new FireBaseAuthModel();
                    authenticator.signUp(UserEmail,UserPass).subscribe(authResult -> {
                        RxFirebaseUser.sendEmailVerification(authResult.getUser());
                        DataBaseWriter writer= new FireBaseDatabaseModel();
                        User user= new User(authResult.getUser().getUid(),authResult.getUser().getEmail());
                        writer.addUser(FirebaseDataRefrences.getInstance().getReference(),user).subscribe();
                    },throwable -> {
                        Toast.makeText(SignUpActivity.this,"Error",Toast.LENGTH_LONG).show();
                    });
                } else {
                    RegUserConfirmPass.setText("");
                }
            } else {
                RegUserPassword.setError("minimum size 6 ");
            }

        }
    }
}
