package com.ug.air.alrite.Activities;

import static com.ug.air.alrite.Activities.FinalActivity.S6;
import static com.ug.air.alrite.Activities.FinalActivity.S7;
import static com.ug.air.alrite.Activities.PatientActivity.INCOMPLETE;
import static com.ug.air.alrite.Fragments.Patient.Allergies.CHOICEY2;
import static com.ug.air.alrite.Fragments.Patient.Assess.DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Assess.FINAL_DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Assess.S4;
import static com.ug.air.alrite.Fragments.Patient.Breathless.S5;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.BRONCHODILATOR;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.DATE;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.DURATION;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.FILENAME;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.USERNAME;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.UUIDS;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator2.BDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator2.REASON;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator3.B3DIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator3.BRONC;
import static com.ug.air.alrite.Fragments.Patient.ChestIndrawing.CHOICE7;
import static com.ug.air.alrite.Fragments.Patient.ChestIndrawing.CHOICE72;
import static com.ug.air.alrite.Fragments.Patient.ChestIndrawing.CIDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.ChestIndrawing.POINT;
import static com.ug.air.alrite.Fragments.Patient.ChestIndrawing.POINT2;
import static com.ug.air.alrite.Fragments.Patient.Cough.CHOICE2;
import static com.ug.air.alrite.Fragments.Patient.Cough.NODIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.CoughD.DAY1;
import static com.ug.air.alrite.Fragments.Patient.Eczema.CHOICEX2;
import static com.ug.air.alrite.Fragments.Patient.FTouch.FTDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.FTouch.TOUCH;
import static com.ug.air.alrite.Fragments.Patient.HIVCare.CHOICEHC;
import static com.ug.air.alrite.Fragments.Patient.HIVStatus.CHOICE3;
import static com.ug.air.alrite.Fragments.Patient.HIVStatus.HDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Initials.CIN;
import static com.ug.air.alrite.Fragments.Patient.Initials.INITIAL_DATE;
import static com.ug.air.alrite.Fragments.Patient.Initials.PIN;
import static com.ug.air.alrite.Fragments.Patient.Kerosene.ADIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Kerosene.CHOICET2;
import static com.ug.air.alrite.Fragments.Patient.Kerosene.TUDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Nasal.CHOICEGN;
import static com.ug.air.alrite.Fragments.Patient.Nasal.GNDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Oxygen.OXDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Oxygen.OXY;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.FASTBREATHING;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.FASTBREATHING2;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.INITIAL_DATE_2;
import static com.ug.air.alrite.Fragments.Patient.RRCounter.SECOND;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE2;
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
import static com.ug.air.alrite.Fragments.Patient.Wheezing.CHOICE82;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Adapters.DiagnosisAdapter;
import com.ug.air.alrite.Adapters.SummaryAdapter;
import com.ug.air.alrite.BuildConfig;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.Models.Diagnosis;
import com.ug.air.alrite.Models.Summary;
import com.ug.air.alrite.R;
import com.ug.air.alrite.Utils.Calculations.Instructions;
import com.ug.air.alrite.Utils.Credentials;

import java.io.File;
import java.text.ParseException;
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
import java.util.concurrent.TimeUnit;

public class DiagnosisActivity extends AppCompatActivity {

    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    Button btnExit, btnExit2, btnContinue, btnSave;
    ImageView imageView1, imageView2;
    RecyclerView recyclerView1, recyclerView2;
    TextView txtInitials, txtAge, txtGender;
    DiagnosisAdapter diagnosisAdapter;
    SummaryAdapter summaryAdapter;
    List<Summary> summaryList;
    List<String> messages = new ArrayList<>();;
    List messageList = new ArrayList<>();;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String DATE_2 = "end_date_2";
    public static final String PENDING = "pending";
    public static final String DURATION_2 = "duration_2";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;
    String age, uniqueID, age2, folder, value;
    float ag;
    Dialog dialog;
    RecyclerView recyclerView;
    LinearLayout linearLayout_instruction;
    TextView txtDiagnosis;
    ArrayList<Assessment> assessments;
    List messages2;
    AssessmentAdapter assessmentAdapter;

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
        btnSave = findViewById(R.id.btnExit2);
        recyclerView2 = findViewById(R.id.recyclerView2);

        Intent intent = getIntent();
        if (intent.hasExtra("filename")){
            folder = intent.getExtras().getString("filename");
            sharedPreferences = getSharedPreferences(folder, Context.MODE_PRIVATE);

            String pending = sharedPreferences.getString(PENDING, "");
//            String incomplete = sharedPreferences.getString(INCOMPLETE, "");

            if (pending.equals("pending")){
                btnSave.setVisibility(View.GONE);
                btnExit.setVisibility(View.VISIBLE);
            } else{
                btnSave.setVisibility(View.GONE);
                btnExit.setVisibility(View.GONE);
            }

        }else{
            sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String bron = sharedPreferences.getString(BRONCHODILATOR, "");
            String fin = sharedPreferences.getString(BRONC, "");
            if (bron.equals("Bronchodialtor Given") && fin.isEmpty()){
                btnSave.setVisibility(View.GONE);
            }else {
                btnSave.setVisibility(View.VISIBLE);
            }
            btnExit.setVisibility(View.VISIBLE);
        }

        editor = sharedPreferences.edit();

        String initials = sharedPreferences.getString(CIN, "");
        age2 = sharedPreferences.getString(AGE, "");
        age = sharedPreferences.getString(AGE2, "");
        String[] split = age.split("\\.");
        ag = Float.parseFloat(age2);
        String gender = sharedPreferences.getString(CHOICE, "");
        txtInitials.setText(initials);
        txtAge.setText("Age: " + split[0] + " years and " + split[1] + " months");
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
                Diagnosis diagnosis = buildItemList().get(position);
                String dia = diagnosis.getDiagnosis();
                showInstructions(dia);
//                Toast.makeText(DiagnosisActivity.this, dia, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick2(int position) {

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "not pending";
                saveForm();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = "pending";
                saveForm();
            }
        });
    }

    private void showInstructions(String dia) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.assessment_layout);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        linearLayout_instruction = dialog.findViewById(R.id.diagnosis);
        txtDiagnosis = dialog.findViewById(R.id.txtDiagnosis);
        recyclerView = dialog.findViewById(R.id.recyclerView1);
        btnExit2 = dialog.findViewById(R.id.btnSave);
        btnContinue = dialog.findViewById(R.id.btnContinue);

        linearLayout_instruction.setBackgroundColor(getResources().getColor(R.color.severeDiagnosisColor));
        txtDiagnosis.setText("Diagnosis: " + dia);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Assessment> assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments);

//        messages2 = createList(dia);
        createList(dia);

        for (int i = 0; i < messageList.size(); i++){
            Assessment assessment = new Assessment((Integer) messageList.get(i));
            assessments.add(assessment);
        }
        recyclerView.setAdapter(assessmentAdapter);

        btnExit2.setVisibility(View.GONE);
        btnContinue.setText("Close");

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();

    }

    private List<Diagnosis> buildItemList() {
        List<Diagnosis> diagnosisList = new ArrayList<>();

        String mDiagnosis = sharedPreferences.getString(MDIAGNOSIS, "");
        String oneDiagnosis = sharedPreferences.getString(FINAL_DIAGNOSIS, "");
        String tDiagnosis = sharedPreferences.getString(TDIAGNOSIS, "");
//        String oxDiagnosis = sharedPreferences.getString(OXDIAGNOSIS, "");
        String noDiagnosis = sharedPreferences.getString(NODIAGNOSIS, "");
        String hDiagnosis = sharedPreferences.getString(HDIAGNOSIS, "");
//        String stDiagnosis = sharedPreferences.getString(STDIAGNOSIS, "");
//        String gnDiagnosis = sharedPreferences.getString(GNDIAGNOSIS, "");
        String ciDiagnosis = sharedPreferences.getString(CIDIAGNOSIS, "");
        String wDiagnosis = sharedPreferences.getString(BDIAGNOSIS, "");
        String asDiagnosis = sharedPreferences.getString(ADIAGNOSIS, "");
        String tuDiagnosis = sharedPreferences.getString(TUDIAGNOSIS, "");
        String b3Diagnosis = sharedPreferences.getString(B3DIAGNOSIS, "");
        String ftDiagnosis = sharedPreferences.getString(FTDIAGNOSIS, "");

//        if (!aDiagnosis.isEmpty() || !oxDiagnosis.isEmpty() || !stDiagnosis.isEmpty() || !gnDiagnosis.isEmpty()){
//            addToList2("Severe Pneumonia OR very Severe Disease");
//        }

        addToList2(oneDiagnosis);
        addToList2(mDiagnosis);
        addToList2(tDiagnosis);
//        addToList2(oxDiagnosis);
        addToList2(noDiagnosis);
        addToList2(hDiagnosis);
//        addToList2(stDiagnosis);
//        addToList2(gnDiagnosis);
        addToList2(ciDiagnosis);
        addToList2(wDiagnosis);
        addToList2(asDiagnosis);
        addToList2(tuDiagnosis);
        addToList2(b3Diagnosis);
        addToList2(ftDiagnosis);


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
            Assessment assessment = new Assessment((Integer) messageList.get(i));
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
        String wheez2 = sharedPreferences.getString(CHOICE82, "");
        String cough = sharedPreferences.getString(CHOICE2, "");
        String hiv = sharedPreferences.getString(CHOICE3, "");
        String care = sharedPreferences.getString(CHOICEHC, "");
        String coughD = sharedPreferences.getString(DAY1, "");
        String fastbreathing = sharedPreferences.getString(FASTBREATHING, "");
        String stidor = sharedPreferences.getString(CHOICE6, "");
//        Boolean steth = sharedPreferences.getBoolean(CHECKSTETHO, false);
        String nasal = sharedPreferences.getString(CHOICEGN, "");
        String chest = sharedPreferences.getString(CHOICE7, "");
        String chest2 = sharedPreferences.getString(CHOICE72, "");
        String bronch = sharedPreferences.getString(BRONCHODILATOR, "");
        String reason = sharedPreferences.getString(REASON, "");
        String wheezD = sharedPreferences.getString(CHOICEX, "");
        String wheezY = sharedPreferences.getString(DAY2, "");
        String breathless = sharedPreferences.getString(S5, "");
        String eczema = sharedPreferences.getString(CHOICEX2, "");
        String allergies = sharedPreferences.getString(CHOICEY2, "");
        String smoke = sharedPreferences.getString(CHOICET1, "");
        String kerosene = sharedPreferences.getString(CHOICET2, "");
        String fastbreathing2 = sharedPreferences.getString(FASTBREATHING2, "");
        String better = sharedPreferences.getString(BRONC, "");
        String point1 = sharedPreferences.getString(POINT, "");
        String point2 = sharedPreferences.getString(POINT2, "");
        String diagnosis = sharedPreferences.getString(S7, "");
        String treatment = sharedPreferences.getString(S6, "");

        addToList("Parent's initials", pin);
        addToList("Child's weight", weight);
        addToList("MUAC value", muac);
        addToList("Danger Signs", assess);
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
//        addToList("Stethoscope was used", String.valueOf(steth));
        addToList("Child has grunting or nasal flaring", nasal);
        addToList("Child has chest indrawing", chest);
        addToList("Respiratory score", point1);
        addToList("Bronchodilator", bronch);
        addToList("Reason", reason);
        addToList("Respiratory Rate (After bronchodilator)", fastbreathing2);
        addToList("Child Wheezing", wheez2);
        addToList("Child has chest indrawing", chest2);
        addToList("Child's breathing after bronchodilator", better);
        addToList("Respiratory score 2", point2);
        addToList("Child has breathing difficulty", wheezD);
        addToList("Episodes in the past year", wheezY);
        addToList("Child his breathless", breathless);
        addToList("Child has Eczema", eczema);
        addToList("Child's family has Allergies", allergies);
        addToList("Any family member smoking tobacco", smoke);
        addToList("Any family member using kerosene", kerosene);
        addToList("Clinician's diagnosis", diagnosis);
        addToList("Clinician's treatment", treatment);

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
        String weight = sharedPreferences.getString(KILO, "");
        int ag = Integer.parseInt(age2);

        if (s.equals("Severe Pneumonia OR very Severe Disease")){
            String st = sharedPreferences.getString(S4, "");
            Instructions instructions = new Instructions();
            messageList = instructions.GetInstructions(ag, weight, st);
        }
        else if (s.equals("Severe acute malnutrition")){
            messageList = Arrays.asList(R.string.muac, R.string.muac1,
                    R.string.muac2, R.string.muac3, R.string.muac4, R.string.muac5);
        }
        else if (s.equals("Moderate acute malnutrition")){
            messageList = Arrays.asList(R.string.muac6, R.string.muac7);
        }
        else if (s.equals("Fever without danger signs")){
            if (!weight.isEmpty()) {
                float we = Float.parseFloat(weight);
                if (we >= 4.0 && we < 14.0) {
                    messageList = Arrays.asList(R.string.febril3, R.string.paracetamol2, R.string.febril5,
                            R.string.febril6, R.string.febril7);
                }else if (we >= 14.0) {
                    messageList = Arrays.asList(R.string.febril3, R.string.paracetamol3, R.string.febril5,
                            R.string.febril6, R.string.febril7);
                }else {
                    if (ag >= 2 && ag < 36){
                        messageList = Arrays.asList(R.string.febril3, R.string.paracetamol2, R.string.febril5,
                                R.string.febril6, R.string.febril7);
                    }else  if (ag >= 36 && ag < 60){
                        messageList = Arrays.asList(R.string.febril3, R.string.paracetamol3, R.string.febril5,
                                R.string.febril6, R.string.febril7);
                    }
                }
            } else {
                if (ag >= 2 && ag < 36){
                    messageList = Arrays.asList(R.string.febril3, R.string.paracetamol2, R.string.febril5,
                            R.string.febril6, R.string.febril7);
                }else  if (ag >= 36 && ag < 60){
                    messageList = Arrays.asList(R.string.febril3, R.string.paracetamol3, R.string.febril5,
                            R.string.febril6, R.string.febril7);
                }
            }

        }
        else if (s.equals("Very severe febrile illness")){
            Instructions instructions = new Instructions();
            messageList = instructions.GetFebrilInstructions(ag, weight);
        }
        else if (s.equals("No signs of Pneumonia or Wheezing illness")){
            messageList = Arrays.asList(R.string.selected, R.string.alrite, R.string.no_anti, R.string.other_illness);
        }
        else if (s.contains("HIV-Infected")){
            String care = sharedPreferences.getString(CHOICEHC, "");
            String chest = sharedPreferences.getString(CHOICE7, "");

            Instructions instructions = new Instructions();
            messageList = instructions.GetHIVInfected(care, chest, ag, weight);

        }
        else if (s.contains("HIV-Exposed")){
            String care = sharedPreferences.getString(CHOICEHC, "");
            String chest = sharedPreferences.getString(CHOICE7, "");

            Instructions instructions = new Instructions();
            messageList = instructions.GetHIVExposed(care, chest, ag, weight);

        }
        else if (s.equals("Pneumonia")){
            Instructions instructions = new Instructions();
            messageList = instructions.GetPneumoniaInstructions(ag, weight);
        }
        else if (s.equals("Cough/Cold/No Pneumonia")){
            messageList = Arrays.asList(R.string.cold1, R.string.cold2, R.string.cold3, R.string.cold4);
        }
        else if (s.equals("Wheezing (not clear Bronchodilator response)")){
            if (ag < 24){
                messageList = Arrays.asList(R.string.wheez_ill1, R.string.wheez_ill2, R.string.wheez_ill3,
                        R.string.wheez_ill4, R.string.wheez_ill5, R.string.wheez_ill6);
            }else {
                messageList = Arrays.asList(R.string.wheez_ill1, R.string.wheez_ill2, R.string.wheez_ill7,
                        R.string.wheez_ill8, R.string.wheez_ill9);
            }
        }
        else if (s.equals("Asthma Risk")){
            messageList = Arrays.asList(R.string.asthma1, R.string.wheez3, R.string.asthma2, R.string.asthma3);
        }
        else if (s.equals("Tuberculosis Risk")){
            messageList = Arrays.asList(R.string.tuber1, R.string.tuber2);
        }
        else if (s.equals("Wheezing illness (Bronchodilator response)")){
            messageList = Arrays.asList(R.string.wheez_ill00, R.string.wheez_ill01, R.string.wheez_ill71,
                    R.string.wheez_ill72, R.string.wheez_ill8, R.string.wheez_ill9);
        }else if (s.equals("Severe Disease")){
            messageList = Collections.singletonList(R.string.bronc1x);
        }

    }

    private void saveForm() {

        String file = sharedPreferences.getString(FILENAME, "");

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(currentTime);

        Credentials credentials = new Credentials();
        String username = credentials.creds(DiagnosisActivity.this).getUsername();

        uniqueID = UUID.randomUUID().toString();

        if (file.isEmpty()){

            getDuration(currentTime);

            editor.putString(USERNAME, username);
            editor.putString(DATE, formattedDate);
            editor.putString(UUIDS, uniqueID);
            editor.putString(PENDING, value);
            editor.putString(INCOMPLETE, "complete");
            editor.apply();

            String filename = formattedDate + "_" + uniqueID;
            editor.putString(FILENAME, filename);
            editor.apply();
//            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
            doLogic(filename);
        }else {
            editor.putString(PENDING, value);
            editor.putString(INCOMPLETE, "complete");
            String filename = formattedDate + "_" + uniqueID;
            editor.putString(FILENAME, filename);
            editor.putString(DATE, formattedDate);
            editor.apply();

            getDuration2(currentTime);
//            Toast.makeText(this, "not empty", Toast.LENGTH_SHORT).show();
            doLogic(filename);
        }

    }

    private void getDuration(Date currentTime) {
        String initial_date = sharedPreferences.getString(INITIAL_DATE, "");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        try {
            Date d1 = format.parse(initial_date);

            long diff = currentTime.getTime() - d1.getTime();//as given

            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
            String duration = String.valueOf(minutes);
            editor.putString(DURATION, duration);
            editor.apply();
            Log.d("Difference in time", "getTimeDifference: " + minutes);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void getDuration2(Date currentTime) {
        String initial_date = sharedPreferences.getString(INITIAL_DATE_2, "");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        try {
            Date d1 = format.parse(initial_date);

            long diff = currentTime.getTime() - d1.getTime();//as given

            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
            String duration = String.valueOf(minutes);
            editor.putString(DURATION_2, duration);
            editor.apply();
            Log.d("Difference in time", "getTimeDifference: " + minutes);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void doLogic(String file) {
        sharedPreferences1 = getSharedPreferences(file, Context.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();
        Map<String, ?> all = sharedPreferences.getAll();
        for (Map.Entry<String, ?> x : all.entrySet()) {
            if (x.getValue().getClass().equals(String.class))  editor1.putString(x.getKey(),  (String)x.getValue());
//            else if (x.getValue().getClass().equals(Boolean.class)) editor1.putBoolean(x.getKey(), (Boolean)x.getValue());
        }
        editor1.commit();
        editor.clear();
        editor.commit();

        String filename = sharedPreferences1.getString(SECOND, "");
        if (!filename.isEmpty()){
            filename = filename + ".xml";
            File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs/" + filename);
            if (src.exists()){
                src.delete();
//                Toast.makeText(this, "filename", Toast.LENGTH_SHORT).show();
            }else{
//                Toast.makeText(this, "no file filename", Toast.LENGTH_SHORT).show();
            }
        }

        String bron = sharedPreferences1.getString(BRONCHODILATOR, "");
        String fin = sharedPreferences1.getString(BRONC, "");
        Intent intent;
        if (bron.equals("Bronchodialtor Given") && fin.isEmpty()){
            intent = new Intent(DiagnosisActivity.this, Dashboard.class);
        }else{
            if (value.equals("not pending")){
                intent = new Intent(DiagnosisActivity.this, FinalActivity.class);
                intent.putExtra("filename", file);
            }else {
                editor1.putString(SECOND, file);
                editor1.apply();
                intent = new Intent(DiagnosisActivity.this, Dashboard.class);
            }
        }

        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        if (intent.hasExtra("filename")){
            Bundle bundle = new Bundle();
            Intent intent2 = new Intent(this, PatientActivity.class);
            bundle.putInt("Fragment", 3);
            intent2.putExtras(bundle);
            startActivity(intent2);
            finish();
        }else{
            Toast.makeText(this, "Please click the Save button", Toast.LENGTH_SHORT).show();
        }

    }
}