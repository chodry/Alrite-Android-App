package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Assess.S4;
import static com.ug.air.alrite.Fragments.Patient.FTouch.TOUCH;
import static com.ug.air.alrite.Fragments.Patient.Fragment7v4.CHOICE3Y2;
import static com.ug.air.alrite.Fragments.Patient.HIVCare.CHOICEHC;
import static com.ug.air.alrite.Fragments.Patient.HIVStatus.CHOICE3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ug.air.alrite.R;

import java.util.Objects;


public class Temperature extends Fragment {

    View view;
    EditText etDay;
    Button back, next, btnSkip;
    String temp, diagnosis;
    public static final String TEMP = "temp";
    public static final String TDIAGNOSIS = "tDiagnosis";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_temperature, container, false);

        etDay = view.findViewById(R.id.days);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        btnSkip = view.findViewById(R.id.skip);

        etDay.requestFocus();

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();


        etDay.addTextChangedListener(textWatcher);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = etDay.getText().toString();
                if (temp.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in the field before you continue", Toast.LENGTH_SHORT).show();
                }else{
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String assess = sharedPreferences.getString(S4, "");
                String care = sharedPreferences.getString(CHOICEHC, "");

                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                if (!assess.equals("None of these")){
                    fr.replace(R.id.fragment_container, new Assess());
                }else if (care.isEmpty()){
                    fr.replace(R.id.fragment_container, new HIVStatus());
                }else{
                    fr.replace(R.id.fragment_container, new HIVCare());
                }
                fr.commit();
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new FTouch());
                fr.addToBackStack(null);
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
            temp = etDay.getText().toString();
            if (!temp.isEmpty()){
                float dy = Float.parseFloat(temp);
                btnSkip.setEnabled(false);
                if (dy < 33.0){
                    etDay.setError("The minimum temperature is 33.0");
                }else if (dy > 42.0){
                    etDay.setError("The maximum temperature is 42.0");
                }
            }else {
                btnSkip.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void saveData() {
        deleteSharedPreferences();

        String assess = sharedPreferences.getString(S4, "");
        float tp = Float.parseFloat(temp);
        if (tp >= 38.5 && assess.contains("None of these")){
            diagnosis = "Fever without danger signs";
            editor.putString(TDIAGNOSIS, diagnosis);
        }

        editor.putString(TEMP, temp);
        editor.apply();


        FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Oxygen());
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        temp = sharedPreferences.getString(TEMP, "");
    }

    private void updateViews() {
        if (!temp.isEmpty()){
            etDay.setText(temp);
            btnSkip.setEnabled(false);
        }else {
            btnSkip.setEnabled(true);
        }
    }

    private void deleteSharedPreferences() {
        editor.remove(TOUCH);
        editor.remove(TDIAGNOSIS);
        editor.apply();
    }
}