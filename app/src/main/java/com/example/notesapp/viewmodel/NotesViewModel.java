package com.example.notesapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private final LiveData<List<Note>> allNotes;
    private final NotesRepository notesRepository;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        allNotes = notesRepository.getAllNotes();
    }
    public void insertNote(Note note){
        notesRepository.insertNote(note);
    }
    public void deleteNote(Note note){
        notesRepository.deleteNote(note);
    }
    public void updateNote(Note note){
        notesRepository.updateNote(note);
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
