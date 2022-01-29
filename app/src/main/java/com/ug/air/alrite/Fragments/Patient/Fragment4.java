package com.ug.air.alrite.Fragments.Patient;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Fragment4 extends Fragment {

    View view;
    Button back, next, btnSave;
    CheckBox drink, vomit, resp, convu, none;
    String s = "";
    Boolean check1, check2, check3, check4, check5;
    Dialog dialog;
    RecyclerView recyclerView;
    LinearLayout linearLayout_instruction;
    TextView txtDiagnosis;
    ArrayList<Assessment> assessments;
    AssessmentAdapter assessmentAdapter;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CHECK1 = "check1";
    public static final String CHECK2 = "check2";
    public static final String CHECK3 = "check3";
    public static final String CHECK4 = "check4";
    public static final String CHECK5 = "check5";
    public static final String S4 = "s4";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_4, container, false);

        back = view.findViewById(R.id.back);
        next = view.findViewById(R.id.next);
        drink = view.findViewById(R.id.drink);
        vomit = view.findViewById(R.id.vomit);
        none = view.findViewById(R.id.none);
        convu = view.findViewById(R.id.convu);
        resp = view.findViewById(R.id.responsive);

        loadData();
        updateViews();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedList();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment3());
                fr.commit();
            }
        });

        return view;
    }

    private void checkedList() {
        s = "";

        if(drink.isChecked()){
            s += "Unable to drink or breastfeed, ";
        }
        if(vomit.isChecked()){
            s += "Vomiting Everything, ";
        }
        if(resp.isChecked()){
            s += "Unresponsive, no awareness of surroundings, ";
        }
        if(convu.isChecked()){
            s += "Convulsions (uncontrolled jerking/ seizures), ";
        }
        if(none.isChecked()){
            s = "None of these, ";
        }
        s = s.replaceAll(", $", "");
        if (s.isEmpty()){
            Toast.makeText(getActivity(), "Choose at least one option", Toast.LENGTH_SHORT).show();
        }else {
            saveData(s);
        }

    }

    private void saveData(String s) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(CHECK1, drink.isChecked());
        editor.putBoolean(CHECK2, vomit.isChecked());
        editor.putBoolean(CHECK3, resp.isChecked());
        editor.putBoolean(CHECK4, convu.isChecked());
        editor.putBoolean(CHECK5, none.isChecked());
        editor.putString(S4, s);

        editor.apply();

        checkIfNone();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        check1 = sharedPreferences.getBoolean(CHECK1, false);
        check2 = sharedPreferences.getBoolean(CHECK2, false);
        check3 = sharedPreferences.getBoolean(CHECK3, false);
        check4 = sharedPreferences.getBoolean(CHECK4, false);
        check5 = sharedPreferences.getBoolean(CHECK5, false);
    }

    private void updateViews() {
        drink.setChecked(check1);
        vomit.setChecked(check2);
        resp.setChecked(check3);
        convu.setChecked(check4);
        none.setChecked(check5);
    }

    private void checkIfNone() {
        if (s.contains("None of these")){
            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Fragment5());
            fr.addToBackStack(null);
            fr.commit();
        }else{
            displayDialog();
        }
    }

    private void displayDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.assessment_layout);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        linearLayout_instruction = dialog.findViewById(R.id.diagnosis);
        txtDiagnosis = dialog.findViewById(R.id.txtDiagnosis);
        recyclerView = dialog.findViewById(R.id.recyclerView1);
        btnSave = dialog.findViewById(R.id.btnSave);

        linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.severeDiagnosisColor));
        txtDiagnosis.setText(R.string.severe);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments, getActivity());

        List<Integer> messages = Arrays.asList(R.string.first_dose, R.string.first_dose_IM, R.string.IM_dosing_under1, R.string.give_diazepam_if, R.string.refer_urgently);
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

        dialog.getWindow().setLayout(650, 1300);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void saveForm() {
    }

}