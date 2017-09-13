package com.project.todolist.presenter;

/**
 * Created by mah_y on 9/12/2017.
 */

public interface AuthPresenter {
    void checkUserSession();
    void signIn(String email,String pass);
    void signUp(String email,String pass);
}
