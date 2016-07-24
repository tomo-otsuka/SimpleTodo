package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    ArrayList<String> items;
    int selectedItemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent intent = getIntent();
        selectedItemIndex = intent.getIntExtra("selectedItemIndex", -1);
        readItems();
        TextView etEditItem = (TextView) findViewById(R.id.etEditItem);
        etEditItem.setText(items.get(selectedItemIndex));
    }

    protected void onSaveItem(View v) {
        EditText etEditText = (EditText) findViewById(R.id.etEditItem);
        String newText = etEditText.getText().toString();
        items.set(selectedItemIndex, newText);
        writeItems();

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
