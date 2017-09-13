package com.project.todolist.callback;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by mah_y on 8/29/2017.
 */

public interface Authenticator {
    Completable SignOut();
}
