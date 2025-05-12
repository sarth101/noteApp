package com.example.notesapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesapp.Dao.NotesDao;
import com.example.notesapp.model.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static volatile NoteDatabase noteDatabase;
    public abstract NotesDao notesDao();

    public static NoteDatabase getNoteDatabase(Context context) {
        if(noteDatabase == null){
            synchronized (NoteDatabase.class){
                if(noteDatabase == null){
                    noteDatabase = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "notes_db").build();
                }
            }
        }
        return noteDatabase;
    }

}
