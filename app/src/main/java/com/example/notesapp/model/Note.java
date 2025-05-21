package com.example.notesapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String noteHeading;
    private String noteBody;
    private Priority notePriority;

    public Note() {}

    public Note(String noteHeading, String noteBody, Priority priority) {
        this.noteHeading = noteHeading;
        this.noteBody = noteBody;
        notePriority = priority;
    }

    public Note(int id,String noteHeading, String noteBody, Priority priority) {
        this.id = id;
        this.noteHeading = noteHeading;
        this.noteBody = noteBody;
        notePriority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteHeading() {
        return noteHeading;
    }

    public void setNoteHeading(String noteHeading) {
        this.noteHeading = noteHeading;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public Priority getNotePriority() {
        return notePriority;
    }

    public void setNotePriority(Priority notePriority) {
        this.notePriority = notePriority;
    }

}
