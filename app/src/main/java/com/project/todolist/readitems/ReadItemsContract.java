package com.project.todolist.readitems;

import com.project.todolist.datamodel.ItemKeyVal;

import java.util.List;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface ReadItemsContract {
    interface View{
        void bindData(List<ItemKeyVal> data);
        void showErrorFetchingMessage();
        void navigateToSignInActivity();
    }
    interface Presenter{
        void onActivityReady();
        void signOut();
    }
}
