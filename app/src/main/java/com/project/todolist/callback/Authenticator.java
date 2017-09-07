package com.project.todolist.callback;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by mah_y on 8/29/2017.
 */

public interface Authenticator {
    Maybe<AuthResult> signIn(String email, String pass);
    Maybe<AuthResult> signUp(String email, String pass);
    Completable SignOut();
}
