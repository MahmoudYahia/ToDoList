package com.project.todolist.firebase.authentication;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.todolist.firebase.dataWriter.DataWriterContract;
import com.project.todolist.firebase.dataWriter.FirebaseDatabaseWriter;
import com.project.todolist.firebase.refrences.FirebaseDataRefrences;
import com.project.todolist.datamodel.User;
import durdinapps.rxfirebase2.RxFirebaseAuth;
import durdinapps.rxfirebase2.RxFirebaseUser;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by mah_y on 8/29/2017.
 */

public class FireBaseAuthModel implements AuthContract {

    FirebaseAuth mAuth;

    public FireBaseAuthModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    Single<Boolean> checkUserIsLoogedIn() {
        if (FirebaseDataRefrences.getInstance().getFirebaseUser() != null) {
            return Single.just(true);
        } else {
            return Single.just(false);
        }
    }

    Single<Boolean> checkFirebaseAuthResult(AuthResult authResult) {
        if (authResult.getUser() != null) {
            return Single.just(true);
        } else {
            return Single.just(false);
        }
    }

    Single<FirebaseUser> addNewAccount(String email, String pass) {
        return RxFirebaseAuth.createUserWithEmailAndPassword(mAuth, email, pass)
                .flatMapSingle(authResult -> Single.just(authResult.getUser()));
    }


    @Override
    public Single<Boolean> SignIn(String email, String pass) {
        return RxFirebaseAuth.signInWithEmailAndPassword(mAuth, email, pass)
                .flatMapSingle(authResult -> checkFirebaseAuthResult(authResult));

    }

    @Override
    public Single<Boolean> isUserLoggedIn() {
        return checkUserIsLoogedIn();
    }

    @Override
    public Completable SignUp(String email, String pass) {
       return addNewAccount(email, pass)
                .filter(firebaseUser -> firebaseUser != null)
                .doOnSuccess(firebaseUser -> RxFirebaseUser.sendEmailVerification(firebaseUser))
                .flatMapSingle(firebaseUser -> Single.just(new User(firebaseUser.getUid(), firebaseUser.getEmail())))
                .flatMapCompletable(user -> {
                    DataWriterContract contract = new FirebaseDatabaseWriter();
                  return  contract.writeUser(user);
                });


    }



/*
    @Override
    public void SignUp(String email, String pass, AuthCompleteListener authCompleteListener) {
        addNewAccount(email, pass)
                .filter(firebaseUser -> firebaseUser != null)
                .doOnSuccess(firebaseUser -> RxFirebaseUser.sendEmailVerification(firebaseUser))
                .flatMapSingle(firebaseUser -> Single.just(new User(firebaseUser.getUid(), firebaseUser.getEmail())))
                .doOnSuccess(user -> {
                    DataWriterContract contract = new FirebaseDatabaseWriter(new DataWriterContract.WriteComleteListener() {
                        @Override
                        public void onWriteComplete() {

                        }

                        @Override
                        public void onWriteError() {

                        }
                    });
                    contract.writeUser(user);
                    /*


                     */


        }


