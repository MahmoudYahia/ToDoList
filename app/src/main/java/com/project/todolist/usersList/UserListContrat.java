package com.project.todolist.usersList;

import java.util.List;

/**
 * Created by mah_y on 9/14/2017.
 */

public interface UserListContrat {

    interface View {
       void onDataFetched(List list);
        void onErrorFetchingData();
    }

    interface Presenter{
      void  onActivtyReady();
    }

}
