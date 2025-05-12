package com.example.notesapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String noteHeading;
    private String noteBody;
    private int Priority;
    private String alertDate;
    private String alertTime;

    public Note() {}

    public Note(String noteHeading, String noteBody, int priority, String alertDate, String alertTime) {
        this.noteHeading = noteHeading;
        this.noteBody = noteBody;
        Priority = priority;
        this.alertDate = alertDate;
        this.alertTime = alertTime;
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

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public String getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(String alertDate) {
        this.alertDate = alertDate;
    }

    public String getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(String alertTime) {
        this.alertTime = alertTime;
    }
}
