package com.rastha.googlemap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void insert(String fname, String lname, String addr, String user, String pass, int phone,
                       double lat, double lng) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.F_NAME, fname);
        contentValue.put(DBHelper.L_NAME, lname);
        contentValue.put(DBHelper.ADDRESS, addr);
        contentValue.put(DBHelper.USERNAME, user);
        contentValue.put(DBHelper.PASSWORD, pass);
        contentValue.put(DBHelper.PHONE, phone);
        contentValue.put(DBHelper.LATITUDE, lat);
        contentValue.put(DBHelper.LONGITUDE, lng);

        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        final String[] columns = new String[] {
                DBHelper.USER_ID,
                DBHelper.F_NAME,
                DBHelper.L_NAME,
                DBHelper.ADDRESS,
                DBHelper.USERNAME,
                DBHelper.PASSWORD,
                DBHelper.PHONE,
                DBHelper.LATITUDE,
                DBHelper.LONGITUDE};
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public boolean Login(String username, String password) throws SQLException
    {
        Cursor mCursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE username=? AND password=?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }
    public Cursor fetch_data(String fusername, String fpassword) throws SQLException{
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE Username=? AND Password=?", new String[]{fusername,fpassword});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_d(String fusername, String fpassword) throws SQLException{
        final String[] columns = new String[] {
                DBHelper.USER_ID,
                DBHelper.F_NAME,
                DBHelper.L_NAME,
                DBHelper.ADDRESS,
                DBHelper.USERNAME,
                DBHelper.PASSWORD,
                DBHelper.PHONE,
                DBHelper.LATITUDE,
                DBHelper.LONGITUDE};
        String selection = "Username=" + fusername +" AND Password ="+ fpassword;
        Cursor cursor= database.query(DBHelper.TABLE_NAME, columns, selection, null, null, null, null);
    return cursor;
    }
    public void close() {
        dbHelper.close();
    }
}
