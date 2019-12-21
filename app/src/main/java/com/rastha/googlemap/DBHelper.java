package com.rastha.googlemap;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    static final int DB_VERSION = 1;

    private static final String DATABASE_NAME = "UserList.db";
    public static final String TABLE_NAME = "Users";
   public static final String USER_ID = "UserID";
   public static final String F_NAME = "First_Name";
   public static final String L_NAME = "Last_Name";
   public static final String ADDRESS = "Address";
   public static final String USERNAME = "Username";
   public static final String PASSWORD = "Password";
   public static final String PHONE = "Phone";
   public static final String LATITUDE = "Latitude";
   public static final String LONGITUDE = "Longitude";


    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                                      + F_NAME + " TEXT NOT NULL,"
                                     + L_NAME + " TEXT NOT NULL,"
                                     + ADDRESS + " TEXT NOT NULL,"
                                     + USERNAME + " TEXT NOT NULL,"
                                     + PASSWORD + " TEXT NOT NULL,"
                                     + PHONE + " BIGINT,"
                                     + LATITUDE + " double,"
                                     + LONGITUDE + " double)";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
