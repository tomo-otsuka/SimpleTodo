package com.codepath.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "items")
public class TodoItem extends Model {
    @Column(name = "text")
    public String text;

    public TodoItem() {
        super();
    }

    public TodoItem(String text) {
        super();
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}