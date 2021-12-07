package com.example.studyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

}
