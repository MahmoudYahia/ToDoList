package com.project.todolist.firebase.dataReader;

import com.project.todolist.datamodel.ItemKeyVal;
import com.project.todolist.datamodel.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by mah_y on 9/13/2017.
 */

public interface DataReaderContract {

    Flowable<List<ItemKeyVal>> readItems();

    Single<List<User>> readUsersList();

}
