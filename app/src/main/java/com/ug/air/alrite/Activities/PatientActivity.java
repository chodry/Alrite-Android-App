package com.ug.air.alrite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.ug.air.alrite.Fragments.Patient.Fragment1;
import com.ug.air.alrite.Fragments.Patient.Fragment10;
import com.ug.air.alrite.Fragments.Patient.Fragment11;
import com.ug.air.alrite.Fragments.Patient.Fragment12;
import com.ug.air.alrite.Fragments.Patient.Fragment2v1;
import com.ug.air.alrite.Fragments.Patient.Fragment4;
import com.ug.air.alrite.Fragments.Patient.Fragment5;
import com.ug.air.alrite.Fragments.Patient.Fragment6;
import com.ug.air.alrite.Fragments.Patient.Fragment7;
import com.ug.air.alrite.Fragments.Patient.Fragment8;
import com.ug.air.alrite.Fragments.Patient.Fragment9;
import com.ug.air.alrite.R;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new Fragment1());
        fragmentTransaction.commit();

//        String s = "30.0";
//        String[] separated = s.split("\\.");
//        Toast.makeText(PatientActivity.this, separated[0] + " : " + separated[1], Toast.LENGTH_SHORT).show();
    }
}