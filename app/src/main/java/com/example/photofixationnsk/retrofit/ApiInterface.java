package com.example.photofixationnsk.retrofit;

import com.example.photofixationnsk.retrofit.models.CarPhoto;
import com.example.photofixationnsk.retrofit.models.CarPhotoFixation;
import com.example.photofixationnsk.retrofit.models.Cars;
import com.example.photofixationnsk.retrofit.models.Categories;
import com.example.photofixationnsk.retrofit.models.PhotoFix;
import com.example.photofixationnsk.retrofit.models.RegAndAuthResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("registration")
    Call<RegAndAuthResponse> registerUser(@Query("login") String login,
                                          @Query("password") String password);

    @GET("authorization")
    Call<RegAndAuthResponse> authUser(@Header("Authorization") String token);

    @GET("category/get")
    Call<ArrayList<Categories>> getCategoriesList(@Header("Authorization") String token);

    @GET("car/get")
    Call<ArrayList<Cars>> getCarsList(@Header("Authorization") String token,
                                      @Query("category") long id,
                                      @Query("number") String number);

    @GET("fix/photofixlast5/{carId}")
    Call<ArrayList<PhotoFix>> getPhotoFixes(@Header("Authorization") String token,
                                            @Path("carId") long carId);

    @GET("fix/photofix/{id}")
    Call<CarPhotoFixation> getCarPhoto(@Header("Authorization") String token,
                                       @Path("id") long id);

    @Multipart
    @POST("fix/putphotofix")
    Call<RegAndAuthResponse> addNewPhotoFix(@Header("Authorization") String token,
                                            @Part("car_id") RequestBody car_id,
                                            @Part("comment") RequestBody comment,
                                            @Part MultipartBody.Part photo_front,
                                            @Part MultipartBody.Part photo_back,
                                            @Part MultipartBody.Part photo_left,
                                            @Part MultipartBody.Part photo_right,
                                            @Part MultipartBody.Part photo_add_1,
                                            @Part MultipartBody.Part photo_add_2,
                                            @Part MultipartBody.Part photo_add_3,
                                            @Part MultipartBody.Part photo_add_4);

}
