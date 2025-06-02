package com.example.notesapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.model.Note;
import com.example.notesapp.utility.Utility;
import com.example.notesapp.views.ui.note.notes_layout;
import com.example.notesapp.viewmodel.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final Context context;

    private List<Note> noteList = new ArrayList<>();
    private NoteViewModel noteViewModel;


    public NotesAdapter(@NonNull Context context) {
        super();
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNoteList(List<Note> noteList){
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public void  setNoteViewModel(NoteViewModel noteViewModel){
        this.noteViewModel = noteViewModel;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_recycler_block, parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Note note = noteList.get(position);
        holder.noteTitleTxt.setText(note.getTitle());
        holder.noteContentTxt.setText(note.getContent());
        holder.notesCategoryList.setText(note.getCategory());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteTile = note.getTitle();
                String noteContent = note.getContent();
                String noteCategory = note.getCategory();
                int noteId = note.getNoteId();

                Intent intent = new Intent(context, notes_layout.class);
                intent.putExtra("noteId", noteId);
                intent.putExtra("noteTitle", noteTile);
                intent.putExtra("noteContent", noteContent);
                intent.putExtra("noteCategory", noteCategory);
                intent.putExtra("intentType", "Update");
                context.startActivity(intent);
            }
        });

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Utility.getNoteAleartBuilder(note, context, noteViewModel);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        public TextView noteTitleTxt, noteContentTxt;
        public TextView notesCategoryList;
        public ConstraintLayout layout;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.note_recycler_block);
            noteTitleTxt = itemView.findViewById(R.id.noteTitle);
            noteContentTxt = itemView.findViewById(R.id.noteContent);
            notesCategoryList = itemView.findViewById(R.id.category);
        }
    }
}
