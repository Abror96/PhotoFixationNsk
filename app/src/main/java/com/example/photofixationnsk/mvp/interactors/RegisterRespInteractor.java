package com.example.photofixationnsk.mvp.interactors;

import android.util.Log;

import com.example.photofixationnsk.mvp.contracts.RegisterContract;
import com.example.photofixationnsk.retrofit.ApiClient;
import com.example.photofixationnsk.retrofit.ApiInterface;
import com.example.photofixationnsk.retrofit.models.RegAndAuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRespInteractor implements RegisterContract.Interactor {

    private final String TAG = "LOGGER Register";

    private ApiInterface apiService =
            ApiClient.getInstance().create(ApiInterface.class);

    @Override
    public void registerUser(final OnFinishedListener onFinishedListener, String username, String password) {
        Call<RegAndAuthResponse> responseCall = apiService.registerUser(username, password);

        responseCall.enqueue(new Callback<RegAndAuthResponse>() {
            @Override
            public void onResponse(Call<RegAndAuthResponse> call, Response<RegAndAuthResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    RegAndAuthResponse regAndAuthResponse = response.body();
                    if (regAndAuthResponse.getStatus().toLowerCase().equals("ok")) {
                        onFinishedListener.onFinished(response.body().getToken());
                        Log.d(TAG, "getRegisterResponse: " + response.body().getStatus());
                    } else if (regAndAuthResponse.getStatus().toLowerCase().equals("error")) {
                        onFinishedListener.onFailure(regAndAuthResponse.getError());
                    }
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

