package com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapter extends ArrayAdapter<TodoItem> {
    public ItemsAdapter(Context context, ArrayList<TodoItem> items) {
        super(context, 0, items);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TodoItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvItemText);
        // Populate the data into the template view using the data object
        tvName.setText(item.text);
        // Return the completed view to render on screen
        return convertView;
    }
}
