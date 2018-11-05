package com.example.photofixationnsk.mvp.contracts;

public interface LoginContract {

    interface View {

        void showSnackbar(String message);

        void authUser(String token);

    }

    interface Presenter {

        void onLoginBtnClicked(String username, String password);

    }

    interface Interactor {

        interface OnFinishedListener {
            void onFinished(String token);
            void onFailure(String message);
        }
        void authUser(OnFinishedListener onFinishedListener, String token, String username, String password);

    }

}
