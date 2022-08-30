package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Activities.PatientActivity.INCOMPLETE;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.DATE;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.BRONCHODILATOR;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator3.BRONC;
import static com.ug.air.alrite.Fragments.Patient.Initials.CIN;
import static com.ug.air.alrite.Fragments.Patient.Initials.PIN;
import static com.ug.air.alrite.Fragments.Patient.Sex.AGE2;
import static com.ug.air.alrite.Fragments.Patient.Sex.CHOICE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ug.air.alrite.Activities.Dashboard;
import com.ug.air.alrite.Activities.DiagnosisActivity;
import com.ug.air.alrite.Adapters.PatientAdapter;
import com.ug.air.alrite.BuildConfig;
import com.ug.air.alrite.Models.History;
import com.ug.air.alrite.Models.Item;
import com.ug.air.alrite.Models.Patient;
import com.ug.air.alrite.R;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;


public class OtherPatients extends Fragment {

    View view;
    RecyclerView recyclerView;
    EditText etSearch;
    ImageView back;
    ArrayList<Item> items;
    PatientAdapter patientAdapter;
    String cin, pin, gender, age, search, dat;
    ArrayList<String> types, files, file;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_other_patients, container, false);

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

        accessSharedFile2();

        patientAdapter = new PatientAdapter(getActivity(), items);
        recyclerView.setAdapter(patientAdapter);

        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                History history = (History) items.get(position).getObject();
                String name = history.getFilename();
                Intent intent = new Intent(getActivity(), DiagnosisActivity.class);
                intent.putExtra("filename", name);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        return view;
    }

    private void accessSharedFile2(){
        File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs");
        if (src.exists()) {
            File[] contents = src.listFiles();
//        Toast.makeText(getActivity(), "" + contents, Toast.LENGTH_SHORT).show();
            if (contents.length != 0) {
                for (File f : contents) {
                    if (f.isFile()) {
                        String name = f.getName().toString();
                        files.add(name);
                    }
                }
                Collections.reverse(files);
                for(String name : files){
                    if (!name.equals("sharedPrefs.xml") && !name.equals("counter_file.xml")){
                        String names = name.replace(".xml", "");
                        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(names, Context.MODE_PRIVATE);
                        String bron = sharedPreferences.getString(BRONCHODILATOR, "");
                        String incomplete = sharedPreferences.getString(INCOMPLETE, "");
                        String fin = sharedPreferences.getString(BRONC, "");
                        if (incomplete.isEmpty() && (bron.isEmpty() || bron.equals("Bronchodialtor Not Given") || !fin.isEmpty())){
                            cin = sharedPreferences.getString(CIN, "");
                            pin = sharedPreferences.getString(PIN, "");
                            age = sharedPreferences.getString(AGE2, "");
                            gender = sharedPreferences.getString(CHOICE, "");
                            dat = sharedPreferences.getString(DATE, "");
                            String[] split = age.split("\\.");
                            String ag = split[0] + " years and " + split[1] + " months";
                            try {
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                                Date date = df.parse(dat);
                                SimpleDateFormat df1 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
                                String formattedDate = df1.format(date);
                                History history = new History("Age: " + ag, "Gender: " + gender, cin, "Parent/Guardian: " + pin, formattedDate, names);
                                items.add(new Item(1, history));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }else {
                Toast.makeText(getActivity(), "There are no patients' records", Toast.LENGTH_SHORT).show();
            }
        }
    }
}