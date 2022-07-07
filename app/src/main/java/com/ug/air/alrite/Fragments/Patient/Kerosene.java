package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Assess.DATE;
import static com.ug.air.alrite.Fragments.Patient.Assess.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.CoughD.DAY1;
import static com.ug.air.alrite.Fragments.Patient.HIVStatus.CHOICE3;
import static com.ug.air.alrite.Fragments.Patient.WheezY.DAY2;
import static com.ug.air.alrite.Fragments.Patient.WheezD.CHOICEX;
import static com.ug.air.alrite.Fragments.Patient.Breathless.S5;
import static com.ug.air.alrite.Fragments.Patient.Eczema.CHOICEX2;
import static com.ug.air.alrite.Fragments.Patient.Allergies.CHOICEY2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.Activities.Dashboard;
import com.ug.air.alrite.Activities.DiagnosisActivity;
import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


public class Kerosene extends Fragment {

    View view;
    Button back, next, btnSave;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3;
    String value5 = "none";
    Dialog dialog, dialog2;
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NOT = 2;
    public static final String CHOICET2 = "use_kerosene";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ADIAGNOSIS = "diagnosis_10";
    public static final String TUDIAGNOSIS = "diagnosis_11";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;
    RecyclerView recyclerView;
    LinearLayout linearLayout_instruction;
    TextView txtDiagnosis;
    ArrayList<Assessment> assessments;
    AssessmentAdapter assessmentAdapter;
    String diagnosis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kerosene, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);
        radioButton3 = view.findViewById(R.id.not);

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

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

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
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Smoke());
                fr.commit();
            }
        });


        return view;
    }

    private void saveData() {

        editor.putString(CHOICET2, value5);
        editor.apply();

        makeAssessment();
    }

    private void loadData() {
        value5 = sharedPreferences.getString(CHOICET2, "");
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

    private void makeAssessment() {
        int total = 0;
        String episodes = sharedPreferences.getString(DAY2, "");
        String breathless = sharedPreferences.getString(S5, "");
        String eczema = sharedPreferences.getString(CHOICEX2, "");
        String allergies = sharedPreferences.getString(CHOICEY2, "");
        if (!episodes.isEmpty()){
            int ep = Integer.parseInt(episodes);
            if (ep >= 3){
                total = total + 1;
            }
        }
        if (!breathless.contains("None of these")){
            total = total + 1;
        }
        if (eczema.equals("Yes")){
            total = total + 1;
        }
        if (allergies.equals("Yes")){
            total = total + 1;
        }
        
        makeFinalAssement(total);
        
    }

    private void makeFinalAssement(int total) {
        String cough = sharedPreferences.getString(DAY1, "");
        long co = Long.parseLong(cough);
        String hiv = sharedPreferences.getString(CHOICE3, "");
        String dif = sharedPreferences.getString(CHOICEX, "");
        boolean yes = co > 14 || (hiv.equals("HIV-Infected") && dif.equals("Yes"));
        if (total >= 2){
            editor.putString(ADIAGNOSIS, "Asthma Risk");

        }else {
            editor.remove(ADIAGNOSIS);
        }
        if (yes){
            editor.putString(TUDIAGNOSIS, "Tuberculosis Risk");
        }else {
            editor.remove(TUDIAGNOSIS);
        }

        editor.apply();
        startActivity(new Intent(getActivity(), DiagnosisActivity.class));
    }

    private void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.assessment_layout);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        linearLayout_instruction = dialog.findViewById(R.id.diagnosis);
        txtDiagnosis = dialog.findViewById(R.id.txtDiagnosis);
        recyclerView = dialog.findViewById(R.id.recyclerView1);
        btnSave = dialog.findViewById(R.id.btnSave);
        Button btnContinue = dialog.findViewById(R.id.btnContinue);

        linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.moderateDiagnosisColor));
        txtDiagnosis.setText(R.string.asthma);
        diagnosis = txtDiagnosis.getText().toString();
        diagnosis = diagnosis.replace("Diagnosis: ", "");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments);

        List<Integer> messages = Arrays.asList(R.string.asthma1, R.string.wheez3, R.string.asthma2, R.string.asthma3);
        for (int i = 0; i < messages.size(); i++){
            Assessment assessment = new Assessment(messages.get(i));
            assessments.add(assessment);
        }
        recyclerView.setAdapter(assessmentAdapter);

        btnContinue.setVisibility(View.GONE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(ADIAGNOSIS, diagnosis);
                String cough = sharedPreferences.getString(DAY1, "");
                long co = Long.parseLong(cough);
                String hiv = sharedPreferences.getString(CHOICE3, "");
                String dif = sharedPreferences.getString(CHOICEX, "");
                if (co > 14 || (hiv.equals("HIV-Infected") && dif.equals("Yes"))){
//                    showDialog2();
                }else {
                    editor.remove(TUDIAGNOSIS);
                    startActivity(new Intent(getActivity(), DiagnosisActivity.class));
                }
                editor.apply();
                dialog.dismiss();
            }
        });

//        dialog.getWindow().setLayout(650, 800);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void showDialog2() {
        dialog2 = new Dialog(getActivity());
        dialog2.setContentView(R.layout.assessment_layout);
        dialog2.setCancelable(true);
        Window window = dialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        linearLayout_instruction = dialog2.findViewById(R.id.diagnosis);
        txtDiagnosis = dialog2.findViewById(R.id.txtDiagnosis);
        recyclerView = dialog2.findViewById(R.id.recyclerView1);
        btnSave = dialog2.findViewById(R.id.btnSave);
        Button btnContinue = dialog2.findViewById(R.id.btnContinue);

        linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.moderateDiagnosisColor));
        txtDiagnosis.setText(R.string.tuber);
        diagnosis = txtDiagnosis.getText().toString();
        diagnosis = diagnosis.replace("Diagnosis: ", "");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments);

        List<Integer> messages = Arrays.asList(R.string.tuber1, R.string.tuber2);
        for (int i = 0; i < messages.size(); i++){
            Assessment assessment = new Assessment(messages.get(i));
            assessments.add(assessment);
        }
        recyclerView.setAdapter(assessmentAdapter);

        btnContinue.setVisibility(View.GONE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(TUDIAGNOSIS, diagnosis);
                editor.apply();
                dialog2.dismiss();
                startActivity(new Intent(getActivity(), DiagnosisActivity.class));
            }
        });

//        dialog.getWindow().setLayout(650, 800);
        dialog2.getWindow().setGravity(Gravity.CENTER);
        dialog2.show();
    }


}