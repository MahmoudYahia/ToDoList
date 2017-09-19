package com.project.todolist.readitems;

import com.project.todolist.datamodel.ItemKeyVal;

import java.util.List;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface ReadItemsContract {
    interface View {
        // read items
        void bindData(List<ItemKeyVal> data);
        void showErrorFetchingMessage();
        // navigation
        void navigateToSignInActivity();
        void navigateToAddItemActivity();
        // share
        void showItemSharedMessage();
        void showShareItemFailedMessage();

        void navigateToUserListActivity();
        //
    }

    interface Presenter{
        void onActivityReady();
        void onSignOutButtonClicked();
        void onFloatButtonClicked();
        void onUserSelectedToShare(String userId,String itemId);
        void onItemSelectedToShare(String itemKey);
        void activityResult(String user_id);
    }
}
