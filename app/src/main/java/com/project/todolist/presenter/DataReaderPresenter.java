package com.project.todolist.presenter;

import com.google.firebase.database.DataSnapshot;
import com.project.todolist.datamodel.ItemKeyVal;
import com.project.todolist.datamodel.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by mah_y on 8/29/2017.
 */

public interface DataReaderPresenter {

    void readUserItemsId();

    void readUsers();

    void readItems();

}
