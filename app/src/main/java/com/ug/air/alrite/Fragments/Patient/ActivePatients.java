package com.ug.air.alrite.Fragments.Patient;


import static com.ug.air.alrite.Activities.PatientActivity.INCOMPLETE;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.BRONCHODILATOR;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.DATE;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.REASSESS;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator2.REASON;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator3.BRONC;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator3.FINAL;
import static com.ug.air.alrite.Fragments.Patient.Initials.CIN;
import static com.ug.air.alrite.Fragments.Patient.Initials.PIN;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE2;
import static com.ug.air.alrite.Fragments.Patient.Sex.CHOICE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ug.air.alrite.Activities.Dashboard;
import com.ug.air.alrite.Activities.DiagnosisActivity;
import com.ug.air.alrite.Adapters.PatientAdapter;
import com.ug.air.alrite.BuildConfig;
import com.ug.air.alrite.Models.Item;
import com.ug.air.alrite.Models.Patient;
import com.ug.air.alrite.R;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ActivePatients extends Fragment {

    View view;
    RecyclerView recyclerView;
    EditText etSearch;
    ImageView back;
    ArrayList<Item> items;
    PatientAdapter patientAdapter;
    String cin, pin, gender, age, search, dat;
    ArrayList<String> types, files, file;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_active_patients, container, false);

        recyclerView = view.findViewById(R.id.recyclerView3);
        etSearch = view.findViewById(R.id.search);
        back = view.findViewById(R.id.back);

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), Dashboard.class));
//            }
//        });

        back.setVisibility(View.GONE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        items = new ArrayList<>();
        files = new ArrayList<String>();

        accessSharedFile();

        etSearch.addTextChangedListener(textWatcher);

        patientAdapter = new PatientAdapter(getActivity(), items);
        recyclerView.setAdapter(patientAdapter);

        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Patient patient = (Patient) items.get(position).getObject();
                String name = patient.getFilename();
                boolean reassess = patient.isReassess();
                if (reassess){
                    Bundle bundle = new Bundle();
                    bundle.putString("fileName", name);
                    RRCounter rrCounter = new RRCounter();
                    rrCounter.setArguments(bundle);
                    FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, rrCounter);
                    fr.addToBackStack(null);
                    fr.commit(); 
                }else {
                    Toast.makeText(getActivity(), "Patient not ready for reassessment", Toast.LENGTH_SHORT).show();
                }
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
            search = etSearch.getText().toString();
            if (!search.isEmpty()) {
                items.clear();
                for(String type : types){
                    String ty = type.toLowerCase();
                    if (ty.contains(search)){
                        int index = types.indexOf(type);
                        String fileName = file.get(index);
                        sharedPreferences = requireActivity().getSharedPreferences(fileName, Context.MODE_PRIVATE);
                        cin = sharedPreferences.getString(CIN, "");
                        pin = sharedPreferences.getString(PIN, "");
                        age = sharedPreferences.getString(AGE2, "");
                        gender = sharedPreferences.getString(CHOICE, "");
                        dat = sharedPreferences.getString(DATE, "");
                        boolean reassess = sharedPreferences.getBoolean(REASSESS, false);
                        String[] split = age.split("\\.");
                        String ag = split[0] + " years and " + split[1] + " months";
                        try {
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                            Date date = df.parse(dat);
                            SimpleDateFormat df1 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
                            String formattedDate = df1.format(date);
                            Patient patient = new Patient("Age: " + ag, "Gender: " + gender, cin, "Parent/Guardian: " + pin, formattedDate, fileName, reassess);
                            items.add(new Item(0, patient));
//                            patientAdapter.notifyDataSetChanged();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
//                    patientAdapter.notifyDataSetChanged();
                }
                patientAdapter.notifyDataSetChanged();
            }else {
                items.clear();
                accessSharedFile();
                patientAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void accessSharedFile() {
        File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs");
        if (src.exists()){
            File[] contents = src.listFiles();
//        Toast.makeText(getActivity(), "" + contents, Toast.LENGTH_SHORT).show();
            if (contents.length != 0) {
                types = new ArrayList<String>();
                file = new ArrayList<String>();
                for (File f : contents) {
                    if (f.isFile()) {
                        String name = f.getName().toString();
                        if (!name.equals("sharedPrefs.xml") && !name.equals("counter_file.xml")){
                            String names = name.replace(".xml", "");
                            sharedPreferences = requireActivity().getSharedPreferences(names, Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            String bron = sharedPreferences.getString(BRONCHODILATOR, "");
                            String fin = sharedPreferences.getString(BRONC, "");
                            String incomplete = sharedPreferences.getString(INCOMPLETE, "");
                            if (bron.equals("Bronchodialtor Given") && fin.isEmpty()){
                                file.add(names);
                                cin = sharedPreferences.getString(CIN, "");
                                pin = sharedPreferences.getString(PIN, "");
                                age = sharedPreferences.getString(AGE2, "");
                                gender = sharedPreferences.getString(CHOICE, "");
                                dat = sharedPreferences.getString(DATE, "");
                                boolean reassess = sharedPreferences.getBoolean(REASSESS, false);
                                types.add(cin);
                                String[] split = age.split("\\.");
                                String ag = split[0] + " years and " + split[1] + " months";
                                try {
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                                    Date date = df.parse(dat);
                                    SimpleDateFormat df1 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
                                    String formattedDate = df1.format(date);

                                    Date currentTime = Calendar.getInstance().getTime();
                                    long diff = currentTime.getTime() - date.getTime();
                                    long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
                                    if (minutes >= 15 && minutes < 240){
                                        editor.putBoolean(REASSESS, true);
                                        editor.apply();
                                        Patient patient = new Patient("Age: " + ag, "Gender: " + gender, cin, "Parent/Guardian: " + pin, formattedDate, names, reassess);
                                        items.add(new Item(0, patient));
                                    }else if (minutes >= 240){
                                        editor.putString(BRONCHODILATOR, "Bronchodialtor Not Given");
                                        editor.putString(REASON, "A 4 hour time period elapsed");
                                        editor.apply();
                                    }else {
                                        Patient patient = new Patient("Age: " + ag, "Gender: " + gender, cin, "Parent/Guardian: " + pin, formattedDate, names, reassess);
                                        items.add(new Item(0, patient));
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }

            }else{
                Toast.makeText(getActivity(), "There no patients' records", Toast.LENGTH_SHORT).show();

            }
        }
    }

}