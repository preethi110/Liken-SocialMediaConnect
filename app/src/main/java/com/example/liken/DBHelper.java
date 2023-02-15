package com.example.liken;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(username TEXT primary key, instagram TEXT, facebook TEXT, twitter TEXT, spotify TEXT, whatsapp TEXT, snapchat TEXT, linkedin TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String username, String instagram, String facebook, String twitter, String spotify, String whatsapp, String snapchat, String linkedin) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", username);
        contentValues.put("instagram", instagram);
        contentValues.put("facebook", facebook);
        contentValues.put("twitter", twitter);
        contentValues.put("spotify", spotify);
        contentValues.put("whatsapp", whatsapp);
        contentValues.put("snapchat", snapchat);
        contentValues.put("linkedin", linkedin);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean deletedata (String username)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }
    public Boolean updateuserdata(String username, String instagram, String facebook, String twitter, String spotify, String whatsapp, String snapchat, String linkedin) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", username);
        contentValues.put("instagram", instagram);
        contentValues.put("facebook", facebook);
        contentValues.put("twitter", twitter);
        contentValues.put("spotify", spotify);
        contentValues.put("whatsapp", whatsapp);
        contentValues.put("snapchat", snapchat);
        contentValues.put("linkedin", linkedin);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;



    }
}