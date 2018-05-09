package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

/**
 * Created by josephcriseno on 5/7/18.
 */

public class MyDBHandler extends SQLiteOpenHelper{

    private final static String TAG = "DBHandler";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "messages.db";
    public static final String TABLE_MESSAGES = "messages";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PHONENUMBER = "phonenumber";
    public static final String COLUMN_MESSAGE = "message";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_MESSAGES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_PHONENUMBER + " TEXT ," +
                COLUMN_MESSAGE + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        onCreate(db);
    }

    // Add a new row to the database
    public void addMessage(SavedMessage SavedMessage){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONENUMBER, SavedMessage.get_phonenumber());
        values.put(COLUMN_MESSAGE, SavedMessage.get_message());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_MESSAGES, null, values);
        db.close();
    }

    // Delete a product from the database
    public void deleteMessage(String phonenumber){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MESSAGES + " WHERE " +
                COLUMN_PHONENUMBER + "=\"" + phonenumber + "\";");
    }

    // Print out the database as a string
    public String databaseToString(){
        Log.d(TAG, "attempting to print database");
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MESSAGES + " WHERE 1";

        //Cursors point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            String phoneNumber = c.getString(c.getColumnIndex(COLUMN_PHONENUMBER));
            String message = c.getString(c.getColumnIndex(COLUMN_MESSAGE));
            if(phoneNumber != null){
                dbString += phoneNumber + ' ' + message + '\n';
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        Log.d(TAG, "printed database");
        return dbString;
    }
}
