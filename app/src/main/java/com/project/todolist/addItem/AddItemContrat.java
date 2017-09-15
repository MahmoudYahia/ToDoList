package com.project.todolist.addItem;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface AddItemContrat {

    interface View{
        void onAddComplete();
        void onAddFailed();
    }

    interface Presenter{
       void onAddButtonClicked(String title ,String desc);
    }
}
