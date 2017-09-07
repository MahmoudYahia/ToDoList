package com.project.todolist.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.project.todolist.activity.UsersListActivity;
import com.project.todolist.adapter.ItemsAdapter;
import com.project.todolist.callback.DataBaseWriter;
import com.project.todolist.callback.DataFetcher;
import com.project.todolist.callback.OnUserSelectedListener;
import com.project.todolist.datamodel.Item;
import com.project.todolist.firebase.FireBaseDatabaseModel;
import com.project.todolist.firebase.FirebaseDataRefrences;
import com.project.todolist.R;
import com.project.todolist.callback.DataBaseReader;
import com.project.todolist.callback.ItemShareListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ItemShareListener {

    int R_Code = 100;
    OnUserSelectedListener userSelectedListener;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView itemsRecycler = (RecyclerView) view.findViewById(R.id.item_recucler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        itemsRecycler.setLayoutManager(layoutManager);
        itemsRecycler.setHasFixedSize(true);
        DataBaseReader reader = new FireBaseDatabaseModel();
        ItemsAdapter itemsAdapter = new ItemsAdapter(getActivity(), this);
        itemsRecycler.setAdapter(itemsAdapter);

        reader.readItems(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid(), new DataFetcher() {
            @Override
            public void OnDataFetched(List data) {
                itemsAdapter.SetList(data);
                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnKeysFetched(List keys) {
                itemsAdapter.setKeysList(keys);
            }
        });

        return view;
    }

    @Override
    public void onItemSelecteds(String item_id) {

        Intent i = new Intent(getActivity(), UsersListActivity.class);
        startActivityForResult(i, R_Code);
        DataBaseWriter writer = new FireBaseDatabaseModel();

        userSelectedListener = uid -> {
            writer.shareItem(FirebaseDataRefrences.getInstance().getReference(), uid, item_id)
                    .subscribe();
            Log.i("share", uid + "  " + item_id);
            Toast.makeText(getActivity(), "Shared", Toast.LENGTH_LONG).show();
        };

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == R_Code) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getExtras() != null) {
                    userSelectedListener.onUserSelected(data.getExtras().getString("user_id"));
                    Log.i("user", data.getExtras().getString("user_id"));
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
}
