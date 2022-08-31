package com.ug.air.alrite.APIs;

import com.ug.air.alrite.Models.Token;
import com.ug.air.alrite.Models.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface JsonPlaceHolder {

    @POST("login/")
    Call<Token> login(@Body User user);

    @Multipart
    @POST("saveData/")
    Call<String> sendFile(@Header("Authorization") String header, @Part MultipartBody.Part patient);

    @Multipart
    @POST("saveCounter/")
    Call<String> sendCounterFile(@Header("Authorization") String header, @Part MultipartBody.Part counter);

}
