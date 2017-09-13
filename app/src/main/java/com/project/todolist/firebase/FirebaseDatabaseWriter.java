package com.project.todolist.firebase;

import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.project.todolist.view.CompletableView;
import com.project.todolist.presenter.DataWriterPresenter;
import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.User;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Single;


/**
 * Created by mah_y on 8/29/2017.
 */

public class FirebaseDatabaseWriter implements DataWriterPresenter {

    CompletableView completableView;

    public FirebaseDatabaseWriter(CompletableView completableView) {
        this.completableView = completableView;
    }

    @Override
    public void addItem(Item item) {
        addItemToDataBase(item)
                .flatMapCompletable(itemKey -> addItemToUserToDataBase(itemKey))
                .subscribe(() -> completableView.onWorkFinish());
    }

    @Override
    public void addUser(User user) {
        addUsertoDatabase(user)
                .subscribe(() -> completableView.onWorkFinish(),throwable -> completableView.OnWorkError());
    }

    @Override
    public void shareItem(String UserId, String ItemId) {
       shareItemToUser(UserId, ItemId)
               .subscribe(() -> completableView.onWorkFinish(),throwable -> completableView.OnWorkError());
    }

    public Single<String> addItemToDataBase(Item item) {
        DatabaseReference itemsRef = FirebaseDataRefrences.getInstance().getReference().child("items");
        DatabaseReference childRef = itemsRef.push();
        String key = childRef.getKey();
        Log.i("itemKey", key);
        return RxFirebaseDatabase.setValue(childRef, item).andThen(Single.just(key));
    }

    public Completable addItemToUserToDataBase(String itemKey) {
        return RxFirebaseDatabase.setValue(FirebaseDataRefrences.getInstance().getReference()
                .child("users").child(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid())
                .child("userItems").push(), itemKey);
    }

    public Completable shareItemToUser(String UserId, String ItemId) {
        return RxFirebaseDatabase.setValue(FirebaseDataRefrences.getInstance().getReference()
                .child("users").child(UserId).child("userItems").push(), ItemId);
    }

    public Completable addUsertoDatabase(User user) {
        return RxFirebaseDatabase.setValue(FirebaseDataRefrences.getInstance().getReference()
                .child("users").child(user.getUid()), user);
    }
}
