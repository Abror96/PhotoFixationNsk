package com.example.photofixationnsk.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photofixationnsk.CompressAndConvertBitmap;
import com.example.photofixationnsk.R;
import com.example.photofixationnsk.RequestPermissionHandler;
import com.example.photofixationnsk.mvp.contracts.PhotoFixContract;
import com.example.photofixationnsk.mvp.interactors.PhotoFixRespInteractor;
import com.example.photofixationnsk.mvp.presenterImpls.PhotoFixPresenterImpl;
import com.example.photofixationnsk.retrofit.models.PhotoFix;
import com.example.photofixationnsk.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;

import static com.example.photofixationnsk.activities.MainActivity.counter;

public class PhotoFixActivity extends AppCompatActivity implements PhotoFixContract.View {

    @BindView(R.id.photoFixLayout)
    NestedScrollView photoFixLayout;
    @BindView(R.id.addPhoto1)
    TextView addPhoto1;
    @BindView(R.id.addPhoto2)
    TextView addPhoto2;
    @BindView(R.id.addPhoto3)
    TextView addPhoto3;
    @BindView(R.id.addPhoto4)
    TextView addPhoto4;
    @BindView(R.id.addPhoto5)
    TextView addPhoto5;
    @BindView(R.id.addPhoto6)
    TextView addPhoto6;
    @BindView(R.id.addPhoto7)
    TextView addPhoto7;
    @BindView(R.id.addPhoto8)
    TextView addPhoto8;
    @BindView(R.id.addComment)
    TextView addComment;
    @BindView(R.id.carName)
    TextView carName;
    @BindView(R.id.carNumber)
    TextView carNumber;


    private long carId;
    private PhotoFixContract.Presenter presenter;
    private Bitmap pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8;
    private MultipartBody.Part img1, img2, img3, img4, img5, img6, img7, img8;
    private ProgressDialog progressDialog;
    private RequestPermissionHandler mRequestPermissionHandler;
    private Bundle extras;
    private String comment = "";
    private AlertDialog alertDialog;
    private String pictureImagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_fix);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            carId = getIntent().getLongExtra("carId", -1);
            carName.setText(getIntent().getStringExtra("carName"));
            carNumber.setText(getIntent().getStringExtra("carNumber"));
        }

        presenter = new PhotoFixPresenterImpl(this, new PhotoFixRespInteractor());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Идет загрузка");
        progressDialog.setCanceledOnTouchOutside(false);

        // init permission handler
        mRequestPermissionHandler = new RequestPermissionHandler();
    }

    @OnClick(R.id.addPhoto1)
    public void onAddPhoto1Clicked() {
        uploadImage(1111);
    }

    @OnClick(R.id.addPhoto2)
    public void onAddPhoto2Clicked() {
        uploadImage(2222);
    }

    @OnClick(R.id.addPhoto3)
    public void onAddPhoto3Clicked() {
        uploadImage(3333);
    }

    @OnClick(R.id.addPhoto4)
    public void onAddPhoto4Clicked() {
        uploadImage(4444);
    }

    @OnClick(R.id.addPhoto5)
    public void onAddPhoto5Clicked() {
        uploadImage(5555);
    }

    @OnClick(R.id.addPhoto6)
    public void onAddPhoto6Clicked() {
        uploadImage(6666);
    }

    @OnClick(R.id.addPhoto7)
    public void onAddPhoto7Clicked() {
        uploadImage(7777);
    }

    @OnClick(R.id.addPhoto8)
    public void onAddPhoto8Clicked() {
        uploadImage(8888);
    }

    @OnClick(R.id.addComment)
    public void onAddCommentClicked() {
        final EditText editText = new EditText(getApplicationContext());
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        linearLayout.setLayoutParams(lp);
        linearLayout.setPadding(40, 0, 40, 0);
        editText.setLayoutParams(lp);
        editText.setText("");
        linearLayout.addView(editText);

        AlertDialog.Builder builder = new AlertDialog.Builder(PhotoFixActivity.this);
        builder.setTitle("Напишите свой отзыв");
        builder.setView(linearLayout);
        builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                comment = editText.getText().toString();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    @OnClick(R.id.send)
    public void onSaveClicked() {
        if ( pic1 != null& pic2 != null& pic3 != null & pic4 != null) {
            if(!comment.isEmpty()) {
                presenter.onSaveClicked(
                        String.valueOf(carId), comment,img1, img2,img3,img4,img5,img6,img7,img8, getApplicationContext());
                progressDialog.show();
            } else {
                Snackbar.make(photoFixLayout, "Добавьте комментарий", Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(photoFixLayout, "Выберите фото", Snackbar.LENGTH_LONG).show();
        }

    }


    private void uploadImage(final int reqCode) {
        mRequestPermissionHandler.requestPermission(this, 777,
                new RequestPermissionHandler.RequestPermissionListener() {
                    @Override
                    public void onSuccess() {
                        File myDir = new File(Environment.getExternalStorageDirectory() + "/req_images");
                        myDir.mkdirs();
                        String nameImg = "";
                        switch (reqCode) {
                            case 1111:
                                nameImg = "front";
                                break;
                            case 2222:
                                nameImg = "back";
                                break;
                            case 3333:
                                nameImg = "left";
                                break;
                            case 4444:
                                nameImg = "right";
                                break;
                            case 5555:
                                nameImg = "add1";
                                break;
                            case 6666:
                                nameImg = "add2";
                                break;
                            case 7777:
                                nameImg = "add3";
                                break;
                            case 8888:
                                nameImg = "add4";
                                break;
                        }
                        File f = new File(myDir, nameImg+".jpg");
                        Uri outputFileUri = Uri.fromFile(f);

                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                        if (intent.resolveActivity(getPackageManager()) != null) {
                            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                            StrictMode.setVmPolicy(builder.build());
                            startActivityForResult(intent, reqCode);
                        }
                    }
                    @Override
                    public void onFailed() {
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String _path;
            switch (requestCode) {
                case 1111:
                    addPhoto1.setText("Добавлено");
                    _path = Environment.getExternalStorageDirectory() + "/req_images/" + "front.jpg";
                    pic1 = BitmapFactory.decodeFile(_path);
                    img1 = new CompressAndConvertBitmap().compressBitmap(pic1,
                            getApplicationContext(),
                            "photo_front",
                            "front");
                    //pic1 = BitmapFactory.decodeFile(_path);
                    break;
                case 2222:
                    addPhoto2.setText("Добавлено");
                    _path = Environment.getExternalStorageDirectory() + "/req_images/" + "back.jpg";
                    pic2 = BitmapFactory.decodeFile(_path);
                    img2 = new CompressAndConvertBitmap().compressBitmap(pic2,
                            getApplicationContext(),
                            "photo_back",
                            "back");
                    //pic2 = BitmapFactory.decodeFile(_path);
                    break;
                case 3333:
                    addPhoto3.setText("Добавлено");
                    _path = Environment.getExternalStorageDirectory() + "/req_images/" + "left.jpg";
                    pic3 = BitmapFactory.decodeFile(_path);
                    img3 = new CompressAndConvertBitmap().compressBitmap(pic3,
                            getApplicationContext(),
                            "photo_left",
                            "left");
                    //pic3 = BitmapFactory.decodeFile(_path);
                    break;
                case 4444:
                    addPhoto4.setText("Добавлено");
                    _path = Environment.getExternalStorageDirectory() + "/req_images/" + "right.jpg";
                    pic4 = BitmapFactory.decodeFile(_path);
                    img4 = new CompressAndConvertBitmap().compressBitmap(pic4,
                            getApplicationContext(),
                            "photo_right",
                            "right");
                    //pic4 = BitmapFactory.decodeFile(_path);
                    break;
                case 5555:
                    addPhoto5.setText("Добавлено");
                    _path = Environment.getExternalStorageDirectory() + "/req_images/" + "add1.jpg";
                    pic5 = BitmapFactory.decodeFile(_path);
                    img5 = new CompressAndConvertBitmap().compressBitmap(pic5,
                            getApplicationContext(),
                            "photo_add_1",
                            "add1");
                    //pic5 = BitmapFactory.decodeFile(_path);
                    break;
                case 6666:
                    addPhoto6.setText("Добавлено");
                    _path = Environment.getExternalStorageDirectory() + "/req_images/" + "add2.jpg";
                    pic6 = BitmapFactory.decodeFile(_path);
                    img6 = new CompressAndConvertBitmap().compressBitmap(pic6,
                            getApplicationContext(),
                            "photo_add_2",
                            "add2");
                    //pic6 = BitmapFactory.decodeFile(_path);
                    break;
                case 7777:
                    addPhoto7.setText("Добавлено");
                    _path = Environment.getExternalStorageDirectory() + "/req_images/" + "add3.jpg";
                    pic7 = BitmapFactory.decodeFile(_path);
                    img7 = new CompressAndConvertBitmap().compressBitmap(pic7,
                            getApplicationContext(),
                            "photo_add_3",
                            "add3");
                    //pic7 = BitmapFactory.decodeFile(_path);
                    break;
                case 8888:
                    addPhoto8.setText("Добавлено");
                    _path = Environment.getExternalStorageDirectory() + "/req_images/"+ "add4.jpg";
                    pic8 = BitmapFactory.decodeFile(_path);
                    img8 = new CompressAndConvertBitmap().compressBitmap(pic8,
                            getApplicationContext(),
                            "photo_add_4",
                            "add4");
                    //pic8 = BitmapFactory.decodeFile(_path);
                    break;
            }
        }
    }


    @Override
    public void showSnackbar(String message) {
        progressDialog.dismiss();
        Snackbar.make(photoFixLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void updateView(PhotoFixContract.View view) {

    }

    @Override
    public void returnBack() {
        progressDialog.dismiss();
        Intent intent = new Intent();
        setResult(4567, intent);
        finish();
    }
}
