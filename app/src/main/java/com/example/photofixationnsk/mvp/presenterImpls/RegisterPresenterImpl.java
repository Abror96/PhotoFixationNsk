package com.example.photofixationnsk.mvp.presenterImpls;

import com.example.photofixationnsk.mvp.contracts.RegisterContract;

public class RegisterPresenterImpl implements RegisterContract.Presenter, RegisterContract.Interactor.OnFinishedListener {

    private RegisterContract.View view;
    private RegisterContract.Interactor interactor;

    public RegisterPresenterImpl(RegisterContract.View view, RegisterContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onRegisterClicked(String username, String password) {

        if (view != null) {
            interactor.registerUser(this, username, password);
        }
    }

    @Override
    public void onAlreadyRegisteredClicked() {
        if (view != null) {
            view.openLoginPage();
        }
    }

    @Override
    public void onFinished(String token) {
        if (view != null) {
            view.registerUser(token);
        }
    }

    @Override
    public void onFailure(String message) {
        if (view != null) {
            view.showSnackbar(message);
        }
    }
}
