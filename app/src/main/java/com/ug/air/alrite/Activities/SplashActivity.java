package com.ug.air.alrite.Activities;

import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.REASSESS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

import com.ug.air.alrite.BuildConfig;
import com.ug.air.alrite.R;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    Intent i = null;
    LinearLayout logo;
    File[] contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);

//        String dat = "2022-04-29_13:30:21";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
//
//        try {
//            Date date = format.parse(dat);
//            String time = simpleDateFormat.format(date);
//            Date date1 = simpleDateFormat.parse(time);
//            Date cur = new Date();
//            String tim = simpleDateFormat.format(cur);
//            Date date2 = simpleDateFormat.parse(tim);
//
//            long difference = date2.getTime() - date1.getTime();
//            int days = (int) (difference / (1000*60*60*24));
//            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
//            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//            min = (min < 0 ? -min : min);
//            Log.d("======= Minutes"," :: "+min);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        startSplashTimeout(2000);
    }

    private void startSplashTimeout(int timeout){
        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(1500);
        animation1.setStartOffset(100);
        animation1.setFillAfter(true);
        logo.startAnimation(animation1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                i = new Intent(SplashActivity.this, Dashboard.class);

                startActivity(i);

                finish();
            }
        }, timeout);
    }
}