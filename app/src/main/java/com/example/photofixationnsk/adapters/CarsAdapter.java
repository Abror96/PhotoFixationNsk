package com.example.photofixationnsk.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photofixationnsk.R;
import com.example.photofixationnsk.mvp.contracts.CategoriesContract;
import com.example.photofixationnsk.retrofit.models.Cars;

import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsViewHolder> {

    ArrayList<Cars> carsArrayList = new ArrayList<>();
    Context context;
    CategoriesContract.Presenter presenter;

    public CarsAdapter(ArrayList<Cars> carsArrayList, Context context, CategoriesContract.Presenter presenter) {
        this.carsArrayList = carsArrayList;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.cars_item, viewGroup, false);
        return new CarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, final int position) {
        holder.carName.setText(carsArrayList.get(position).getName());
        holder.carNumber.setText(carsArrayList.get(position).getNumber());

        holder.carsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCarClicked(
                        Long.parseLong(String.valueOf(carsArrayList.get(position).getId())),
                        carsArrayList.get(position).getName(),
                        carsArrayList.get(position).getNumber(),
                        context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carsArrayList.size();
    }

    class CarsViewHolder extends RecyclerView.ViewHolder {

        private TextView carName, carNumber;
        LinearLayout carsItem;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);

            carName = itemView.findViewById(R.id.carName);
            carNumber = itemView.findViewById(R.id.carNumber);
            carsItem = itemView.findViewById(R.id.carsItem);
        }
    }
}
