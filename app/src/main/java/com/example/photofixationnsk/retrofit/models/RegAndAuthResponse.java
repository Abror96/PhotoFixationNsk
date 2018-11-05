package com.example.photofixationnsk.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegAndAuthResponse {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("status")
    @Expose
    private String status;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

