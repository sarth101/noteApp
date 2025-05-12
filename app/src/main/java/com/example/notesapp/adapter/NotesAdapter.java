package com.example.notesapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> noteList = new ArrayList<Note>();

    public void setNotes(List<Note> note){
        this.noteList = note;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_recycler_block,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteHeadingTV.setText(note.getNoteHeading());
        holder.noteBodyTV.setText(note.getNoteBody());
        holder.notePriorityV.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        public TextView noteHeadingTV, noteBodyTV;
        public View notePriorityV;
        public NotesViewHolder(View view){
            super(view);
            noteHeadingTV = view.findViewById(R.id.notesHeading);
            noteBodyTV = view.findViewById(R.id.notesBody);
            notePriorityV = view.findViewById(R.id.priorityView);
        }
    }
}
