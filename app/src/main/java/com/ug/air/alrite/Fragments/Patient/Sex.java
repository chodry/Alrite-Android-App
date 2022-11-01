package com.ug.air.alrite.Fragments.Patient;

import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.R;
import com.ug.air.alrite.Utils.XML.ItemFactory;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Objects;


public class Sex extends Fragment {
    View view;
    EditText etYears, etKilo1, etKilo2, etMonths, etMuac;
    Button back, next;
    LinearLayout linearLayout;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    String age, age2, weight, text, kg1, fileName, months, years, muac, diagnosis, score, message;
    Spinner spinner;
    Dialog dialog;
    String value2 = "none";
    private static final int YES = 0;
    private static final int NO = 1;
    public static final String MDIAGNOSIS = "diagnosis_2";
    public static final String AGE = "age";
    public static final String AGE2 = "age2";
    public static final String KILO = "weight";
    public static final String MUAC = "muac";
    public static final String CHOICE = "gender";
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
        linearLayout = view.findViewById(R.id.linearMUAC);

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


        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                years = etYears.getText().toString();
                months = etMonths.getText().toString();
                kg1 = etKilo1.getText().toString();
                muac = etMuac.getText().toString();
//                kg2 = etKilo2.getText().toString();

                if (value2.equals("none") || years.isEmpty() || months.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    int year = Integer.parseInt(years);
                    int ag = (year*12) + Integer.parseInt(months);
                    age = String.valueOf(ag);
                    age2 = years + "." + months;
                    weight = kg1;
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
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
                    next.setEnabled(false);
                }else if (k1 < 0.5){
                    etKilo1.setError("The minimum kilograms for a child has to be 0.5kgs");
                    next.setEnabled(false);
                }else {
                    next.setEnabled(true);
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
                    next.setEnabled(false);
                }else if (k1 < 9.0){
                    etMuac.setError("The minimum value for MUAC has to be 9.0cm");
                    next.setEnabled(false);
                }else {
                    next.setEnabled(true);
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
                if (yr > 4){
                    etYears.setError("The maximum number of years should be 4");
                    next.setEnabled(false);
                }else {
                    next.setEnabled(true);
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
                int mt = Integer.parseInt(months);
                if (mt > 11){
                    etMonths.setError("The maximum number of months should be 11");
                    next.setEnabled(false);
                }else {
                    next.setEnabled(true);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void saveData() {
        diagnosis = "";
        if(!weight.isEmpty()){
            editor.putString(KILO, weight);
            editor.apply();
            float we = Float.parseFloat(weight);
            ItemFactory itemFactory = new ItemFactory();
            try {
                if (value2.equals("Male")){
                    score = itemFactory.GetMaleItem(requireActivity(), age, we);
                }else{
                    score = itemFactory.GetFemaleItem(requireActivity(), age, we);
                }
                makeDecisions();

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            editor.remove(KILO);
            moveToNext();
        }

    }

    private void loadData() {
        age = sharedPreferences.getString(AGE, "");
        age2 = sharedPreferences.getString(AGE2, "");
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

        if (!age2.isEmpty()) {
            String[] separated = age2.split("\\.");
            etYears.setText(separated[0]);
            etMonths.setText(separated[1]);
        }
    }

    private void makeDecisions() {
        if (score.equals("acceptable")){
            moveToNext();
        }else if (score.equals("above 2")){
            message = "This child’s weight is greater than 2 SD, above average. Are you sure you entered the right weight?";
            diagnosis = "none";
            showDialog(message);
        }else if (score.equals("below 2")){
            message = "This child’s weight is less than 2 SD, below average. The child may have Moderate Acute Malnutrition.. Are you sure you entered the right weight?";
            diagnosis = "Moderate acute malnutrition";
            showDialog(message);
        }else {
            message = "This child’s weight is less than 3 SD, below average. The child may have Severe Acute Malnutrition.. Are you sure you entered the right weight?";
            diagnosis = "Severe acute malnutrition";
            showDialog(message);
        }
    }

    private void showDialog(String message) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.assess);
        dialog.setCancelable(true);

        TextView txtMessage = dialog.findViewById(R.id.message);
        Button btnSave = dialog.findViewById(R.id.ContinueButton);
        Button btnNo = dialog.findViewById(R.id.cancel);

        btnSave.setText("Yes");
        btnNo.setVisibility(View.VISIBLE);

        txtMessage.setText(message);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (!diagnosis.equals("none")){
                    editor.putString(MDIAGNOSIS, diagnosis);
                    editor.apply();
                }
                moveToNext();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                etKilo1.setText("");
                etKilo1.requestFocus();
                Toast.makeText(getActivity(), "Please check your scale and enter the weight again", Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }

    private void moveToNext() {
        if (!muac.isEmpty()){
            editor.putString(MUAC, muac);
            if (diagnosis.isEmpty() || diagnosis.equals("none")){
                float mw = Float.parseFloat(muac);
                if (mw < 11.5){
                    diagnosis = "Severe Acute malnutrition";
                }else if (mw >= 11.5 && mw <= 12.4){
                    diagnosis = "Moderate acute malnutrition";
                }else {
                    diagnosis = "none";
                }

                if (!diagnosis.equals("none")){
                    editor.putString(MDIAGNOSIS, diagnosis);
                }
            }
        }

        editor.putString(AGE, age);
        editor.putString(AGE2, age2);
        editor.putString(CHOICE, value2);
        editor.apply();

        FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Assess());
        fr.addToBackStack(null);
        fr.commit();
    }
}