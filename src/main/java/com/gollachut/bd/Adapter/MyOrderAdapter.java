package com.gollachut.bd.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gollachut.bd.Model.MyOrderItemModal;
import com.gollachut.bd.OrderDetailsActivity;
import com.gollachut.bd.R;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private List<MyOrderItemModal> myOrderItemModalList;

    public MyOrderAdapter(List<MyOrderItemModal> myOrderItemModalList) {
        this.myOrderItemModalList = myOrderItemModalList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_order_item_layout,viewGroup,false);
      return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {

        int resource = myOrderItemModalList.get(position).getProductsImage();
        int rating = myOrderItemModalList.get(position).getRatting();
        String title = myOrderItemModalList.get(position).getProductTitle();
        String deliveryDate = myOrderItemModalList.get(position).getDeliveryStatus();

        holder.setData(resource,title,deliveryDate,rating);


    }

    @Override
    public int getItemCount() {
        return myOrderItemModalList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

         private ImageView productImage;
         private ImageView orderIndicator;
         private TextView productTitle;
         private TextView deliveryStatus;
         private LinearLayout rateNowConatiner;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
             productTitle = itemView.findViewById(R.id.product_title);
            deliveryStatus = itemView.findViewById(R.id.order_delivery_date);
            rateNowConatiner = itemView.findViewById(R.id.rate_now_container);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent orderDetailsInten = new Intent(itemView.getContext(), OrderDetailsActivity.class);
                    itemView.getContext().startActivity(orderDetailsInten);
                }
            });
        }

        private void setData(int resources, String title, String deliveryDate, int rating){
            productImage.setImageResource(resources);
            productTitle.setText(title);
//            // check if deliveryDate id cancelled
//            if(deliveryDate.equals("Cancelled")){
////
//                orderIndicator.setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary));
//            }else {
////
//                orderIndicator.setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.successGreen));
//            }
            deliveryStatus.setText(deliveryDate);
            //// rating Layout
            setRating(rating);
            for(int x =0; x< rateNowConatiner.getChildCount(); x++){
                final int starPosition = x;
                rateNowConatiner.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setRating(starPosition);

                    }
                });

            }
            //// rating Layout
        }// end of setData

        //// rating Layout
        private void setRating(int starPosition){
            for(int x= 0; x <rateNowConatiner.getChildCount(); x++){
                ImageView starBtn = (ImageView)rateNowConatiner.getChildAt(x);
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if(x <= starPosition){
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));

                }

            }

        }

        //// rating Layout


    }// end of viewHolder


}
