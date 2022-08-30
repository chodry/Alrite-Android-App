package com.ug.air.alrite.Fragments.Patient;

import static com.ug.air.alrite.Activities.SplashActivity.ECZEMA_COUNT;
import static com.ug.air.alrite.Activities.SplashActivity.STRIDOR_COUNT;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.R;
import com.ug.air.alrite.Utils.Counter;

import java.util.ArrayList;


public class Eczema extends Fragment {

    View view;
    Button back, next, btnEczema;
    RadioGroup radioGroup;
    TextView txtDisease, txtDefinition, txtOk,txtDiagnosis;
    LinearLayout linearLayoutDisease, linearLayout_instruction;
    RadioButton radioButton1, radioButton2, radioButton3;
    String value5 = "none";
    VideoView videoView;
    MediaPlayer mediaPlayer;
    Dialog dialog, dialog1;
    RecyclerView recyclerView;
    ArrayList<Assessment> assessments;
    AssessmentAdapter assessmentAdapter;
    CardView inst;
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NOT = 2;
    public static final String CHOICEX2 = "child_ever_had_eczema";
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_eczema, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        btnEczema = view.findViewById(R.id.eczema);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);
        radioButton3 = view.findViewById(R.id.not);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case YES:
                        value5 = "Yes";
                        break;
                    case NO:
                        value5 = "No";
                        break;
                    case NOT:
                        value5 = "Not Sure";
                        break;
                    default:
                        break;
                }
            }
        });


        loadData();
        updateViews();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value5.isEmpty()){
                    Toast.makeText(getActivity(), "Please select at least one option", Toast.LENGTH_SHORT).show();
                }else {
                    saveData();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Breathless());
                fr.commit();
            }
        });

        btnEczema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter counter = new Counter();
                counter.Count(requireActivity(), ECZEMA_COUNT);
                showDialog();
            }
        });


        return view;
    }

    private void saveData() {
        SharedPreferences sharedPreferences = this.requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(CHOICEX2, value5);
        editor.apply();

        FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Allergies());
        fr.addToBackStack(null);
        fr.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        value5 = sharedPreferences.getString(CHOICEX2, "");
    }

    private void updateViews() {
        if (value5.equals("Yes")){
            radioButton1.setChecked(true);
        }else if (value5.equals("No")){
            radioButton2.setChecked(true);
        }else if (value5.equals("Not Sure")){
            radioButton3.setChecked(true);
        }else {
            radioButton1.setChecked(false);
            radioButton2.setChecked(false);
            radioButton3.setChecked(false);
        }

    }

    private void showDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.learn_popup);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView txtDefinition = dialog.findViewById(R.id.definition);
        txtDisease = dialog.findViewById(R.id.diseaseName);
        txtOk = dialog.findViewById(R.id.ok);
        linearLayoutDisease = dialog.findViewById(R.id.disease);
        videoView = dialog.findViewById(R.id.video_view);
        inst = dialog.findViewById(R.id.inst);

        inst.setVisibility(View.GONE);

        txtDisease.setText("Eczema");
        txtDefinition.setText("Eczema is a skin condition that causes itchy, red, dry, bumpy rash. In infants <1 year, the rash tends to appear on cheeks and head and sometimes knees, elbows, and trunk. In older children, the rash appears in the bends of the elbows, behind the knees, wrists, ankles or neck. It is not caused by an infection but may become infected. Skin allergies are involved in some eczema, and it is more common in children with asthma or other allergies.");
        linearLayoutDisease.setBackgroundColor(getResources().getColor(R.color.green_dark));

        videoView.setVisibility(View.GONE);
        CardView cardView = dialog.findViewById(R.id.learn);
        cardView.setVisibility(View.GONE);
//        String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.stridor_glossary_video;
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);
//        videoView.start();

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}