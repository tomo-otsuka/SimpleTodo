package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<TodoItem> items;
    ArrayAdapter<TodoItem> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration dbConfig = new Configuration.Builder(this).setDatabaseName("todo.db").create();
        ActiveAndroid.initialize(dbConfig);

        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<>();
        readItems();
        itemsAdapter = new ArrayAdapter<TodoItem>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        TodoItem newItem = new TodoItem(itemText);
        newItem.save();
        itemsAdapter.add(newItem);
        etNewItem.setText("");
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapter,
                                               View item, int pos, long id) {
                    TodoItem todoItem = items.get(pos);
                    todoItem.delete();
                    items.remove(pos);
                    itemsAdapter.notifyDataSetChanged();

                    return true;
                }
            }
        );

        lvItems.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter,
                                           View item, int pos, long id) {
                    Intent intent = new Intent(getBaseContext(), EditItemActivity.class);
                    TodoItem todoItem = items.get(pos);
                    intent.putExtra("selectedItemId", todoItem.getId());
                    startActivity(intent);
                }
            }
        );
    }

    private void readItems() {
        List<TodoItem> todoItems = new Select().from(TodoItem.class).execute();
        items = (ArrayList<TodoItem>) todoItems;
    }
}
