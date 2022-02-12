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
import java.util.UUID;

public class Fragment2 extends Fragment {

   View view;
   EditText etSurname, etOther, etMiddle, etPhone;
   Button back, next;
   String surname, middleNme, otherName, phone;
   public static final String SURNAME = "surname";
   public static final String MIDDLENAME = "miidleName";
   public static final String OTHERNAME = "otherName";
   public static final String PHONE = "phone";
   public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_2, container, false);

        etSurname = view.findViewById(R.id.surname);
        etMiddle = view.findViewById(R.id.middle);
        etOther = view.findViewById(R.id.other);
        etPhone = view.findViewById(R.id.phoneNumber);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        loadData();
        updateViews();

        etPhone.addTextChangedListener(textWatcher);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                surname = etSurname.getText().toString();
                middleNme = etMiddle.getText().toString();
                otherName = etOther.getText().toString();
                phone = etPhone.getText().toString();

                if (surname.isEmpty() || otherName.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }else {
                    saveData();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment1());
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
            phone = etPhone.getText().toString();
            if (!phone.isEmpty()){
                if (!phone.startsWith("0") || phone.length() != 10){
                    etPhone.setError("the contact number should start with a 0 and be with 10 digits");
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void saveData() {
        SharedPreferences sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SURNAME, surname);
        editor.putString(MIDDLENAME, middleNme);
        editor.putString(OTHERNAME, otherName);
        editor.putString(PHONE, phone);
        editor.apply();

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Fragment3());
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        surname = sharedPreferences.getString(SURNAME, "");
        middleNme = sharedPreferences.getString(MIDDLENAME, "");
        otherName = sharedPreferences.getString(OTHERNAME, "");
        phone = sharedPreferences.getString(PHONE, "");
    }

    private void updateViews() {
        etSurname.setText(surname);
        etMiddle.setText(middleNme);
        etOther.setText(otherName);
        etPhone.setText(phone);
    }
}