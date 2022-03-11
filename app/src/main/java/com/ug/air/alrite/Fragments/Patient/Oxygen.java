package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Assess.S4;
import static com.ug.air.alrite.Fragments.Patient.FTouch.TOUCH;
import static com.ug.air.alrite.Fragments.Patient.HIVCare.CHOICEHC;

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
                    etDay.setError("The minimum accepted value is 50");
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

        List<Integer> messages = Arrays.asList(R.string.first_dose, R.string.first_dose_IM,
                R.string.IM_dosing_under1, R.string.give_diazepam_if,
                R.string.low_oxygen, R.string.quick, R.string.quick2, R.string.quick3,
                R.string.refer_urgently);
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