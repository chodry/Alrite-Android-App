package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Fragments.Patient.Bronchodilator2.BDIAGNOSIS;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator2.REASON;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.ug.air.alrite.Activities.DiagnosisActivity;
import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bronchodilator extends Fragment {

    View view;
    Button btnBron, btnGiven, btnNot, btnSave;
    Dialog dialog;
    String bronchodilator;
    TextView txtMessage;
    public static final String BRONCHODILATOR = "bronchodilator";
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor, editor1;
    TextView txtDisease, txtDefinition, txtOk, txtDiagnosis;
    LinearLayout linearLayoutDisease;
    LinearLayout linearLayout_instruction;
    VideoView videoView;
    String diagnosis, s, assess, second;
    RecyclerView recyclerView;
    Boolean check1;
    ArrayList<Assessment> assessments;
    AssessmentAdapter assessmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bronchodilator, container, false);

        btnBron = view.findViewById(R.id.bronchodilator);
        btnGiven = view.findViewById(R.id.given);
        btnNot = view.findViewById(R.id.not_given);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnBron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showVideoDialog();
            }
        });

        btnGiven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove(BDIAGNOSIS);
                editor.remove(REASON);
                bronchodilator = "Bronchodialtor Given";
                editor.putString(BRONCHODILATOR, bronchodilator);
                editor.apply();
                showDialog();
            }
        });

        btnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bronchodilator = "Bronchodialtor Not Given";
                editor.putString(BRONCHODILATOR, bronchodilator);
                editor.apply();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Bronchodilator2());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }

    private void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.assess);
        dialog.setCancelable(true);

        txtMessage = dialog.findViewById(R.id.message);
        btnSave = dialog.findViewById(R.id.ContinueButton);

        btnSave.setText("Continue");

        txtMessage.setText("Please wait for 15 minutes to reassess the patient");
        btnSave.setText("OK");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startBackGroundTask();
                startActivity(new Intent(getActivity(), DiagnosisActivity.class));
            }
        });

        dialog.show();
    }

    private void showVideoDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.learn_popup);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        txtDefinition = dialog.findViewById(R.id.definition);
        txtDisease = dialog.findViewById(R.id.diseaseName);
        txtOk = dialog.findViewById(R.id.ok);
        linearLayoutDisease = dialog.findViewById(R.id.disease);
        videoView = dialog.findViewById(R.id.video_view);
        recyclerView = dialog.findViewById(R.id.recyclerView2);
        CardView inst = dialog.findViewById(R.id.inst);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments);

        List<Integer> messages = Arrays.asList(R.string.spacer1, R.string.spacer2, R.string.spacer3,
                R.string.spacer4, R.string.spacer5, R.string.spacer6, R.string.spacer7, R.string.spacer8,
                R.string.spacer9, R.string.spacer10, R.string.spacer11, R.string.spacer12, R.string.spacer13,
                R.string.spacer14, R.string.spacer15, R.string.spacer16, R.string.spacer17);

        for (int i = 0; i < messages.size(); i++){
            Assessment assessment = new Assessment(messages.get(i));
            assessments.add(assessment);
        }
        recyclerView.setAdapter(assessmentAdapter);

        txtDisease.setText("Bronchodilator");
        txtDefinition.setText(R.string.bronchodi);
        linearLayoutDisease.setBackgroundColor(getResources().getColor(R.color.green_dark));

        String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.bronchodilator_glossary_video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void startBackGroundTask() {
    }
}