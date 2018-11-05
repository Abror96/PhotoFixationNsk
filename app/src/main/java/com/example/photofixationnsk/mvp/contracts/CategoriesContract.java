package com.example.photofixationnsk.mvp.contracts;

import android.content.Context;

import com.example.photofixationnsk.retrofit.models.Cars;
import com.example.photofixationnsk.retrofit.models.Categories;
import com.example.photofixationnsk.retrofit.models.PhotoFix;

import java.util.ArrayList;

public interface CategoriesContract {

    interface View {

        void showCategoriesList(ArrayList<Categories> categoriesArrayList);

        void showSnackbar(String message);

        void showCarsList(ArrayList<Cars> carsArrayList);

        void showProgressDialog();

        void sendPhotoFixList(ArrayList<PhotoFix> photoFixes, String carName, String carNumber, long id);

    }

    interface Presenter {
        void onGetCategoriesCalled(Context context);

        void onCategoryClicked(long id, Context context);

        void onShowSnackbarCalled();

        void onCarClicked(long id, String carName, String carNumber, Context context);
    }

    interface Interactor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Categories> categories);
            void onCarsFinished(ArrayList<Cars> carsArrayList);
            void onPhotoFixFinished(ArrayList<PhotoFix> photoFixes, String carName, String carNumber, long id);
            void onFailure(String message);
        }
        void getCategoriesList(OnFinishedListener onFinishedListener, Context context);
        void getCarsList(OnFinishedListener onFinishedListener, Context context, long id, String number);
        void getPhotoFixList(OnFinishedListener onFinishedListener, String carName,
                             String carNumber, Context context, long id);

    }

}
