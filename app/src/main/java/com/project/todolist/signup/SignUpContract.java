package com.project.todolist.signup;

import com.project.todolist.datamodel.User;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface SignUpContract {

    interface SignupView{
        void navigateToActivity();
        void signUpFailed();
    }

    interface Presenter {
        void onRegisterButtonClicked(String email , String pass);
    }

}
