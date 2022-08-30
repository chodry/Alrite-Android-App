package com.ug.air.alrite.Activities;

import static com.ug.air.alrite.Activities.SplashActivity.LEARN_OPENING_COUNT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.ug.air.alrite.Adapters.AssessmentAdapter;
import com.ug.air.alrite.Adapters.LearnAdapter;
import com.ug.air.alrite.Models.Assessment;
import com.ug.air.alrite.Models.Learn;
import com.ug.air.alrite.R;
import com.ug.air.alrite.Utils.Counter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LearnActivity extends AppCompatActivity {

    RecyclerView recyclerView, recyclerView2;
    ArrayList<Learn> learnArrayList;
    LearnAdapter learnAdapter;
    String title ;
    TextView txtDisease, txtDefinition, txtOk,txtDiagnosis;
    LinearLayout linearLayoutDisease, linearLayout_instruction;
    VideoView videoView;
    MediaPlayer mediaPlayer;
    ImageView backBtn, imageView;
    Dialog dialog, dialog1;
    CardView inst, img, video, learn;
    ArrayList<Assessment> assessments;
    AssessmentAdapter assessmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        recyclerView = findViewById(R.id.learn);
        backBtn = findViewById(R.id.back);

        Counter counter = new Counter();
        counter.Count(this, LEARN_OPENING_COUNT);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        learnArrayList = new ArrayList<>();
        learnAdapter = new LearnAdapter(learnArrayList);

        addInforToList(R.string.chest1, R.string.chest2);
        addInforToList(R.string.con1, R.string.con2);
        addInforToList(R.string.dia1, R.string.dia2);
        addInforToList(R.string.improve1, R.string.improve2);
        addInforToList(R.string.bro1, R.string.bro2);
        addInforToList(R.string.pn1, R.string.pn2);
        addInforToList(R.string.pulse1, R.string.pulse2);
        addInforToList(R.string.st1, R.string.st2);
        addInforToList(R.string.un1, R.string.un2);
        addInforToList(R.string.whe1, R.string.whe2);

        recyclerView.setAdapter(learnAdapter);

        learnAdapter.setOnItemClickListener(new LearnAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Learn learn = learnArrayList.get(position);
                title = getResources().getString(learn.getTitle());
                showDialog(title);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LearnActivity.this, Dashboard.class));
                finish();
            }
        });
    }

    private void addInforToList(int chest1, int chest2) {
        Learn learn = new Learn(chest1, chest2);
        learnArrayList.add(learn);
    }

    private void showDialog(String title) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.learn_popup);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        txtDefinition = dialog.findViewById(R.id.definition);
        txtDisease = dialog.findViewById(R.id.diseaseName);
        txtOk = dialog.findViewById(R.id.ok);
        linearLayoutDisease = dialog.findViewById(R.id.disease);
        videoView = dialog.findViewById(R.id.video_view);
        inst = dialog.findViewById(R.id.inst);
        recyclerView2 = dialog.findViewById(R.id.recyclerView2);
        learn = dialog.findViewById(R.id.learn);
        img = dialog.findViewById(R.id.imageCard);
        imageView = dialog.findViewById(R.id.image);

        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        assessments = new ArrayList<>();
        assessmentAdapter = new AssessmentAdapter(assessments);

        if (title.equals("Chest Indrawing")){
            setTitleAndDefinition(title, R.string.chest2);

            List<Integer> messages = Arrays.asList(R.string.chest3, R.string.chest4, R.string.chest5);

            for (int i = 0; i < messages.size(); i++){
                Assessment assessment = new Assessment(messages.get(i));
                assessments.add(assessment);
            }

            String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.chest_indrawing_glossary_video;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);

        }else if (title.equals("Convulsions")){
            setTitleAndDefinition(title, R.string.con3);

            inst.setVisibility(View.GONE);

            String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.convulsions_glossary_video;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);

        }else if (title.equals("Diazepam")){
            setTitleAndDefinition(title, R.string.dia3);

            inst.setVisibility(View.GONE);
            learn.setVisibility(View.GONE);
        }else if (title.equals("Improvise a Spacer")){
            setTitleAndDefinition(title, R.string.improve3);

            img.setVisibility(View.VISIBLE);
            Picasso.get().load(R.drawable.improv_spacer_glossary_image).into(imageView);

            learn.setVisibility(View.GONE);

            List<Integer> messages = Arrays.asList(R.string.improve4, R.string.improve5, R.string.improve6, R.string.improve7, R.string.improve8, R.string.improve9);

            for (int i = 0; i < messages.size(); i++){
                Assessment assessment = new Assessment(messages.get(i));
                assessments.add(assessment);
            }
        }else if (title.equals("Inhaled Bronchodilator")){
            setTitleAndDefinition(title, R.string.bro2);

            img.setVisibility(View.VISIBLE);
            Picasso.get().load(R.drawable.bronchodilator_glossary_image).into(imageView);

            List<Integer> messages = Arrays.asList(R.string.bro4, R.string.bro5, R.string.bro6, R.string.bro7,
                    R.string.bro8, R.string.bro9, R.string.bro10, R.string.bro11, R.string.bro12, R.string.bro13, R.string.bro14);

            for (int i = 0; i < messages.size(); i++){
                Assessment assessment = new Assessment(messages.get(i));
                assessments.add(assessment);
            }

            String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.bronchodilator_glossary_video;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);

        }else if (title.equals("Pneumonia")){
            setTitleAndDefinition(title, R.string.pn3);
            inst.setVisibility(View.GONE);
            learn.setVisibility(View.GONE);
        }else if (title.equals("Pulse Oximeter")){
            setTitleAndDefinition(title, R.string.pulse3);

            img.setVisibility(View.VISIBLE);
            Picasso.get().load(R.drawable.pulse_oximeter_glossary_image).into(imageView);

            inst.setVisibility(View.GONE);
            learn.setVisibility(View.GONE);
        }else if (title.equals("Stridor")){
            setTitleAndDefinition(title, R.string.st4);
            inst.setVisibility(View.GONE);

            String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.stridor_glossary_video;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);

        }else if (title.equals("Unresponsive")){
            setTitleAndDefinition(title, R.string.un3);
            inst.setVisibility(View.GONE);
            learn.setVisibility(View.GONE);
        }else if (title.equals("Wheezing")){
            setTitleAndDefinition(title, R.string.whe2);

            List<Integer> messages = Arrays.asList(R.string.whe8, R.string.whe9, R.string.whe10, R.string.whe11);

            for (int i = 0; i < messages.size(); i++){
                Assessment assessment = new Assessment(messages.get(i));
                assessments.add(assessment);
            }

            String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.wheezing_glossary_video;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
        }

        recyclerView2.setAdapter(assessmentAdapter);

        ImageView imPlay = dialog.findViewById(R.id.play);
        imPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imPlay.setVisibility(View.GONE);
                videoView.start();
            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imPlay.setVisibility(View.VISIBLE);
                videoView.pause();
            }
        });

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void setTitleAndDefinition(String title, int chest2) {
        txtDisease.setText(title);
        txtDefinition.setText(getResources().getString(chest2));
        linearLayoutDisease.setBackgroundColor(getResources().getColor(R.color.green_dark));
    }


}