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
import android.widget.TextView;
import android.widget.Toast;

import com.example.photofixationnsk.R;
import com.example.photofixationnsk.SharedPrefs.PrefsConfig;
import com.example.photofixationnsk.mvp.contracts.RegisterContract;
import com.example.photofixationnsk.mvp.interactors.RegisterRespInteractor;
import com.example.photofixationnsk.mvp.presenterImpls.RegisterPresenterImpl;
import com.example.photofixationnsk.R;

import org.w3c.dom.Text;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.tv_username)
    TextInputLayout tv_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.tv_password)
    TextInputLayout tv_password;
    @BindView(R.id.btn_sign_up)
    Button btn_sign_up;
    @BindView(R.id.registerMainLayout)
    NestedScrollView registerMainLayout;
    @BindView(R.id.already_registered)
    TextView already_registered;

    private RegisterContract.Presenter presenter;
    private PrefsConfig prefsConfig;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        presenter = new RegisterPresenterImpl(this, new RegisterRespInteractor());
        prefsConfig = new PrefsConfig(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Идёт загрузка");
        progressDialog.setCanceledOnTouchOutside(false);

        if (prefsConfig.readLoginStatus()) {
            launchActivity();
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void onRegisterClicked() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (!username.isEmpty() && !password.isEmpty()) {
            presenter.onRegisterClicked(username, password);
            progressDialog.show();
        } else Snackbar.make(registerMainLayout, "Заполните все поля", Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.already_registered)
    public void onAlreadyRegisteredClicked() {
        presenter.onAlreadyRegisteredClicked();
    }

    @Override
    public void registerUser(String token) {
        Log.d("LOGGER", "registerUser token: " + token);

        // set status loggined and save token
        prefsConfig.writeLoginStatus(true);
        prefsConfig.writeToken(token);

        // launch activity
        launchActivity();

        // turn off dialog
        progressDialog.dismiss();
    }

    @Override
    public void showSnackbar(String message) {
        progressDialog.dismiss();
        Snackbar.make(registerMainLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void openLoginPage() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void launchActivity() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

