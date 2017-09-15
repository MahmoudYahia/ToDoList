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
import com.project.todolist.raedItems.ReadItemsContract;
import com.project.todolist.raedItems.ReadItemsPresneter;
import com.project.todolist.shareItem.ShareContract;
import com.project.todolist.shareItem.SharePresenter;
import com.project.todolist.callback.OnUserSelectedListener;
import com.project.todolist.R;
import com.project.todolist.callback.ItemShareListener;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ItemShareListener,ReadItemsContract.View,ShareContract.View {

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
        itemsAdapter = new ItemsAdapter(getActivity(), this);
        itemsRecycler.setAdapter(itemsAdapter);

        ReadItemsContract.Presenter presenter= new ReadItemsPresneter(this);
        presenter.onActivityReady();

        return view;
    }

    @Override
    public void onItemSelecteds(String item_id) {

        Intent i = new Intent(getActivity(), UsersListActivity.class);
        startActivityForResult(i, R_Code);
        ShareContract.Presenter presenter =new SharePresenter(this);

        userSelectedListener = uid -> presenter.userSelected(uid,item_id);

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
    public void dataFetched(List data) {
        itemsAdapter.setAdapterLsit(data);
        itemsAdapter.notifyDataSetChanged();
        if (data.size()>1){
            itemsRecycler.smoothScrollToPosition(data.size()-1);
        }
    }

    @Override
    public void errorFetchingFailed() {
        Toast.makeText(getActivity(),"Failed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShareComplete() {
        Toast.makeText(getActivity(),"Shared",Toast.LENGTH_LONG).show();
    }
}
