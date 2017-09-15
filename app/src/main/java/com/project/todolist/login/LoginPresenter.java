package com.project.todolist.login;

import com.project.todolist.firebase.authentication.AuthContract;
import com.project.todolist.firebase.authentication.FireBaseAuthModel;

/**
 * Created by mah_y on 9/14/2017.
 */

public class LoginPresenter implements LoginContract.Presenter,AuthContract.AuthCompleteListener {

    LoginContract.LoginView mView;
    AuthContract authContract;
    public LoginPresenter(LoginContract.LoginView mView) {
        this.mView = mView;
        authContract=new FireBaseAuthModel();
    }

    @Override
    public void checkUserSession() {
        authContract.isUserLoggedIn(this);
    }

    @Override
    public void onButtonClicked(String email, String pass) {
        authContract.SignIn(email,pass,this);
    }

    @Override
    public void onAuthSuccess() {
        mView.navigateToActivity();
    }

    @Override
    public void onAuthFailed() {
        mView.loginFailed();
    }
}
