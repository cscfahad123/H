package com.gollachut.bd.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gollachut.bd.Model.HorizontalProductScrollmodal;
import com.gollachut.bd.ProductsDetailsActivity;
import com.gollachut.bd.R;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<HorizontalProductScrollmodal> horizontalProductScrollmodalList;

    public GridProductLayoutAdapter(List<HorizontalProductScrollmodal> horizontalProductScrollmodalList) {
        this.horizontalProductScrollmodalList = horizontalProductScrollmodalList;
    }

    @Override
    public int getCount() {
        return horizontalProductScrollmodalList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productDetailsInten = new Intent(parent.getContext(), ProductsDetailsActivity.class);
                    productDetailsInten.putExtra("PRODUCT_ID",horizontalProductScrollmodalList.get(position).getProductID());

                    parent.getContext().startActivity(productDetailsInten);
                }
            });


            ImageView productsImage = view.findViewById(R.id.hs_product_image);
            TextView productTitle = view.findViewById(R.id.hs_product_title);
            TextView productDescription = view.findViewById(R.id.hs_product_description);
            TextView productPrice = view.findViewById(R.id.hs_product_price);

            Glide.with(parent.getContext()).load(horizontalProductScrollmodalList.get(position).getProductImage()).apply(new RequestOptions().placeholder(R.mipmap.home_logo)).into(productsImage);
            productTitle.setText(horizontalProductScrollmodalList.get(position).getProductTitle());
            productDescription.setText(horizontalProductScrollmodalList.get(position).getProductDescription());
            productPrice.setText("TK."+horizontalProductScrollmodalList.get(position).getProductPrice()+"/-");


        }else{
            view = convertView;

        }

        return view;

    }
}
