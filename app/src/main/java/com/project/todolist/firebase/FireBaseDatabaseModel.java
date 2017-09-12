package com.project.todolist.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.project.todolist.callback.DataBaseReader;
import com.project.todolist.callback.DataBaseWriter;
import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.ItemKeyVal;
import com.project.todolist.datamodel.User;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseChildEvent;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;


/**
 * Created by mah_y on 8/29/2017.
 */

public class FireBaseDatabaseModel implements DataBaseWriter, DataBaseReader {

    @Override
    public Single<String> addItem(Item item) {
        DatabaseReference itemsRef = FirebaseDataRefrences.getInstance().getReference().child("items");
        DatabaseReference childRef = itemsRef.push();
        String key = childRef.getKey();
        Log.i("itemKey", key);
        return RxFirebaseDatabase.setValue(childRef, item).andThen(Single.just(key));
    }

    @Override
    public Completable addUser(User user) {
        return RxFirebaseDatabase.setValue(FirebaseDataRefrences.getInstance().getReference()
                .child("users").child(user.getUid()), user);
    }

    @Override
    public Completable shareItem(String UserId, String ItemId) {
        return RxFirebaseDatabase.setValue(FirebaseDataRefrences.getInstance().getReference()
                .child("users").child(UserId).child("userItems").push(), ItemId);
    }

    @Override
    public Completable addItemToUser(String itemKey) {
        return RxFirebaseDatabase.setValue(FirebaseDataRefrences.getInstance().getReference()
                .child("users").child(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid())
                .child("userItems").push(), itemKey);
    }

    @Override
    public Maybe<DataSnapshot> readUserItemsId() {
        return RxFirebaseDatabase.observeSingleValueEvent(FirebaseDataRefrences.getInstance().getReference()
                .child("users").child(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid())
                .child("userItems"));
    }

    @Override
    public Single<List<User>> readUsers() {

        return RxFirebaseDatabase.observeSingleValueEvent(FirebaseDataRefrences.getInstance().getReference().child("users"),
                DataSnapshotMapper.listOf(User.class))
                .flatMapObservable(Observable::fromIterable)
                .filter(user -> !user.getUid().equals(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid()))
                .toList();
    }

    public Flowable<List<ItemKeyVal>> listenChanges() {
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


    @Override
    public Single<List<ItemKeyVal>> readItems() {
        DatabaseReference reference = FirebaseDataRefrences.getInstance().getReference();
        Query where = reference.child("users")
                .child(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid())
                .child("userItems");

        return
                RxFirebaseDatabase.observeSingleValueEvent(where, DataSnapshotMapper.listOf(String.class))
                        .flatMapObservable(Observable::fromIterable)
                        .flatMapMaybe(str ->
                                RxFirebaseDatabase.observeSingleValueEvent(reference.child("items").child(str)))
                        .flatMap(snapshot -> Observable.just(new ItemKeyVal(snapshot.getKey(), snapshot.getValue(Item.class))))
                        .toList();

    }


}

   /*    RxFirebaseDatabase.observeSingleValueEvent(where, DataSnapshotMapper.listOf(String.class))
                .flatMapObservable(Observable::fromIterable)
                .flatMapMaybe(str -> RxFirebaseDatabase.observeSingleValueEvent(reference.child("items").child(str)))
                .flatMap(keyVal -> Observable.just(keyVal.getKey()))
                .toList()
                .subscribe(data -> dataFetcher.OnKeysFetched(data));
*/
//--
/*
        RxFirebaseDatabase.observeSingleValueEvent(where, DataSnapshotMapper.listOf(String.class))
                .flatMapObservable(Observable::fromIterable)
                .flatMapMaybe(str -> RxFirebaseDatabase.observeSingleValueEvent(reference.child("items").child(str)))
                .flatMap(keyVal -> Observable.just(keyVal.getKey()))
                .toList()
                .subscribe(data -> dataFetcher.OnKeysFetched(data));
*/
//-----


/*
        RxFirebaseDatabase.observeSingleValueEvent(where, DataSnapshotMapper.listOf(String.class))
                .flatMapObservable(Observable::fromIterable)
                .flatMapMaybe(str -> RxFirebaseDatabase.observeSingleValueEvent(reference.child("items").child(str)))

                .doOnNext(snapshot -> {
                    Observable.just(snapshot.getKey())
                            .toList();
                })

                .flatMap(snapshot -> Observable.just(snapshot.getValue(Item.class)))
                .toList()

                .subscribe(data->{
                    dataFetcher.OnDataFetched(data);

                });


*/