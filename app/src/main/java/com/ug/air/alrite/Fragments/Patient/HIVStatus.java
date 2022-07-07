package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.HIVCare.CHOICEHC;

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


public class HIVStatus extends Fragment {

    View view;
    Button back, next;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    String value4 = "none";
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NOT = 2;
    private static final int SURE = 3;
    public static final String CHOICE3 = "hiv_status";
    public static final String HDIAGNOSIS = "diagnosis_6";
    public static final String SHARED_PREFS = "sharedPrefs";
    String cough, hist, diagnosis;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hiv_status, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);
        radioButton3 = view.findViewById(R.id.negative);
        radioButton4 = view.findViewById(R.id.unknown);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case YES:
                        value4 = "HIV-Infected";
                        break;
                    case NO:
                        value4 = "HIV-Exposed";
                        break;
                    case NOT:
                        value4 = "HIV-Negative";
                        break;
                    case SURE:
                        value4 = "Unknown";
                        break;
                    default:
                        break;
                }
            }
        });

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value4.isEmpty()){
                    Toast.makeText(getActivity(), "Please select one option", Toast.LENGTH_SHORT).show();
                }else {
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new CoughD());
                fr.commit();

            }
        });

        return view;
    }

    private void saveData() {
        editor.putString(CHOICE3, value4);
        editor.apply();

        FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
        if (value4.equals("HIV-Infected")){
            diagnosis = "HIV-Infected";
            editor.putString(HDIAGNOSIS, diagnosis);
            editor.apply();
            fr.replace(R.id.fragment_container, new HIVCare());
        }else if (value4.equals("HIV-Exposed")){
            diagnosis = "HIV-Exposed";
            editor.putString(HDIAGNOSIS, diagnosis);
            editor.apply();
            fr.replace(R.id.fragment_container, new HIVCare());
        }else {
            deleteSharedPreferences();
            fr.replace(R.id.fragment_container, new Temperature());
        }
        fr.addToBackStack(null);
        fr.commit();

    }

    private void loadData() {
        value4 = sharedPreferences.getString(CHOICE3, "");
    }

    private void updateViews() {
        if (value4.equals("HIV-Infected")){
            radioButton1.setChecked(true);
        }else if (value4.equals("HIV-Exposed")){
            radioButton2.setChecked(true);
        }else if (value4.equals("HIV-Negative")){
            radioButton3.setChecked(true);
        }else if (value4.equals("Unknown")){
            radioButton4.setChecked(true);
        }else {
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
            radioButton3.setChecked(false);
            radioButton4.setChecked(false);
        }

    }

    private void deleteSharedPreferences() {
        editor.remove(CHOICEHC);
        editor.remove(HDIAGNOSIS);
        editor.apply();
    }

}