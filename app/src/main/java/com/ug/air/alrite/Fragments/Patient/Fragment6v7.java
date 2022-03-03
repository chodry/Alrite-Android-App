package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Assess.DATE;
import static com.ug.air.alrite.Fragments.Patient.Assess.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Assess.UUIDS;
import static com.ug.air.alrite.Fragments.Patient.CoughD.DAY1;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v1.DAY2;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v2.CHOICEX;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.S5;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v4.CHOICEX2;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v5.CHOICEY2;

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


public class Fragment6v7 extends Fragment {

    View view;
    Button back, next, btnSave;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3;
    String value5 = "none";
    Dialog dialog;
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NOT = 2;
    public static final String CHOICET2 = "choiceT2";
    public static final String SHARED_PREFS = "sharedPrefs";
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
        view = inflater.inflate(R.layout.fragment_6v7, container, false);

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
                fr.replace(R.id.fragment_container, new Fragment6v6());
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
        String cough = sharedPreferences.getString(DAY1, "");
        int co = Integer.parseInt(cough);
        if (co > 10 && co < 14){
            total = total + 1;
        }
        String episodes = sharedPreferences.getString(DAY2, "");
        int ep = Integer.parseInt(episodes);
        if (ep > 3){
            total = total + 1;
        }
        String wheezing = sharedPreferences.getString(CHOICEX, "");
        String breathe = sharedPreferences.getString(S5, "");
        if (wheezing.equals("Yes") || !breathe.contains("None of these")){
            total = total + 1;
        }
        String hist1 = sharedPreferences.getString(CHOICEX2, "");
        String hist2 = sharedPreferences.getString(CHOICEY2, "");
        if (hist1.equals("Yes") || hist2.equals("Yes")){
            total = total + 1;
        }
        if (total >= 3){
            showDialog();
        }else {
            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new RRCounter());
            fr.addToBackStack(null);
            fr.commit();
        }
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

        linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.severeDiagnosisColor));
        txtDiagnosis.setText(R.string.asthma);
        diagnosis = txtDiagnosis.getText().toString();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments, getActivity());

        List<Integer> messages = Arrays.asList(R.string.asthma1, R.string.asthma2, R.string.asthma3);
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

//        dialog.getWindow().setLayout(650, 800);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void saveForm() {
        editor.putString(DIAGNOSIS, diagnosis);

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(currentTime);

        String uniqueID = UUID.randomUUID().toString();

        editor.putString(DATE, formattedDate);
        editor.putString(UUIDS, uniqueID);
        editor.apply();

        uniqueID = formattedDate + "_" + uniqueID;
        sharedPreferences1 = Objects.requireNonNull(getActivity()).getSharedPreferences(uniqueID, Context.MODE_PRIVATE);
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
        startActivity(new Intent(getActivity(), Dashboard.class));
    }

}