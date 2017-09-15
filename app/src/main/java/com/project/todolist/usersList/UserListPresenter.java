package com.project.todolist.usersList;

import com.project.todolist.firebase.dataReader.DataReaderContract;
import com.project.todolist.firebase.dataReader.FirebaseDatabaseReader;

import java.util.List;

/**
 * Created by mah_y on 9/14/2017.
 */

public class UserListPresenter implements UserListContrat.Presenter,DataReaderContract.DataFetcherListener{

    UserListContrat.View view;
    DataReaderContract contract;
    public UserListPresenter(UserListContrat.View view) {
        this.view = view;
        contract= new FirebaseDatabaseReader(this);
    }

    @Override
    public void onActivtyReady() {
        contract.readUsersList();
    }

    @Override
    public void onDataFetched(List list) {
        view.onDataFetched(list);
    }

    @Override
    public void onErrorFetchingData() {
        view.onErrorFetchingData();
    }
}
