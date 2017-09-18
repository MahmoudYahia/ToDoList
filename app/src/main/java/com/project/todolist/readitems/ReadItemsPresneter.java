package com.project.todolist.readitems;

import com.project.todolist.firebase.dataReader.DataReaderContract;
import com.project.todolist.firebase.dataReader.FirebaseDatabaseReader;

import java.util.List;

/**
 * Created by mah_y on 9/14/2017.
 */

public class ReadItemsPresneter implements ReadItemsContract.Presenter {

    ReadItemsContract.View view;
    DataReaderContract readerContract;

    public ReadItemsPresneter(ReadItemsContract.View view) {
        this.view = view;
        readerContract = new FirebaseDatabaseReader();
    }

    @Override
    public void onActivityReady() {
        readerContract.readItems()
                .subscribe(itemKeyVals -> {
                    view.bindData(itemKeyVals);
                }, throwable -> {
                    view.showErrorFetchingMessage();
                });

    }

    @Override
    public void signOut() {
        readerContract.signOut()
                .subscribe(() -> {
                    view.navigateToSignInActivity();
                },throwable -> {
                    view.showErrorFetchingMessage(); //
                });
    }

}
