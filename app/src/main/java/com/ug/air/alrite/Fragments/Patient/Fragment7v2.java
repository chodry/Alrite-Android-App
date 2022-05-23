package com.ug.air.alrite.Fragments.Patient;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.R;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Fragment7v2 extends Fragment {

    View view;
    Button next, back, reset, manual, btnRate, btnReset, btnContinue;
    EditText etRate;
    LinearLayout tap;
    TextView txtElapse, txtRate, txtBreathe, txtMsg;
    Dialog dialog, dialog1;
    private boolean fastBreathing;
    String age, rate, rating, check;
    float ag = 0;
    int score = 0;
    long duration;
    long taps = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_7v2, container, false);

        back = view.findViewById(R.id.back);
        next = view.findViewById(R.id.next);
        reset = view.findViewById(R.id.reset);
        manual = view.findViewById(R.id.manual);
        txtElapse = view.findViewById(R.id.elapsedTime);
        tap = view.findViewById(R.id.Circle);

        duration = TimeUnit.MINUTES.toMillis(1);

        CountDownTimer countDownTimer = new CountDownTimer(duration, 1000){
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(l),
                        TimeUnit.MILLISECONDS.toSeconds(l) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                txtElapse.setText(sDuration);
            }

            @Override
            public void onFinish() {
                long ag = 24;
                evalFastBreathing(ag);
                showDialog();
            }
        };

        tap.setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.circle_animation));
            if (taps == 0){
                ((TextView) view.findViewById(R.id.TapOnInhale)).setText(R.string.tap_on_inhale);
                countDownTimer.start();
            }
            taps += 1;

        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                txtElapse.setText("Elasped Time: ");
                taps = 0;
            }
        });

        return view;
    }

    public void evalFastBreathing(long birthday){
        if (birthday < 2){
            if (taps >= 60){
                fastBreathing = true;
                score = R.string.breathing_info_under2;
                return;
            }
            fastBreathing = false;
        }
        if (birthday >= 2 && birthday <= 12 ){
            if (taps >= 50){
                fastBreathing = true;
                score = R.string.breathing_info_under1;
                return;
            }
            fastBreathing = false;
        }
        if (birthday > 12){
                if (taps >= 40){
                    fastBreathing = true;
                    score = R.string.breathing_info_over1;
                    return;
                }
                fastBreathing = false;
        }
    }

    private void showDialog() {
        dialog1 = new Dialog(getActivity());
        dialog1.setContentView(R.layout.respiratory_results_ayout);

        txtRate = dialog1.findViewById(R.id.RespRateNum);
        txtMsg = dialog1.findViewById(R.id.breathingInfo);
        txtBreathe = dialog1.findViewById(R.id.FastBreathing);
        btnReset = dialog1.findViewById(R.id.ResetButton);
        btnContinue = dialog1.findViewById(R.id.ContinueButton);

        rate = String.valueOf(taps);
        String[] separated = rate.split("\\.");
        txtRate.setText(separated[0]);

        if (fastBreathing){
            rating = "Fast Breathing";
            txtBreathe.setText(rating);
            txtBreathe.setTextColor(getResources().getColor(R.color.red));
            txtMsg.setText(score);
        }else {
            rating = "Normal Breathing";
            txtBreathe.setText(rating);
            txtBreathe.setTextColor(getResources().getColor(R.color.green));
            txtMsg.setVisibility(View.GONE);
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
                txtElapse.setText("Elasped Time: ");
                taps = 0;
//                resetRespRate();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
//                saveData();
            }
        });

        dialog1.show();

    }
}