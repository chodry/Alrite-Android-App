package com.ug.air.alrite.Activities;

import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.BRONCHODILATOR;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.DATE;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.UUIDS;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator3.BRONC;
import static com.ug.air.alrite.Fragments.Patient.Initials.CIN;
import static com.ug.air.alrite.Fragments.Patient.Initials.PIN;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.SECOND;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.BuildConfig;
import com.ug.air.alrite.Fragments.Patient.ActivePatients;
import com.ug.air.alrite.Fragments.Patient.HIVStatus;
import com.ug.air.alrite.Fragments.Patient.Initials;
import com.ug.air.alrite.Fragments.Patient.OtherPatients;
import com.ug.air.alrite.Fragments.Patient.Wheezing;
import com.ug.air.alrite.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class PatientActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String INCOMPLETE = "incomplete";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;
    int frag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Intent intent = getIntent();
        if (intent.hasExtra("Fragment")){
            frag = intent.getExtras().getInt("Fragment");
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (frag == 1){
                fragmentTransaction.add(R.id.fragment_container, new Initials());
            }else if (frag == 2){
                fragmentTransaction.add(R.id.fragment_container, new ActivePatients());
            }else {
                fragmentTransaction.add(R.id.fragment_container, new OtherPatients());
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (frag == 2 || frag == 3){
            startActivity(new Intent(PatientActivity.this, Dashboard.class));
            finish();
        }
        else {
            String pin = sharedPreferences.getString(PIN, "");
            String cin = sharedPreferences.getString(CIN, "");

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.exit);
            dialog.setCancelable(true);

            Button btnYes = dialog.findViewById(R.id.yes);
            Button btnNo = dialog.findViewById(R.id.no);

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (pin.isEmpty() && cin.isEmpty()){
                        dialog.dismiss();
                        startActivity(new Intent(PatientActivity.this, Dashboard.class));
                        finish();
                    }else{
                        Date currentTime = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
                        String formattedDate = df.format(currentTime);

                        String uniqueID = UUID.randomUUID().toString();

                        editor.putString(DATE, formattedDate);
                        editor.putString(UUIDS, uniqueID);
                        editor.putString(INCOMPLETE, "incomplete");
                        editor.apply();

                        String filename = formattedDate + "_" + uniqueID;
                        doLogic(filename);
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();
        }

    }

    private void doLogic(String file) {
        sharedPreferences1 = getSharedPreferences(file, Context.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();
        Map<String, ?> all = sharedPreferences.getAll();
        for (Map.Entry<String, ?> x : all.entrySet()) {
            if (x.getValue().getClass().equals(String.class))  editor1.putString(x.getKey(),  (String)x.getValue());
            else if (x.getValue().getClass().equals(Boolean.class)) editor1.putBoolean(x.getKey(), (Boolean)x.getValue());
        }
        editor1.commit();

        String filename = sharedPreferences1.getString(SECOND, "");
        if (!filename.isEmpty()){
            filename = filename + ".xml";
            File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs/" + filename);
            if (src.exists()){
                src.delete();
            }
        }

        editor.clear();
        editor.commit();

        Intent intent;
        intent = new Intent(PatientActivity.this, Dashboard.class);
        startActivity(intent);
    }
}