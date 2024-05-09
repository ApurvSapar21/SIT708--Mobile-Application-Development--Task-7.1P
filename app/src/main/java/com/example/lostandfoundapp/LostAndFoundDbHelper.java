package com.example.lostandfoundapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LostAndFoundDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LostAndFound.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ADVERTS = "adverts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_POST_TYPE = "post_type";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LOCATION = "location";

    private static final String SQL_CREATE_ADVERTS_TABLE =
            "CREATE TABLE " + TABLE_ADVERTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_POST_TYPE + " TEXT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_PHONE + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_LOCATION + " TEXT)";

    public LostAndFoundDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ADVERTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADVERTS);
        onCreate(db);
    }

    public void insertAdvert(String postType, String name, String phone, String description, String date, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POST_TYPE, postType);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_LOCATION, location);
        long newRowId = db.insert(TABLE_ADVERTS, null, values);
        db.close();
    }

    public void deleteItem(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ADVERTS, COLUMN_ID + " = ?", new String[]{String.valueOf(itemId)});
        db.close();
    }
}
