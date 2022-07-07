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


public class HIVCare extends Fragment {

    View view;
    Button back, next;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3;
    String value4 = "none";
    private static final int YES = 0;
    private static final int NO = 1;
    public static final String CHOICEHC = "child_in_hiv_care";
    public static final String SHARED_PREFS = "sharedPrefs";
    String cough, hist, diagnosis;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hiv_care, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case YES:
                        value4 = "Yes";
                        break;
                    case NO:
                        value4 = "No";
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
                fr.replace(R.id.fragment_container, new HIVStatus());
                fr.commit();

            }
        });

        return view;
    }

    private void saveData() {
        editor.putString(CHOICEHC, value4);
        editor.apply();

        FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Temperature());
        fr.addToBackStack(null);
        fr.commit();

    }

    private void loadData() {
        value4 = sharedPreferences.getString(CHOICEHC, "");
    }

    private void updateViews() {
        if (value4.equals("Yes")){
            radioButton1.setChecked(true);
        }else if (value4.equals("No")){
            radioButton2.setChecked(true);
        }else {
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
        }

    }

}