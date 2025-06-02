package com.example.notesapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesapp.model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    void insertTodo(Todo Todo);

    @Update
    void updateTodo(Todo Todo);

    @Delete
    void deleteTodo(Todo Todo);

    @Query("select * from Todo")
    LiveData<List<Todo>> getAllTodos();

    @Query("select * from Todo order by id")
    LiveData<List<Todo>> getLatestTodo();

    @Query("select * from Todo where todopriority = :priority")
    LiveData<List<Todo>> getNoteByPriority(String priority);

}
