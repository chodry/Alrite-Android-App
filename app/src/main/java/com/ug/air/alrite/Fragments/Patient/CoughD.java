package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Assess.DATE;
import static com.ug.air.alrite.Fragments.Patient.Assess.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Assess.S4;
import static com.ug.air.alrite.Fragments.Patient.Assess.UUIDS;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.Activities.Dashboard;
import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


public class CoughD extends Fragment {

    View view;
    EditText etDay;
    Button back, next, btnSave;
    String day1;
    public static final String DAY1 = "day1";
    public static final String SHARED_PREFS = "sharedPrefs";
    Dialog dialog;
    TextView txtMessage;
    String diagnosis;
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cough_d, container, false);

        etDay = view.findViewById(R.id.days);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        etDay.requestFocus();

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

        etDay.addTextChangedListener(textWatcher);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day1 = etDay.getText().toString();
                if (day1.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in the field before you continue", Toast.LENGTH_SHORT).show();
                }else{
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Cough());
                fr.commit();
            }
        });

        return view;
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            day1 = etDay.getText().toString();
            if (!day1.isEmpty()){
                long dy = Long.parseLong(day1);
                if (dy == 0){
                   etDay.setError("The number of days should not be 0");
                   next.setEnabled(false);
                }else {
                    next.setEnabled(true);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void saveData() {

        editor.putString(DAY1, day1);
        editor.apply();

        int dt = Integer.parseInt(day1);

        String assess = sharedPreferences.getString(S4, "");

        if (!assess.equals("None of these")){
            FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Temperature());
            fr.addToBackStack(null);
            fr.commit();
        }else {
            if (dt < 10) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new HIVStatus());
                fr.addToBackStack(null);
                fr.commit();
            }else if (dt >= 14){
                showDialog(dt);
            }else {
                showDialog(dt);
            }
        }

    }

    private void loadData() {
        day1 = sharedPreferences.getString(DAY1, "");
    }

    private void updateViews() {
        etDay.setText(day1);
    }

    private void showDialog(int dt) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.assess);
        dialog.setCancelable(true);

        txtMessage = dialog.findViewById(R.id.message);
        btnSave = dialog.findViewById(R.id.ContinueButton);

        btnSave.setText("Continue");

        if (dt >= 14){
            txtMessage.setText("Children with prolonged cough (>= 14 days) should be assessed for both Asthma and Tuberculosis");
        }else {
            txtMessage.setText("Children with prolonged cough (>= 10 days) should be assessed for Asthma");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new HIVStatus());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        dialog.show();
    }

}