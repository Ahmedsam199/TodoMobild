package com.example.notbasictodolist;

import androidx.recyclerview.widget.RecyclerView;

public class DataModel {
    int id;
    String Task;
    String isDone;
 public int getId() {
        return id;
    }

    public void setisDone(String isDone) {
        this.isDone = isDone;
    }
    public String getisDone() {
        return isDone;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTask() {
        return Task;
    }

    public void setTask(String text) {
        this.Task = text;
    }
}
