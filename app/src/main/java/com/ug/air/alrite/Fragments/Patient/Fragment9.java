package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Fragment12.CHOICE8;
import static com.ug.air.alrite.Fragments.Patient.Fragment3.AGE;

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
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Fragment9 extends Fragment {

    View view;
    Button next, back, reset, manual, btnRate, btnReset, btnContinue;
    EditText etRate;
    LinearLayout tap;
    TextView txtElapse, txtRate, txtBreathe, txtMsg;
    Dialog dialog, dialog1;
    private ArrayList<Long> durations;
    private long lastBreath;
    private double value;
    private int numBreaths, margin, birthday;
    private String ifFastBreathing,ifNormalBreathing;
    private boolean fastBreathing;
    private CountDownTimer timer;
    private boolean completed;
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String FASTBREATHING = "fastBreathing";
    public static final String RATE = "rate";
    String age, rate, rating;
    float ag = 0;
    int score = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_9, container, false);

        back = view.findViewById(R.id.back);
        reset = view.findViewById(R.id.reset);
        manual = view.findViewById(R.id.manual);
        txtElapse = view.findViewById(R.id.elapsedTime);
        tap = view.findViewById(R.id.Circle);

        sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        age = sharedPreferences.getString(AGE, "");
        ag = Float.parseFloat(age);

        numBreaths = 5;
        margin=13;
        durations = new ArrayList<>();
        newTimer();
        lastBreath=-1;

        tap.setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.circle_animation));
            ((TextView) view.findViewById(R.id.TapOnInhale)).setText(R.string.tap_on_inhale);
            breathTaken();
            if (validateDataCollection()) {
                value = getBreathRate(numBreaths);
                rate = String.valueOf(value);
                completeMeasuring();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetRespRate();
            }
        });

        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnotherDialog();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wheezing = sharedPreferences.getString(CHOICE8, "");
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                if (wheezing.equals("Yes") || wheezing.equals("No")){
                    fr.replace(R.id.fragment_container, new Fragment12());
                }else {
                    fr.replace(R.id.fragment_container, new Fragment6v7());
                }
                fr.commit();
            }
        });

        return view;
    }

    public void breathTaken() {
        long currTime = System.currentTimeMillis();
        if (lastBreath != -1) {
            long dur = currTime - lastBreath;
            durations.add(dur);
        } else{
            startTimer();
        }

        lastBreath = currTime;
    }

    public boolean validateDataCollection() {
        return getValidProgress() >= numBreaths;
    }

    public double getBreathRate(int num) {
        return 60 / (getMedian(num) / 1000.0);
    }

    public long getMedian(int length) {
        if (length > durations.size()) {
            return -1;
        }
        if (length == 0) {
            return -1;
        }

        ArrayList<Long> sub = new ArrayList<Long>(durations.subList(durations.size() - (length), durations.size()));
        Collections.sort(sub);
        int half = (length / 2);
        if (length % 2 == 0) {
            return (sub.get(half - 1) + sub.get(half)) / 2;
        }
        return sub.get(half);
    }

    public void resetTimer(){
        timer.cancel();
        newTimer();
    }

    public void startTimer(){
        timer.start();
    }

    public void newTimer(){
        timer = new CountDownTimer(60*1000,1000) {
            @Override
            public void onTick(long l) {
                updateElapsedTimeView((60*1000)-l);
            }

            @Override
            public void onFinish() {
                if(durations.size()<5){
                    resetRespRate();
                } else {
                    value = getBreathRate(durations.size());
                    completeMeasuring();
                }
            }
        };
    }

    public void completeMeasuring(){
        setCompleted(true);
        resetTimer();
        evalFastBreathing(ag);
        showDialog();
    }

    public int getValidProgress() {
        for (int ii = 1; ii <= numBreaths; ii++) {
            if (ii > durations.size()) {
                return ii - 1;
            }
            long median = getMedian(ii);
            if (median == -1) {
                return ii - 1;
            }
            long up = upperBound(median);
            long low = lowerBound(median);

            for (int jj = durations.size() - 1; jj > ((durations.size() - 1) - ii); jj--) {
                if (!inBounds(durations.get(jj), up, low)) {
                    return ii - 1;
                }
            }
        }
        return numBreaths;
    }

    public long lowerBound(long med) {
        return (long) (med * (1.0 - (margin / 100.0)));
    }

    public long upperBound(long med) {
        return (long) (med * (1.0 + (margin / 100.0)));
    }

    public boolean inBounds(long val, long up, long low) {
        return (val < up) && (val > low);
    }

    public void updateElapsedTimeView(long millis){
        String b = this.getResources().getString(R.string.elapsed_time);
        txtElapse.setText(b+" "+millisecondsToString(millis));
    }

    public String millisecondsToString(long millis){
        String  ms = (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)))+
                ":"+ (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return ms;
    }

    public void resetRespRate(){
        ((TextView) tap.findViewById(R.id.TapOnInhale)).setText(R.string.start_text);
        durations.clear();
        lastBreath = -1;
        resetTimer();
        updateElapsedTimeView(0);
    }

    public void evalFastBreathing(float birthday){
        if(isCompleted()) {
            if (birthday <= 0.2){
                if (value > 60){
                    fastBreathing = true;
                    score = R.string.breathing_info_under2;
                    return;
                }
                fastBreathing = false;
            }
            if (birthday <= 1 && birthday > 0.2 ){
                if (value > 50){
                    fastBreathing = true;
                    score = R.string.breathing_info_under1;
                    return;
                }
                fastBreathing = false;
            }
            if (birthday > 1){
                if (value > 40){
                    fastBreathing = true;
                    score = R.string.breathing_info_over1;
                    return;
                }
                fastBreathing = false;
            }
//            if (birthday>-1) {
//                if (birthday <2) {
//                    if(value>=50 && value<60){
//                        fastBreathing = true;
//                        score = 2;
//                        return;
//                    }
//
//                    if (value >=60) {
//                        fastBreathing = true;
//                        score = 3;
//                        return;
//                    }
//                }
//                else {
//                    if (value >= 40 && value <45) {
//                        fastBreathing = true;
//                        score = 2;
//                        return;
//                    }
//                    if (value >= 45) {
//                        fastBreathing = true;
//                        score = 3;
//                        return;
//                    }
//                }
//
//                fastBreathing = false;
//                Toast.makeText(getActivity(), "" + fastBreathing, Toast.LENGTH_SHORT).show();
//            } else {
//                //TODO
//            }
        } else {
            //TODO
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;

    }

    private void showAnotherDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.manual_layout);

        etRate = dialog.findViewById(R.id.rate);
        btnRate = dialog.findViewById(R.id.saveRate);

        etRate.requestFocus();

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate = etRate.getText().toString();
                if (rate.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in the required field", Toast.LENGTH_SHORT).show();
                }else {
                    ag = Float.parseFloat(age);
                    double rt = Double.parseDouble(rate);
                    value = rt;
                    dialog.dismiss();
                    completeMeasuring();
                }
            }
        });

        dialog.show();

    }

    private void showDialog() {
        dialog1 = new Dialog(getActivity());
        dialog1.setContentView(R.layout.respiratory_results_ayout);

        txtRate = dialog1.findViewById(R.id.RespRateNum);
        txtMsg = dialog1.findViewById(R.id.breathingInfo);
        txtBreathe = dialog1.findViewById(R.id.FastBreathing);
        btnReset = dialog1.findViewById(R.id.ResetButton);
        btnContinue = dialog1.findViewById(R.id.ContinueButton);

        rate = String.valueOf(value);
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
                resetRespRate();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
                saveData();
            }
        });

        dialog1.show();

    }

    private void saveData() {

        editor.putString(FASTBREATHING, rating);
        editor.putString(RATE, rate);
        editor.apply();

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Fragment10());
        fr.addToBackStack(null);
        fr.commit();
    }

}