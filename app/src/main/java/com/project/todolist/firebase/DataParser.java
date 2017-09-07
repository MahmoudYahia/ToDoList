package com.project.todolist.firebase;

import com.google.firebase.database.DataSnapshot;
import com.project.todolist.callback.SnapshotParser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by mah_y on 8/30/2017.
 */

public class DataParser implements SnapshotParser {
    @Override
    public Maybe<List<String>> parseSnap(DataSnapshot snapshot, Class clzz) {
        ArrayList list = new ArrayList<>();

        if (snapshot.getChildren() != null) {
            for (DataSnapshot data : snapshot.getChildren()) {
                list.add(data.getValue(clzz));
            }
        }

        return null;
    }
}
