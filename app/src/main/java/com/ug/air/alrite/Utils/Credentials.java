package com.ug.air.alrite.Utils;

import android.content.Context;
import android.database.Cursor;

import com.ug.air.alrite.Database.DatabaseHelper;
import com.ug.air.alrite.Models.Cred;

import java.util.ArrayList;
import java.util.List;

public class Credentials {

    DatabaseHelper databaseHelper;
    String username, token;
    int period;

    public Cred creds(Context context){

//        List<String> credentials = new ArrayList<>();

        databaseHelper = new DatabaseHelper(context);
        Cursor res = databaseHelper.getData("1");
        while (res.moveToNext()){
            period = res.getInt(1);
            token = res.getString(2);
            username = res.getString(3);
        }

        Cred cred = new Cred(username, token, period);

        return cred;
    }

}
