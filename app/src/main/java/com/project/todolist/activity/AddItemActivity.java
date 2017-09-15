package com.project.todolist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.todolist.R;
import com.project.todolist.addItem.AddItemContrat;
import com.project.todolist.addItem.AddItemPresenter;


public class AddItemActivity extends AppCompatActivity implements AddItemContrat.View {

    EditText item_title, item_desc;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        item_title = (EditText) findViewById(R.id.item_tile_input);
        item_desc = (EditText) findViewById(R.id.item_desc_input);
        submit_btn = (Button) findViewById(R.id.item_submit_btn);

        submit_btn.setOnClickListener(v -> {

            String title = item_title.getText().toString();
            String desc = item_desc.getText().toString();
            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(desc)) {
                Toast.makeText(this, "Empty Fields", Toast.LENGTH_LONG).show();
            } else {

                AddItemContrat.Presenter presenter = new AddItemPresenter(this);
                presenter.onAddButtonClicked(title,desc);
            }

        });

    }

    @Override
    public void onAddComplete() {
        this.finish();
        Toast.makeText(this,"Shared",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void onAddFailed() {
        Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
    }
}
