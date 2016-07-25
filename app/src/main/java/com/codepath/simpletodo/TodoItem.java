package com.codepath.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "items")
public class TodoItem extends Model {
    @Column(name = "text")
    public String text;

    @Column(name = "dueDate")
    public Date dueDate;

    public TodoItem() {
        super();
    }

    public TodoItem(String text) {
        super();
        this.text = text;
    }

    public TodoItem(String text, Date dueDate) {
        super();
        this.text = text;
        this.dueDate = dueDate;
    }
}