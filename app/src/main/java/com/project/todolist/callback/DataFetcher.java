package com.project.todolist.callback;

import com.project.todolist.datamodel.Item;

import java.util.List;

/**
 * Created by mah_y on 9/6/2017.
 */

public interface DataFetcher {
    void OnDataFetched(List data);
    void OnKeysFetched(List keys);
}
