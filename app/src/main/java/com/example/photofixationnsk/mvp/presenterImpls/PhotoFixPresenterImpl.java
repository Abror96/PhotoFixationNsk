package com.example.photofixationnsk.mvp.presenterImpls;

import android.content.Context;

import com.example.photofixationnsk.mvp.contracts.PhotoFixContract;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PhotoFixPresenterImpl implements PhotoFixContract.Presenter, PhotoFixContract.Interactor.OnFinishedListener {

    private PhotoFixContract.View view;
    private PhotoFixContract.Interactor interactor;

    public PhotoFixPresenterImpl(PhotoFixContract.View view, PhotoFixContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onViewClicked(PhotoFixContract.View view) {
        if (view != null){
            view.updateView(view);
        }
    }

    @Override
    public void onSaveClicked(String car_id, String comment, MultipartBody.Part photo_front,
                              MultipartBody.Part photo_back, MultipartBody.Part photo_left,

                              MultipartBody.Part photo_right, MultipartBody.Part photo_add_1,
                              MultipartBody.Part photo_add_2, MultipartBody.Part photo_add_3,
                              MultipartBody.Part photo_add_4, Context context) {

        if (view != null) {

            RequestBody carIdRB =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), car_id);

            RequestBody commentRB =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"), comment);


            interactor.addNewPhotoFix(this, carIdRB, commentRB, photo_front, photo_back,
                    photo_left, photo_right, photo_add_1, photo_add_2, photo_add_3, photo_add_4, context);
        }
    }

    @Override
    public void onFinished() {
        if (view != null) {
            view.returnBack();
        }
    }

    @Override
    public void onFailure(String message) {

    }
}

