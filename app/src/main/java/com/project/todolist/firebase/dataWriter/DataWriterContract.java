package com.project.todolist.firebase.dataWriter;

import com.project.todolist.datamodel.Item;
import com.project.todolist.datamodel.User;

/**
 * Created by mah_y on 9/13/2017.
 */

public interface DataWriterContract {
    public void writeItem(String title ,String desc);
    public void writeUser(User user);
    public void shareItemTouser(String UserId, String ItemId);

      interface WriteComleteListener{
         void onWriteComplete();
         void onWriteError();
     }

}
