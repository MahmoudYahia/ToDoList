package com.project.todolist.readitems;

import com.project.todolist.firebase.authentication.SignOut;
import com.project.todolist.firebase.authentication.SignOutContract;
import com.project.todolist.firebase.dataReader.DataReaderContract;
import com.project.todolist.firebase.dataReader.FirebaseDatabaseReader;
import com.project.todolist.firebase.dataWriter.DataWriterContract;
import com.project.todolist.firebase.dataWriter.FirebaseDatabaseWriter;

/**
 * Created by mah_y on 9/14/2017.
 */

public class ReadItemsPresenter implements ReadItemsContract.Presenter {

    ReadItemsContract.View view;
    DataReaderContract readerContract;
    DataWriterContract writerContract;
    SignOutContract signOutContract;

    public ReadItemsPresenter(ReadItemsContract.View view) {
        this.view = view;
        readerContract = new FirebaseDatabaseReader();
        writerContract = new FirebaseDatabaseWriter();
        signOutContract=new SignOut();
    }

    @Override
    public void onActivityReady() {
        readerContract.readItems()
                .subscribe(itemKeyVals ->
                                view.bindData(itemKeyVals)
                        , throwable -> view.showErrorFetchingMessage());

    }

    @Override
    public void onSignOutButtonClicked() {
        signOutContract.signOut()
                .subscribe(() -> {
                    view.navigateToSignInActivity();
                }, throwable -> {
                    view.showErrorFetchingMessage(); //
                });
    }

    @Override
    public void onFloatButtonClicked() {
        view.navigateToAddItemActivity();
    }

    @Override
    public void onUserSelectedToShare(String userId, String itemId) {
        writerContract.shareItemToUser(userId, itemId)
                .subscribe(() -> {
                    view.showItemSharedMessage();
                }, throwable -> {
                    view.showShareItemFailedMessage();
                });
    }


}
