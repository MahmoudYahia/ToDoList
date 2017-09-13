package com.project.todolist.firebase;

import com.google.firebase.database.Query;
import com.project.todolist.view.DataOfView;
import com.project.todolist.presenter.DataReaderPresenter;
import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.ItemKeyVal;
import com.project.todolist.datamodel.User;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mah_y on 9/12/2017.
 */

public class FirebaseDatabaseReader implements DataReaderPresenter {
    DataOfView dataOfView;

    public FirebaseDatabaseReader(DataOfView dataOfView) {
        this.dataOfView = dataOfView;
    }

    @Override
    public void readUsers() {
        readUsersfromDataBase().subscribe(users -> {
            dataOfView.dataFitched(users);
        },throwable -> {
            dataOfView.dataFitched(new ArrayList());
        });
    }

    @Override
    public void readItems() {
        readItemsFromDataBase()
                .subscribe(itemKeyVals -> {
            dataOfView.dataFitched(itemKeyVals);
        },throwable -> {
                    dataOfView.dataFitched(new ArrayList());
                });
    }

//    public Flowable<List<ItemKeyVal>> listenChangesfromDatabase() {
//        Query where = FirebaseDataRefrences.getInstance().getReference().child("users")
//                .child(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid())
//                .child("userItems");
//
//        return RxFirebaseDatabase.observeValueEvent(where, DataSnapshotMapper.listOf(String.class))
//                .flatMapSingle((source) -> Flowable.fromIterable(source)
//                        .flatMapMaybe(s -> RxFirebaseDatabase.observeSingleValueEvent(FirebaseDataRefrences.getInstance().getReference()
//                                .child("items")
//                                .child(s)))
//                        .map(snapshot -> new ItemKeyVal(snapshot.getKey(), snapshot.getValue(Item.class)))
//                        .toList());
//    }

    @Override
    public void readUserItemsId() {
         RxFirebaseDatabase.observeSingleValueEvent(FirebaseDataRefrences.getInstance().getReference()
                .child("users").child(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid())
                .child("userItems"));
    }

    public Single<List<User>> readUsersfromDataBase(){
        return RxFirebaseDatabase.observeSingleValueEvent(FirebaseDataRefrences.getInstance().getReference().child("users"),
                DataSnapshotMapper.listOf(User.class))
                .flatMapObservable(Observable::fromIterable)
                .filter(user -> !user.getUid().equals(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid()))
                .toList();
    }

    public Flowable<List<ItemKeyVal>> readItemsFromDataBase(){
        Query where = FirebaseDataRefrences.getInstance().getReference().child("users")
                .child(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid())
                .child("userItems");

        return RxFirebaseDatabase.observeValueEvent(where, DataSnapshotMapper.listOf(String.class))
                .flatMapSingle((source) -> Flowable.fromIterable(source)
                        .flatMapMaybe(s -> RxFirebaseDatabase.observeSingleValueEvent(FirebaseDataRefrences.getInstance().getReference()
                                .child("items")
                                .child(s)))
                        .map(snapshot -> new ItemKeyVal(snapshot.getKey(), snapshot.getValue(Item.class)))
                        .toList());

    }


}
