package com.project.todolist.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.project.todolist.callback.DataBaseReader;
import com.project.todolist.callback.DataBaseWriter;
import com.project.todolist.callback.DataFetcher;
import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.User;

import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mah_y on 8/29/2017.
 */

public class FireBaseDatabaseModel implements DataBaseWriter, DataBaseReader {

    @Override
    public Single<String> addItem(DatabaseReference reference, Item item) {
        DatabaseReference itemsRef = reference.child("items");

        DatabaseReference childRef = itemsRef.push();
        String key = childRef.getKey();
        Log.i("itemKey", key);
        return RxFirebaseDatabase.setValue(childRef, item).andThen(Single.just(key));
    }

    @Override
    public Completable addUser(DatabaseReference reference, User user) {
        return RxFirebaseDatabase.setValue(reference.child("users").child(user.getUid()), user);
    }

    @Override
    public Completable shareItem(DatabaseReference reference, String UserId, String ItemId) {
        Log.i("shareit",reference.child("users").child(UserId).child("userItems")+"->"+ItemId);
        return RxFirebaseDatabase.setValue(reference.child("users").child(UserId).child("userItems").push(), ItemId);
    }

    @Override
    public Completable addItemToUser(DatabaseReference reference, String UserId, String itemKey) {
        Log.i("addit",reference.child("users").child(UserId).child("userItems")+"->"+itemKey);
        return RxFirebaseDatabase.setValue(reference.child("users").child(UserId).child("userItems").push(), itemKey);
    }

    @Override
    public Maybe<DataSnapshot> readUserItemsId(DatabaseReference reference, String userId, Class clazz) {
        return RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").child(userId).child("userItems"));
    }

    @Override
    public void readUsers(DatabaseReference reference, DataFetcher dataFetcher) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("users"), DataSnapshotMapper.listOf(User.class))
                .subscribe(data -> dataFetcher.OnDataFetched(data));
    }

    @Override
    public List<Item> readItems(String userId, DataFetcher dataFetcher) {
        DatabaseReference reference = FirebaseDataRefrences.getInstance().getReference();
        Query where = reference.child("users").child(userId).child("userItems");

        RxFirebaseDatabase.observeSingleValueEvent(where, DataSnapshotMapper.listOf(String.class))
              // list
                .flatMapObservable(Observable::fromIterable)
                .flatMapMaybe(str ->
                        RxFirebaseDatabase.observeSingleValueEvent(reference.child("items").child(str), Item.class))
                .toList()
                .subscribe(data -> dataFetcher.OnDataFetched(data));

        RxFirebaseDatabase.observeSingleValueEvent(where, DataSnapshotMapper.listOf(String.class))
                .flatMapObservable(Observable::fromIterable)
                .flatMapMaybe(str -> RxFirebaseDatabase.observeSingleValueEvent(reference.child("items").child(str)))
                .flatMap(hashmap -> Observable.just(hashmap.getKey()))
                .toList()
                .subscribe(data->dataFetcher.OnKeysFetched(data));

        /**RxFirebaseDatabase.observeSingleValueEvent(where, DataSnapshotMapper.listOf(DataSnapshot.class))
         .flatMapObservable(dataSnapshots -> Observable.fromIterable(dataSnapshots))
         .flatMap(snapshot -> Observable.just(snapshot.getKey()))
         .toList()
         .subscribe(data->dataFetcher.OnKeysFetched(data));
         */
        return null;
    }
//    Maybe<ArrayList<Item>> addTolist(Item item){
//        ArrayList<Item>list= new ArrayList<>();
//        list.add(item);
//        return Observable.cre;
//    }

}
