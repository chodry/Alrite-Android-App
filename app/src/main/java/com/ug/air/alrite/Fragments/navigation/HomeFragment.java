package com.ug.air.alrite.Fragments.navigation;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ug.air.alrite.Activities.PatientActivity;
import com.ug.air.alrite.Database.DatabaseHelper;
import com.ug.air.alrite.R;
import com.ug.air.alrite.Worker.NotifyWorker2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class HomeFragment extends Fragment {

    View view;
    DatabaseHelper databaseHelper;
    int period = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.btn_learn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), GlossaryActivity.class);
//                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_patients).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), PatientActivity.class);
                bundle.putInt("Fragment", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_clinic_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), PatientActivity.class);
                bundle.putInt("Fragment", 3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_assessment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), PatientActivity.class);
                bundle.putInt("Fragment", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(getActivity());
        Cursor res = databaseHelper.getData("1");
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            period =  res.getInt(1);
        }

        if (period == 1){
            getMinuteDifference();
            databaseHelper.updatePeriod("1", 2);
            Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
        }

//        getMinuteDifference();


//        view.findViewById(R.id.btn_start_assessment).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), PatientActivity.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }

    private void getMinuteDifference() {
        Constraints constraints = new Constraints.Builder().build();

        WorkRequest uploadWorkRequest = new PeriodicWorkRequest
                .Builder(NotifyWorker2.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(getActivity()).enqueue(uploadWorkRequest);

    }
}