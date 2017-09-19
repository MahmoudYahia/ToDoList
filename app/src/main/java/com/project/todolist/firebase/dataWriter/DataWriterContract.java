package com.project.todolist.firebase.dataWriter;

import com.project.todolist.datamodel.User;

import io.reactivex.Completable;

/**
 * Created by mah_y on 9/13/2017.
 */

public interface DataWriterContract {
     Completable writeItem(String title , String desc);
     Completable writeUser(User user);
     Completable writeSharedItemToUser(String UserId, String ItemId);
}
