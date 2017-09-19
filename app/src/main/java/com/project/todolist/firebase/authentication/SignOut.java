package com.project.todolist.firebase.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.project.todolist.firebase.refrences.FirebaseDataRefrences;

import io.reactivex.Completable;

/**
 * Created by mah_y on 9/18/2017.
 */

public class SignOut implements SignOutContract {
    @Override
    public Completable signOut() {
        FirebaseAuth.getInstance().signOut();
        FirebaseDataRefrences.getInstance().setFirebaseUserNull();
        return Completable.complete();
    }
}
