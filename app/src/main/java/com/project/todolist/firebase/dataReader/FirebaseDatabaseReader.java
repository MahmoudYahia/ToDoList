package com.project.todolist.firebase.dataReader;

import com.google.firebase.database.Query;
import com.project.todolist.firebase.refrences.FirebaseDataRefrences;
import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.ItemKeyVal;
import com.project.todolist.datamodel.User;

import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mah_y on 9/12/2017.
 */

public class FirebaseDatabaseReader implements DataReaderContract  {
    DataFetcherListener fetcherListener;

    public FirebaseDatabaseReader(DataFetcherListener fetcherListener) {
        this.fetcherListener = fetcherListener;
    }

    @Override
    public void readItems() {
        readItemsFromDataBase()
                .subscribe(itemKeyVals -> {
                    fetcherListener.onDataFetched(itemKeyVals);
        },throwable -> {
                    fetcherListener.onErrorFetchingData();
             });
    }

    @Override
    public void readUsersList() {
        readUsersfromDataBase().subscribe(users -> {
           fetcherListener.onDataFetched(users);
        },throwable -> {
            fetcherListener.onErrorFetchingData();
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

//    @Override
//    public void readUserItemsId() {
//         RxFirebaseDatabase.observeSingleValueEvent(FirebaseDataRefrences.getInstance().getReference()
//                .child("users").child(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid())
//                .child("userItems"));
//    }

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
