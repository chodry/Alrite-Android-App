package com.ug.air.alrite.Utils;

import static com.ug.air.alrite.Activities.SplashActivity.APP_OPENING_COUNT;
import static com.ug.air.alrite.Activities.SplashActivity.COUNTING_DATA;

import android.content.Context;
import android.content.SharedPreferences;

public class Counter {

    int counting = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public void Count(Context context, String object_name){

        sharedPreferences = context.getSharedPreferences(COUNTING_DATA, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        counting = sharedPreferences.getInt(object_name, 0);
        counting = counting + 1;
        editor.putInt(object_name, counting);
        editor.apply();
    }
}
