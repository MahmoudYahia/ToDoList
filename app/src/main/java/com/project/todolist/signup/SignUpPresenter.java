package com.project.todolist.signup;

import com.project.todolist.datamodel.User;
import com.project.todolist.firebase.authentication.AuthContract;
import com.project.todolist.firebase.authentication.FireBaseAuthModel;

/**
 * Created by mah_y on 9/14/2017.
 */

public class SignUpPresenter implements SignUpContract.Presenter
        , AuthContract.AuthCompleteListener {

    SignUpContract.SignupView signupView;
    AuthContract authContract;

    public SignUpPresenter(SignUpContract.SignupView signupView) {
        this.signupView = signupView;
        authContract = new FireBaseAuthModel();

    }

    @Override
    public void onRegisterButtonClicked(String email, String pass) {
        authContract.SignUp(email, pass, this);
    }


    @Override
    public void onAuthSuccess() {
        signupView.navigateToActivity();
    }

    @Override
    public void onAuthFailed() {
        signupView.signUpFailed();
    }
}
