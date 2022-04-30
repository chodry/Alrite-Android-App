package com.ug.air.alrite.Worker;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.DATE;
import static com.ug.air.alrite.Fragments.Patient.Bronchodilator.REASSESS;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ug.air.alrite.BuildConfig;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotifyWorker extends Worker {

    private NotificationManager notificationManager;
    File[] contents;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    Uri alarmSound;
    Vibrator vibrator;
    String s, progress;

    public NotifyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), alarmSound);
        vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
    }

    @NonNull
    @Override
    public Result doWork() {
        readData();
        return Result.success();
    }

    private void readData() {
        String filename = getInputData().getString("filename");
        File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs");
        if (src.exists()){
            contents = src.listFiles();
            if (contents.length != 0) {
                for (File f : contents) {
                    if (f.isFile()) {
                        String name = f.getName().replace(".xml", "");
                        if (name.equals(filename)){
                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(name, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(REASSESS, true);
                            editor.apply();
                            Log.d("Background Task", "Executed successfully");
                        }
                    }
                }
            }
        }
    }

}
