package com.example.photofixationnsk.mvp.contracts;

import android.content.Context;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface PhotoFixContract {

    interface View {

        void showSnackbar(String message);

        void updateView(View view);

        void returnBack();

    }

    interface Presenter {

        void onViewClicked(View view);

        void onSaveClicked(String car_id, String comment, MultipartBody.Part photo_front, MultipartBody.Part photo_back,
                           MultipartBody.Part photo_left, MultipartBody.Part photo_right,
                           MultipartBody.Part photo_add_1, MultipartBody.Part photo_add_2,
                           MultipartBody.Part photo_add_3, MultipartBody.Part photo_add_4, Context context);

    }

    interface Interactor {

        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }
        void addNewPhotoFix(OnFinishedListener onFinishedListener, RequestBody car_id,
                            RequestBody comment, MultipartBody.Part photo_front, MultipartBody.Part photo_back,
                            MultipartBody.Part photo_left, MultipartBody.Part photo_right,
                            MultipartBody.Part photo_add_1, MultipartBody.Part photo_add_2,
                            MultipartBody.Part photo_add_3, MultipartBody.Part photo_add_4, Context context);

    }

}
