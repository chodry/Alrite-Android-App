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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ug.air.alrite.R;

import java.util.Objects;


public class Fragment8 extends Fragment {

    View view;
    Button back, next;
    RadioGroup radioGroup, radioGroup1;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    String value5, value6 = "none";
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int YES1 = 0;
    private static final int NO1 = 1;
    public static final String CHOICE4 = "choice4";
    public static final String CHOICE5 = "choice5";
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_8, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup1 = view.findViewById(R.id.radioGroup1);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);
        radioButton3 = view.findViewById(R.id.yes1);
        radioButton4 = view.findViewById(R.id.no1);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case YES:
                        value5 = "Yes";
                        break;
                    case NO:
                        value5 = "No";
                        break;
                    default:
                        break;
                }
            }
        });

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup1.findViewById(checkedId);
                int index = radioGroup1.indexOfChild(radioButton);

                switch (index) {
                    case YES:
                        value6 = "Yes";
                        break;
                    case NO:
                        value6 = "No";
                        break;
                    default:
                        break;
                }
            }
        });

        loadData();
        updateViews();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value5.isEmpty() || value6.isEmpty()){
                    Toast.makeText(getActivity(), "Please select one option for both options", Toast.LENGTH_SHORT).show();
                }else {
                    saveData();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment7());
                fr.commit();
            }
        });


        return view;
    }

    private void saveData() {
        SharedPreferences sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(CHOICE4, value5);
        editor.putString(CHOICE5, value6);
        editor.apply();

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Fragment9());
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        value5 = sharedPreferences.getString(CHOICE4, "");
        value6 = sharedPreferences.getString(CHOICE5, "");
    }


    private void updateViews() {
        if (value5.equals("Yes")){
            radioButton1.setChecked(true);
        }else if (value5.equals("No")){
            radioButton2.setChecked(true);
        }else {
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
        }

        if (value6.equals("Yes")){
            radioButton3.setChecked(true);
        }else if (value6.equals("No")){
            radioButton4.setChecked(true);
        }else {
            radioButton3.setChecked(false);
            radioButton4.setChecked(false);
        }

    }

}