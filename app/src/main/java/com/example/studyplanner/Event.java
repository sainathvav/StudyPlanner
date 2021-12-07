package com.example.studyplanner;

public class Event {
    private String Type;
    private String Title;
    private String Date;
    private String Time;
    private String Description;
    private int ID;

    public Event(int ID, String type, String title, String date, String time, String description) {
        Type = type;
        Title = title;
        Date = date;
        Time = time;
        Description = description;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Event() {
    }

    public Event(String type, String title, String date, String time, String description) {
        Type = type;
        Title = title;
        Date = date;
        Time = time;
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
