package com.ug.air.alrite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ug.air.alrite.Fragments.Patient.Assess;
import com.ug.air.alrite.R;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new Assess());
        fragmentTransaction.commit();

//        String s = "30.0";
//        String[] separated = s.split("\\.");
//        Toast.makeText(PatientActivity.this, separated[0] + " : " + separated[1], Toast.LENGTH_SHORT).show();
    }
}