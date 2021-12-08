package com.example.studyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, "events.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE EVENTS_TABLE " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, EVENT_TYPE TEXT, EVENT_TITLE TEXT, EVENT_DATE TEXT, EVENT_TIME TEXT, EVENT_DESCRIPTION TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("EVENT_TYPE", event.getType());
        cv.put("EVENT_TITLE", event.getTitle());
        cv.put("EVENT_DATE", event.getDate());
        cv.put("EVENT_TIME", event.getTime());
        cv.put("EVENT_DESCRIPTION", event.getDescription());

        long insert = db.insert("EVENTS_TABLE", null, cv);

        if (insert == -1) {
            return false;
        }
        return true;
    }

    public List<Event> getAllEvents(String type){
        List<Event> eventList = new ArrayList<>();
        String query = "";

        if (type.equals("STUDY PLAN")) {
            query = "SELECT * FROM EVENTS_TABLE WHERE EVENT_TYPE='STUDY PLAN'";
        }
        else if (type.equals("ASSIGNMENTS")) {
            query = "SELECT * FROM EVENTS_TABLE WHERE EVENT_TYPE='ASSIGNMENTS'";
        }
        else if (type.equals("EXAMS")) {
            query = "SELECT * FROM EVENTS_TABLE WHERE EVENT_TYPE='EXAMS'";
        }
        else {
            query = "SELECT * FROM EVENTS_TABLE WHERE EVENT_TYPE='LECTURES'";
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int ID = cursor.getInt(0);
                String eventType = cursor.getString(1);
                String title = cursor.getString(2);
                String date = cursor.getString(3);
                String time = cursor.getString(4);
                String description = cursor.getString(5);
                Event event = new Event(ID, eventType, title, date, time, description);
                eventList.add(event);
            }
            while(cursor.moveToNext());
        }
        return eventList;
    }

    public List<String> getAllDates(){
        List<String> dateList = new ArrayList<>();
        String queryDate = "";
        queryDate = "SELECT * FROM EVENTS_TABLE ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryDate, null);
        if (cursor.moveToFirst()) {
            do {
//                if event is not alredy present create new one else increase count
                String date = cursor.getString(3);
                if(!dateList.contains(date)){
//                   Log.i("not present asdfasdf",date);
                    dateList.add(date);
                }
            } while (cursor.moveToNext());
        }
        return dateList;
    }




}
