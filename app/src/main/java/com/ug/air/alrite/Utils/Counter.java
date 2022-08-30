package com.ug.air.alrite.Utils;

import static com.ug.air.alrite.Activities.SplashActivity.APP_OPENING_COUNT;
import static com.ug.air.alrite.Activities.SplashActivity.COUNTING_DATA;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.DURATION;
import static com.ug.air.alrite.Fragments.Patient.Initials.INITIAL_DATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Counter {

    int counting = 0;
    String counter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public void Count(Context context, String object_name){

        sharedPreferences = context.getSharedPreferences(COUNTING_DATA, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        counter = sharedPreferences.getString(object_name, "");
        counting = Integer.parseInt(counter);
        counting = counting + 1;
        String county = String.valueOf(counting);
        editor.putString(object_name, county);
        editor.apply();
    }

    public void Timer(Context context, Date start_date, Date current_date, String object_name){

        long diff = current_date.getTime() - start_date.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

    }
}
