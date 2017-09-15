package com.project.todolist.addItem;

import com.project.todolist.firebase.dataReader.DataReaderContract;
import com.project.todolist.firebase.dataWriter.DataWriterContract;
import com.project.todolist.firebase.dataWriter.FirebaseDatabaseWriter;

/**
 * Created by mah_y on 9/14/2017.
 */

public class AddItemPresenter implements AddItemContrat.Presenter,DataWriterContract.WriteComleteListener {

    AddItemContrat.View view;
    DataWriterContract contract;

    public AddItemPresenter(AddItemContrat.View view) {
        this.view = view;
        contract=new FirebaseDatabaseWriter(this);
    }

    @Override
    public void onAddButtonClicked(String title, String desc) {
        contract.writeItem(title,desc);
    }

    @Override
    public void onWriteComplete() {
        view.onAddComplete();
    }

    @Override
    public void onWriteError() {
        view.onAddFailed();
    }
}
