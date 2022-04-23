package com.ug.air.alrite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ug.air.alrite.Fragments.Patient.ActivePatients;
import com.ug.air.alrite.Fragments.Patient.HIVStatus;
import com.ug.air.alrite.Fragments.Patient.Initials;
import com.ug.air.alrite.Fragments.Patient.OtherPatients;
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
            }else if (frag == 2){
                fragmentTransaction.add(R.id.fragment_container, new ActivePatients());
            }else {
                fragmentTransaction.add(R.id.fragment_container, new OtherPatients());
            }
            fragmentTransaction.commit();
        }
    }

//    @Override
//    public void onBackPressed() {
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.assess);
//        dialog.setCancelable(true);
//
//        TextView txtMessage = dialog.findViewById(R.id.message);
//        Button btnSave = dialog.findViewById(R.id.ContinueButton);
//
//        btnSave.setText("OK");
//
//        txtMessage.setText("If you want to go back to the previous form please click the Back button on the screen");
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
}