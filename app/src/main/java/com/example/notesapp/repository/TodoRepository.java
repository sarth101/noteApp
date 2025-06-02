package com.example.notesapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesapp.Dao.TodoDao;
import com.example.notesapp.database.ToDoDatabase;
import com.example.notesapp.model.Todo;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TodoRepository {
    private final TodoDao TodosDao;
    private final Executor executor;
    private final LiveData<List<Todo>> getAllTodos;

    public TodoRepository(Application application) {
        ToDoDatabase TodoDatabase = ToDoDatabase.getTodoDatabase(application);
        TodosDao = TodoDatabase.TodosDao();
        getAllTodos = TodosDao.getAllTodos();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insertTodo(Todo Todo){
        executor.execute(()-> TodosDao.insertTodo(Todo));
    }

    public void deleteTodo(Todo Todo){
        executor.execute(()-> TodosDao.deleteTodo(Todo));
    }

    public LiveData<List<Todo>> getAllTodos() {
        return getAllTodos;
    }

    public void updateTodo(Todo Todo){
        executor.execute(()-> TodosDao.updateTodo(Todo));
    }
}
