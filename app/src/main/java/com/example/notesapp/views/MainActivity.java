package com.example.notesapp.views;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notesapp.R;
import com.example.notesapp.adapter.NotesAdapter;
import com.example.notesapp.model.Note;
import com.example.notesapp.viewmodel.NotesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView notesRecyclerView;
NotesViewModel notesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        NotesAdapter notesAdapter = new NotesAdapter();
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        notesRecyclerView.setAdapter(notesAdapter);


//        Note note = new Note("Close TV", "Close TV before Sleep", 1, null, null);
//        notesViewModel.insertNote(note);
//        List<Note>


        notesViewModel.getAllNotes().observe(this, notesAdapter::setNotes);
    }
}