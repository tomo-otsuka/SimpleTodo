package com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ItemsAdapter extends ArrayAdapter<TodoItem> {
    private static class ViewHolder {
        TextView text;
        TextView dueDate;
    }

    public ItemsAdapter(Context context, ArrayList<TodoItem> items) {
        super(context, 0, items);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.tvItemText);
            viewHolder.dueDate = (TextView) convertView.findViewById(R.id.tvItemDueDate);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(item.text);
        String dueDateString = "";
        if (item.dueDate != null) {
            dueDateString = new SimpleDateFormat("MMM dd").format(item.dueDate);
        }
        viewHolder.dueDate.setText(dueDateString);
        return convertView;
    }
}
