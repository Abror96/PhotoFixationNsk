package com.example.photofixationnsk.mvp.interactors;

import android.content.Context;
import android.util.Log;

import com.example.photofixationnsk.SharedPrefs.PrefsConfig;
import com.example.photofixationnsk.mvp.contracts.CategoriesContract;
import com.example.photofixationnsk.retrofit.ApiClient;
import com.example.photofixationnsk.retrofit.ApiInterface;
import com.example.photofixationnsk.retrofit.models.Cars;
import com.example.photofixationnsk.retrofit.models.Categories;
import com.example.photofixationnsk.retrofit.models.PhotoFix;
import com.example.photofixationnsk.retrofit.models.RegAndAuthResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesRespInteractor implements CategoriesContract.Interactor {

    private final String TAG = "LOGGER Register";
    private PrefsConfig prefsConfig;

    private ApiInterface apiService =
            ApiClient.getInstance().create(ApiInterface.class);

    @Override
    public void getCategoriesList(final OnFinishedListener onFinishedListener, Context context) {
        prefsConfig = new PrefsConfig(context);

        Call<ArrayList<Categories>> responseCall = apiService.getCategoriesList("Basic "+prefsConfig.readToken());

        responseCall.enqueue(new Callback<ArrayList<Categories>>() {
            @Override
            public void onResponse(Call<ArrayList<Categories>> call, Response<ArrayList<Categories>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {

                    ArrayList<Categories> categories = response.body();
                    onFinishedListener.onFinished(categories);
                } else onFinishedListener.onFailure("Произошла ошибка сервера "+ statusCode +". Попытайтесь снова");
            }

            @Override
            public void onFailure(Call<ArrayList<Categories>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure("Произошла ошибкаasdasdasdas сервера. Попытайтесь снова");
            }
        });
    }

    @Override
    public void getCarsList(final OnFinishedListener onFinishedListener, Context context, long id, String number) {
        prefsConfig = new PrefsConfig(context);

        Call<ArrayList<Cars>> responseCall = apiService.getCarsList(
                "Basic "+prefsConfig.readToken(),
                id,
                number);

        responseCall.enqueue(new Callback<ArrayList<Cars>>() {
            @Override
            public void onResponse(Call<ArrayList<Cars>> call, Response<ArrayList<Cars>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {

                    ArrayList<Cars> cars = response.body();
                    onFinishedListener.onCarsFinished(cars);
                } else onFinishedListener.onFailure("Произошла ошибка сервера "+ statusCode +". Попытайтесь снова");
            }

            @Override
            public void onFailure(Call<ArrayList<Cars>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure("Произошла ошибка сервера. Попытайтесь снова");
            }
        });
    }

    @Override
    public void getPhotoFixList(final OnFinishedListener onFinishedListener, final String carName,
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
                    onFinishedListener.onPhotoFixFinished(photoFixes, carName, carNumber, id);
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
