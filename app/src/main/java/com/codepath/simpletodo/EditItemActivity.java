package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.Date;

public class EditItemActivity extends AppCompatActivity {
    TodoItem selectedItem;

    private void loadSelectedItem() {
        Intent intent = getIntent();
        long selectedItemId = intent.getLongExtra("selectedItemId", -1);
        selectedItem = new Select().from(TodoItem.class).where("id = ?", selectedItemId).executeSingle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        loadSelectedItem();
        TextView etEditItem = (TextView) findViewById(R.id.etEditItem);
        etEditItem.setText(selectedItem.text);
        CalendarView cvDueDate = (CalendarView) findViewById(R.id.cvDueDate);
        if (selectedItem.dueDate != null){
            cvDueDate.setDate(selectedItem.dueDate.getTime());
        }
    }

    protected void onSaveItem(View v) {
        EditText etEditText = (EditText) findViewById(R.id.etEditItem);
        String newText = etEditText.getText().toString();
        CalendarView cvDueDate = (CalendarView) findViewById(R.id.cvDueDate);
        Date newDueDate = new Date(cvDueDate.getDate());
        selectedItem.text = newText;
        selectedItem.dueDate = newDueDate;
        selectedItem.save();

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}
