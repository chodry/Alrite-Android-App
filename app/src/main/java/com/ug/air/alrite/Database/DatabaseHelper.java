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
    public static final String COL_4 = "USERNAME";
    public static final String COL_5 = "CODE";
    public static final String COL_6 = "HFCODE";
    public static final String COL_7 = "COUNTER";
    public static final String COL_8 = "PASSWORD";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, PERIOD INTEGER, TOKEN TEXT, USERNAME TEXT, CODE TEXT, HFCODE TEXT, COUNTER TEXT, PASSWORD TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int period, String token, String username, String code, String hcode, String counter, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, period);
        contentValues.put(COL_3, token);
        contentValues.put(COL_4, username);
        contentValues.put(COL_5, code);
        contentValues.put(COL_6, hcode);
        contentValues.put(COL_7, counter);
        contentValues.put(COL_8, password);
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

    public boolean updateCounter(String id, String counter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_7, counter);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }

    public boolean updateToken(String id, String token, String username, String code, String hcode, String counter, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_3, token);
        contentValues.put(COL_4, username);
        contentValues.put(COL_5, code);
        contentValues.put(COL_6, hcode);
        contentValues.put(COL_7, counter);
        contentValues.put(COL_8, password);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }
}
