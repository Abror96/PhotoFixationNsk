package com.example.photofixationnsk.mvp.presenterImpls;

import android.content.Context;
import android.util.Log;

import com.example.photofixationnsk.mvp.contracts.CategoriesContract;
import com.example.photofixationnsk.retrofit.models.Cars;
import com.example.photofixationnsk.retrofit.models.Categories;
import com.example.photofixationnsk.retrofit.models.PhotoFix;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPresenterImpl implements CategoriesContract.Presenter, CategoriesContract.Interactor.OnFinishedListener {

    private CategoriesContract.View view;
    private CategoriesContract.Interactor interactor;

    public CategoriesPresenterImpl(CategoriesContract.View view, CategoriesContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onGetCategoriesCalled(Context context) {
        if (view != null) {
            interactor.getCategoriesList(this, context);
        }
    }

    @Override
    public void onCategoryClicked(long id, Context context) {
        if (view != null) {
            interactor.getCarsList(this, context, id, "");
        }
    }

    @Override
    public void onShowSnackbarCalled() {
        if (view != null) {
            view.showProgressDialog();
        }
    }

    @Override
    public void onCarClicked(long id, String carName, String carNumber, Context context) {
        interactor.getPhotoFixList(this, carName, carNumber, context, id);
        if (view != null) {
            view.showProgressDialog();
        }
    }

    @Override
    public void onFinished(ArrayList<Categories> categories) {
        if (view != null) {
            view.showCategoriesList(categories);
        }
    }

    @Override
    public void onCarsFinished(ArrayList<Cars> carsArrayList) {
        if (view != null) {
            view.showCarsList(carsArrayList);
        }
    }

    @Override
    public void onPhotoFixFinished(ArrayList<PhotoFix> photoFixes, String carName, String carNumber, long id) {
        if (view != null) {
            view.sendPhotoFixList(photoFixes, carName, carNumber, id);
        }
    }

    @Override
    public void onFailure(String message) {
        if (view != null) {
            view.showSnackbar(message);
        }
    }
}

