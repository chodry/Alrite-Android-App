package com.ug.air.alrite.Activities;

import static com.ug.air.alrite.Fragments.Patient.Assess.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Assess.S4;
import static com.ug.air.alrite.Fragments.Patient.Initials.CIN;
import static com.ug.air.alrite.Fragments.Patient.Initials.PIN;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE;
import static com.ug.air.alrite.Fragments.Patient.Sex.CHOICE;
import static com.ug.air.alrite.Fragments.Patient.Sex.KILO;
import static com.ug.air.alrite.Fragments.Patient.Sex.MDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Sex.MUAC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ug.air.alrite.Adapters.DiagnosisAdapter;
import com.ug.air.alrite.Adapters.SummaryAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.Models.Diagnosis;
import com.ug.air.alrite.Models.Summary;
import com.ug.air.alrite.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiagnosisActivity extends AppCompatActivity {

    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    ImageView imageView1, imageView2;
    RecyclerView recyclerView1, recyclerView2;
    TextView txtInitials, txtAge, txtGender;
    DiagnosisAdapter diagnosisAdapter;
    SummaryAdapter summaryAdapter;
    List<Summary> summaryList;
    List<String> messages = new ArrayList<>();;
    List<Integer> messageList = new ArrayList<>();;
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        txtAge = findViewById(R.id.patient_age);
        txtGender = findViewById(R.id.patient_sex);
        txtInitials = findViewById(R.id.initials);
        imageView1 = findViewById(R.id.accordion);
        linearLayout1 = findViewById(R.id.clickable);
        linearLayout2 = findViewById(R.id.summary2);
        recyclerView1 = findViewById(R.id.recyclerView1);
        imageView2 = findViewById(R.id.accordion2);
        linearLayout3 = findViewById(R.id.clickable2);
        linearLayout4 = findViewById(R.id.summary3);
        recyclerView2 = findViewById(R.id.recyclerView2);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String initials = sharedPreferences.getString(CIN, "");
        String age = sharedPreferences.getString(AGE, "");
        String gender = sharedPreferences.getString(CHOICE, "");
        txtInitials.setText(initials);
        txtAge.setText("Age: " + age + " years");
        txtGender.setText("Gender: " + gender);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout2.getVisibility() == View.GONE){
                    linearLayout2.setVisibility(View.VISIBLE);
                    imageView1.setImageResource(R.drawable.ic_sub);
                }else{
                    linearLayout2.setVisibility(View.GONE);
                    imageView1.setImageResource(R.drawable.ic_add);
                }
            }
        });

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(DiagnosisActivity.this);
        summaryAdapter = new SummaryAdapter(buildSummaryList());
        recyclerView1.setAdapter(summaryAdapter);
        recyclerView1.setLayoutManager(layoutManager1);

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout4.getVisibility() == View.GONE){
                    linearLayout4.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.drawable.ic_sub);
                }else{
                    linearLayout4.setVisibility(View.GONE);
                    imageView2.setImageResource(R.drawable.ic_add);
                }
            }
        });

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(DiagnosisActivity.this);
        diagnosisAdapter = new DiagnosisAdapter(buildItemList());
        recyclerView2.setAdapter(diagnosisAdapter);
        recyclerView2.setLayoutManager(layoutManager2);

        diagnosisAdapter.setOnItemClickListener(new DiagnosisAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onClick2(int position) {

            }
        });
    }

    private List<Diagnosis> buildItemList() {
        List<Diagnosis> diagnosisList = new ArrayList<>();

        String aDiagnosis = sharedPreferences.getString(DIAGNOSIS, "");
        String mDiagnosis = sharedPreferences.getString(MDIAGNOSIS, "");

        addToList2(aDiagnosis);
        addToList2(mDiagnosis);

        for (int i=0; i < messages.size(); i++) {
            Diagnosis diagnosis = new Diagnosis(messages.get(i), buildSubItemList(messages.get(i)));
            diagnosisList.add(diagnosis);
        }
        return diagnosisList;
    }

    private List<Assessment> buildSubItemList(String s) {
        List<Assessment> assessmentList = new ArrayList<>();

        createList(s);

        for (int i=0; i < messageList.size(); i++) {
            Assessment assessment = new Assessment(messageList.get(i));
            assessmentList.add(assessment);
        }
        return assessmentList;
    }

    private List<Summary> buildSummaryList(){
        summaryList = new ArrayList<>();
        String pin = sharedPreferences.getString(PIN, "");
        String weight = sharedPreferences.getString(KILO, "");
        String muac = sharedPreferences.getString(MUAC, "");
        String assess = sharedPreferences.getString(S4, "");
        addToList("Parent's initials", pin);
        addToList("Child's weight", weight);
        addToList("MUAC value", muac);
        addToList("Symptoms", assess);

        return summaryList;
    }

    private void addToList(String s, String pin) {
        if (!pin.isEmpty()){
            Summary summary = new Summary(s, pin);
            summaryList.add(summary);
        }
    }

    private void addToList2(String s) {
        if (!s.isEmpty()){
            messages.add(s);
        }
    }

    private void createList(String s) {
        if (s.equals("Severe Pneumonia OR very Severe Disease")){
            messageList = Arrays.asList(R.string.first_dose, R.string.first_dose_IM,
                    R.string.IM_dosing_under1, R.string.give_diazepam_if, R.string.quick,
                    R.string.quick2, R.string.quick3, R.string.refer_urgently);
        }
        else if (s.equals("Severe Acute malnutrition")){
            messageList = Arrays.asList(R.string.muac, R.string.muac1,
                    R.string.muac2, R.string.muac3, R.string.muac4, R.string.muac5);
        }

        else if (s.equals("Moderate acute malnutrition")){
            messageList = Arrays.asList(R.string.muac6, R.string.muac7);
        }
    }
}