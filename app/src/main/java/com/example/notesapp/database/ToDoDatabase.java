package com.example.notesapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesapp.Dao.NoteDao;
import com.example.notesapp.Dao.TodoDao;
import com.example.notesapp.model.Note;
import com.example.notesapp.model.Todo;

@Database(entities = {Todo.class, Note.class}, version = 2)
public abstract class ToDoDatabase extends RoomDatabase {
    private static volatile ToDoDatabase todoDatabase;
    public abstract TodoDao TodosDao();
    public abstract NoteDao noteDao();

    public static ToDoDatabase getTodoDatabase(Context context) {
        if(todoDatabase == null){
            synchronized (ToDoDatabase.class){
                if(todoDatabase == null){
                    todoDatabase = Room.databaseBuilder(context.getApplicationContext(), ToDoDatabase.class, "Todo_db").build();
                }
            }
        }
        return todoDatabase;
    }

}
