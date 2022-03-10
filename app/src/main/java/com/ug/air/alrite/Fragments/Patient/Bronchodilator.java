package com.ug.air.alrite.Fragments.Patient;

import android.app.Dialog;
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
import android.widget.TextView;

import com.ug.air.alrite.Activities.DiagnosisActivity;
import com.ug.air.alrite.R;

public class Bronchodilator extends Fragment {

    View view;
    Button btnBron, btnGiven, btnNot, btnSave;
    Dialog dialog;
    String bronchodilator;
    TextView txtMessage;
    public static final String BRONCHODILATOR = "bronchodilator";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bronchodilator, container, false);

        btnBron = view.findViewById(R.id.bronchodilator);
        btnGiven = view.findViewById(R.id.given);
        btnNot = view.findViewById(R.id.not_given);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnBron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showVideoDialog();
            }
        });

        btnGiven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bronchodilator = "Bronchodialtor Given";
                editor.putString(BRONCHODILATOR, bronchodilator);
                editor.apply();
                showDialog();
            }
        });

        btnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bronchodilator = "Bronchodialtor Not Given";
                editor.putString(BRONCHODILATOR, bronchodilator);
                editor.apply();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Bronchodilator2());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }

    private void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.assess);
        dialog.setCancelable(true);

        txtMessage = dialog.findViewById(R.id.message);
        btnSave = dialog.findViewById(R.id.ContinueButton);

        txtMessage.setText("Please wait for 15 minutes then reassess the patient");
        btnSave.setText("OK");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(getActivity(), DiagnosisActivity.class));
            }
        });

        dialog.show();
    }

    private void showVideoDialog() {
    }
}