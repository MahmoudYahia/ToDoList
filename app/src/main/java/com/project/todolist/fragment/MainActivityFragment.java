package com.project.todolist.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.todolist.activity.AddItemActivity;
import com.project.todolist.activity.SignInActivity;
import com.project.todolist.activity.UsersListActivity;
import com.project.todolist.adapter.ItemsAdapter;
import com.project.todolist.datamodel.ItemKeyVal;
import com.project.todolist.readitems.ReadItemsContract;
import com.project.todolist.readitems.ReadItemsPresenter;
import com.project.todolist.callback.OnUserSelectedListener;
import com.project.todolist.R;
import com.project.todolist.callback.ItemShareListener;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ItemShareListener,ReadItemsContract.View{

    int R_Code = 100;
    OnUserSelectedListener userSelectedListener;
    RecyclerView itemsRecycler;
    ItemsAdapter itemsAdapter;
    ReadItemsContract.Presenter presenter;

    public MainActivityFragment() {
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        itemsRecycler = (RecyclerView) view.findViewById(R.id.item_recucler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        itemsRecycler.setLayoutManager(layoutManager);
        itemsRecycler.setHasFixedSize(true);
        itemsAdapter = new ItemsAdapter(getActivity(), this);
        itemsRecycler.setAdapter(itemsAdapter);
        fab.setOnClickListener(v -> presenter.onFloatButtonClicked());

        presenter= new ReadItemsPresenter(this);
        presenter.onActivityReady();

        return view;
    }

    @Override
    public void onItemSelected(String item_id) {
        presenter.onItemSelectedToShare(item_id);
     //   userSelectedListener = uid -> presenter.onUserSelectedToShare(uid,item_id);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == R_Code) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getExtras() != null) {
                    presenter.activityResult(data.getExtras().getString("user_id"));
                   // userSelectedListener.onUserSelected(data.getExtras().getString("user_id"));
                }

            }

        }
    }


    @Override
    public void bindData(List<ItemKeyVal> data) {
        itemsAdapter.setAdapterLsit(data);
        itemsAdapter.notifyDataSetChanged();

        if (data.size()>0){
            itemsRecycler.smoothScrollToPosition(data.size()-1);
        }
    }


    @Override
    public void showErrorFetchingMessage() {
        Toast.makeText(getActivity(),"Failed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToSignInActivity() {
        startActivity(new Intent(getActivity(),SignInActivity.class));
        getActivity().finish();
    }

    @Override
    public void navigateToAddItemActivity() {
        Intent intent = new Intent(getActivity(),AddItemActivity.class);
        startActivity(intent);
    }

    @Override
    public void showItemSharedMessage() {
        Toast.makeText(getActivity(),"Shared",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showShareItemFailedMessage() {
        Toast.makeText(getActivity(),"Failed to Share item",Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToUserListActivity() {
        Intent i = new Intent(getActivity(), UsersListActivity.class); // violated
        startActivityForResult(i, R_Code);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            presenter.onSignOutButtonClicked(); // rename
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
