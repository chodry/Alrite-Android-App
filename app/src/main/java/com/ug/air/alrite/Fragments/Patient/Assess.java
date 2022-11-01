package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Sex.AGE;
import static com.ug.air.alrite.Fragments.Patient.Sex.KILO;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.Activities.DiagnosisActivity;
import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.R;
import com.ug.air.alrite.Utils.Calculations.Instructions;

import java.util.ArrayList;
import java.util.List;


public class Assess extends Fragment {

    View view;
    Button back, next, btnExit, btnContinue;
    CheckBox drink, vomit, resp, convu, none;
    String s = "";
    Boolean check1, check2, check3, check4, check5;
    Dialog dialog;
    RecyclerView recyclerView;
    LinearLayout linearLayout_instruction;
    TextView txtDiagnosis;
    ArrayList<Assessment> assessments;
    List messages;
    AssessmentAdapter assessmentAdapter;
    String diagnosis;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CHECK1 = "check1";
    public static final String CHECK2 = "check2";
    public static final String CHECK3 = "check3";
    public static final String CHECK4 = "check4";
    public static final String CHECK5 = "check5";
    public static final String DATE = "date";
    public static final String S4 = "symptoms";
    public static final String FINAL_DIAGNOSIS= "diagnosis_1";
    public static final String DIAGNOSIS = "diagnosis";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_assess, container, false);

        back = view.findViewById(R.id.back);
        next = view.findViewById(R.id.next);
        drink = view.findViewById(R.id.drink);
        vomit = view.findViewById(R.id.vomit);
        none = view.findViewById(R.id.none);
        convu = view.findViewById(R.id.convu);
        resp = view.findViewById(R.id.responsive);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

        vomit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (vomit.isChecked() ){
                    none.setChecked(false);
                    none.setSelected(false);
                    none.setEnabled(false);
                }
               else if (!resp.isChecked() && !convu.isChecked() && !drink.isChecked() && !vomit.isChecked()){
                   none.setEnabled(true);
                }
            }
        });

        convu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (convu.isChecked() ){
                    none.setChecked(false);
                    none.setSelected(false);
                    none.setEnabled(false);
                }
                else if (!resp.isChecked() && !convu.isChecked() && !drink.isChecked() && !vomit.isChecked()){
                    none.setEnabled(true);
                }
            }
        });

        drink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (drink.isChecked() ){
                    none.setChecked(false);
                    none.setSelected(false);
                    none.setEnabled(false);
                }
                else if (!resp.isChecked() && !convu.isChecked() && !drink.isChecked() && !vomit.isChecked()){
                    none.setEnabled(true);
                }
            }
        });

        resp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (resp.isChecked() ){
                    none.setChecked(false);
                    none.setSelected(false);
                    none.setEnabled(false);
                }
                else if (!resp.isChecked() && !convu.isChecked() && !drink.isChecked() && !vomit.isChecked()){
                    none.setEnabled(true);
                }
            }
        });

        none.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (none.isChecked()){
                    checked(vomit);
                    checked(drink);
                    checked(resp);
                    checked(convu);
                }else {
                    vomit.setEnabled(true);
                    drink.setEnabled(true);
                    resp.setEnabled(true);
                    convu.setEnabled(true);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedList();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Sex());
                fr.commit();
            }
        });

        return view;
    }

    private void checked(CheckBox checkBox){
        checkBox.setChecked(false);
        checkBox.setSelected(false);
        checkBox.setEnabled(false);
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
            saveData();
        }

    }

    private void saveData() {

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
            editor.remove(DIAGNOSIS);
            editor.remove(FINAL_DIAGNOSIS);
            editor.apply();
            FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Cough());
            fr.addToBackStack(null);
            fr.commit();
        }else{
            displayDialog();
        }
    }

    private void displayDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.assessment_layout);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        linearLayout_instruction = dialog.findViewById(R.id.diagnosis);
        txtDiagnosis = dialog.findViewById(R.id.txtDiagnosis);
        recyclerView = dialog.findViewById(R.id.recyclerView1);
        btnExit = dialog.findViewById(R.id.btnSave);
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
        String weight = sharedPreferences.getString(KILO, "");
        int ag = Integer.parseInt(age);

        Instructions instructions = new Instructions();
        messages = instructions.GetInstructions(ag, weight, s);

        for (int i = 0; i < messages.size(); i++){
            Assessment assessment = new Assessment((Integer) messages.get(i));
            assessments.add(assessment);
        }
        recyclerView.setAdapter(assessmentAdapter);
        
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDiagnosis();
                editor.putString(DIAGNOSIS, diagnosis);
                editor.apply();
                dialog.dismiss();
//                Toast.makeText(getActivity(), diagnosis, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), DiagnosisActivity.class));
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDiagnosis();
                editor.putString(DIAGNOSIS, diagnosis);
                editor.apply();
                dialog.dismiss();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Cough());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

//        dialog.getWindow().setLayout(650, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void finalDiagnosis() {
        editor.putString(FINAL_DIAGNOSIS, "Severe Pneumonia OR very Severe Disease");
        editor.apply();
    }

}