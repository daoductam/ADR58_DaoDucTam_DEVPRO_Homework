package com.tamdao.adr58_daoductam_day8;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String date;
    private String duration;
    private String type; // youtube, voice, folder
    private boolean isNew;

    public Note(String title, String date, String duration, String type, boolean isNew) {
        this.title = title;
        this.date = date;
        this.duration = duration;
        this.type = type;
        this.isNew = isNew;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getDuration() { return duration; }
    public String getType() { return type; }
    public boolean isNew() { return isNew; }
}