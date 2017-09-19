package com.project.todolist.login;

import android.text.TextUtils;

import com.project.todolist.firebase.authentication.AuthContract;
import com.project.todolist.firebase.authentication.FireBaseAuthModel;

import io.reactivex.Single;

/**
 * Created by mah_y on 9/14/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View mView;
    AuthContract authContract;


    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        authContract = new FireBaseAuthModel();
    }

    @Override
    public void onLoginButtonClicked(String email, String pass) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            mView.showEmptyFieldMessage();
        } else {
            authContract.SignIn(email, pass)
                    .subscribe(aBoolean -> {
                        if (aBoolean) {
                            mView.navigateToMainActivity();
                        } else {
                            mView.showFailedMessage();
                        }
                    },throwable -> {
                        mView.showFailedMessage();
                    });
        }

    }

    @Override
    public void onRegisterButtonClicked() {
        mView.navigateToSignUpActivity();
    }

    @Override
    public void onActivityStarted() {
        authContract.isUserLoggedIn()
                .subscribe(aBoolean -> {
            if (aBoolean){
              mView.navigateToMainActivity();
            }
        });
    }
}
