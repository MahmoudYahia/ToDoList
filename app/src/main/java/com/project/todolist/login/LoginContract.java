package com.project.todolist.login;

/**
 * Created by mah_y on 9/12/2017.
 */

public interface LoginContract {

    interface LoginView {

        void navigateToMainActivity(); /// activity name

        void showFailedMessage(); // show message

        void showEmptyFieldMessage();

        void navigateToSignUpActivity();


        void notLoggedUser();

    }

    interface Presenter extends BasePresenter {

        void onLoginButtonClicked(String email, String pass);

        void onRegisterButtonClicked();


    }
}