package com.project.todolist.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.project.todolist.R;
import com.project.todolist.adapter.UserAdapter;
import com.project.todolist.callback.DataBaseReader;
import com.project.todolist.callback.OnUserSelectedListener;
import com.project.todolist.firebase.FireBaseDatabaseModel;


public class UsersListActivity extends AppCompatActivity implements OnUserSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        RecyclerView usersRecycler = (RecyclerView)findViewById(R.id.user_list_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        usersRecycler.setLayoutManager(layoutManager);
        usersRecycler.setHasFixedSize(true);
        UserAdapter userAdapter= new UserAdapter(this,this);
        usersRecycler.setAdapter(userAdapter);
        DataBaseReader reader= new FireBaseDatabaseModel();

        reader.readUsers().subscribe((users, throwable) -> {
            userAdapter.setList(users);
            userAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onUserSelected(String uid) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("user_id",uid);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
