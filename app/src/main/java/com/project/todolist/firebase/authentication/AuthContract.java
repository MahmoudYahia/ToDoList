package com.project.todolist.firebase.authentication;


/**
 * Created by mah_y on 9/13/2017.
 */

public interface AuthContract {

    void SignIn(String emial,String pass,AuthCompleteListener authCompleteListener);
    void isUserLoggedIn(AuthCompleteListener authCompleteListener);
    void SignUp(String emial,String pass,AuthCompleteListener authCompleteListener);

     interface AuthCompleteListener{
        void onAuthSuccess();
        void onAuthFailed();
    }

}
