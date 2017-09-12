package com.project.todolist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.todolist.R;
import com.project.todolist.callback.DataBaseWriter;
import com.project.todolist.datamodel.Item;
import com.project.todolist.firebase.FireBaseDatabaseModel;
import com.project.todolist.firebase.FirebaseDataRefrences;

import static com.project.todolist.R.id.ifRoom;
import static com.project.todolist.R.id.item_tile_input;

public class AddItemActivity extends AppCompatActivity {

    EditText item_title, item_desc;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        item_title = (EditText) findViewById(item_tile_input);
        item_desc = (EditText) findViewById(R.id.item_desc_input);
        submit_btn = (Button) findViewById(R.id.item_submit_btn);

        submit_btn.setOnClickListener(v -> {

            String title = item_title.getText().toString();
            String Desc = item_desc.getText().toString();
            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(Desc)) {
                Toast.makeText(this, "Empty Fields", Toast.LENGTH_LONG).show();
            } else {
                Item item = new Item(FirebaseDataRefrences.getInstance().getFirebaseUser().getUid(), title, Desc);

                DataBaseWriter dataBaseWriter = new FireBaseDatabaseModel();
                dataBaseWriter.addItem(
                         item)
                        .flatMapCompletable(itemKey ->
                                dataBaseWriter.addItemToUser(itemKey))
                        .subscribe(() -> finish());
            }

        });

    }
}
