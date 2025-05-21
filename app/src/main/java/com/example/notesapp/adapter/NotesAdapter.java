package com.example.notesapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.model.Note;
import com.example.notesapp.model.Priority;
import com.example.notesapp.viewmodel.NotesViewModel;
import com.example.notesapp.views.MainActivity;
import com.example.notesapp.views.insert_note;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> noteList = new ArrayList<>();
    private Context context;
    public void setNotes(List<Note> note){
        this.noteList = note;
        notifyDataSetChanged();
    }

    NotesViewModel notesViewModel;

    public NotesAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_recycler_block,parent,false);
        return new NotesViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteHeadingTV.setText(note.getNoteHeading());
        holder.noteBodyTV.setText(note.getNoteBody());
        if (noteList.get(position).getNotePriority() == Priority.LOW){
            holder.notePriorityV.setBackgroundColor(Color.parseColor("#008450"));
        } else if (noteList.get(position).getNotePriority() == Priority.MEDIUM ){
            holder.notePriorityV.setBackgroundColor(Color.parseColor("#EFB700"));
        } else if (noteList.get(position).getNotePriority() == Priority.HIGH) {
            holder.notePriorityV.setBackgroundColor(Color.parseColor("#B81D13"));
        }else {
            holder.notePriorityV.setBackgroundColor(R.color.default1);
        }

        holder.block.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                notesViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(NotesViewModel.class);

                AlertDialog.Builder alertDialog = getBuilder(note);
                alertDialog.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
                return true;
            }
        });

        holder.block.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                if (holder.noteBodyTV.getVisibility() == View.VISIBLE){
                    holder.noteBodyTV.setVisibility(View.GONE);
                }
                else{
                    holder.noteBodyTV.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onDoubleClick(View view) {
                Intent intent = new Intent(context, insert_note.class);
                intent.putExtra("noteID", note.getId());
                intent.putExtra("heading", note.getNoteHeading());
                intent.putExtra("subHeading", note.getNoteBody());
                intent.putExtra("priority", note.getNotePriority().toString());
                intent.putExtra("intentType", "update");

                context.startActivity(intent);
            }
        }));

    }

    private AlertDialog.Builder getBuilder(Note note) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Delete Note");
        alertDialog.setMessage("Are you sure you want to delete");
        alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notesViewModel.deleteNote(note);
            }
        });
        return alertDialog;
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        public final TextView noteHeadingTV, noteBodyTV;
        public final View notePriorityV;
        public final ConstraintLayout block;
        public NotesViewHolder(View view){
            super(view);
            noteHeadingTV = view.findViewById(R.id.notesHeading);
            noteBodyTV = view.findViewById(R.id.notesBody);
            notePriorityV = view.findViewById(R.id.priorityView);
            block = view.findViewById(R.id.note_recycler_block);

        }


    }
}
