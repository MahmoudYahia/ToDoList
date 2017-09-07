package com.project.todolist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.project.todolist.R;
import com.project.todolist.callback.Authenticator;
import com.project.todolist.callback.DataBaseWriter;
import com.project.todolist.datamodel.User;
import com.project.todolist.firebase.FireBaseAuthModel;
import com.project.todolist.firebase.FireBaseDatabaseModel;
import com.project.todolist.firebase.FirebaseDataRefrences;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    SignInButton mSignInButton;
    Button loginBtn, RegisterBtn;
    EditText InputEmial, InputPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (FirebaseDataRefrences.getInstance().getFirebaseUser()!=null){
            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        InputEmial = (EditText) findViewById(R.id.txt_email);
        InputPass = (EditText) findViewById(R.id.txt_pass);

        loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(this);

        RegisterBtn = (Button) findViewById(R.id.btn_sign_up);
        RegisterBtn.setOnClickListener(this);

        mSignInButton = (SignInButton) findViewById(R.id.sign_in_google);
        mSignInButton.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.sign_in_google:
                break;

            case R.id.btn_login:
                String email=InputEmial.getText().toString();
                String pass=InputPass.getText().toString();

                if (TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){
                    Toast.makeText(SignInActivity.this,"empty field",Toast.LENGTH_LONG).show();
                }
                else {
                    Authenticator authenticator= new FireBaseAuthModel();
                    authenticator.signIn(email,pass).subscribe(authResult -> {

                        if (authResult.getUser()!=null){
                            Log.i("checkuser",authResult.getUser().getEmail());

                            startActivity(new Intent(SignInActivity.this,MainActivity.class));
                        }
                    },throwable ->
                    {
                        Toast.makeText(SignInActivity.this,"authentication Failed",Toast.LENGTH_LONG).show();
                    });
                }


                break;
            case R.id.btn_sign_up:
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                break;

        }
    }



}




/*
        RxFirebaseDatabase.observeSingleValueEvent(FirebaseDatabase.getInstance().getReference().child("posts"),
                dataSnapshot -> {
                    // do your own mapping here
                    return new Author();
                })
                .subscribe(author -> {
                    // process author value
                });
*/

/*
        RxFirebaseDatabase.observeSingleValueEvent(FirebaseDatabase.getInstance().getReference().child("posts"),
                DataSnapshotMapper.listOf(PostComment.class))

                .subscribe(blogPost -> {
                    // process postcomment list item
                });
*/
       /*
        RxFirebaseDatabase.observeSingleValueEvent(FirebaseDatabase.getInstance().getReference().child("posts"),
                DataSnapshotMapper.mapOf(PostComment.class))
                .subscribe(PostCommentAsMapItem -> {
                    // process blogPost as key-value pair
                });
                */


/*
     RxFirebaseDatabase.observeSingleValueEvent((FirebaseDatabase.getInstance().getReference().child("posts")),MainActivity.class)
                .subscribe(post -> {
           //Do something with yourpost
        });
*/
