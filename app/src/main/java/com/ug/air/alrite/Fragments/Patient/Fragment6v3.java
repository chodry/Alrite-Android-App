package com.ug.air.alrite.Fragments.Patient;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.R;

import java.util.ArrayList;
import java.util.Objects;


public class Fragment6v3 extends Fragment {

   View view;
    Button back, next;
    CheckBox drink, vomit, resp, none;
    String s = "";
    Boolean check1, check2, check3, check4;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CHECK11 = "check11";
    public static final String CHECK21 = "check21";
    public static final String CHECK31 = "check31";
    public static final String CHECK41 = "check41";
    public static final String S5 = "s5";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_6v3, container, false);

        back = view.findViewById(R.id.back);
        next = view.findViewById(R.id.next);
        drink = view.findViewById(R.id.drink);
        vomit = view.findViewById(R.id.vomit);
        none = view.findViewById(R.id.none);
        resp = view.findViewById(R.id.responsive);

        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedList();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment6v1());
                fr.commit();
            }
        });


        return view;
    }

    private void checkedList() {
        s = "";

        if(drink.isChecked()){
            s += "At night, ";
        }
        if(vomit.isChecked()){
            s += "When playing, so they have to take a break, ";
        }
        if(resp.isChecked()){
            s += "When laughing/crying or exposed to smoke or strong smells, ";
        }
        if(none.isChecked()){
            s = "None of these, ";
        }
        s = s.replaceAll(", $", "");
        if (s.isEmpty()){
            Toast.makeText(getActivity(), "Choose at least one option", Toast.LENGTH_SHORT).show();
        }else {
            saveData(s);
        }

    }

    private void saveData(String s) {

        editor.putBoolean(CHECK11, drink.isChecked());
        editor.putBoolean(CHECK21, vomit.isChecked());
        editor.putBoolean(CHECK31, resp.isChecked());
        editor.putBoolean(CHECK41, none.isChecked());
        editor.putString(S5, s);

        editor.apply();

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Fragment6v4());
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        check1 = sharedPreferences.getBoolean(CHECK11, false);
        check2 = sharedPreferences.getBoolean(CHECK21, false);
        check3 = sharedPreferences.getBoolean(CHECK31, false);
        check4 = sharedPreferences.getBoolean(CHECK41, false);
    }

    private void updateViews() {
        drink.setChecked(check1);
        vomit.setChecked(check2);
        resp.setChecked(check3);
        none.setChecked(check4);
    }

}