package com.gollachut.bd.Adapter;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gollachut.bd.Model.CartItemModel;
import com.gollachut.bd.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

   private List<CartItemModel> cartItemModalList;

    public CartAdapter(List<CartItemModel> cartItemModalList) {
        this.cartItemModalList = cartItemModalList;
    }

    @Override
    public int getItemViewType(int position) {
       switch (cartItemModalList.get(position).getType()){
           case 0:
               return  CartItemModel.CART_ITEM;
           case 1:
               return  CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
       }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout,viewGroup,false);
                return  new CartItemViewHolder(cartItemView);

            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalAmountView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_total_amount_layout,viewGroup,false);
                return  new CartTotalAmountViewHolder(cartTotalAmountView);
             default:
                 return null;


        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            switch (cartItemModalList.get(position).getType()){
                case CartItemModel.CART_ITEM:
                    int resource = cartItemModalList.get(position).getProductImage();
                    String title = cartItemModalList.get(position).getProductTitle();
                    int freeCoupons = cartItemModalList.get(position).getFreeCoupons();
                    String productPrice = cartItemModalList.get(position).getProductPrice();
                    String cuttedPrice = cartItemModalList.get(position).getCuttedePrice();
                    int offerApplied = cartItemModalList.get(position).getOffersApplied();

                    ((CartItemViewHolder)viewHolder).setItemDetails(resource,title,freeCoupons,productPrice,cuttedPrice,offerApplied);
                    break;
                case CartItemModel.TOTAL_AMOUNT:

                    String totalItems = cartItemModalList.get(position).getTotalItems();
                    String totalItemPrice = cartItemModalList.get(position).getTotalItemPrice();
                    String deliveryPrice = cartItemModalList.get(position).getDeliveryPrice();
                    String totalAmount = cartItemModalList.get(position).getTotalAmount();
                    String  savedAmount= cartItemModalList.get(position).getSavedAmount();
                    ((CartTotalAmountViewHolder)viewHolder).serTotalAmount(totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount);

                    break;
                default:
                    return;

            }
    }

    @Override
    public int getItemCount() {
        return cartItemModalList.size();
    }


    /// cart item view Holder

    class CartItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private ImageView freeCouponeIcon;
        private TextView productTitle;
        private TextView freeCupons;
        private TextView cartProductPrice;
        private TextView cartCuttedPrice;
        private TextView offersApplied;
        private TextView productQuantity;
        private  TextView couponApplied;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image_cart);
            productTitle = itemView.findViewById(R.id.product_title_cart);
            freeCouponeIcon = itemView.findViewById(R.id.free_coupon_icon_cart);
            freeCupons = itemView.findViewById(R.id.tv_free_coupon_cart);
            cartProductPrice = itemView.findViewById(R.id.product_price_cart);
            cartCuttedPrice = itemView.findViewById(R.id.cutted_price_cart);
            offersApplied = itemView.findViewById(R.id.offers_applied);
            couponApplied = itemView.findViewById(R.id.coupon_applied);
            productQuantity = itemView.findViewById(R.id.product_quantity);

        }

        private void setItemDetails(int resource,
                                    String title,
                                    int freeCouponNumber,
                                    String productPriceText,
                                    String cuttedPrice,
                                    int offerAppliedNum
                                    ){
            productImage.setImageResource(resource);
            productTitle.setText(title);



            if(freeCouponNumber > 0 ){
                freeCouponeIcon.setVisibility(View.VISIBLE);
                freeCupons.setVisibility(View.VISIBLE);
                if(freeCouponNumber ==1){
                    freeCupons.setText("free "+freeCouponNumber+ " coupon");
                }else{
                    freeCupons.setText("free "+freeCouponNumber+ " coupons");
                }

            }else{
                freeCouponeIcon.setVisibility(View.INVISIBLE);
                freeCupons.setVisibility(View.INVISIBLE);
            }

            cartProductPrice.setText(productPriceText);
            cartCuttedPrice.setText(cuttedPrice);

            if(offerAppliedNum > 0){
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offerAppliedNum + " offer applied");
            }else {
                offersApplied.setVisibility(View.INVISIBLE);

            }

            // show dialog when quantity text clicked
            productQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog quantityDialog = new Dialog(itemView.getContext());
                    quantityDialog.setContentView(R.layout.quantity_dialog);
                    quantityDialog.setCancelable(false);
                    quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);


                    final EditText quantityNum = quantityDialog.findViewById(R.id.quantity_number);
                    Button cancelBtn = quantityDialog.findViewById(R.id.cancel_btn);
                    Button okBtn = quantityDialog.findViewById(R.id.ok_btn);

                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            quantityDialog.dismiss();
                        }
                    });

                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productQuantity.setText("Qty: "+quantityNum.getText());
                            quantityDialog.dismiss();

                        }
                    });

                    quantityDialog.show();



                }
            });

          // show dialog when quantity text clicked


        }
    }

    /// cart item view Holder

    // cart total amount
    class  CartTotalAmountViewHolder extends RecyclerView.ViewHolder{

        private TextView totalItems;
        private TextView cart_totalitemPrice;
        private TextView dliveryPrice;
        private TextView totalAmount;
        private TextView saveAmount;



        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);

            totalItems = itemView.findViewById(R.id.total_items);
            cart_totalitemPrice = itemView.findViewById(R.id.total_item_price);
            dliveryPrice = itemView.findViewById(R.id.delivery_charge_price);
            totalAmount = itemView.findViewById(R.id.total_price);
            saveAmount = itemView.findViewById(R.id.saved_amount);

        }

        private void serTotalAmount(String totalItemText, String totalItemPriceText,
                                    String deliveryPriceText,
                                    String totalAmountText,
                                    String savedAmountText ){
            totalItems.setText(totalItemText);
            cart_totalitemPrice.setText(totalItemPriceText);
            dliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(totalAmountText);
            saveAmount.setText(savedAmountText);
        }
    }


    // cart total amount
}
