package com.example.photofixationnsk.mvp.interactors;

import android.content.Context;
import android.util.Log;

import com.example.photofixationnsk.SharedPrefs.PrefsConfig;
import com.example.photofixationnsk.mvp.contracts.CategoriesContract;
import com.example.photofixationnsk.mvp.contracts.MainContract;
import com.example.photofixationnsk.retrofit.ApiClient;
import com.example.photofixationnsk.retrofit.ApiInterface;
import com.example.photofixationnsk.retrofit.models.PhotoFix;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainResponseInteractor implements MainContract.Interactor {

    private final String TAG = "LOGGER Register";
    private PrefsConfig prefsConfig;

    private ApiInterface apiService =
            ApiClient.getInstance().create(ApiInterface.class);

    @Override
    public void getPhotoFixData(final OnFinishedListener onFinishedListener, final String carName,
                                final String carNumber, Context context, final long id) {

        prefsConfig = new PrefsConfig(context);

        Call<ArrayList<PhotoFix>> responseCall = apiService.getPhotoFixes(
                "Basic "+prefsConfig.readToken(),
                id);

        responseCall.enqueue(new Callback<ArrayList<PhotoFix>>() {
            @Override
            public void onResponse(Call<ArrayList<PhotoFix>> call, Response<ArrayList<PhotoFix>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {

                    ArrayList<PhotoFix> photoFixes = response.body();
                    onFinishedListener.onFinished(photoFixes);
                } else onFinishedListener.onFailure("Произошла ошибка сервера "+ statusCode +". Попытайтесь снова");
            }

            @Override
            public void onFailure(Call<ArrayList<PhotoFix>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure("Произошла ошибка сервера. Попытайтесь снова");
            }
        });
    }
}
