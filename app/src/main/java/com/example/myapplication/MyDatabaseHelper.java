package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper {

    private final String DATABASE_NAME = "UsersDB";
    private final int DATABASE_VERSION = 1;

    private final String TABLE_NAME = "user_table";
    private final String KEY_ID = "_id";
    private final String KEY_NAME = "_name";
    private final String KEY_EMAIL = "_email";
    private final String KEY_PASSWORD = "_password";


    CreateDataBase helper;
    SQLiteDatabase database;
    Context context;

    public MyDatabaseHelper(Context context)
    {
        this.context = context;

    }

    public void insert(String fname,String lname, String email, String password)
    {
        ContentValues cv = new ContentValues();
        String name = fname +" "+lname;

        cv.put(KEY_NAME,name);
        cv.put(KEY_EMAIL,email);
        cv.put(KEY_PASSWORD,password);

        long records = database.insert(TABLE_NAME, null, cv);
        if(records == -1)
        {
            Toast.makeText(context, "User Registration Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean authenticate(String email, String pass) {
        String[] columns = {KEY_ID};
        String selection = KEY_EMAIL + " = ?" + " AND " + KEY_PASSWORD + " = ?";
        String[] selectionArgs = {email, pass};

        Cursor cursor = database.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean userFound = cursor.getCount() > 0;
        cursor.close();

        return userFound;
    }



    public void open()
    {
        helper = new CreateDataBase(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = helper.getWritableDatabase();
    }

    public void close()
    {
        database.close();
        helper.close();
    }

    private class CreateDataBase extends SQLiteOpenHelper
    {
        public CreateDataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + "(" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_NAME + " TEXT NOT NULL," +
                    KEY_EMAIL + " TEXT NOT NULL," +
                    KEY_PASSWORD + " TEXT NOT NULL" +
                    ");";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // backup code here
        }
    }
}
