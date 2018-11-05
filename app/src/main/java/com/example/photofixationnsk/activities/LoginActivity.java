package com.example.photofixationnsk.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.photofixationnsk.R;
import com.example.photofixationnsk.SharedPrefs.PrefsConfig;
import com.example.photofixationnsk.mvp.contracts.LoginContract;
import com.example.photofixationnsk.mvp.contracts.RegisterContract;
import com.example.photofixationnsk.mvp.interactors.LoginRespInteractor;
import com.example.photofixationnsk.mvp.interactors.RegisterRespInteractor;
import com.example.photofixationnsk.mvp.presenterImpls.LoginPresenterImpl;
import com.example.photofixationnsk.mvp.presenterImpls.RegisterPresenterImpl;
import com.example.photofixationnsk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.tv_username)
    TextInputLayout tv_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.tv_password)
    TextInputLayout tv_password;
    @BindView(R.id.btn_sign_in)
    Button btn_sign_in;
    @BindView(R.id.loginMainLayout)
    NestedScrollView loginMainLayout;

    private LoginContract.Presenter presenter;
    private PrefsConfig prefsConfig;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenterImpl(this, new LoginRespInteractor());
        prefsConfig = new PrefsConfig(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Идёт загрузка");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.btn_sign_in)
    public void onLoginBtnClicked() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (!username.isEmpty() && !password.isEmpty()) {
            presenter.onLoginBtnClicked(username, password);
            progressDialog.show();
        } else Snackbar.make(loginMainLayout, "Заполните все поля", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSnackbar(String message) {
        progressDialog.dismiss();
        Snackbar.make(loginMainLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void authUser(String token) {
        Log.d("LOGGER", "registerUser token: " + token);

        // set status loggined and save token
        prefsConfig.writeLoginStatus(true);
        prefsConfig.writeToken(token);

        // launch activity
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

        // turn off dialog
        progressDialog.dismiss();
    }
}

