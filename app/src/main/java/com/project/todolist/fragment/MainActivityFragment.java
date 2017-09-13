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
import android.widget.Toast;

import com.project.todolist.activity.UsersListActivity;
import com.project.todolist.adapter.ItemsAdapter;
import com.project.todolist.view.CompletableView;
import com.project.todolist.view.DataOfView;
import com.project.todolist.presenter.DataWriterPresenter;
import com.project.todolist.callback.OnUserSelectedListener;
import com.project.todolist.firebase.FirebaseDatabaseReader;
import com.project.todolist.firebase.FirebaseDatabaseWriter;
import com.project.todolist.R;
import com.project.todolist.presenter.DataReaderPresenter;
import com.project.todolist.callback.ItemShareListener;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ItemShareListener,DataOfView {

    int R_Code = 100;
    OnUserSelectedListener userSelectedListener;
    RecyclerView itemsRecycler;
    ItemsAdapter itemsAdapter;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        itemsRecycler = (RecyclerView) view.findViewById(R.id.item_recucler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        itemsRecycler.setLayoutManager(layoutManager);
        itemsRecycler.setHasFixedSize(true);
        DataReaderPresenter reader = new FirebaseDatabaseReader(this);
        itemsAdapter = new ItemsAdapter(getActivity(), this);
        itemsRecycler.setAdapter(itemsAdapter);

        reader.readItems();

        return view;
    }

    @Override
    public void onItemSelecteds(String item_id) {

        Intent i = new Intent(getActivity(), UsersListActivity.class);
        startActivityForResult(i, R_Code);
        DataWriterPresenter writer = new FirebaseDatabaseWriter(new CompletableView() {
            @Override
            public void onWorkFinish() {
                Toast.makeText(getActivity(), "Shared", Toast.LENGTH_LONG).show();
            }

            @Override
            public void OnWorkError() {

            }
        });
        userSelectedListener = uid -> {
            writer.shareItem(uid, item_id); };

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

    @Override
    public void dataFitched(List data) {
        itemsAdapter.setAdapterLsit(data);
        itemsAdapter.notifyDataSetChanged();
        if (data.size()>1){
            itemsRecycler.smoothScrollToPosition(data.size()-1);
        }
    }
}
/* reader.readItems()
                .subscribe(itemKeyVals -> {
                    Log.i("dddd",itemKeyVals.size()+"");
            itemsAdapter.setAdapterLsit(itemKeyVals);
            itemsAdapter.notifyDataSetChanged();
        });
*/
/*
        model.listenChanges().subscribe(itemKeyVals -> {
            itemsAdapter.setAdapterLsit(itemKeyVals);
            itemsAdapter.notifyDataSetChanged();
            itemsRecycler.smoothScrollToPosition(itemKeyVals.size()-1);
        },throwable -> {
            itemsAdapter.setAdapterLsit(new ArrayList<>());
            itemsAdapter.notifyDataSetChanged();
        });
*/