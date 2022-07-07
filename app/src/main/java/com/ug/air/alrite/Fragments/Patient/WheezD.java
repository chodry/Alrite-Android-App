package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Bronchodilator2.BDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.FTouch.TOUCH;
import static com.ug.air.alrite.Fragments.Patient.WheezY.DAY2;

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


public class WheezD extends Fragment {

    View view;
    Button back, next;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3;
    String value5 = "none";
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NOT = 2;
    public static final String CHOICEX = "wheezing_before_this_illness";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wheez_d, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);
        radioButton3 = view.findViewById(R.id.not);

        sharedPreferences = this.requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

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
                    case NOT:
                        value5 = "Not Sure";
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
                if (value5.isEmpty()){
                    Toast.makeText(getActivity(), "Please select at least one option", Toast.LENGTH_SHORT).show();
                }else {
                    saveData();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bronchodilator = sharedPreferences.getString(BDIAGNOSIS, "");
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                if (bronchodilator.isEmpty()){
                    fr.replace(R.id.fragment_container, new Bronchodilator3());
                }else{
                    fr.replace(R.id.fragment_container, new Bronchodilator2());
                }
                fr.commit();

            }
        });

        return view;
    }

    private void saveData() {
        editor.putString(CHOICEX, value5);
        editor.apply();

        FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
        if (value5.equals("No")) {
            deleteSharedPreferences();
            fr.replace(R.id.fragment_container, new Breathless());
        }else {
            fr.replace(R.id.fragment_container, new WheezY());
        }
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        value5 = sharedPreferences.getString(CHOICEX, "");
    }

    private void updateViews() {
        if (value5.equals("Yes")){
            radioButton1.setChecked(true);
        }else if (value5.equals("No")){
            radioButton2.setChecked(true);
        }else if (value5.equals("Not Sure")){
            radioButton3.setChecked(true);
        }else {
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
            radioButton3.setChecked(false);
        }

    }

    private void deleteSharedPreferences() {
        editor.remove(DAY2);
        editor.apply();
    }

}