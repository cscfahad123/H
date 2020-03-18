package com.gollachut.bd.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gollachut.bd.Model.MyRewardModal;
import com.gollachut.bd.ProductsDetailsActivity;
import com.gollachut.bd.R;

import java.util.List;

public class MyRewardAdapter extends RecyclerView.Adapter<MyRewardAdapter.ViewHolder> {

    private List<MyRewardModal> myRewardModalList;
    private Boolean useMiniLayout = false;

    public MyRewardAdapter(List<MyRewardModal> myRewardModalList, boolean useMiniLayout) {
        this.myRewardModalList = myRewardModalList;
        this.useMiniLayout = useMiniLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if(useMiniLayout){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mini_rewards_item_layout,viewGroup,false);
        }else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rewards_item_layout,viewGroup,false);
        }

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title = myRewardModalList.get(position).getTitle();
        String date = myRewardModalList.get(position).getExpiryDate();
        String body = myRewardModalList.get(position).getCouponBody();
        holder.setData(title,date,body);

    }

    @Override
    public int getItemCount() {
        return myRewardModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView couponTitle;
        private TextView couponExpiryDate;
        private TextView couponBody;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            couponTitle= itemView.findViewById(R.id.coupon_title);
            couponExpiryDate = itemView.findViewById(R.id.coupon_validity);
            couponBody = itemView.findViewById(R.id.coupon_body);
        }

        private void setData(final String title,final String couponExpiary,final String coupon_Body){
            couponTitle.setText(title);
            couponExpiryDate.setText(couponExpiary);
            couponBody.setText(coupon_Body);

            if(useMiniLayout){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProductsDetailsActivity.couponTitle.setText(title);
                        ProductsDetailsActivity.couponExparyDate.setText(couponExpiary);
                        ProductsDetailsActivity.couponBody.setText(coupon_Body);
                        ProductsDetailsActivity.showDialogRecyclerView();

                    }
                });
            }
        }
    }
}
