package com.project.todolist.firebase.dataWriter;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.project.todolist.firebase.refrences.FirebaseDataRefrences;
import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.User;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Single;


/**
 * Created by mah_y on 8/29/2017.
 */

public class FirebaseDatabaseWriter implements DataWriterContract {

    public FirebaseDatabaseWriter() {
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

    @Override
    public Completable writeItem(String title ,String desc) {

        Item newItem = new Item(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid(), title, desc);

       return addItemToDataBase(newItem)
                .flatMapCompletable(itemKey -> addItemToUserToDataBase(itemKey));
//                .subscribe(() -> listener.onWriteComplete()
//                        ,throwable ->listener.onWriteError() );
    }

    @Override
    public Completable writeUser(User user) {
       return addUsertoDatabase(user);
//                .subscribe(() ->listener.onWriteComplete(),throwable -> {
//                    listener.onWriteError();
//                });
    }

    @Override
    public Completable shareItemTouser(String UserId, String ItemId) {
      return shareItemToUser(UserId, ItemId);
//                .subscribe(() -> listener.onWriteComplete(),throwable -> listener.onWriteError());
    }


}
