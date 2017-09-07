package com.project.todolist.callback;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.User;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by mah_y on 8/29/2017.
 */

public interface DataBaseReader {

    Maybe<DataSnapshot> readUserItemsId(DatabaseReference reference, String userId, Class clazz);
    void readUsers(DatabaseReference reference,DataFetcher dataFetcher,String CurrentUserID);
    List<Item> readItems(String userId,DataFetcher dataFetcher);

}
