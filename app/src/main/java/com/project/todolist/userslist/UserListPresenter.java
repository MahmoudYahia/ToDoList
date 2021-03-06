package com.project.todolist.userslist;

import com.project.todolist.firebase.dataReader.DataReaderContract;
import com.project.todolist.firebase.dataReader.FirebaseDatabaseReader;

/**
 * Created by mah_y on 9/14/2017.
 */

public class UserListPresenter implements UserListContract.Presenter{

    UserListContract.View view;
    DataReaderContract contract;

    public UserListPresenter(UserListContract.View view) {
        this.view = view;
        contract= new FirebaseDatabaseReader();
    }

    @Override
    public void onActivityReady() {
        contract.readUsersList().subscribe(users -> {
            view.bindData(users);
        },throwable -> {
            view.showErrorFetchingDataMessages();
        });
    }

    @Override
    public void userSelected(String userId) {
        view.navigateToMainActivityWithUser(userId);
    }

}
