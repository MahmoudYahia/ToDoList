package com.project.todolist.firebase.authentication;


import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by mah_y on 9/13/2017.
 */

public interface AuthContract {

    Single<Boolean> SignIn(String email, String pass);
    Single<Boolean> isUserLoggedIn();
    Completable SignUp(String emial, String pass);



}
