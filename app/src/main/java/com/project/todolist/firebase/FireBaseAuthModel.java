package com.project.todolist.firebase;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.todolist.view.CompletableView;
import com.project.todolist.presenter.DataWriterPresenter;
import com.project.todolist.datamodel.User;
import com.project.todolist.view.AuthView;
import com.project.todolist.presenter.AuthPresenter;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import durdinapps.rxfirebase2.RxFirebaseUser;
import io.reactivex.Single;

/**
 * Created by mah_y on 8/29/2017.
 */

public class FireBaseAuthModel implements AuthPresenter {

    FirebaseAuth mAuth;
    AuthView authView;

    public FireBaseAuthModel(AuthView authView) {
        mAuth = FirebaseAuth.getInstance();
        this.authView = authView;
    }

    @Override
    public void checkUserSession() {
        checkUserIsLoogedIn().subscribe(aBoolean -> {
            if (aBoolean) {
                authView.navigateActivity();
            } else {
                authView.authFailed();
            }
        });
    }

    @Override
    public void signIn(String email, String password) {
        RxFirebaseAuth.signInWithEmailAndPassword(mAuth, email, password)
                .flatMapSingle(authResult -> checkFirebaseAuthResult(authResult))
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        authView.navigateActivity();
                    } else {
                        authView.authFailed();
                    }
                })

        ;

        //  return RxFirebaseAuth.signInWithEmailAndPassword(mAuth , email , password);
    }

    @Override
    public void signUp(String email, String pass) {
        addNewAccount(email, pass)
                .filter(firebaseUser -> firebaseUser != null)
                .doOnSuccess(firebaseUser -> RxFirebaseUser.sendEmailVerification(firebaseUser))
                .flatMapSingle(firebaseUser -> Single.just(new User(firebaseUser.getUid(), firebaseUser.getEmail())))
                .doOnSuccess(user -> {
                    DataWriterPresenter dataWriterPresenter = new FirebaseDatabaseWriter(new CompletableView() {
                        @Override
                        public void onWorkFinish() {
                        }
                        @Override
                        public void OnWorkError() {
                        }
                    });
                    dataWriterPresenter.addUser(user);
                })
                .subscribe((user, throwable) -> authView.navigateActivity());

    }

    public Single<Boolean> checkUserIsLoogedIn() {
        if (FirebaseDataRefrences.getInstance().getFirebaseUser() != null) {
            return Single.just(true);
        } else {
            return Single.just(false);
        }
    }

    public Single<Boolean> checkFirebaseAuthResult(AuthResult authResult) {
        if (authResult.getUser() != null) {
            return Single.just(true);
        } else {
            return Single.just(false);
        }
    }

    public Single<FirebaseUser> addNewAccount(String email, String pass) {
        return RxFirebaseAuth.createUserWithEmailAndPassword(mAuth, email, pass)
                .flatMapSingle(authResult -> Single.just(authResult.getUser()));
    }


}


