package com.example.photofixationnsk.activities;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.photofixationnsk.Constants;
import com.example.photofixationnsk.R;
import com.example.photofixationnsk.SharedPrefs.PrefsConfig;
import com.example.photofixationnsk.mvp.contracts.CarPhotosContract;
import com.example.photofixationnsk.mvp.interactors.CarPhotosRespInteractor;
import com.example.photofixationnsk.mvp.presenterImpls.CarPhotosPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarPhotosActivity extends AppCompatActivity implements CarPhotosContract.View {

    @BindView(R.id.carName)
    TextView carName;
    @BindView(R.id.carNumber)
    TextView carNumber;
    @BindView(R.id.carPhotosLayout)
    NestedScrollView carPhotosLayout;
    @BindView(R.id.carComment)
    TextView carComment;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.image4)
    ImageView image4;
    @BindView(R.id.image5)
    ImageView image5;
    @BindView(R.id.image6)
    ImageView image6;
    @BindView(R.id.image7)
    ImageView image7;
    @BindView(R.id.image8)
    ImageView image8;


    private long carId;
    private CarPhotosContract.Presenter presenter;
    private PrefsConfig prefsConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_photos);
        ButterKnife.bind(this);

        presenter = new CarPhotosPresenterImpl(this, new CarPhotosRespInteractor());
        prefsConfig = new PrefsConfig(this);

        if (getIntent().getExtras() != null) {
            carName.setText(getIntent().getStringExtra("carName"));
            carNumber.setText(getIntent().getStringExtra("carNumber"));
            carId = getIntent().getLongExtra("carId", -1);

            presenter.onGetDataCalled(carId, getApplicationContext());
        }

    }

    @Override
    public void showData(String comment, String photo1, String photo2, String photo3, String photo4,
                         String photo5, String photo6, String photo7, String photo8) {
        carComment.setText(comment);

        loadImageToView(photo1, image1);
        loadImageToView(photo2, image2);
        loadImageToView(photo3, image3);
        loadImageToView(photo4, image4);
        loadImageToView(photo5, image5);
        loadImageToView(photo6, image6);
        loadImageToView(photo7, image7);
        loadImageToView(photo8, image8);

    }

    private void loadImageToView(String url, ImageView view) {
        Glide.with(this)
                .load(Constants
                        .getUrlWithHeaders(Constants.BASE_URL + url, prefsConfig.readToken()))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC).skipMemoryCache(false).placeholder(R.drawable.placeholder))
                .into(view);
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(carPhotosLayout, message, Snackbar.LENGTH_LONG).show();
    }
}
