package com.example.photofixationnsk.mvp.presenterImpls;

import android.content.Context;

import com.example.photofixationnsk.mvp.contracts.CategoriesContract;
import com.example.photofixationnsk.mvp.contracts.MainContract;
import com.example.photofixationnsk.retrofit.models.Cars;
import com.example.photofixationnsk.retrofit.models.Categories;
import com.example.photofixationnsk.retrofit.models.PhotoFix;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.Presenter, MainContract.Interactor.OnFinishedListener {

    private MainContract.View view;
    private MainContract.Interactor interactor;
    private CategoriesContract.Interactor categoriesInteractor;

    public MainPresenterImpl(MainContract.View view, MainContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onChooseCarClicked() {
        if (view != null) {
            view.chooseCar();
        }
    }

    @Override
    public void onNewPhotoFixClicked() {
        if (view != null) {
            view.newPhotoFix();
        }
    }

    @Override
    public void onPhotoFixClicked(long id, Context context) {
        if (view != null) {
            view.openPhotoFix(id);
        }
    }


    @Override
    public void onFinished(ArrayList<PhotoFix> photoFixes) {
        if (view != null) {
            view.updatePhotoFixList(photoFixes);
        }
    }

    @Override
    public void onFailure(String message) {
        if (view != null) {
            view.showSnackbar(message);
        }
    }

    @Override
    public void onNewPhotoFixListCalled(long id, String carName, String carNumber, Context context) {
        interactor.getPhotoFixData(this, carName, carNumber, context, id);
    }
}
