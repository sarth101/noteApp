package com.example.notesapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private final NoteRepository noteRepository;
    private final LiveData<List<Note>> getAllNote;
    public NoteViewModel(Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        getAllNote = noteRepository.getAllNotes();
    }

    public void insertNote(Note note){
        noteRepository.insertNote(note);
    }
    public void updateNote(Note note){
        noteRepository.updateNote(note);
    }
    public void deleteNote(Note note){
        noteRepository.deleteNote(note);
    }
    public LiveData<List<Note>> getAllNote(){
        return getAllNote;
    }

    public LiveData<List<Note>> getNoteByCategory(String category){
        return noteRepository.getNoteByCategory(category);
    }

    public LiveData<List<String>> getCategories(){
        return noteRepository.getCategories();
    }
}