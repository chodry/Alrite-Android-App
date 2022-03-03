package com.ug.air.alrite.Fragments.Patient;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ug.air.alrite.R;

import java.util.Map;
import java.util.Objects;


public class Sex extends Fragment {
    View view;
    EditText etYears, etKilo1, etKilo2, etMonths, etMuac;
    Button back, next;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    String age, weight, text, kg1, fileName, months, years, muac;
    Spinner spinner;
    String value2 = "none";
    private static final int YES = 0;
    private static final int NO = 1;
    public static final String AGE = "age";
    public static final String KILO = "kilo";
    public static final String MUAC = "MUAC";
    public static final String CHOICE = "choice";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sex, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);
        etYears = view.findViewById(R.id.years);
        etMonths = view.findViewById(R.id.months);
        etKilo1 = view.findViewById(R.id.kg1);
        etMuac = view.findViewById(R.id.muac);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case YES:
                        value2 = "Male";
                        break;
                    case NO:
                        value2 = "Female";
                        break;

                    default:
                        break;
                }
            }
        });

        etKilo1.addTextChangedListener(textWatcher1);
        etYears.addTextChangedListener(textWatcher2);
        etMonths.addTextChangedListener(textWatcher3);
        etMuac.addTextChangedListener(textWatcher4);

        bundle = this.getArguments();
        if (bundle != null){
            fileName = bundle.getString("fileName");
            String names = fileName.replace(".xml", "");
            sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(names, Context.MODE_PRIVATE);
            sharedPreferences1 = Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            editor = sharedPreferences1.edit();
            Map<String, ?> all = sharedPreferences.getAll();
            for (Map.Entry<String, ?> x : all.entrySet()) {
                if (x.getValue().getClass().equals(String.class))  editor.putString(x.getKey(),  (String)x.getValue());
            }

            editor.commit();

            loadData();
            updateViews();

        }else {
            sharedPreferences1 = Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

            loadData();
            updateViews();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                years = etYears.getText().toString();
                months = etMonths.getText().toString();
                kg1 = etKilo1.getText().toString();
                muac = etMuac.getText().toString();
//                kg2 = etKilo2.getText().toString();

                if (value2.equals("none") || years.isEmpty() || kg1.isEmpty() || months.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    age = years + "." + months;
                    weight = kg1;
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Initials());
                fr.commit();
            }
        });

        return view;
    }

    public TextWatcher textWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            kg1 = etKilo1.getText().toString();
            if (!kg1.isEmpty()){
                float k1 = Float.parseFloat(kg1);
                if (k1 > 30.0){
                    etKilo1.setError("The maximum kilograms for a child has to be 30.0kgs");
                }else if (k1 < 0.5){
                    etKilo1.setError("The minimum kilograms for a child has to be 0.5kgs");
                }
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public TextWatcher textWatcher4 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            muac = etMuac.getText().toString();
            if (!muac.isEmpty()){
                float k1 = Float.parseFloat(muac);
                if (k1 > 40.0){
                    etMuac.setError("The maximum value for MUAC has to be 40.0cm");
                }else if (k1 < 9.0){
                    etMuac.setError("The minimum value for MUAC has to be 9.0cm");
                }
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public TextWatcher textWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            years = etYears.getText().toString();
            if (!years.isEmpty()){
                int yr = Integer.parseInt(years);
                if (yr > 5){
                    etYears.setError("The maximum number of years should be 5");
                    etMonths.setText("");
                    etMonths.setEnabled(true);
                }else if (yr == 5) {
                    etMonths.setText("0");
                    etMonths.setEnabled(false);
                }else {
                    etMonths.setText("");
                    etMonths.setEnabled(true);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public TextWatcher textWatcher3 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            months = etMonths.getText().toString();
            if (!months.isEmpty()){
                int yr = Integer.parseInt(months);
                if (yr > 11){
                    etMonths.setError("The maximum number of months should be 11");
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void saveData() {
        editor = sharedPreferences1.edit();

        editor.putString(AGE, age);
        editor.putString(MUAC, muac);
        editor.putString(KILO, weight);
        editor.putString(CHOICE, value2);
        editor.apply();

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Assess());
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        age = sharedPreferences.getString(AGE, "");
        muac = sharedPreferences.getString(MUAC, "");
        weight = sharedPreferences.getString(KILO, "");
        value2 = sharedPreferences.getString(CHOICE, "");
    }

    private void updateViews() {
        if (value2.equals("Male")){
            radioButton1.setChecked(true);
        }else if (value2.equals("Female")){
            radioButton2.setChecked(true);
        }else {
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
        }

        if (!weight.isEmpty()){
            etKilo1.setText(weight);
        }
        if (!muac.isEmpty()){
            etMuac.setText(muac);
        }

        if (!age.isEmpty()) {
            String[] separated = age.split("\\.");
            etYears.setText(separated[0]);
            etMonths.setText(separated[1]);
        }
    }
}