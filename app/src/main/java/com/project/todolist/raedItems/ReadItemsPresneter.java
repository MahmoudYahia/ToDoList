package com.project.todolist.raedItems;

import com.project.todolist.firebase.dataReader.DataReaderContract;
import com.project.todolist.firebase.dataReader.FirebaseDatabaseReader;

import java.util.List;

/**
 * Created by mah_y on 9/14/2017.
 */

public class ReadItemsPresneter implements ReadItemsContract.Presenter,DataReaderContract.DataFetcherListener{

    ReadItemsContract.View view;
    DataReaderContract readerContract;

    public ReadItemsPresneter(ReadItemsContract.View view) {
        this.view = view;
        readerContract=new FirebaseDatabaseReader(this);
    }

    @Override
    public void onActivityReady() {
        readerContract.readItems();
        readerContract= new FirebaseDatabaseReader(this);
    }

    @Override
    public void onDataFetched(List list) {
        view.dataFetched(list);
    }

    @Override
    public void onErrorFetchingData() {
        view.errorFetchingFailed();
    }
}
