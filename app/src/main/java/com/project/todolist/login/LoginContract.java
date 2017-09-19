package com.project.todolist.login;

/**
 * Created by mah_y on 9/12/2017.
 */

public interface LoginContract {

    interface View {

        void navigateToMainActivity();

        void showFailedMessage();

        void showEmptyFieldMessage();

        void navigateToSignUpActivity();
    }

    interface Presenter extends BasePresenter {

        void onLoginButtonClicked(String email, String pass);

        void onRegisterButtonClicked();


    }
}