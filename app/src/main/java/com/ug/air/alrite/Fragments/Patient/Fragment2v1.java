package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Fragment12.DATE;
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
import com.ug.air.alrite.Models.Item;
import com.ug.air.alrite.Models.Patient;
import com.ug.air.alrite.R;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class Fragment2v1 extends Fragment {

    View view;
    RecyclerView recyclerView;
    EditText search;
    ImageView back;
    ArrayList<Item> items;
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

        items = new ArrayList<>();
        ArrayList<String> files = new ArrayList<String>();

        File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs");
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
            ArrayList<String> types = new ArrayList<String>();
            for(String name : files){
                if (!name.equals("sharedPrefs.xml")){
                    String names = name.replace(".xml", "");
                    SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(names, Context.MODE_PRIVATE);
                    sName = sharedPreferences.getString(SURNAME, "");
                    oName = sharedPreferences.getString(OTHERNAME, "");
                    mName = sharedPreferences.getString(MIDDLENAME, "");
                    phone = sharedPreferences.getString(DATE, "");
                    String namesy = sName + " " + oName + " " + mName;
                    if (!types.contains(namesy)){
                        types.add(namesy);
                        char inti1 = sName.charAt(0);
                        char inti2 = oName.charAt(0);
                        String inti = inti1 + "" + inti2;

                        try {
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                            Date date = df.parse(phone);
                            SimpleDateFormat df1 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
                            String formattedDate = df1.format(date);
                            Patient patient = new Patient(namesy, formattedDate, inti, name);
                            items.add(new Item(0, patient));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

        }else{
//            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }

        patientAdapter = new PatientAdapter(getActivity(), items);
        recyclerView.setAdapter(patientAdapter);

        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Patient patient = (Patient) items.get(position).getObject();
                String name = patient.getFileName();
                Bundle bundle = new Bundle();
                bundle.putString("fileName", name);
                Fragment2v2 fragment2v2 = new Fragment2v2();
                fragment2v2.setArguments(bundle);
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, fragment2v2);
                fr.addToBackStack(null);
                fr.commit();
            }
        });

//        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Patient patient) {
//                String name = patient.getFileName();
//                Bundle bundle = new Bundle();
//                bundle.putString("fileName", name);
//                Fragment2v2 fragment2v2 = new Fragment2v2();
//                fragment2v2.setArguments(bundle);
//                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//                fr.replace(R.id.fragment_container, fragment2v2);
//                fr.addToBackStack(null);
//                fr.commit();
//            }
//        });

        return view;
    }
}