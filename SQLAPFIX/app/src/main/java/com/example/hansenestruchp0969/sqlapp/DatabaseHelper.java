package com.example.hansenestruchp0969.sqlapp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactApp2018.db";
    public static final String TABLE_NAME = "ContactApp2018_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_PHONE_CONTACT = "contact";
    public static final String COLUMN_ADDRESS_CONTACT = "contact";



    public static final String SQL_CREATE_ENTRIES =
            "Create Table " +  TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CONTACT + " TEXT," + COLUMN_PHONE_CONTACT + " TEXT," + COLUMN_ADDRESS_CONTACT + "TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE ID EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("MyContactApp2018", "Databasehelper: constructed databasehelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp2018", "Databasehelper: creating database");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp2018", "Databasehelper: upgrading database");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertData(String name){
        Log.d("MyContactApp2018","Databasehelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues contentValue = new android.content.ContentValues();
        contentValue.put(COLUMN_NAME_CONTACT, name);
        contentValue.put(COLUMN_PHONE_CONTACT, name);
        contentValue.put(COLUMN_ADDRESS_CONTACT, name);
        long result = db.insert(TABLE_NAME,  null, contentValue);

        if (result == -1) {
            Log.d("MyContactApp2018", "Databasehelper: Contnt insert - FAILED");
            return false;
        }
        else {
            Log.d("MyContactApp2018","Databasehelper: Content insert - PASSED");
            return true;
        }
    }

    public Cursor getAllData(){
        Log.d("MyContactApp2018",  "Databasehelper: calling getAllData Method");
        SQLiteDatabase db = this.getWritableDatabase();
        //fix
        Log.d("MyContactApp2018",  "Databasehelper: calling getAllData Method");
        Cursor res = db.rawQuery( "select * from " + TABLE_NAME, null);
        Log.d("MyContactApp2018",  "Databasehelper: cursor res");
        return res;

    }





}
