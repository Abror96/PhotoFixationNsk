package com.example.photofixationnsk.mvp.presenterImpls;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.photofixationnsk.mvp.contracts.LoginContract;

import java.nio.charset.StandardCharsets;

public class LoginPresenterImpl implements LoginContract.Presenter, LoginContract.Interactor.OnFinishedListener {

    private LoginContract.View view;
    private LoginContract.Interactor interactor;

    public LoginPresenterImpl(LoginContract.View view, LoginContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onLoginBtnClicked(String username, String password) {
        if (view != null) {
            String userPass = username + ":" + password;
            byte[] data = userPass.getBytes(StandardCharsets.UTF_8);
            String token = Base64.encodeToString(data, Base64.DEFAULT);

            Log.d("LOGGER", "Created token: " + token);

            interactor.authUser(this, token.trim(), username, password);
        }
    }

    @Override
    public void onFinished(String token) {
        if (view != null) {
            view.authUser(token);
        }
    }

    @Override
    public void onFailure(String message) {
        if (view != null) {
            view.showSnackbar(message);
        }
    }
}
