package com.ug.air.alrite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "alrite.db";
    public static final String TABLE_NAME = "alrite_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PERIOD";
    public static final String COL_3 = "TOKEN";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, PERIOD INTEGER, TOKEN TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int period, String token){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, period);
        contentValues.put(COL_3, token);
        long results = db.insert(TABLE_NAME, null, contentValues);
        if (results == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where ID = ? ", new String[] { id });
        return res;
    }

    public boolean updatePeriod(String id, int period){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, period);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }

    public boolean updateToken(String id, String token){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_3, token);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }
}
