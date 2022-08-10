package com.ug.air.alrite.Fragments.navigation;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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

import com.ug.air.alrite.APIs.ApiClient;
import com.ug.air.alrite.APIs.JsonPlaceHolder;
import com.ug.air.alrite.Activities.Dashboard;
import com.ug.air.alrite.Activities.LearnActivity;
import com.ug.air.alrite.Activities.PatientActivity;
import com.ug.air.alrite.Activities.SplashActivity;
import com.ug.air.alrite.BuildConfig;
import com.ug.air.alrite.Database.DatabaseHelper;
import com.ug.air.alrite.Fragments.Patient.Nasal;
import com.ug.air.alrite.Fragments.Patient.Wheezing;
import com.ug.air.alrite.R;
import com.ug.air.alrite.Worker.NotifyWorker2;
import com.ug.air.alrite.Worker.NotifyWorker3;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    View view;
    DatabaseHelper databaseHelper;
    int period = 0;
    String token;
    File[] contents;
    JsonPlaceHolder jsonPlaceHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseHelper = new DatabaseHelper(getActivity());

        view =  inflater.inflate(R.layout.fragment_home, container, false);

        checkCredentials();

        return view;
    }

    private void checkCredentials() {
        File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/databases/alrite.db");
        if (src.exists()){
            intFunction();
//            sendDataToServer();
        }else {
            FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.navHostFragment, new AccountFragment());
            fr.commit();
        }

    }

    private void intFunction() {
        // Inflate the layout for this fragment

        view.findViewById(R.id.btn_learn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LearnActivity.class);
                startActivity(intent);
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

//        Cursor res = databaseHelper.getData("1");
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()){
//            period =  res.getInt(1);
//            token = res.getString(2);
//        }
//
////        sendDataToServer();
////
//        if (period == 1){
//            checkPatientReadiness();
////            sendDataToServer();
//            databaseHelper.updatePeriod("1", 2);
//        }

    }


    private void checkPatientReadiness() {
        Constraints constraints = new Constraints.Builder().build();

        WorkRequest uploadWorkRequest = new PeriodicWorkRequest
                .Builder(NotifyWorker2.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(getActivity()).enqueue(uploadWorkRequest);

    }


    private void sendDataToServer(){

        Constraints constraints = new Constraints.Builder().build();

        WorkRequest uploadWorkRequest = new PeriodicWorkRequest
                .Builder(NotifyWorker3.class, 20, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(getActivity()).enqueue(uploadWorkRequest);

    }
}