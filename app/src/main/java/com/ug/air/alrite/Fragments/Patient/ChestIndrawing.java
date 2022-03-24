package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Cough.CHOICE2;
import static com.ug.air.alrite.Fragments.Patient.Nasal.CHOICEGN;
import static com.ug.air.alrite.Fragments.Patient.Nasal.GNDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Oxygen.OXDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.FASTBREATHING2;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.RATE;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.RATE2;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.SECOND;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE;
import static com.ug.air.alrite.Fragments.Patient.Stridor.STDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Wheezing.CHOICE8;
import static com.ug.air.alrite.Fragments.Patient.Assess.DATE;
import static com.ug.air.alrite.Fragments.Patient.Assess.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Assess.UUIDS;
import static com.ug.air.alrite.Fragments.Patient.CoughD.DAY1;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.FASTBREATHING;
import static com.ug.air.alrite.Fragments.Patient.Wheezing.CHOICE82;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
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
import android.widget.VideoView;

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

public class ChestIndrawing extends Fragment {

    View view;
    Button back, next, btnSave, btnChest, btnContinue;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3;
    String value8 = "none";
    String diagnosis, wheezing, second;
    long day;
    TextView txtDisease, txtDefinition, txtOk, txtDiagnosis;
    LinearLayout linearLayoutDisease;
    LinearLayout linearLayout_instruction;
    VideoView videoView;
    Dialog dialog;
    RecyclerView recyclerView;
    ArrayList<Assessment> assessments;
    AssessmentAdapter assessmentAdapter;
    private static final int YES = 1;
    private static final int NO = 0;
    private static final int NOT = 2;
    public static final String CHOICE7 = "choice7";
    public static final String POINT = "point";
    public static final String POINT2 = "point2";
    public static final String CHOICE72 = "choice72";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CIDIAGNOSIS = "ciDiagnosis";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;
    String fastBreathing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_indrawing, container, false);


        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.no);
        radioButton2 = view.findViewById(R.id.yes);
        radioButton3 = view.findViewById(R.id.not);
        btnChest = view.findViewById(R.id.chest);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        second = sharedPreferences.getString(SECOND, "");

        if (second.isEmpty()){
            loadData();
            updateViews();
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case NO:
                        value8 = "No";
                        break;
                    case YES:
                        value8 = "Mild";
                        break;
                    case NOT:
                        value8 = "Moderate/Severe";
                        break;
                    default:
                        break;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value8.isEmpty()){
                    Toast.makeText(getActivity(), "Please select at least one of the options", Toast.LENGTH_SHORT).show();
                }else {
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                if (second.isEmpty()){
                    fr.replace(R.id.fragment_container, new Nasal());
                }else {
                    fr.replace(R.id.fragment_container, new Wheezing());
                }
                fr.commit();
            }
        });

        btnChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    private void saveData() {
        if (second.isEmpty()){
            editor.putString(CHOICE7, value8);
            editor.apply();

            makeAssessment();
        }else {
            calculatePoints();
            editor.putString(CHOICE72, value8);
            editor.apply();
            FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Bronchodilator3());
            fr.addToBackStack(null);
            fr.commit();
        }

    }

    private void loadData() {
        value8 = sharedPreferences.getString(CHOICE7, "");
    }

    private void updateViews() {
        if (value8.equals("Mild")){
            radioButton2.setChecked(true);
        }else if (value8.equals("No")){
            radioButton1.setChecked(true);
        }else if (value8.equals("Moderate/Severe")){
            radioButton3.setChecked(true);
        }else {
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
            radioButton3.setChecked(false);
        }
    }

    private void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.learn_popup);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        txtDefinition = dialog.findViewById(R.id.definition);
        txtDisease = dialog.findViewById(R.id.diseaseName);
        txtOk = dialog.findViewById(R.id.ok);
        linearLayoutDisease = dialog.findViewById(R.id.disease);
        videoView = dialog.findViewById(R.id.video_view);
        recyclerView = dialog.findViewById(R.id.recyclerView2);
        CardView inst = dialog.findViewById(R.id.inst);

        inst.setVisibility(View.GONE);

        txtDisease.setText("Chest Indrawing");
        txtDefinition.setText(R.string.chest_in);
        linearLayoutDisease.setBackgroundColor(getResources().getColor(R.color.green_dark));

        String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.chest_indrawing_glossary_video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void makeAssessment(){
        String cough = sharedPreferences.getString(CHOICE2, "");
        String oxDiagnosis = sharedPreferences.getString(OXDIAGNOSIS, "");
        String stDiagnosis = sharedPreferences.getString(STDIAGNOSIS, "");
        String gnDiagnosis = sharedPreferences.getString(GNDIAGNOSIS, "");
        fastBreathing = sharedPreferences.getString(FASTBREATHING, "");
        wheezing = sharedPreferences.getString(CHOICE8, "");
        String days = sharedPreferences.getString(DAY1, "");
        day = Long.parseLong(days);
        boolean b = oxDiagnosis.isEmpty() && stDiagnosis.isEmpty() && gnDiagnosis.isEmpty();
        if (b && ((cough.equals("Yes") && fastBreathing.equals("Fast Breathing")) ||
                (cough.equals("Yes") && (value8.equals("Mild") || value8.equals("Moderate/Severe"))))){

            showDialog2("pneumonia");


        }else if (b && (cough.equals("Yes") && fastBreathing.equals("Normal Breathing") &&
                value8.equals("No") && (wheezing.equals("Other abnormal breath sounds") || wheezing.equals("Normal breath sounds")))){

            showDialog2("cold");

        } else {
            if (wheezing.equals("Wheezing")  || day >= 10){
                calculatePoints();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Bronchodilator());
                fr.addToBackStack(null);
                fr.commit();
            }else {
                calculatePoints();
                editor.remove(CIDIAGNOSIS);
                editor.apply();
                startActivity(new Intent(getActivity(), DiagnosisActivity.class));
            }
        }
    }

    private void showDialog2(String disease) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.assessment_layout);
        dialog.setCancelable(true);
//        Window window = dialog.getWindow();
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        linearLayout_instruction = dialog.findViewById(R.id.diagnosis);
        txtDiagnosis = dialog.findViewById(R.id.txtDiagnosis);
        recyclerView = dialog.findViewById(R.id.recyclerView1);
        btnSave = dialog.findViewById(R.id.btnSave);
        btnContinue = dialog.findViewById(R.id.btnContinue);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments);

        List<Integer> messages = new ArrayList<>();

        if (disease.equals("pneumonia")){
            linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.moderateDiagnosisColor));
            txtDiagnosis.setText(R.string.pneumonia);

            String age = sharedPreferences.getString(AGE, "");
            float ag = Float.parseFloat(age);
            if (ag >= 0.2 && ag < 1.0){
                messages = Arrays.asList(R.string.amoxicillin2, R.string.pneumonia1, R.string.pneumonia2);
            }else if (ag >= 1.0 && ag < 3.0){
                messages = Arrays.asList(R.string.amoxicillin12, R.string.pneumonia1, R.string.pneumonia2);
            }else if (ag >= 3.0 && ag < 5.0){
                messages = Arrays.asList(R.string.amoxicillin3, R.string.pneumonia1, R.string.pneumonia2);
            }

        }else {
            linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.mildDiagnosisColor));
            txtDiagnosis.setText(R.string.cold);
            messages = Arrays.asList(R.string.cold1, R.string.cold2, R.string.cold3, R.string.cold4);
        }

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, 1300);
        diagnosis = txtDiagnosis.getText().toString();
        diagnosis = diagnosis.replace("Diagnosis: ", "");

        for (int i = 0; i < messages.size(); i++){
            Assessment assessment = new Assessment(messages.get(i));
            assessments.add(assessment);
        }
        recyclerView.setAdapter(assessmentAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(CIDIAGNOSIS, diagnosis);
                editor.apply();
                dialog.dismiss();
                calculatePoints();
                startActivity(new Intent(getActivity(), DiagnosisActivity.class));
            }
        });

        if (wheezing.equals("Wheezing") || day >= 10){
            btnContinue.setVisibility(View.VISIBLE);
        }else {
            btnContinue.setVisibility(View.GONE);
        }

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(CIDIAGNOSIS, diagnosis);
                editor.apply();
                dialog.dismiss();
                calculatePoints();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Bronchodilator());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

//        dialog.getWindow().setLayout(650, 800);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void calculatePoints() {
        String age = sharedPreferences.getString(AGE, "");
        String granting = sharedPreferences.getString(CHOICEGN, "");
        String rate = sharedPreferences.getString(RATE, "");

        float ag = Float.parseFloat(age);

        if (second.isEmpty()){
            float fast = Float.parseFloat(rate);
            int point = 0;

            if (((ag < 0.2 && fast < 60) || ((ag >= 0.2 && ag < 1.0) && fast < 50) || ((ag >= 1.0 && ag < 5.0) && fast < 40)) && value8.equals("No") && wheezing.equals("Normal breath sounds")){
                point = 0;
                String pot = String.valueOf(point);
                editor.putString(POINT, pot);
            }else if (((ag < 0.2 && (fast >= 60 && fast < 70)) || ((ag >= 0.2 && ag < 1.0) && (fast >= 50 && fast < 60)) || ((ag >= 1.0 && ag < 5.0) && (fast >= 40 && fast < 50))) && value8.equals("Mild")){
                point = 1;
                String pot = String.valueOf(point);
                editor.putString(POINT, pot);
            }else if (((ag < 0.2 && (fast >= 70 && fast < 80)) || ((ag >= 0.2 && ag < 1.0) && (fast >= 60 && fast < 70)) || ((ag >= 1.0 && ag < 5.0) && (fast >= 50 && fast < 60))) && value8.equals("Moderate/Severe") && wheezing.equals("Other abnormal breath sounds")){
                point = 2;
                String pot = String.valueOf(point);
                editor.putString(POINT, pot);
            }else if (((ag < 0.2 && fast > 80) || ((ag >= 0.2 && ag < 1.0) && fast > 70) || ((ag >= 1.0 && ag < 5.0) && fast > 60)) && !value8.equals("No") && wheezing.equals("Wheezing") && granting.equals("Yes")){
                point = 3;
                String pot = String.valueOf(point);
                editor.putString(POINT, pot);
            }else {
                editor.remove(POINT);
            }
        }else {
            String wheez = sharedPreferences.getString(CHOICE82, "");
            String rate1 = sharedPreferences.getString(RATE2, "");
            float fast2 = Float.parseFloat(rate1);
            int point2 = 0;

            if (((ag < 0.2 && fast2 < 60) || ((ag >= 0.2 && ag < 1.0) && fast2 < 50) || ((ag >= 1.0 && ag < 5.0) && fast2 < 40)) && value8.equals("No") && wheez.equals("Normal breath sounds")){
                point2 = 0;
                String pot2 = String.valueOf(point2);
                editor.putString(POINT2, pot2);
            }else if (((ag < 0.2 && (fast2 >= 60 && fast2 < 70)) || ((ag >= 0.2 && ag < 1.0) && (fast2 >= 50 && fast2 < 60)) || ((ag >= 1.0 && ag < 5.0) && (fast2 >= 40 && fast2 < 50))) && value8.equals("Mild")){
                point2 = 1;
                String pot2 = String.valueOf(point2);
                editor.putString(POINT2, pot2);
            }else if (((ag < 0.2 && (fast2 >= 70 && fast2 < 80)) || ((ag >= 0.2 && ag < 1.0) && (fast2 >= 60 && fast2 < 70)) || ((ag >= 1.0 && ag < 5.0) && (fast2 >= 50 && fast2 < 60))) && value8.equals("Moderate/Severe") && wheez.equals("Other abnormal breath sounds")){
                point2 = 2;
                String pot2 = String.valueOf(point2);
                editor.putString(POINT2, pot2);
            }else if (((ag < 0.2 && fast2 > 80) || ((ag >= 0.2 && ag < 1.0) && fast2 > 70) || ((ag >= 1.0 && ag < 5.0) && fast2 > 60)) && !value8.equals("No") && wheez.equals("Wheezing") && granting.equals("Yes")){
                point2 = 3;
                String pot2 = String.valueOf(point2);
                editor.putString(POINT2, pot2);
            }else {
                editor.remove(POINT2);
            }
        }
        editor.apply();

    }
}