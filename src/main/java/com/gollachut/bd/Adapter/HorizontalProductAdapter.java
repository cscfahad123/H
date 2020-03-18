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
import com.gollachut.bd.Model.HorizontalProductScrollmodal;
import com.gollachut.bd.ProductsDetailsActivity;
import com.gollachut.bd.R;

import java.util.List;

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.ViewHolder> {

    private List<HorizontalProductScrollmodal> horizontalProductScrollmodalList;

    public HorizontalProductAdapter(List<HorizontalProductScrollmodal> horizontalProductScrollmodalList) {
        this.horizontalProductScrollmodalList = horizontalProductScrollmodalList;
    }

    @NonNull
    @Override
    public HorizontalProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductAdapter.ViewHolder holder, int position) {
        String resources = horizontalProductScrollmodalList.get(position).getProductImage();
        String title = horizontalProductScrollmodalList.get(position).getProductTitle();
        String description = horizontalProductScrollmodalList.get(position).getProductDescription();
        String price = horizontalProductScrollmodalList.get(position).getProductPrice();
       // String product_ID =horizontalProductScrollmodalList.get(position).getProductID();
       String productid =horizontalProductScrollmodalList.get(position).getProductID();


        holder.setData(productid,resources,title,description,price);
    }

    @Override
    public int getItemCount() {
        if(horizontalProductScrollmodalList.size() > 8){
            return 8;
        }else{
            return horizontalProductScrollmodalList.size();

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // define all variable

        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.hs_product_image);
            productTitle = itemView.findViewById(R.id.hs_product_title);
            productDescription = itemView.findViewById(R.id.hs_product_description);
            productPrice = itemView.findViewById(R.id.hs_product_price);



        }

        private void setData(final String productid, String resource, String title, String description, String price){
            Glide.with(itemView.getContext()).load(resource)
                    .apply(new RequestOptions().placeholder(R.mipmap.home)).into(productImage);

            productPrice.setText("Bdt."+price+"/-");
            productDescription.setText(description);
            productTitle.setText(title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productsDetailsIntent = new Intent(itemView.getContext(), ProductsDetailsActivity.class);
                    //productsDetailsIntent.putExtra("product_ID",productid);
                  productsDetailsIntent.putExtra("PRODUCT_ID",productid);
                    itemView.getContext().startActivity(productsDetailsIntent);
                }
            });


        }









    }
}
