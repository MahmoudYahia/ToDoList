package com.project.todolist.shareitem;

import com.project.todolist.firebase.dataWriter.DataWriterContract;
import com.project.todolist.firebase.dataWriter.FirebaseDatabaseWriter;

/**
 * Created by mah_y on 9/14/2017.
 */

public class SharePresenter implements ShareContract.Presenter{

    ShareContract.View view;
    DataWriterContract contract;

    public SharePresenter(ShareContract.View view) {
        this.view = view;
        contract=new FirebaseDatabaseWriter();
    }

    @Override
    public void userSelected(String userId, String itemId) {
        contract.shareItemTouser(userId, itemId)
                .subscribe(() -> {
                    view.showCompleteSharingMessage();
                },throwable -> {
                    view.showFailedMessage();
                });
    }


}
