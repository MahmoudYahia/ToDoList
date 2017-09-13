package com.project.todolist.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.project.todolist.R;
import com.project.todolist.adapter.UserAdapter;
import com.project.todolist.view.DataOfView;
import com.project.todolist.presenter.DataReaderPresenter;
import com.project.todolist.callback.OnUserSelectedListener;
import com.project.todolist.firebase.FirebaseDatabaseReader;

import java.util.List;


public class UsersListActivity extends AppCompatActivity implements OnUserSelectedListener, DataOfView {
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

        DataReaderPresenter reader = new FirebaseDatabaseReader(this);
        reader.readUsers();

    }

    @Override
    public void onUserSelected(String uid) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("user_id", uid);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    @Override
    public void dataFitched(List data) {
        userAdapter.setList(data);
        userAdapter.notifyDataSetChanged();

    }
}
