package com.example.photofixationnsk.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.photofixationnsk.R;
import com.example.photofixationnsk.activities.MainActivity;
import com.example.photofixationnsk.mvp.contracts.MainContract;
import com.example.photofixationnsk.retrofit.models.PhotoFix;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhotoFixAdapter extends RecyclerView.Adapter<PhotoFixAdapter.PhotoFixViewHolder> {

    private ArrayList<PhotoFix> photoFixes;
    private MainContract.Presenter presenter;
    private Context context;

    public PhotoFixAdapter(ArrayList<PhotoFix> photoFixes, MainContract.Presenter presenter, Context context) {
        this.photoFixes = photoFixes;
        this.presenter = presenter;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoFixViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.photofix_item, viewGroup, false);
        return new PhotoFixViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoFixViewHolder holder, final int position) {
        holder.photoComment.setText(photoFixes.get(position).getComment());
        holder.photoDate.setText(getDate(photoFixes.get(position).getDateFixation()));
        holder.photoFixItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPhotoFixClicked(photoFixes.get(position).getId(), context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoFixes.size();
    }

    class PhotoFixViewHolder extends RecyclerView.ViewHolder {

        TextView photoComment, photoDate;
        LinearLayout photoFixItem;

        public PhotoFixViewHolder(@NonNull View itemView) {
            super(itemView);

            photoComment = itemView.findViewById(R.id.photoComment);
            photoDate = itemView.findViewById(R.id.photoDate);
            photoFixItem = itemView.findViewById(R.id.photoFixItem);
        }
    }

    public String getDate(long timeStamp){

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

