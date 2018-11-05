package com.example.photofixationnsk.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarPhotoFixation {

    @SerializedName("photoFixation")
    @Expose
    private CarPhoto photoFixation;
    @SerializedName("urlPhotoFront")
    @Expose
    private String urlPhotoFront;
    @SerializedName("urlPhotoBack")
    @Expose
    private String urlPhotoBack;
    @SerializedName("urlPhotoLeft")
    @Expose
    private String urlPhotoLeft;
    @SerializedName("urlPhotoRihgt")
    @Expose
    private String urlPhotoRihgt;
    @SerializedName("urlPhotoAdd1")
    @Expose
    private String urlPhotoAdd1;
    @SerializedName("urlPhotoAdd2")
    @Expose
    private String urlPhotoAdd2;
    @SerializedName("urlPhotoAdd3")
    @Expose
    private String urlPhotoAdd3;
    @SerializedName("urlPhotoAdd4")
    @Expose
    private String urlPhotoAdd4;

    public CarPhoto getPhotoFixation() {
        return photoFixation;
    }

    public void setPhotoFixation(CarPhoto photoFixation) {
        this.photoFixation = photoFixation;
    }

    public String getUrlPhotoFront() {
        return urlPhotoFront;
    }

    public void setUrlPhotoFront(String urlPhotoFront) {
        this.urlPhotoFront = urlPhotoFront;
    }

    public String getUrlPhotoBack() {
        return urlPhotoBack;
    }

    public void setUrlPhotoBack(String urlPhotoBack) {
        this.urlPhotoBack = urlPhotoBack;
    }

    public String getUrlPhotoLeft() {
        return urlPhotoLeft;
    }

    public void setUrlPhotoLeft(String urlPhotoLeft) {
        this.urlPhotoLeft = urlPhotoLeft;
    }

    public String getUrlPhotoRihgt() {
        return urlPhotoRihgt;
    }

    public void setUrlPhotoRihgt(String urlPhotoRihgt) {
        this.urlPhotoRihgt = urlPhotoRihgt;
    }

    public String getUrlPhotoAdd1() {
        return urlPhotoAdd1;
    }

    public void setUrlPhotoAdd1(String urlPhotoAdd1) {
        this.urlPhotoAdd1 = urlPhotoAdd1;
    }

    public String getUrlPhotoAdd2() {
        return urlPhotoAdd2;
    }

    public void setUrlPhotoAdd2(String urlPhotoAdd2) {
        this.urlPhotoAdd2 = urlPhotoAdd2;
    }

    public String getUrlPhotoAdd3() {
        return urlPhotoAdd3;
    }

    public void setUrlPhotoAdd3(String urlPhotoAdd3) {
        this.urlPhotoAdd3 = urlPhotoAdd3;
    }

    public String getUrlPhotoAdd4() {
        return urlPhotoAdd4;
    }

    public void setUrlPhotoAdd4(String urlPhotoAdd4) {
        this.urlPhotoAdd4 = urlPhotoAdd4;
    }



}
