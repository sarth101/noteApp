package com.example.notesapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int noteId;
    private String title;
    private String content;
    private String category;

    private Note(){}
    public Note(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Note(int id, String title, String content, String category) {
        this.noteId = id;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public int getNoteId() {
        return this.noteId;
    }

    public void setNoteId(int id) {
        this.noteId = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String subTitle) {
        this.content = subTitle;
    }

    public String getCategory(){
        return this.category;
    }

    public void setCategory(String category){
        this.category = category;
    }

}
