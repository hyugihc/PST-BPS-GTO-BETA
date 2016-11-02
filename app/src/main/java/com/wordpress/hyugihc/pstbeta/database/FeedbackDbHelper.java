package com.wordpress.hyugihc.pstbeta.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hyugihc on 3/18/2016.
 */
public class FeedbackDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Feedback.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATE_TYPE = " DATETIME";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedbackContract.FeedbackEntry.TABLE_NAME + " (" +
                    FeedbackContract.FeedbackEntry.COLUMN_NAME_TIMESTAMP + DATE_TYPE + " DEFAULT (datetime('now','localtime'))PRIMARY KEY" + COMMA_SEP +
                    FeedbackContract.FeedbackEntry.COLUMN_NAME_RESPONSE + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedbackContract.FeedbackEntry.TABLE_NAME;

    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbackList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedbackContract.FeedbackEntry.TABLE_NAME, null);

        Feedback feedback = null;
        if (cursor.moveToFirst()) {
            do {
                feedback = new Feedback();
                feedback.setDate(cursor.getString(0));
                feedback.setResponse(cursor.getString(1));
                feedbackList.add(feedback);
            } while (cursor.moveToNext());
        }
        return feedbackList;
    }

    public void deleteAllFeedback(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ FeedbackContract.FeedbackEntry.TABLE_NAME);

    }

    public List<Feedback> getFeedbackByRangeDate(String minDate, String maxDate) {
        List<Feedback> feedbackList = new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(FeedbackContract.FeedbackEntry.TABLE_NAME, FeedbackContract.FeedbackEntry.TABLE_COLUMN,
                FeedbackContract.FeedbackEntry.COLUMN_NAME_TIMESTAMP + " BETWEEN ? AND ?",
                new String[]{minDate + " 00:00:00", maxDate + " 23:59:59"}, null, null, null, null);

        Feedback feedback = null;
        if (cursor.moveToFirst()) {
            do {
                feedback = new Feedback();
                feedback.setDate(cursor.getString(0));
                feedback.setResponse(cursor.getString(1));
                feedbackList.add(feedback);
            } while (cursor.moveToNext());
        }

        return feedbackList;
    }

    public FeedbackDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
