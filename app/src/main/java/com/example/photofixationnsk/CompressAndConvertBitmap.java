package com.example.photofixationnsk;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.example.photofixationnsk.activities.MainActivity.counter;

public class CompressAndConvertBitmap {
    MultipartBody.Part body;

    public MultipartBody.Part compressBitmap(Bitmap selectedImageBmp,
                                             Context context,
                                             String nameMultipartParam,
                                             String namePic) {

        Bitmap resizedBitmap;


        if (selectedImageBmp != null) {
            int width = 0, height = 0;

            if (selectedImageBmp.getWidth() < 800) {
                width = (int)(selectedImageBmp.getWidth()*0.9);
                height = (int)(selectedImageBmp.getHeight()*0.9);
            } else if (selectedImageBmp.getWidth() >= 800 && selectedImageBmp.getWidth() < 1500) {
                width = (int)(selectedImageBmp.getWidth()*0.5);
                height = (int)(selectedImageBmp.getHeight()*0.5);
            } else if (selectedImageBmp.getWidth() >= 1500) {
                width = (int)(selectedImageBmp.getWidth()*0.25);
                height = (int)(selectedImageBmp.getHeight()*0.25);
            }

            resizedBitmap = Bitmap.createScaledBitmap(selectedImageBmp, width, height, true);

            File myDir = new File(Environment.getExternalStorageDirectory() + "/req_images");
            myDir.mkdirs();

            File f = new File(myDir, namePic+".jpg");
            try {
                f.createNewFile();

                //Convert bitmap to byte array
                FileOutputStream fos = new FileOutputStream(f);
                resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 50 /*ignored for PNG*/, fos);

                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("LOGGER UPLOAD IMAGE", "compressBitmap: ошибка при создании файла");
            }

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpeg"), f);
            body = MultipartBody.Part.createFormData(nameMultipartParam, "image"+counter, reqFile);


        }

        return body;
    }
}
