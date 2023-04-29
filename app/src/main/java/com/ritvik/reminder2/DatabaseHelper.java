package com.ritvik.reminder2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String REMINDERS="REMINDERS";
    public static final String NAME="NAME";
    public static final String HOUR="HOUR";
    public static final String MINUTE="MINUTE";
    public static final String DATE="DATE";
    public static final String MONTH="MONTH";
    public static final String YEAR="YEAR";
    public static final String DESCR="DESCR";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "reminders.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + REMINDERS + " ("+NAME+" TEXT, "+ HOUR+" INT, "+ MINUTE+" INT, "+DATE+" INT, "+MONTH+" INT, "+YEAR+" INT, "+DESCR+" TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(ReminderModel reminderModel) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,reminderModel.getName());
        cv.put(HOUR,reminderModel.getHour());
        cv.put(MINUTE,reminderModel.getMinute());
        cv.put(DATE,reminderModel.getDate());
        cv.put(MONTH,reminderModel.getMonth());
        cv.put(YEAR,reminderModel.getYear());
        cv.put(DESCR,reminderModel.getDesc());

        long insert = db.insert("reminders", null, cv);
        if (insert==-1) {
            return false;
        }
        else {
            return true;
        }
    }
    public List<ReminderModel> getAll() {
        List<ReminderModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + REMINDERS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToFirst()) {
            do {
                String name=cursor.getString(0);
                int hour=cursor.getInt(1);
                int minute=cursor.getInt(2);
                int date=cursor.getInt(3);
                int month=cursor.getInt(4);
                int year=cursor.getInt(5);
                String desc=cursor.getString(6);

                ReminderModel newReminder = new ReminderModel(name,hour,minute,date,month,year,desc);
                returnList.add(newReminder);


            } while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }
}
