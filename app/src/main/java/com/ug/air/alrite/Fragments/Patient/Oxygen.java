package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Assess.S4;
import static com.ug.air.alrite.Fragments.Patient.FTouch.TOUCH;
import static com.ug.air.alrite.Fragments.Patient.HIVCare.CHOICEHC;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Oxygen extends Fragment {

    View view;
    EditText etDay;
    Button back, next, btnSave, btSkip,btnContinue,btnContinue2;
    String oxy;
    public static final String OXY = "oxy";
    public static final String OXDIAGNOSIS = "oxDiagnosis";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;
    long percent = 0;
    Dialog dialog, dialog2;
    RecyclerView recyclerView;
    LinearLayout linearLayout_instruction;
    TextView txtDiagnosis, txtOxygen;
    ArrayList<Assessment> assessments;
    AssessmentAdapter assessmentAdapter;
    String diagnosis;
    List<Integer> messages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_oxygen, container, false);

        etDay = view.findViewById(R.id.days);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        btSkip = view.findViewById(R.id.skip);

        etDay.requestFocus();

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

        etDay.addTextChangedListener(textWatcher);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oxy = etDay.getText().toString();
                if (oxy.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in the field before you continue", Toast.LENGTH_SHORT).show();
                }else{
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String touch = sharedPreferences.getString(TOUCH, "");
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                if (touch.isEmpty()){
                    fr.replace(R.id.fragment_container, new Temperature());
                }else {
                    fr.replace(R.id.fragment_container, new FTouch());
                }
                fr.commit();
            }
        });

        btSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove(OXDIAGNOSIS);
                editor.remove(OXY);
                editor.apply();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new RRCounter());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            oxy = etDay.getText().toString();
            if (!oxy.isEmpty()){
                long dy = Long.parseLong(oxy);
                if (dy < 50){
                    etDay.setError("The minimum accepted value is 80");
                }else if (dy > 100){
                    etDay.setError("The maximum accepted value is 100");
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void saveData() {

        editor.putString(OXY, oxy);
        editor.apply();

        percent = Integer.parseInt(oxy);
        if (percent >= 92){
            nextInterface();
        }else if (percent > 89 && percent < 92){
            showDialog2();
        } else {
            showDialog();
        }
    }

    private void loadData() {
        oxy = sharedPreferences.getString(OXY, "");
    }

    private void updateViews() {
        etDay.setText(oxy);
    }

    private void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.assessment_layout);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, 1200);

        linearLayout_instruction = dialog.findViewById(R.id.diagnosis);
        txtDiagnosis = dialog.findViewById(R.id.txtDiagnosis);
        recyclerView = dialog.findViewById(R.id.recyclerView1);
        btnSave = dialog.findViewById(R.id.btnSave);
        btnContinue = dialog.findViewById(R.id.btnContinue);

        linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.severeDiagnosisColor));
        txtDiagnosis.setText(R.string.severe);
        diagnosis = txtDiagnosis.getText().toString();
        diagnosis = diagnosis.replace("Diagnosis: ", "");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments);

        String age = sharedPreferences.getString(AGE, "");
        String s = sharedPreferences.getString(S4, "");
        float ag = Float.parseFloat(age);
        if (ag >= 0.2 && ag < 0.4){
            if (s.contains("Convulsions")){
                messages = Arrays.asList(R.string.first_dose, R.string.ampicilin2,
                        R.string.ampicilin0, R.string.gentamicin2, R.string.gentamicin0,
                        R.string.convulsions, R.string.diazepam2, R.string.convulsions1,
                        R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                        R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                        R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                        R.string.other8, R.string.refer_urgently);
            }else{
                messages = Arrays.asList(R.string.first_dose, R.string.ampicilin2,
                        R.string.ampicilin0, R.string.gentamicin2, R.string.gentamicin0,
                        R.string.convulsions, R.string.diazepam2, R.string.other1, R.string.other2, R.string.other3,
                        R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                        R.string.other8, R.string.refer_urgently);
            }
        }else if (ag >= 0.4 && ag < 1.0){
            if (s.contains("Convulsions")){
                messages = Arrays.asList(R.string.first_dose, R.string.ampicilin4,
                        R.string.ampicilin0, R.string.gentamicin4, R.string.gentamicin0,
                        R.string.convulsions, R.string.diazepam4, R.string.convulsions1,
                        R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                        R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                        R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                        R.string.other8, R.string.refer_urgently);
            }else{
                messages = Arrays.asList(R.string.first_dose, R.string.ampicilin4,
                        R.string.ampicilin0, R.string.gentamicin4, R.string.gentamicin0,
                        R.string.convulsions, R.string.diazepam4, R.string.other1, R.string.other2, R.string.other3,
                        R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                        R.string.other8, R.string.refer_urgently);
            }
        }else if (ag >= 1.0 && ag < 3.0){
            if (s.contains("Convulsions")){
                messages = Arrays.asList(R.string.first_dose, R.string.ampicilin12,
                        R.string.ampicilin0, R.string.gentamicin12, R.string.gentamicin0,
                        R.string.convulsions, R.string.diazepam12, R.string.convulsions1,
                        R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                        R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                        R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                        R.string.other8, R.string.refer_urgently);
            }else{
                messages = Arrays.asList(R.string.first_dose, R.string.ampicilin12,
                        R.string.ampicilin0, R.string.gentamicin12, R.string.gentamicin0,
                        R.string.convulsions, R.string.diazepam12, R.string.other1, R.string.other2, R.string.other3,
                        R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                        R.string.other8, R.string.refer_urgently);
            }

        }else if (ag >= 3.0){
            if (s.contains("Convulsions")){
                messages = Arrays.asList(R.string.first_dose, R.string.ampicilin3,
                        R.string.ampicilin0, R.string.gentamicin3, R.string.gentamicin0,
                        R.string.convulsions, R.string.diazepam3, R.string.convulsions1,
                        R.string.convulsions2, R.string.convulsions3, R.string.convulsions4,
                        R.string.convulsions5, R.string.other1, R.string.other2, R.string.other3,
                        R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                        R.string.other8, R.string.refer_urgently);
            }else{
                messages = Arrays.asList(R.string.first_dose, R.string.ampicilin3,
                        R.string.ampicilin0, R.string.gentamicin3, R.string.gentamicin0,
                        R.string.convulsions, R.string.diazepam3, R.string.other1, R.string.other2, R.string.other3,
                        R.string.other4, R.string.other5, R.string.other6, R.string.other7,
                        R.string.other8, R.string.refer_urgently);
            }

        }

        for (int i = 0; i < messages.size(); i++){
            Assessment assessment = new Assessment(messages.get(i));
            assessments.add(assessment);
        }
        recyclerView.setAdapter(assessmentAdapter);

        btnSave.setVisibility(View.GONE);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(OXDIAGNOSIS, diagnosis);
                editor.apply();
                dialog.dismiss();
                nextInterface();
            }
        });

//        dialog.getWindow().setLayout(650, 800);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void showDialog2() {
        dialog2 = new Dialog(getActivity());
        dialog2.setContentView(R.layout.oxygen);
        dialog2.setCancelable(true);

        txtOxygen = dialog2.findViewById(R.id.oxygen);
        btnContinue2 = dialog2.findViewById(R.id.ContinueButton);

        txtOxygen.setText(oxy + "%");

        btnContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove(OXDIAGNOSIS);
                editor.apply();
                dialog2.dismiss();
                nextInterface();
            }
        });

        dialog2.show();

    }

    private void nextInterface(){
        String assess = sharedPreferences.getString(S4, "");

        FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
        if (!assess.equals("None of these")){
            fr.replace(R.id.fragment_container, new Wheezing());
        }else {
            fr.replace(R.id.fragment_container, new RRCounter());
        }
        fr.addToBackStack(null);
        fr.commit();
    }
}