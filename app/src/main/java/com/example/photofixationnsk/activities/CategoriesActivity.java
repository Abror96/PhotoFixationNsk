package com.example.photofixationnsk.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.photofixationnsk.R;
import com.example.photofixationnsk.adapters.CarsAdapter;
import com.example.photofixationnsk.adapters.CategoriesAdapter;
import com.example.photofixationnsk.mvp.contracts.CategoriesContract;
import com.example.photofixationnsk.mvp.interactors.CategoriesRespInteractor;
import com.example.photofixationnsk.mvp.presenterImpls.CategoriesPresenterImpl;
import com.example.photofixationnsk.retrofit.models.Cars;
import com.example.photofixationnsk.retrofit.models.Categories;
import com.example.photofixationnsk.retrofit.models.PhotoFix;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity implements CategoriesContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.categoriesMainLayout)
    ConstraintLayout categoriesMainLayout;


    private CategoriesContract.Presenter presenter;
    private ArrayList<Categories> categoriesArrayList;
    private boolean isCarsList = false;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        presenter = new CategoriesPresenterImpl(this, new CategoriesRespInteractor());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Идёт загрузка");
        progressDialog.setCanceledOnTouchOutside(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter.onGetCategoriesCalled(getApplicationContext());
        progressDialog.show();

    }

    @Override
    public void showCategoriesList(ArrayList<Categories> categoriesArrayList) {
        progressDialog.dismiss();
        CategoriesAdapter adapter = new CategoriesAdapter(categoriesArrayList, getApplicationContext(), presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showSnackbar(String message) {
        progressDialog.dismiss();
        Snackbar.make(categoriesMainLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showCarsList(ArrayList<Cars> carsArrayList) {
        progressDialog.dismiss();
        CarsAdapter adapter = new CarsAdapter(carsArrayList, this, presenter);
        recyclerView.setAdapter(adapter);
        isCarsList = true;
    }

    @Override
    public void showProgressDialog() {
        try {
            progressDialog.show();
        } catch (Exception ignored) {}
    }

    @Override
    public void sendPhotoFixList(ArrayList<PhotoFix> photoFixes, String carName, String carNumber, long id) {
        Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("photoFixesList", photoFixes);
        bundle.putString("carName", carName);
        bundle.putString("carNumber", carNumber);
        bundle.putLong("carId", id);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (isCarsList) {
            presenter.onGetCategoriesCalled(getApplicationContext());
            progressDialog.show();
            isCarsList = false;
        } else super.onBackPressed();
    }
}
