package com.project.todolist.callback;

import com.google.firebase.database.DatabaseReference;
import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by mah_y on 8/29/2017.
 */

public interface DataBaseWriter {
    Single<String> addItem(DatabaseReference reference, Item item);
    Completable addUser(DatabaseReference reference, User user);
    Completable shareItem(DatabaseReference reference, String UserId,String ItemId);
    Completable addItemToUser(DatabaseReference reference,String Uid,String itemKey);


}
