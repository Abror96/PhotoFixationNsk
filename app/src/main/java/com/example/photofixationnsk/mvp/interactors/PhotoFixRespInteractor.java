package com.example.photofixationnsk.mvp.interactors;

import android.content.Context;
import android.util.Log;

import com.example.photofixationnsk.SharedPrefs.PrefsConfig;
import com.example.photofixationnsk.mvp.contracts.PhotoFixContract;
import com.example.photofixationnsk.retrofit.ApiClient;
import com.example.photofixationnsk.retrofit.ApiInterface;
import com.example.photofixationnsk.retrofit.models.PhotoFix;
import com.example.photofixationnsk.retrofit.models.RegAndAuthResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoFixRespInteractor implements PhotoFixContract.Interactor {

    private final String TAG = "LOGGER Register";
    private PrefsConfig prefsConfig;

    private ApiInterface apiService =
            ApiClient.getInstance().create(ApiInterface.class);

    @Override
    public void addNewPhotoFix(final OnFinishedListener onFinishedListener, RequestBody car_id,
                               RequestBody comment, MultipartBody.Part photo_front,
                               MultipartBody.Part photo_back, MultipartBody.Part photo_left,
                               MultipartBody.Part photo_right, MultipartBody.Part photo_add_1,
                               MultipartBody.Part photo_add_2, MultipartBody.Part photo_add_3,
                               MultipartBody.Part photo_add_4, Context context) {

        prefsConfig = new PrefsConfig(context);

        Call<RegAndAuthResponse> responseCall = apiService.addNewPhotoFix(
                "Basic "+prefsConfig.readToken(),
                car_id,
                comment,
                photo_front,
                photo_back,
                photo_left,
                photo_right,
                photo_add_1,
                photo_add_2,
                photo_add_3,
                photo_add_4);

        responseCall.enqueue(new Callback<RegAndAuthResponse>() {
            @Override
            public void onResponse(Call<RegAndAuthResponse> call, Response<RegAndAuthResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {

                    RegAndAuthResponse regAndAuthResponse = response.body();
                    onFinishedListener.onFinished();
                } else onFinishedListener.onFailure("Произошла ошибка сервера "+ statusCode +". Попытайтесь снова");
            }

            @Override
            public void onFailure(Call<RegAndAuthResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure("Произошла ошибка сервера. Попытайтесь снова");
            }
        });

    }
}

