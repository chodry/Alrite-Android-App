package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Cough.CHOICE2;
import static com.ug.air.alrite.Fragments.Patient.Wheezing.CHOICE8;
import static com.ug.air.alrite.Fragments.Patient.Assess.DATE;
import static com.ug.air.alrite.Fragments.Patient.Assess.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Assess.UUIDS;
import static com.ug.air.alrite.Fragments.Patient.CoughD.DAY1;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.FASTBREATHING;

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
    String diagnosis, wheezing;
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
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CIDIAGNOSIS = "ciDiagnosis";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;

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

        loadData();
        updateViews();

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
                fr.replace(R.id.fragment_container, new Nasal());
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

        editor.putString(CHOICE7, value8);
        editor.apply();

        makeAssessment2();

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

    private void makeAssessment2(){
        String cough = sharedPreferences.getString(CHOICE2, "");
        String fastBreathing = sharedPreferences.getString(FASTBREATHING, "");
        wheezing = sharedPreferences.getString(CHOICE8, "");
        String days = sharedPreferences.getString(DAY1, "");
        day = Long.parseLong(days);
        if ((cough.equals("Yes") && fastBreathing.equals("Fast Breathing")) ||
                (cough.equals("Yes") && (value8.equals("Mild") || value8.equals("Moderate/Severe")))){
            showDialog2("pneumonia");
        }else if (cough.equals("Yes") && fastBreathing.equals("Normal Breathing") && value8.equals("No") && wheezing.equals("Normal breathing")){
            showDialog2("cold");
        } else {
            if (wheezing.equals("Wheezing")  || day >= 10){
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Bronchodilator());
                fr.addToBackStack(null);
                fr.commit();
            }else {
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
        assessmentAdapter = new AssessmentAdapter(assessments, getActivity());

        List<Integer> messages = new ArrayList<>();

        if (disease.equals("pneumonia")){
            linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.moderateDiagnosisColor));
            txtDiagnosis.setText(R.string.pneumonia);
            messages = Arrays.asList(R.string.pneumonia1, R.string.pneumonia2, R.string.pneumonia3);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, 1200);
        }else {
            linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.mildDiagnosisColor));
            txtDiagnosis.setText(R.string.cold);
            messages = Arrays.asList(R.string.cold1, R.string.cold2, R.string.cold3, R.string.cold4);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, 1200);
        }

        diagnosis = txtDiagnosis.getText().toString();

        for (int i = 0; i < messages.size(); i++){
            Assessment assessment = new Assessment(messages.get(i));
            assessments.add(assessment);
        }
        recyclerView.setAdapter(assessmentAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveForm();
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

    private void saveForm() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(currentTime);

        String uniqueID = UUID.randomUUID().toString();

        editor.putString(CIDIAGNOSIS, diagnosis);
        editor.putString(DATE, formattedDate);
        editor.putString(UUIDS, uniqueID);
        editor.apply();

        uniqueID = formattedDate + "_" + uniqueID;

        sharedPreferences1 = requireActivity().getSharedPreferences(uniqueID, Context.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();
        Map<String, ?> all = sharedPreferences.getAll();
        for (Map.Entry<String, ?> x : all.entrySet()) {
            if (x.getValue().getClass().equals(String.class))  editor1.putString(x.getKey(),  (String)x.getValue());
            else if (x.getValue().getClass().equals(Boolean.class)) editor1.putBoolean(x.getKey(), (Boolean)x.getValue());
        }
        editor1.commit();
        editor.clear();
        editor.commit();
        dialog.dismiss();
        startActivity(new Intent(getActivity(), DiagnosisActivity.class));
    }
}