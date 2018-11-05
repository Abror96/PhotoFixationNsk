package com.example.photofixationnsk;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Constants {

    public static String BASE_URL = "http://80.78.255.111/papi/";

    public static GlideUrl getUrlWithHeaders(String url, String token){
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", "Basic " + token)
                .build());
    }

    public static String getDate(long timeStamp){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }
}