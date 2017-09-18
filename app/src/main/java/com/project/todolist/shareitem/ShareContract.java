package com.project.todolist.shareitem;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface ShareContract {
    interface View{
        void showCompleteSharingMessage();
        void showFailedMessage();
    }

    interface Presenter{
        void userSelected(String userId, String itemId);
    }
}
