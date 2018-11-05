package com.example.photofixationnsk.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photofixationnsk.R;
import com.example.photofixationnsk.adapters.PhotoFixAdapter;
import com.example.photofixationnsk.mvp.contracts.MainContract;
import com.example.photofixationnsk.mvp.interactors.MainResponseInteractor;
import com.example.photofixationnsk.mvp.presenterImpls.MainPresenterImpl;
import com.example.photofixationnsk.retrofit.models.PhotoFix;
import com.example.photofixationnsk.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.chooseCar)
    Button chooseCar;
    @BindView(R.id.newPhotoFix)
    Button newPhotoFix;
    @BindView(R.id.carName)
    TextView tvCarName;
    @BindView(R.id.carNumber)
    TextView tvCarNumber;
    @BindView(R.id.photoFixRecycler)
    RecyclerView photoFixRecycler;
    @BindView(R.id.carInfoLayout)
    LinearLayout carInfoLayout;
    @BindView(R.id.mainLayout)
    ConstraintLayout mainLayout;

    private MainContract.Presenter presenter;
    private String carName, carNumber;
    private long id;
    public static int counter = 1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenterImpl(this, new MainResponseInteractor());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Идёт загрузка");
        progressDialog.setCanceledOnTouchOutside(false);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            ArrayList<PhotoFix> photoFixes = (ArrayList<PhotoFix>) bundle.getSerializable("photoFixesList");

            carName = bundle.getString("carName");
            carNumber = bundle.getString("carNumber");
            id = bundle.getLong("carId");

            tvCarName.setText(carName);
            tvCarNumber.setText(carNumber);

            newPhotoFix.setVisibility(View.VISIBLE);
            carInfoLayout.setVisibility(View.VISIBLE);
            photoFixRecycler.setVisibility(View.VISIBLE);

            photoFixRecycler.setLayoutManager(new LinearLayoutManager(this));

            setDataToRecyclerView(photoFixes);
        }
    }

    private void setDataToRecyclerView(ArrayList<PhotoFix> photoFixesList) {
        PhotoFixAdapter adapter = new PhotoFixAdapter(photoFixesList, presenter, this);
        photoFixRecycler.setAdapter(adapter);
    }

    @OnClick(R.id.chooseCar)
    public void onChooseCarClicked() {
        presenter.onChooseCarClicked();
    }

    @OnClick(R.id.newPhotoFix)
    public void onNewPhotoFixClicked() {
        presenter.onNewPhotoFixClicked();
    }

    @Override
    public void chooseCar() {
        Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
        startActivity(intent);
    }

    @Override
    public void newPhotoFix() {
        Intent intent = new Intent(MainActivity.this, PhotoFixActivity.class);
        intent.putExtra("carName", carName);
        intent.putExtra("carNumber", carNumber);
        intent.putExtra("carId", id);
        startActivityForResult(intent, 4567);
    }

    @Override
    public void openPhotoFix(long id) {
        Intent intent = new Intent(MainActivity.this, CarPhotosActivity.class);
        intent.putExtra("carName", carName);
        intent.putExtra("carNumber", carNumber);
        intent.putExtra("carId", id);
        startActivity(intent);
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void updatePhotoFixList(ArrayList<PhotoFix> photoFixes) {
        progressDialog.dismiss();
        PhotoFixAdapter adapter = new PhotoFixAdapter(photoFixes, presenter, this);
        photoFixRecycler.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 4567) {
            presenter.onNewPhotoFixListCalled(id, carName, carNumber, getApplicationContext());
            progressDialog.show();
        }
    }
}
