package com.ug.air.alrite.Fragments.Patient;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ug.air.alrite.R;

import java.util.Objects;


public class Fragment8v2 extends Fragment {

    View view;
    EditText etDay, etDay2;
    Button back, next;
    String temp, temp1, temp2;
    public static final String TEMP = "temp";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_8v2, container, false);

        etDay = view.findViewById(R.id.days);
        etDay2 = view.findViewById(R.id.kg2);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        etDay.requestFocus();

        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadData();
        updateViews();

        etDay.addTextChangedListener(textWatcher);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp1 = etDay.getText().toString();
                temp2 = etDay2.getText().toString();
                if (temp1.isEmpty() || temp2.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in the fields before you continue", Toast.LENGTH_SHORT).show();
                }else{
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment8v1());
                fr.commit();
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
            temp = etDay.getText().toString();
            if (!temp.isEmpty()){
                long dy = Long.parseLong(temp);
                if (dy < 33){
                    etDay.setError("The minimum temperature is 33.0");
                }else if (dy > 44){
                    etDay.setError("The maximum temperature is 44.0");
                }else if(dy == 44){
                    etDay2.setText("0");
                    etDay2.setEnabled(false);
                }else {
                    etDay2.setText("");
                    etDay2.setEnabled(true);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void saveData() {
        temp = temp1 + "." + temp2;
        editor.putString(TEMP, temp);
        editor.apply();

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Fragment8v3());
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        temp = sharedPreferences.getString(TEMP, "");
    }

    private void updateViews() {
        if (!temp.isEmpty()){
            String[] separated = temp.split("\\.");
            etDay.setText(separated[0]);
            etDay2.setText(separated[1]);
        }
    }
}