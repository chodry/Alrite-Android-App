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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ug.air.alrite.R;

import java.util.Map;
import java.util.Objects;


public class Fragment3 extends Fragment implements AdapterView.OnItemSelectedListener {

    View view;
    EditText etAge, etKilo1, etKilo2;
    Button back, next;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    String age, weight, text, kg1, kg2, fileName;
    Spinner spinner;
    String value2 = "none";
    private static final int YES = 0;
    private static final int NO = 1;
    public static final String AGE = "age";
    public static final String KILO = "kilo";
    public static final String CHOICE = "choice";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_3, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);
        etAge = view.findViewById(R.id.years);
        etKilo1 = view.findViewById(R.id.kg1);
        etKilo2 = view.findViewById(R.id.kg2);
        spinner = view.findViewById(R.id.yearsormonths);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.same, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
        etKilo2.addTextChangedListener(textWatcher2);

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
                age = etAge.getText().toString();
                kg1 = etKilo1.getText().toString();
                kg2 = etKilo2.getText().toString();

                if (value2.equals("none") || age.isEmpty() || kg1.isEmpty() || kg2.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    long ag = Long.parseLong(age);
                    if (text.equals("Select One")){
                        Toast.makeText(getActivity(), "Please select whether the age selected is in months or years", Toast.LENGTH_SHORT).show();
                    }else if (ag > 5 && text.equals("Years")){
                        etAge.setError("The age should not be greater than 5 years");
                    }else if (ag > 12 && text.equals("Months")){
                        etAge.setError("The months should not be greater than 12 months");
                    }else {
                        if (text.equals("Months")){
                            age = "0." + age;
                        }
                        weight = kg1 + "." + kg2;
                        saveData();
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment2());
                fr.commit();
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public TextWatcher textWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            kg1 = etKilo1.getText().toString();
            if (!kg1.isEmpty()){
                long k1 = Long.parseLong(kg1);
                if (k1 > 30){
                    etKilo1.setError("The maximum kilograms for a child has to be 30.0kgs");
                }else if (k1 == 30){
                    etKilo2.setText("0");
                    etKilo2.setEnabled(false);
                }else {
                    etKilo2.setEnabled(true);
                    etKilo2.setText("");
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
            kg1 = etKilo1.getText().toString();
            kg2 = etKilo2.getText().toString();
            if (!kg1.isEmpty() && !kg2.isEmpty()){
                long k1 = Long.parseLong(kg1);
                long k2 = Long.parseLong(kg2);
                if (k1 == 0 && k2 < 5){
                    etKilo2.setError("The minimum weight has to be .5kgs");
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
        editor.putString(KILO, weight);
        editor.putString(CHOICE, value2);
        editor.apply();

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Fragment4());
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        age = sharedPreferences.getString(AGE, "");
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
//        etAge.setText(age);
        if (age.contains(".")){
            text = "Months";
            spinner.setSelection(2);
        }else if (!age.contains(".") && !age.isEmpty()){
            text = "Years";
            spinner.setSelection(1);
        }else {
            spinner.setSelection(0);
        }
        if (!weight.isEmpty()){
            String[] separated = weight.split("\\.");
            etKilo1.setText(separated[0]);
            etKilo2.setText(separated[1]);
        }
        if (age.contains(".")){
            String[] separated = age.split("\\.");
            etAge.setText(separated[1]);
        }else{
            etAge.setText(age);
        }

    }
}