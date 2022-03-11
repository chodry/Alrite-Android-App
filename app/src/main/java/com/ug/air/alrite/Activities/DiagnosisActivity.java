package com.ug.air.alrite.Activities;

import static com.ug.air.alrite.Fragments.Patient.Assess.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Assess.S4;
import static com.ug.air.alrite.Fragments.Patient.FTouch.TOUCH;
import static com.ug.air.alrite.Fragments.Patient.Initials.CIN;
import static com.ug.air.alrite.Fragments.Patient.Initials.PIN;
import static com.ug.air.alrite.Fragments.Patient.Oxygen.OXDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Oxygen.OXY;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE;
import static com.ug.air.alrite.Fragments.Patient.Sex.CHOICE;
import static com.ug.air.alrite.Fragments.Patient.Sex.KILO;
import static com.ug.air.alrite.Fragments.Patient.Sex.MDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Sex.MUAC;
import static com.ug.air.alrite.Fragments.Patient.Temperature.TDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Temperature.TEMP;
import static com.ug.air.alrite.Fragments.Patient.Wheezing.CHOICE8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ug.air.alrite.Adapters.DiagnosisAdapter;
import com.ug.air.alrite.Adapters.SummaryAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.Models.Diagnosis;
import com.ug.air.alrite.Models.Summary;
import com.ug.air.alrite.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class DiagnosisActivity extends AppCompatActivity {

    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    Button btnExit;
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
    public static final String DATE = "date";
    public static final String UUIDS = "uuid";

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
        btnExit = findViewById(R.id.btnExit);
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

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveForm();
            }
        });
    }

    private List<Diagnosis> buildItemList() {
        List<Diagnosis> diagnosisList = new ArrayList<>();

        String aDiagnosis = sharedPreferences.getString(DIAGNOSIS, "");
        String mDiagnosis = sharedPreferences.getString(MDIAGNOSIS, "");
        String tDiagnosis = sharedPreferences.getString(TDIAGNOSIS, "");
        String oxDiagnosis = sharedPreferences.getString(OXDIAGNOSIS, "");

        addToList2(aDiagnosis);
        addToList2(mDiagnosis);
        addToList2(tDiagnosis);
        addToList2(oxDiagnosis);

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
        String temp = sharedPreferences.getString(TEMP, "");
        String feb = sharedPreferences.getString(TOUCH, "");
        String ox = sharedPreferences.getString(OXY, "");
        String wheez = sharedPreferences.getString(CHOICE8, "");
        addToList("Parent's initials", pin);
        addToList("Child's weight", weight);
        addToList("MUAC value", muac);
        addToList("Symptoms", assess);
        addToList("Temperature", temp);
        addToList("Febrile to touch", feb);
        addToList("Oxgyen Saturation", ox);
        addToList("Child Wheezing", wheez);

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
        else if (s.equals("Fever without danger signs")){
            messageList = Arrays.asList(R.string.febril3, R.string.febril4, R.string.febril5,
                    R.string.febril6, R.string.febril7);
        }
        else if (s.equals("Very severe febrile illness")){
            messageList = Arrays.asList(R.string.febril1, R.string.febril2);
        }
    }

    private void saveForm() {

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(currentTime);

        String uniqueID = UUID.randomUUID().toString();

        editor.putString(DATE, formattedDate);
        editor.putString(UUIDS, uniqueID);
        editor.apply();

        uniqueID = formattedDate + "_" + uniqueID;
        sharedPreferences1 = getSharedPreferences(uniqueID, Context.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();
        Map<String, ?> all = sharedPreferences.getAll();
        for (Map.Entry<String, ?> x : all.entrySet()) {
            if (x.getValue().getClass().equals(String.class))  editor1.putString(x.getKey(),  (String)x.getValue());
            else if (x.getValue().getClass().equals(Boolean.class)) editor1.putBoolean(x.getKey(), (Boolean)x.getValue());
        }
        editor1.commit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(DiagnosisActivity.this, Dashboard.class));
    }
}