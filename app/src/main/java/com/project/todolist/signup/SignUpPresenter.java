package com.project.todolist.signup;

import android.text.TextUtils;

import com.project.todolist.firebase.authentication.AuthContract;
import com.project.todolist.firebase.authentication.FireBaseAuthModel;

/**
 * Created by mah_y on 9/14/2017.
 */

public class SignUpPresenter implements SignUpContract.Presenter
        {

    SignUpContract.SignupView signupView;
    AuthContract authContract;

    public SignUpPresenter(SignUpContract.SignupView signupView) {
        this.signupView = signupView;
        authContract = new FireBaseAuthModel();

    }

    @Override
    public void onRegisterButtonClicked(String email, String pass, String confirmPass) {
        if ( TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmPass) ) {
           signupView.showEmptyFieldsMessage();
        }
        else {

            if (pass.length() > 5) {
                if (pass.equals(confirmPass)) {
                    // matched pass
                    authContract.SignUp(email,pass)
                    .subscribe(() -> {
                        signupView.navigateToSignInActivity();
                    },throwable -> {
                        signupView.showSignUpFailedMessage();
                    });
                }
                else {
                    // reset confirm pass
                    signupView.ResetConfirmPass();
                }
            } else {
                // length is less 6 num
                signupView.showNotValidPassword();
            }
        }
    }
}
