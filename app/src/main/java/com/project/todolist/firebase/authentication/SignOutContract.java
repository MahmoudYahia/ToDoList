package com.project.todolist.firebase.authentication;

import io.reactivex.Completable;

/**
 * Created by mah_y on 9/18/2017.
 */

public interface SignOutContract {
    Completable signOut();
}
