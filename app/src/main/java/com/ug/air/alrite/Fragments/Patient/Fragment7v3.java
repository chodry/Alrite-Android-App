package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.HIVCare.CHOICEHC;
import static com.ug.air.alrite.Fragments.Patient.Fragment7v4.CHOICE3Y2;

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


public class Fragment7v3 extends Fragment {

    View view;
    Button back, next;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3;
    String value4 = "none";
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NOT = 2;
    public static final String CHOICE3X2 = "choice3X2";
    public static final String SHARED_PREFS = "sharedPrefs";
    String cough, hist;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_7v3, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);
        radioButton3 = view.findViewById(R.id.not_sure);

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
                    case NOT:
                        value4 = "Not Sure";
                        break;
                    default:
                        break;
                }
            }
        });

        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
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
                String hist = sharedPreferences.getString(CHOICEHC, "");
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                if (hist.isEmpty()){
                    fr.replace(R.id.fragment_container, new HIVStatus());
                }else {
                    fr.replace(R.id.fragment_container, new HIVCare());
                }
                fr.commit();
            }
        });

        return view;
    }

    private void saveData() {
        editor.putString(CHOICE3X2, value4);
        editor.apply();

        if (value4.equals("Yes")){
            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Fragment7v4());
            fr.addToBackStack(null);
            fr.commit();
        }else {
            deleteSharedPreferences();
            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Fever());
            fr.addToBackStack(null);
            fr.commit();
        }

    }

    private void loadData() {
        value4 = sharedPreferences.getString(CHOICE3X2, "");
    }

    private void updateViews() {
        if (value4.equals("Yes")){
            radioButton1.setChecked(true);
        }else if (value4.equals("No")){
            radioButton2.setChecked(true);
        }else if (value4.equals("Not Sure")){
            radioButton3.setChecked(true);
        }else {
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
            radioButton3.setChecked(false);
        }

    }

    private void deleteSharedPreferences() {
        editor.remove(CHOICE3Y2);
        editor.apply();
    }

}