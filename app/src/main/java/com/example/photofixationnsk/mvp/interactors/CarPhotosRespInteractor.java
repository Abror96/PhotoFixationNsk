package com.example.photofixationnsk.mvp.interactors;

import android.content.Context;
import android.util.Log;

import com.example.photofixationnsk.SharedPrefs.PrefsConfig;
import com.example.photofixationnsk.mvp.contracts.CarPhotosContract;
import com.example.photofixationnsk.retrofit.ApiClient;
import com.example.photofixationnsk.retrofit.ApiInterface;
import com.example.photofixationnsk.retrofit.models.CarPhotoFixation;
import com.example.photofixationnsk.retrofit.models.PhotoFix;
import com.example.photofixationnsk.retrofit.models.RegAndAuthResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarPhotosRespInteractor implements CarPhotosContract.Interactor {

    private final String TAG = "LOGGER Register";
    private PrefsConfig prefsConfig;

    private ApiInterface apiService =
            ApiClient.getInstance().create(ApiInterface.class);

    @Override
    public void getCarPhotos(final OnFinishedListener onFinishedListener, long id, Context context) {

        prefsConfig = new PrefsConfig(context);

        Call<CarPhotoFixation> responseCall = apiService.getCarPhoto(
                "Basic "+prefsConfig.readToken(),
                id);

        responseCall.enqueue(new Callback<CarPhotoFixation>() {
            @Override
            public void onResponse(Call<CarPhotoFixation> call, Response<CarPhotoFixation> response) {
                int statusCode = response.code();
                if (statusCode == 200) {

                    CarPhotoFixation carPhotoFixation = response.body();
                    onFinishedListener.onFinish(
                            carPhotoFixation.getPhotoFixation().getComment(),
                            carPhotoFixation.getUrlPhotoFront(),
                            carPhotoFixation.getUrlPhotoBack(),
                            carPhotoFixation.getUrlPhotoLeft(),
                            carPhotoFixation.getUrlPhotoRihgt(),
                            carPhotoFixation.getUrlPhotoAdd1(),
                            carPhotoFixation.getUrlPhotoAdd2(),
                            carPhotoFixation.getUrlPhotoAdd3(),
                            carPhotoFixation.getUrlPhotoAdd4()
                    );
                } else onFinishedListener.onFailure("Произошла ошибка сервера "+ statusCode +". Попытайтесь снова");
            }

            @Override
            public void onFailure(Call<CarPhotoFixation> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure("Произошла ошибка сервера. Попытайтесь снова");
            }
        });

    }
}