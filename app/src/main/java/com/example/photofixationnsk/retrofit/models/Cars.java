package com.example.photofixationnsk.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cars {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("categoryCar")
    @Expose
    private CategoryCar categoryCar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CategoryCar getCategoryCar() {
        return categoryCar;
    }

    public void setCategoryCar(CategoryCar categoryCar) {
        this.categoryCar = categoryCar;
    }

}