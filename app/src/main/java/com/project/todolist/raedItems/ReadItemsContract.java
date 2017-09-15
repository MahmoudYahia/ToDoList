package com.project.todolist.raedItems;

import java.util.List;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface ReadItemsContract {
    interface View{
        void dataFetched(List data);
        void errorFetchingFailed();
    }
    interface Presenter{
        void onActivityReady();
    }
}
