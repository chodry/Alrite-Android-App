package com.ug.air.alrite.Utils;

import android.content.Context;
import android.database.Cursor;

import com.ug.air.alrite.Database.DatabaseHelper;
import com.ug.air.alrite.Models.Cred;
import com.ug.air.alrite.Models.Cred2;

import java.util.ArrayList;
import java.util.List;

public class Credentials {

    DatabaseHelper databaseHelper;
    String username, token, code, h_code, counter, password;
    int period;

    public Cred creds(Context context){

        databaseHelper = new DatabaseHelper(context);
        Cursor res = databaseHelper.getData("1");
        while (res.moveToNext()){
            period = res.getInt(1);
            token = res.getString(2);
            username = res.getString(3);
            password = res.getString(7);
        }

        Cred cred = new Cred(username, token, period, password);

        return cred;
    }

    public Cred2 creds2(Context context2){

        databaseHelper = new DatabaseHelper(context2);
        Cursor res = databaseHelper.getData("1");
        while (res.moveToNext()){
            code = res.getString(4);
            h_code = res.getString(5);
            counter = res.getString(6);
        }

        Cred2 cred2 = new Cred2(code, h_code, counter);

        return cred2;
    }

    public void counting(Context context3, String count){
        databaseHelper = new DatabaseHelper(context3);
        databaseHelper.updateCounter("1", count);
    }

}
