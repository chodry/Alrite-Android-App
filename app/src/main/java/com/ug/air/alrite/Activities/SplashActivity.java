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
import com.ug.air.alrite.Database.DatabaseHelper;
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
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);

        databaseHelper = new DatabaseHelper(this);

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
                File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/databases/alrite.db");
                if (src.exists()){
                    i = new Intent(SplashActivity.this, Dashboard.class);
                    startActivity(i);
                    finish();
                }else {
                    databaseHelper.insertData(1, "");
                    i = new Intent(SplashActivity.this, Dashboard.class);
                    startActivity(i);
                    finish();
                }

            }
        }, timeout);
    }
}