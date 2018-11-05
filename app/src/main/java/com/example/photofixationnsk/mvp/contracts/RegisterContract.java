package com.example.photofixationnsk.mvp.contracts;

public interface RegisterContract {

    interface View {

        void registerUser(String token);

        void showSnackbar(String message);

        void openLoginPage();

    }

    interface Presenter {

        void onRegisterClicked(String username, String password);
        void onAlreadyRegisteredClicked();

    }

    interface Interactor {

        interface OnFinishedListener {
            void onFinished(String token);
            void onFailure(String message);
        }
        void registerUser(OnFinishedListener onFinishedListener, String username, String password);

    }

}
