package com.example.notesapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesapp.Dao.NotesDao;
import com.example.notesapp.database.NoteDatabase;
import com.example.notesapp.model.Note;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NotesRepository {
    private final NotesDao notesDao;
    private final Executor executor;
    private final LiveData<List<Note>> getAllNotes;

    public NotesRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getNoteDatabase(application);
        notesDao = noteDatabase.notesDao();
        getAllNotes = notesDao.getAllNotes();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insertNote(Note note){
        executor.execute(()-> notesDao.insertNote(note));
    }

    public void deleteNote(Note note){
        executor.execute(()-> notesDao.deleteNote(note));
    }

    public LiveData<List<Note>> getAllNotes() {
        return getAllNotes;
    }

    public void updateNote(Note note){
        executor.execute(()-> notesDao.updateNote(note));
    }
}
