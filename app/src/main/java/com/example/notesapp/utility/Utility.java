package com.example.notesapp.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.notesapp.model.Note;
import com.example.notesapp.model.Todo;
import com.example.notesapp.viewmodel.NoteViewModel;
import com.example.notesapp.viewmodel.ToDoViewModel;

public class Utility {

    public static void getNoteAleartBuilder(Note note, Context context, NoteViewModel viewModel) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Delete Note");
        alertDialog.setMessage("Are you sure you want to delete");
        alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.deleteNote(note);
            }
        });

        alertDialog.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    public static void getTodoAleartBuilder(Todo todo, Context context, ToDoViewModel viewModel) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Delete Todo");
        alertDialog.setMessage("Are you sure you want to delete");
        alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.deleteTodo(todo);
            }
        });

        alertDialog.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

}
