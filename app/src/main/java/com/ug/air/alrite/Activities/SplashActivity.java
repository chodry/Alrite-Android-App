package com.ug.air.alrite.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

import com.ug.air.alrite.R;

public class SplashActivity extends AppCompatActivity {

    Intent i = null;
    LinearLayout logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);
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