package com.gollachut.bd.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gollachut.bd.CategoryActivity;
import com.gollachut.bd.Model.CategoryModel;
import com.gollachut.bd.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categort_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder viewHolder, int position) {
        String icon = categoryModelList.get(position).getCategoryIconName();
        String name = categoryModelList.get(position).getCategoryName();
//        Log.i("adaptericon",icon);
//        Log.i("adaptername",name);
        viewHolder.setCategory(name,position);
        viewHolder.setCategoryIcon(icon);


    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcon;
        private TextView categoryName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryIcon=itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);

        }

        private void setCategoryIcon(String iconUrl){
            if(!iconUrl.equals("null")){
                Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.mipmap.home)).into(categoryIcon);
            }


        }

        private void setCategory(final String name,final int position){
            categoryName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position !=0){
                        Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                        categoryIntent.putExtra("categoryName", name);
                        itemView.getContext().startActivity(categoryIntent);

                    }


                }
            });
        }
    }
}