package com.example.notesapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Todo")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String TodoHeading;
    private String TodoBody;
    private Priority TodoPriority;

    public Todo() {}

    public Todo(String TodoHeading, String TodoBody, Priority priority) {
        this.TodoHeading = TodoHeading;
        this.TodoBody = TodoBody;
        TodoPriority = priority;
    }

    public Todo(int id,String TodoHeading, String TodoBody, Priority priority) {
        this.id = id;
        this.TodoHeading = TodoHeading;
        this.TodoBody = TodoBody;
        TodoPriority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodoHeading() {
        return TodoHeading;
    }

    public void setTodoHeading(String TodoHeading) {
        this.TodoHeading = TodoHeading;
    }

    public String getTodoBody() {
        return TodoBody;
    }

    public void setTodoBody(String TodoBody) {
        this.TodoBody = TodoBody;
    }

    public Priority getTodoPriority() {
        return TodoPriority;
    }

    public void setTodoPriority(Priority TodoPriority) {
        this.TodoPriority = TodoPriority;
    }

}
