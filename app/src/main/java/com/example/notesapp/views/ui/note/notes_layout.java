package com.example.notesapp.views.ui.note;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.notesapp.R;
import com.example.notesapp.model.Note;
import com.example.notesapp.viewmodel.NoteViewModel;

public class notes_layout extends AppCompatActivity {

    EditText titleEdit, ContentEdit, CategoryEdit;
    NoteViewModel noteViewModel;
    Intent intent;
    private int noteId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes_layout);

        titleEdit = findViewById(R.id.noteTitleTxt);
        ContentEdit = findViewById(R.id.noteContentTxt);
        CategoryEdit = findViewById(R.id.category);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        intent = getIntent();

        if (intent.getStringExtra("intentType").equals("Insert")){
            actionBar.setTitle("Insert Note");
        }else{
            actionBar.setTitle("Update Note");
        }


        String noteTitle = intent.getStringExtra("noteTitle");
        String noteContent = intent.getStringExtra("noteContent");
        String noteCategory = intent.getStringExtra("noteCategory");
        noteId = intent.getIntExtra("noteId",0);
        titleEdit.setText(noteTitle);
        ContentEdit.setText(noteContent);
        CategoryEdit.setText(noteCategory);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
    }


    public boolean onOptionsItemSelected(MenuItem menuItem){

        if (menuItem.getItemId() == R.id.editNoteImage){
            String title = titleEdit.getText().toString();
            String content = ContentEdit.getText().toString();
            String category = CategoryEdit.getText().toString();
            int id = noteId;
            if(intent.getStringExtra("intentType").equals("Update")){
                Note note = new Note(id, title, content, category);
                noteViewModel.updateNote(note);
            }
            if(intent.getStringExtra("intentType").equals("Insert")){
                Note note = new Note(title, content, category);
                noteViewModel.insertNote(note);
            }
            finish();
        }
        return true;
    }

}