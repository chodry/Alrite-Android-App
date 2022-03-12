package com.ug.air.alrite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.ug.air.alrite.Fragments.Patient.ActivePatients;
import com.ug.air.alrite.Fragments.Patient.Initials;
import com.ug.air.alrite.R;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Intent intent = getIntent();
        if (intent.hasExtra("Fragment")){
            int frag = intent.getExtras().getInt("Fragment");
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (frag == 1){
                fragmentTransaction.add(R.id.fragment_container, new Initials());
            }else {
                fragmentTransaction.add(R.id.fragment_container, new ActivePatients());
            }
            fragmentTransaction.commit();
        }
    }
}