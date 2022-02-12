package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Fragment4.DATE;
import static com.ug.air.alrite.Fragments.Patient.Fragment4.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Fragment4.UUIDS;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v1.DAY2;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v2.CHOICEX;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.CHECK11;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.CHECK21;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.CHECK31;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.CHECK41;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v3.S5;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v4.CHOICEX2;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v5.CHOICEY2;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v6.CHOICET1;
import static com.ug.air.alrite.Fragments.Patient.Fragment6v7.CHOICET2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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


public class Fragment6 extends Fragment {

    View view;
    EditText etDay;
    Button back, next, btnSave;
    String day1;
    public static final String DAY1 = "day1";
    public static final String SHARED_PREFS = "sharedPrefs";
    Dialog dialog;
    RecyclerView recyclerView;
    LinearLayout linearLayout_instruction;
    TextView txtDiagnosis;
    ArrayList<Assessment> assessments;
    AssessmentAdapter assessmentAdapter;
    String diagnosis;
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_6, container, false);

        etDay = view.findViewById(R.id.days);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        etDay.requestFocus();

        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

        etDay.addTextChangedListener(textWatcher);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day1 = etDay.getText().toString();
                if (day1.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in the field before you continue", Toast.LENGTH_SHORT).show();
                }else{
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment5());
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
            day1 = etDay.getText().toString();
            if (!day1.isEmpty()){
                long dy = Long.parseLong(day1);
                if (dy == 0){
                   etDay.setError("The number of days should not be 0");
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void saveData() {

        editor.putString(DAY1, day1);
        editor.apply();

        int dt = Integer.parseInt(day1);

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        if (dt < 10) {
            fr.replace(R.id.fragment_container, new Fragment12());
        }else if (dt >= 14){
            showDialog();
        }else {
            fr.replace(R.id.fragment_container, new Fragment6v2());
        }
        fr.addToBackStack(null);
        fr.commit();

    }

    private void loadData() {
        day1 = sharedPreferences.getString(DAY1, "");
    }

    private void updateViews() {
        etDay.setText(day1);
    }

    private void deleteSharedPreferences() {
//        editor.remove(DAY2);
//        editor.remove(CHOICEX);
//        editor.remove(CHECK11);
//        editor.remove(CHECK21);
//        editor.remove(CHECK31);
//        editor.remove(CHECK41);
//        editor.remove(S5);
//        editor.remove(CHOICEX2);
//        editor.remove(CHOICEY2);
//        editor.remove(CHOICET2);
//        editor.remove(CHOICET1);
//        editor.apply();
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
        txtDiagnosis.setText(R.string.tuber);
        diagnosis = txtDiagnosis.getText().toString();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments, getActivity());

        List<Integer> messages = Arrays.asList(R.string.first_dose, R.string.tuber1, R.string.tuber2);
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

//        dialog.getWindow().setLayout(650, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    private void saveForm() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(currentTime);

        String uniqueID = UUID.randomUUID().toString();

        editor.putString(DIAGNOSIS, diagnosis);
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
        startActivity(new Intent(getActivity(), Dashboard.class));
    }

}