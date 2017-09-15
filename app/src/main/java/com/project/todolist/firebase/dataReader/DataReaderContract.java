package com.project.todolist.firebase.dataReader;

import java.util.List;

/**
 * Created by mah_y on 9/13/2017.
 */

public interface DataReaderContract {

    void readItems();

    void readUsersList();

    interface DataFetcherListener {
        void onDataFetched(List list);

        void onErrorFetchingData();
    }
}
