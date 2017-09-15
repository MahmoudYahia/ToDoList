package com.project.todolist.login;

/**
 * Created by mah_y on 9/12/2017.
 */

public interface LoginContract {

    interface LoginView {
        void navigateToActivity();
        void loginFailed();
    }

    interface Presenter {
        void checkUserSession();

        void onButtonClicked(String email, String pass);

    }
}