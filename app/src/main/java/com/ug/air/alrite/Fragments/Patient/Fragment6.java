package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Fragment6v1.DAY2;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v2.CHOICEX;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.CHECK11;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.CHECK21;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.CHECK31;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.CHECK41;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.S5;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v4.CHOICEX2;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v5.CHOICEY2;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v6.CHOICET1;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v7.CHOICET2;

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


public class Fragment6 extends Fragment {

    View view;
    EditText etDay;
    Button back, next;
    String day1;
    public static final String DAY1 = "day1";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_6, container, false);

        etDay = view.findViewById(R.id.days);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        etDay.requestFocus();

        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
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
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment5());
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

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        if (dt > 10) {
            fr.replace(R.id.fragment_container, new Fragment6v1());
        }else {
            deleteSharedPreferences();
            fr.replace(R.id.fragment_container, new Fragment7());
        }
        fr.addToBackStack(null);
        fr.commit();

    }

    private void loadData() {
        day1 = sharedPreferences.getString(DAY1, "");
    }

    private void updateViews() {
        etDay.setText(day1);
    }

    private void deleteSharedPreferences() {
        editor.remove(DAY2);
        editor.remove(CHOICEX);
        editor.remove(CHECK11);
        editor.remove(CHECK21);
        editor.remove(CHECK31);
        editor.remove(CHECK41);
        editor.remove(S5);
        editor.remove(CHOICEX2);
        editor.remove(CHOICEY2);
        editor.remove(CHOICET2);
        editor.remove(CHOICET1);
        editor.apply();
    }

}