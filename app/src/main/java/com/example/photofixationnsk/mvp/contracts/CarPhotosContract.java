package com.example.photofixationnsk.mvp.contracts;

import android.content.Context;

public interface CarPhotosContract {

    interface View {

        void showData(String comment, String photo1, String photo2, String photo3, String photo4,
                      String photo5, String photo6, String photo7, String photo8);
        void showSnackbar(String message);

    }

    interface Presenter {

        void onGetDataCalled(long id, Context context);

    }

    interface Interactor {

        interface OnFinishedListener {
            void onFinish(String comment, String photo1,
                          String photo2, String photo3, String photo4,
                          String photo5, String photo6, String photo7, String photo8);
            void onFailure(String message);
        }
        void getCarPhotos(OnFinishedListener onFinishedListener, long id, Context context);
    }

}
