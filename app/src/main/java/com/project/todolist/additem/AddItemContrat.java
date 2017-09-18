package com.project.todolist.additem;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface AddItemContrat {

    interface View {
        void finishCurrentActivity();

        void showFailedMessage();

        void showEmptyItemMessage();
    }

    interface Presenter {
        void onAddButtonClicked(String title, String desc);
    }
}
