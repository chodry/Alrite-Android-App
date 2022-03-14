package com.ug.air.alrite.Fragments.Patient;

import android.content.Context;
import android.content.Intent;
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

import com.ug.air.alrite.Activities.Dashboard;
import com.ug.air.alrite.R;

import java.util.Objects;

public class Initials extends Fragment {

   View view;
   EditText etCin, etPin;
   Button back, next;
   String cin, pin;
   public static final String CIN = "cin";
   public static final String PIN = "pin";
   public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_initials, container, false);

        etCin = view.findViewById(R.id.cin);
        etPin = view.findViewById(R.id.pin);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cin = etCin.getText().toString();
                pin = etPin.getText().toString();

                if (cin.isEmpty() || pin.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }else {
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove(CIN);
                editor.remove(PIN);
                editor.apply();
                startActivity(new Intent(getActivity(), Dashboard.class));
            }
        });

        return view;
    }

    private void saveData() {

        editor.putString(CIN, cin);
        editor.putString(PIN, pin);
        editor.apply();

        FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Sex());
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        pin = sharedPreferences.getString(PIN, "");
        cin = sharedPreferences.getString(CIN, "");
    }

    private void updateViews() {
        etPin.setText(pin);
        etCin.setText(cin);
    }
}