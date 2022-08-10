package com.ug.air.alrite.Worker;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ug.air.alrite.APIs.ApiClient;
import com.ug.air.alrite.APIs.JsonPlaceHolder;
import com.ug.air.alrite.BuildConfig;
import com.ug.air.alrite.Database.DatabaseHelper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifyWorker3 extends Worker {

    private NotificationManager notificationManager;
    File[] contents;
    String s, progress;
    int value = 0;
    String token;
    public static JsonPlaceHolder jsonPlaceHolder;
    Call<String> call;
    DatabaseHelper databaseHelper;

    public NotifyWorker3(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        Cursor res = databaseHelper.getData("1");
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            token =  res.getString(2);
        }
    }

    @NonNull
    @Override
    public Result doWork() {
        readData();
        return Result.success();
    }

    private void readData() {
        jsonPlaceHolder = ApiClient.getClient().create(JsonPlaceHolder.class);
        File src = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs");
        if (src.exists()){
            contents = src.listFiles();
            if (contents.length != 0) {
                for (File f : contents) {
                    if (f.isFile()) {
                        String name = f.getName();
                        if (!name.equals("sharedPrefs.xml")) {
                            File patient = new File("/data/data/" + BuildConfig.APPLICATION_ID + "/shared_prefs/" + name);
                            RequestBody filePart = RequestBody.create(MediaType.parse("*/*"), patient);
                            MultipartBody.Part fileUpload = MultipartBody.Part.createFormData("patient", patient.getName() ,filePart);

                            Call<String> call = jsonPlaceHolder.sendFile("Token " + token, fileUpload);
                            call.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (!response.isSuccessful()){
                                        Log.d("Alrite server issue", "There is an issue with the server" );
                                        return;
                                    }
                                    String message = response.body();
//                                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                                    patient.delete();
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
//                                    Toast.makeText(getActivity(), "Please turn on your internet connection", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }
            }
        }
    }
}
