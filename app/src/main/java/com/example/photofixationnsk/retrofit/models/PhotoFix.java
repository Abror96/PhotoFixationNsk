package com.example.photofixationnsk.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotoFix implements Serializable {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("carId")
    @Expose
    private Long carId;
    @SerializedName("dateFixation")
    @Expose
    private Long dateFixation;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("photoFront")
    @Expose
    private Object photoFront;
    @SerializedName("photoBack")
    @Expose
    private Object photoBack;
    @SerializedName("photoRight")
    @Expose
    private Object photoRight;
    @SerializedName("photoLeft")
    @Expose
    private Object photoLeft;
    @SerializedName("photoAdd1")
    @Expose
    private Object photoAdd1;
    @SerializedName("photoAdd2")
    @Expose
    private Object photoAdd2;
    @SerializedName("photoAdd3")
    @Expose
    private Object photoAdd3;
    @SerializedName("photoAdd4")
    @Expose
    private Object photoAdd4;

    public Long getId() {
        return id;
    }

    public Long getCarId() {
        return carId;
    }

    public Long getDateFixation() {
        return dateFixation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Object getPhotoFront() {
        return photoFront;
    }

    public void setPhotoFront(Object photoFront) {
        this.photoFront = photoFront;
    }

    public Object getPhotoBack() {
        return photoBack;
    }

    public void setPhotoBack(Object photoBack) {
        this.photoBack = photoBack;
    }

    public Object getPhotoRight() {
        return photoRight;
    }

    public void setPhotoRight(Object photoRight) {
        this.photoRight = photoRight;
    }

    public Object getPhotoLeft() {
        return photoLeft;
    }

    public void setPhotoLeft(Object photoLeft) {
        this.photoLeft = photoLeft;
    }

    public Object getPhotoAdd1() {
        return photoAdd1;
    }

    public void setPhotoAdd1(Object photoAdd1) {
        this.photoAdd1 = photoAdd1;
    }

    public Object getPhotoAdd2() {
        return photoAdd2;
    }

    public void setPhotoAdd2(Object photoAdd2) {
        this.photoAdd2 = photoAdd2;
    }

    public Object getPhotoAdd3() {
        return photoAdd3;
    }

    public void setPhotoAdd3(Object photoAdd3) {
        this.photoAdd3 = photoAdd3;
    }

    public Object getPhotoAdd4() {
        return photoAdd4;
    }

    public void setPhotoAdd4(Object photoAdd4) {
        this.photoAdd4 = photoAdd4;
    }

}

