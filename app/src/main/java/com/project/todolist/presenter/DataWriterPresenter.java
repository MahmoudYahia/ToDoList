package com.project.todolist.presenter;

import com.google.firebase.database.DatabaseReference;
import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by mah_y on 8/29/2017.
 */

public interface DataWriterPresenter {
    void addItem( Item item);
    void addUser(User user);
    void shareItem( String UserId,String ItemId);

}
