package com.example.notesapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesapp.model.Note;
import com.example.notesapp.model.Todo;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("select * from notes order by noteId desc")
    LiveData<List<Note>> getAllNotes();

    @Query("select * from notes where category = :category order by noteId desc")
    LiveData<List<Note>> getNoteByCategory(String category);

    @Query("select distinct category from notes ")
    LiveData<List<String>> getCategories();

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

}
