package com.ug.air.alrite.Utils;

import android.content.Context;
import android.database.Cursor;

import com.ug.air.alrite.Database.DatabaseHelper;

public class Credentials {

    DatabaseHelper databaseHelper;
    String username;

    public String username(Context context){
        databaseHelper = new DatabaseHelper(context);
        Cursor res = databaseHelper.getData("1");
        while (res.moveToNext()){
            username = res.getString(3);
        }

        return username;
    }
}
