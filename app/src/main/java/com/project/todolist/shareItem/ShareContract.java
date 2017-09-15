package com.project.todolist.shareItem;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface ShareContract {
    interface View{
        void onShareComplete();
    }

    interface Presenter{
        void userSelected(String userId, String itemId);
    }
}
