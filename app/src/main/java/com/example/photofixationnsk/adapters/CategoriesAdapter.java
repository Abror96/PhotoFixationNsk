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
import com.example.photofixationnsk.mvp.contracts.CategoriesContract;
import com.example.photofixationnsk.retrofit.models.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private ArrayList<Categories> categoriesArrayList;
    private Context context;
    private CategoriesContract.Presenter presenter;

    public CategoriesAdapter(ArrayList<Categories> categoriesArrayList, Context context,
                             CategoriesContract.Presenter presenter) {
        this.categoriesArrayList = categoriesArrayList;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.categories_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        holder.categoryName.setText(categoriesArrayList.get(position).getName());

        holder.categoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onShowSnackbarCalled();
                presenter.onCategoryClicked(
                        Long.parseLong(categoriesArrayList.get(position).getId()),
                        context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        LinearLayout categoryItem;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.categoryName);
            categoryItem = itemView.findViewById(R.id.categoryItem);
        }
    }
}
