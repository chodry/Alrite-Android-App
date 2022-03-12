package com.ug.air.alrite.Activities;

import static com.ug.air.alrite.Fragments.Patient.Allergies.CHOICEY2;
import static com.ug.air.alrite.Fragments.Patient.Assess.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Assess.S4;
import static com.ug.air.alrite.Fragments.Patient.Breathless.S5;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.BRONCHODILATOR;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator2.BDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator2.REASON;
import static com.ug.air.alrite.Fragments.Patient.ChestIndrawing.CHOICE7;
import static com.ug.air.alrite.Fragments.Patient.ChestIndrawing.CIDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Cough.CHOICE2;
import static com.ug.air.alrite.Fragments.Patient.Cough.NODIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.CoughD.DAY1;
import static com.ug.air.alrite.Fragments.Patient.Eczema.CHOICEX2;
import static com.ug.air.alrite.Fragments.Patient.FTouch.TOUCH;
import static com.ug.air.alrite.Fragments.Patient.HIVCare.CHOICEHC;
import static com.ug.air.alrite.Fragments.Patient.HIVStatus.CHOICE3;
import static com.ug.air.alrite.Fragments.Patient.HIVStatus.HDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Initials.CIN;
import static com.ug.air.alrite.Fragments.Patient.Initials.PIN;
import static com.ug.air.alrite.Fragments.Patient.Kerosene.ADIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Kerosene.CHOICET2;
import static com.ug.air.alrite.Fragments.Patient.Kerosene.TUDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Nasal.CHOICEGN;
import static com.ug.air.alrite.Fragments.Patient.Nasal.GNDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Oxygen.OXDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Oxygen.OXY;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.FASTBREATHING;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE;
import static com.ug.air.alrite.Fragments.Patient.Sex.CHOICE;
import static com.ug.air.alrite.Fragments.Patient.Sex.KILO;
import static com.ug.air.alrite.Fragments.Patient.Sex.MDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Sex.MUAC;
import static com.ug.air.alrite.Fragments.Patient.Smoke.CHOICET1;
import static com.ug.air.alrite.Fragments.Patient.Stridor.CHOICE6;
import static com.ug.air.alrite.Fragments.Patient.Stridor.STDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Temperature.TDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Temperature.TEMP;
import static com.ug.air.alrite.Fragments.Patient.WheezD.CHOICEX;
import static com.ug.air.alrite.Fragments.Patient.WheezY.DAY2;
import static com.ug.air.alrite.Fragments.Patient.Wheezing.CHECKSTETHO;
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
import android.widget.Toast;

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
import java.util.Collections;
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
        String noDiagnosis = sharedPreferences.getString(NODIAGNOSIS, "");
        String hDiagnosis = sharedPreferences.getString(HDIAGNOSIS, "");
        String stDiagnosis = sharedPreferences.getString(STDIAGNOSIS, "");
        String gnDiagnosis = sharedPreferences.getString(GNDIAGNOSIS, "");
        String ciDiagnosis = sharedPreferences.getString(CIDIAGNOSIS, "");
        String wDiagnosis = sharedPreferences.getString(BDIAGNOSIS, "");
        String asDiagnosis = sharedPreferences.getString(ADIAGNOSIS, "");
        String tuDiagnosis = sharedPreferences.getString(TUDIAGNOSIS, "");

        addToList2(aDiagnosis);
        addToList2(mDiagnosis);
        addToList2(tDiagnosis);
        addToList2(oxDiagnosis);
        addToList2(noDiagnosis);
        addToList2(hDiagnosis);
        addToList2(stDiagnosis);
        addToList2(gnDiagnosis);
        addToList2(ciDiagnosis);
        addToList2(wDiagnosis);
        addToList2(asDiagnosis);
        addToList2(tuDiagnosis);

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
        String cough = sharedPreferences.getString(CHOICE2, "");
        String hiv = sharedPreferences.getString(CHOICE3, "");
        String care = sharedPreferences.getString(CHOICEHC, "");
        String coughD = sharedPreferences.getString(DAY1, "");
        String fastbreathing = sharedPreferences.getString(FASTBREATHING, "");
        String stidor = sharedPreferences.getString(CHOICE6, "");
        Boolean steth = sharedPreferences.getBoolean(CHECKSTETHO, false);
        String nasal = sharedPreferences.getString(CHOICEGN, "");
        String chest = sharedPreferences.getString(CHOICE7, "");
        String bronch = sharedPreferences.getString(BRONCHODILATOR, "");
        String reason = sharedPreferences.getString(REASON, "");
        String wheezD = sharedPreferences.getString(CHOICEX, "");
        String wheezY = sharedPreferences.getString(DAY2, "");
        String breathless = sharedPreferences.getString(S5, "");
        String eczema = sharedPreferences.getString(CHOICEX2, "");
        String allergies = sharedPreferences.getString(CHOICEY2, "");
        String smoke = sharedPreferences.getString(CHOICET1, "");
        String kerosene = sharedPreferences.getString(CHOICET2, "");

        addToList("Parent's initials", pin);
        addToList("Child's weight", weight);
        addToList("MUAC value", muac);
        addToList("Symptoms", assess);
        addToList("Child Coughing", cough);
        addToList("Days coughing", coughD);
        addToList("Temperature", temp);
        addToList("Febrile to touch", feb);
        addToList("HIV Status", hiv);
        addToList("Child in HIV Care", care);
        addToList("Oxgyen Saturation", ox);
        addToList("Respiratory Rate", fastbreathing);
        addToList("Child has Stridor", stidor);
        addToList("Child Wheezing", wheez);
        addToList("Stethoscope was used", String.valueOf(steth));
        addToList("Child has grunting or nasal flaring", nasal);
        addToList("Child has chest indrawing", chest);
        addToList("Bronchodilator", bronch);
        addToList("Reason", reason);
        addToList("Child has breathing difficulty", wheezD);
        addToList("Episodes in the past year", wheezY);
        addToList("Child his breathless", breathless);
        addToList("Child has Eczema", eczema);
        addToList("Child's family has Allergies", allergies);
        addToList("Any family member smoking tobacco", smoke);
        addToList("Any family member using kerosene", kerosene);

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
        else if (s.equals("No signs of Pneumonia or Wheezing illness")){
            messageList = Arrays.asList(R.string.selected, R.string.alrite, R.string.no_anti, R.string.other_illness);
        }
        else if (s.equals("HIV risk")){
            messageList = Collections.singletonList(R.string.hiv);
        }
        else if (s.equals("Pneumonia")){
            messageList = Arrays.asList(R.string.pneumonia1, R.string.pneumonia2, R.string.pneumonia3);
        }
        else if (s.equals("Cough/Cold/No Pneumonia")){
            messageList = Arrays.asList(R.string.cold1, R.string.cold2, R.string.cold3, R.string.cold4);
        }
        else if (s.equals("Wheezing")){
            String age = sharedPreferences.getString(AGE, "");
            float ag = Float.parseFloat(age);
            if (ag < 2){
                messageList = Arrays.asList(R.string.wheez_ill1, R.string.wheez_ill2, R.string.wheez_ill3,
                        R.string.wheez_ill4, R.string.wheez_ill5, R.string.wheez_ill6);
            }else {
                messageList = Arrays.asList(R.string.wheez_ill1, R.string.wheez_ill2, R.string.wheez_ill7,
                        R.string.wheez_ill8, R.string.wheez_ill9);
            }
        }
        else if (s.equals("Asthma Risk")){
            messageList = Arrays.asList(R.string.asthma1, R.string.asthma2, R.string.asthma3);
        }
        else if (s.equals("Tuberculosis risk")){
            messageList = Arrays.asList(R.string.tuber1, R.string.tuber2);
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

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please click the EXIT button", Toast.LENGTH_SHORT).show();
    }
}