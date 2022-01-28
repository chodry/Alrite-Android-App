package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Fragment2.MIDDLENAME;
import static com.ug.air.alrite.Fragments.Patient.Fragment2.OTHERNAME;
import static com.ug.air.alrite.Fragments.Patient.Fragment2.PHONE;
import static com.ug.air.alrite.Fragments.Patient.Fragment2.SURNAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Adapters.PatientAdapter;
import com.ug.air.alrite.BuildConfig;
import com.ug.air.alrite.Models.Patient;
import com.ug.air.alrite.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


public class Fragment2v1 extends Fragment {

    View view;
    RecyclerView recyclerView;
    EditText search;
    ImageView back;
    ArrayList<Patient> patients;
    PatientAdapter patientAdapter;
    String sName, oName, mName, phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_2v1, container, false);

        recyclerView = view.findViewById(R.id.recyclerView3);
        search = view.findViewById(R.id.search);
        back = view.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment1());
                fr.commit();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        patients = new ArrayList<>();

        File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs");
        File[] contents = src.listFiles();
//        Toast.makeText(getActivity(), "" + contents, Toast.LENGTH_SHORT).show();
        if (contents.length != 0) {
            for (File f : contents) {
                if (f.isFile()) {
                    String name = f.getName().toString();
                    if (!name.equals("sharedPrefs.xml")){
                        String names = name.replace(".xml", "");
                        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(names, Context.MODE_PRIVATE);
                        sName = sharedPreferences.getString(SURNAME, "");
                        oName = sharedPreferences.getString(OTHERNAME, "");
                        mName = sharedPreferences.getString(MIDDLENAME, "");
                        phone = sharedPreferences.getString(PHONE, "");
                        String namesy = sName + " " + oName + " " + mName;
                        char inti1 = sName.charAt(0);
                        char inti2 = oName.charAt(0);
                        String inti = inti1 + "" + inti2;
                        patients.add(new Patient(namesy, phone, inti, name));
                    }
                }
            }
        }else{
//            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }

        patientAdapter = new PatientAdapter(getActivity(), patients);
        recyclerView.setAdapter(patientAdapter);


        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient patient) {
                String name = patient.getFileName();
                Toast.makeText(getActivity(), "" + name, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}