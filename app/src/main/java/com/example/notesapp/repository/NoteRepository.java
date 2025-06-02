package com.example.notesapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesapp.Dao.NoteDao;
import com.example.notesapp.Dao.TodoDao;
import com.example.notesapp.database.ToDoDatabase;
import com.example.notesapp.model.Note;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteRepository {
    private final NoteDao noteDao;
    private final ToDoDatabase noteDatabase;
    private final Executor executor;
    private final LiveData<List<Note>> getAllNotes;

    public NoteRepository(Application application) {
        noteDatabase = ToDoDatabase.getTodoDatabase(application);
        noteDao = noteDatabase.noteDao();
        getAllNotes = noteDao.getAllNotes();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insertNote(Note note) {
        executor.execute(() ->
                noteDao.insertNote(note));
    }

    public void updateNote(Note note) {
        executor.execute(() -> noteDao.updateNote(note));
    }

    public void deleteNote(Note note) {
        executor.execute(() -> noteDao.deleteNote(note));
    }

    public LiveData<List<Note>> getAllNotes() {
        return getAllNotes;
    }

    public LiveData<List<Note>> getNoteByCategory(String category) {
        return noteDao.getNoteByCategory(category);
    }

    public LiveData<List<String>> getCategories() {
        return noteDao.getCategories();
    }

}
