package com.ug.air.alrite.Fragments.Patient;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ug.air.alrite.R;

public class WheezY extends Fragment {

    View view;
    EditText etDay;
    Button back, next;
    String day1;
    public static final String DAY2 = "breathing_difficulties_last_year";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wheez_y, container, false);

        etDay = view.findViewById(R.id.days);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        etDay.requestFocus();

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

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
                fr.replace(R.id.fragment_container, new WheezD());
                fr.commit();
            }
        });

        return view;
    }

    private void saveData() {


        editor.putString(DAY2, day1);
        editor.apply();

        FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Breathless());
        fr.addToBackStack(null);
        fr.commit();

    }

    private void loadData() {
        day1 = sharedPreferences.getString(DAY2, "");
    }

    private void updateViews() {
        etDay.setText(day1);
    }
}