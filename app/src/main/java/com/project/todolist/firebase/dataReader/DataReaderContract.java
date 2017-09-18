package com.project.todolist.firebase.dataReader;

import com.project.todolist.datamodel.ItemKeyVal;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by mah_y on 9/13/2017.
 */

public interface DataReaderContract {

    Flowable<List<ItemKeyVal>> readItems();

    void readUsersList();

    Completable signOut();

}
