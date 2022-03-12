package com.ug.air.alrite.Fragments.Patient;

//import static com.ug.air.alrite.Fragments.Patient.Fragment2.MIDDLENAME;
//import static com.ug.air.alrite.Fragments.Patient.Fragment2.OTHERNAME;
//import static com.ug.air.alrite.Fragments.Patient.Fragment2.SURNAME;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
        import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
        import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

        import com.ug.air.alrite.Adapters.PatientAdapter;
        import com.ug.air.alrite.Models.Item;
        import com.ug.air.alrite.R;

        import java.util.ArrayList;
        import java.util.Objects;


public class Fragment2v2 extends Fragment {

    View view;
    Bundle bundle;
    ImageView back;
    LinearLayout newVisit;
    TextView txtName, txtAge, txtNumber, txtGender;
    String name, fileName, age, number, gender, sName, oName, mName, date1, diagnosis;
    RecyclerView recyclerView;
    ArrayList<Item> items;
    PatientAdapter patientAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_2v2, container, false);

        newVisit = view.findViewById(R.id.newVisit);
        txtName = view.findViewById(R.id.Name);
        txtNumber = view.findViewById(R.id.patient_number);
        txtGender = view.findViewById(R.id.gender);
        txtAge = view.findViewById(R.id.birthday);
        back = view.findViewById(R.id.back);
        recyclerView = view.findViewById(R.id.recyclerView4);

//        bundle = this.getArguments();
//        if (bundle != null){
//            fileName = bundle.getString("fileName");
//            String names = fileName.replace(".xml", "");
//            SharedPreferences sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences(names, Context.MODE_PRIVATE);
//            sName = sharedPreferences.getString(SURNAME, "");
//            oName = sharedPreferences.getString(OTHERNAME, "");
//            mName = sharedPreferences.getString(MIDDLENAME, "");
//            name = sName + " " + oName + " " + mName;
//            txtName.setText(name);
//            age = sharedPreferences.getString(AGE, "");
//            if (age.contains(".")){
//                String[] separated = age.split("\\.");
//                txtAge.setText(separated[1] + " months ago");
//            }else{
//                txtAge.setText(age + " years ago");
//            }
//            gender = sharedPreferences.getString(CHOICE, "");
//            txtGender.setText(gender);
//
//            newVisit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("fileName", fileName);
//                    Fragment3 fragment3 = new Fragment3();
//                    fragment3.setArguments(bundle);
//                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//                    fr.replace(R.id.fragment_container, fragment3);
//                    fr.addToBackStack(null);
//                    fr.commit();
//                }
//            });
//
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//
//            items = new ArrayList<>();
//            ArrayList<String> files = new ArrayList<String>();
//
//            File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs");
//            File[] contents = src.listFiles();
//            if (contents.length != 0) {
//                for (File f : contents) {
//                    if (f.isFile()) {
//                        String namet = f.getName().toString();
//                        files.add(namet);
//                    }
//                }
//                Collections.reverse(files);
//                for(String namee : files){
//                    if (!namee.equals("sharedPrefs.xml")){
//                        String namess = namee.replace(".xml", "");
//                        SharedPreferences sharedPreferences2 = Objects.requireNonNull(getActivity()).getSharedPreferences(namess, Context.MODE_PRIVATE);
//                        sName = sharedPreferences2.getString(SURNAME, "");
//                        oName = sharedPreferences2.getString(OTHERNAME, "");
//                        mName = sharedPreferences2.getString(MIDDLENAME, "");
//                        date1 = sharedPreferences2.getString(DATE, "");
//                        diagnosis = sharedPreferences2.getString(DIAGNOSIS, "");
//                        String namesy = sName + " " + oName + " " + mName;
//                        if (namesy.equals(name)){
//                            try {
//                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//                                Date date = df.parse(date1);
//                                SimpleDateFormat df1 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
//                                String formattedDate = df1.format(date);
//                                History history = new History(diagnosis, formattedDate);
//                                items.add(new Item(1, history));
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                }
//
//            }else{
////            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
//            }
//
//            patientAdapter = new PatientAdapter(getActivity(), items);
//            recyclerView.setAdapter(patientAdapter);
//        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ActivePatients());
                fr.commit();
            }
        });

        return view;
    }
}