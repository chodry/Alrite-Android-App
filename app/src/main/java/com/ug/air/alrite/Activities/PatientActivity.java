package com.ug.air.alrite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ug.air.alrite.Fragments.Patient.Assess;
import com.ug.air.alrite.Fragments.Patient.Bronchodilator;
import com.ug.air.alrite.Fragments.Patient.ChestIndrawing;
import com.ug.air.alrite.Fragments.Patient.CoughD;
import com.ug.air.alrite.Fragments.Patient.HIVStatus;
import com.ug.air.alrite.Fragments.Patient.Kerosene;
import com.ug.air.alrite.Fragments.Patient.Nasal;
import com.ug.air.alrite.Fragments.Patient.Oxygen;
import com.ug.air.alrite.Fragments.Patient.Stridor;
import com.ug.air.alrite.Fragments.Patient.WheezD;
import com.ug.air.alrite.R;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new Kerosene());
        fragmentTransaction.commit();

    }
}