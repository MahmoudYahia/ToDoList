package com.project.todolist.additem;

import android.text.TextUtils;

import com.project.todolist.firebase.dataWriter.DataWriterContract;
import com.project.todolist.firebase.dataWriter.FirebaseDatabaseWriter;

/**
 * Created by mah_y on 9/14/2017.
 */

public class AddItemPresenter implements AddItemContrat.Presenter{

    AddItemContrat.View view;
    DataWriterContract contract;

    public AddItemPresenter(AddItemContrat.View view) {
        this.view = view;
        contract = new FirebaseDatabaseWriter();
    }

    @Override
    public void onAddButtonClicked(String title, String desc) {

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(desc)) {
            view.showEmptyItemMessage();
        } else {
            contract.writeItem(title, desc)
                    .subscribe(() -> view.finishCurrentActivity()
                            , throwable -> view.showFailedMessage());
        }

    }

}
