package com.project.todolist.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.project.todolist.R;
import com.project.todolist.adapter.UserAdapter;
import com.project.todolist.userslist.UserListContract;
import com.project.todolist.userslist.UserListPresenter;
import com.project.todolist.callback.OnUserSelectedListener;

import java.util.List;


public class UsersListActivity extends AppCompatActivity implements OnUserSelectedListener, UserListContract.View {
    RecyclerView usersRecycler;
    LinearLayoutManager layoutManager;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        usersRecycler = (RecyclerView) findViewById(R.id.user_list_recycler);
        layoutManager = new LinearLayoutManager(this);
        usersRecycler.setLayoutManager(layoutManager);
        usersRecycler.setHasFixedSize(true);
        userAdapter = new UserAdapter(this, this);
        usersRecycler.setAdapter(userAdapter);


        UserListContract.Presenter presenter= new UserListPresenter(this);
        presenter.onActivityReady();

    }

    @Override
    public void onUserSelected(String uid) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("user_id", uid);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }



    @Override
    public void onBindData(List list) {
        userAdapter.setList(list);
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorFetchingDataMessages() {

    }
}
