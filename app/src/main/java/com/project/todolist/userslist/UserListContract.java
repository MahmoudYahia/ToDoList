package com.project.todolist.userslist;

import com.project.todolist.datamodel.User;

import java.util.List;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface UserListContract {

    interface View {

        void onBindData(List<User> list);
        void showErrorFetchingDataMessages();
        void navigateToMainActivityWithUser(String userId);

    }

    interface Presenter{
        void onActivityReady();
        void userSelected(String userId);
    }

}
