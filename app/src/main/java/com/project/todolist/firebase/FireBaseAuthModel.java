package com.project.todolist.firebase;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.todolist.callback.Authenticator;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by mah_y on 8/29/2017.
 */

public class FireBaseAuthModel implements Authenticator {

    FirebaseAuth mAuth;

    public FireBaseAuthModel() {
        mAuth=FirebaseAuth.getInstance();
    }

    public Maybe<AuthResult> signIn(String email, String password){
        return RxFirebaseAuth.signInWithEmailAndPassword(mAuth , email , password);
    }

    @Override
    public Maybe<AuthResult> signUp(String email, String pass) {
         return RxFirebaseAuth.createUserWithEmailAndPassword(mAuth , email, pass);
    }

    @Override
    public Completable SignOut() {
        return null;
    }


}
