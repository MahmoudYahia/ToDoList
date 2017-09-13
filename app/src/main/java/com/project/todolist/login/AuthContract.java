package com.project.todolist.login;

/**
 * Created by mah_y on 9/13/2017.
 */

public interface AuthContract {
    void SignIn(String emial,String pass);
    void isUserLoggedIn();

}
