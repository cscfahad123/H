package com.gollachut.bd.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gollachut.bd.Model.WishListModal;
import com.gollachut.bd.ProductsDetailsActivity;
import com.gollachut.bd.R;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private List<WishListModal> wishListModalList;
    private Boolean wishList;

    public WishListAdapter(List<WishListModal> wishListModalList, Boolean wishList) {
        this.wishListModalList = wishListModalList;
        this.wishList=wishList;
    }

    @NonNull
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wish_list_item_layout,viewGroup,false);
       return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.ViewHolder holder, int position) {
        String resourse = wishListModalList.get(position).getProductImage();
        String title = wishListModalList.get(position).getProductTitle();
        long freeCoupone = wishListModalList.get(position).getFreeCoupons();
        String rating = wishListModalList.get(position).getRatting();
        long totlaRating = wishListModalList.get(position).getTotalRatings();
        String price = wishListModalList.get(position).getProductPrice();
        String cuttedPrice = wishListModalList.get(position).getCuttedPrice();
        boolean paymentMethod = wishListModalList.get(position).isCOD();

        holder.setData(resourse, title, freeCoupone, rating, totlaRating,price,cuttedPrice,paymentMethod);






    }

    @Override
    public int getItemCount() {
        return wishListModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCoupons;
        private ImageView couponIcon;
        private TextView rating;
        private TextView totalRating;
        private View priceCutDevider;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private ImageButton deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage= itemView.findViewById(R.id.product_image);
            productTitle= itemView.findViewById(R.id.product_title);
            freeCoupons= itemView.findViewById(R.id.free_coupon);
            couponIcon= itemView.findViewById(R.id.coupon_image);
            rating= itemView.findViewById(R.id.single_product_rating_miniview);
            totalRating= itemView.findViewById(R.id.totla_ratings);
            productPrice= itemView.findViewById(R.id.product_price);
            cuttedPrice= itemView.findViewById(R.id.cutted_price);
            priceCutDevider= itemView.findViewById(R.id.price_cut);
            paymentMethod= itemView.findViewById(R.id.payment_method);
            deleteBtn= itemView.findViewById(R.id.delete_button);

        }

        private void setData(String resources,
                             String title,
                             long freeCouponsNo,
                             String averageRating,
                             long totalRatingsNo,
                             String price,
                             String cuttedPriceValue,
                             boolean COD){
            Glide.with(itemView.getContext()).load(resources).apply(new RequestOptions().placeholder(R.mipmap.home)).into(productImage);
            productTitle.setText(title);
            if(freeCouponsNo != 0){
                couponIcon.setVisibility(View.INVISIBLE);
                if(freeCouponsNo ==1){
                    freeCoupons.setText("free " +freeCouponsNo +" coupon");
                }else{
                    freeCoupons.setText("free " +freeCouponsNo +" coupons");

                }

            }else{
                couponIcon.setVisibility(View.INVISIBLE);
                freeCoupons.setVisibility(View.INVISIBLE);
            }

            rating.setText(averageRating);
            totalRating.setText("("+totalRatingsNo+")rating");
            productPrice.setText("TK."+price+"/-");
            cuttedPrice.setText("TK."+cuttedPriceValue+"/-");
            if(COD){
                paymentMethod.setVisibility(View.VISIBLE);
            }else{
                paymentMethod.setVisibility(View.INVISIBLE);
            }

            if(wishList){
                deleteBtn.setVisibility(View.VISIBLE);
            }else {
                deleteBtn.setVisibility(View.GONE);
            }

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"Delere",Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productDetails = new Intent(itemView.getContext(), ProductsDetailsActivity.class);
                    itemView.getContext().startActivity(productDetails);
                }
            });





        }
    }
}
