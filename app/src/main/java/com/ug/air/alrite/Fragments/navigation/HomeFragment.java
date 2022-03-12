package com.ug.air.alrite.Fragments.navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ug.air.alrite.Activities.PatientActivity;
import com.ug.air.alrite.R;


public class HomeFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.btn_learn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), GlossaryActivity.class);
//                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_patients).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), PatientActivity.class);
                bundle.putInt("Fragment", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_assessment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), PatientActivity.class);
                bundle.putInt("Fragment", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

//        view.findViewById(R.id.btn_start_assessment).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), PatientActivity.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }
}