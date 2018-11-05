package com.example.photofixationnsk.mvp.presenterImpls;

import android.content.Context;

import com.example.photofixationnsk.mvp.contracts.CarPhotosContract;

public class CarPhotosPresenterImpl implements CarPhotosContract.Presenter,
        CarPhotosContract.Interactor.OnFinishedListener {

    private CarPhotosContract.View view;
    private CarPhotosContract.Interactor interactor;

    public CarPhotosPresenterImpl(CarPhotosContract.View view, CarPhotosContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onGetDataCalled(long id, Context context) {
        if (view != null) {
            interactor.getCarPhotos(this, id, context);
        }
    }

    @Override
    public void onFinish(String comment, String photo1, String photo2, String photo3, String photo4,
                         String photo5, String photo6, String photo7, String photo8) {
        if (view != null) {
            view.showData(comment, photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8);
        }
    }

    @Override
    public void onFailure(String message) {
        if (view != null) {
            view.showSnackbar(message);
        }
    }
}

