package com.project.todolist.callback;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by mah_y on 8/30/2017.
 */

public interface SnapshotParser {
    Maybe<List<String>>parseSnap(DataSnapshot snapshot,Class clzz);
}
