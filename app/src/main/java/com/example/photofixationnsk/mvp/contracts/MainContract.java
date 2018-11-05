package com.example.photofixationnsk.mvp.contracts;

import android.content.Context;

import com.example.photofixationnsk.retrofit.models.PhotoFix;

import java.util.ArrayList;

public interface MainContract {

    interface View {

        void chooseCar();

        void newPhotoFix();

        void openPhotoFix(long id);

        void showSnackbar(String message);

        void updatePhotoFixList(ArrayList<PhotoFix> photoFixes);

    }

    interface Presenter {

        void onChooseCarClicked();

        void onNewPhotoFixClicked();

        void onPhotoFixClicked(long id, Context context);

        void onNewPhotoFixListCalled(long id, String carName, String carNumber, Context context);

    }

    interface Interactor {

        interface OnFinishedListener {
            void onFinished(ArrayList<PhotoFix> photoFixes);
            void onFailure(String message);
        }
        void getPhotoFixData(OnFinishedListener onFinishedListener, String carName, String carNumber, Context context, long id);
    }

}
