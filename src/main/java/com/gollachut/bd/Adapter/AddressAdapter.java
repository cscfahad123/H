package com.gollachut.bd.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gollachut.bd.Model.AddressModel;
import com.gollachut.bd.R;

import java.util.List;

import static com.gollachut.bd.DeliveryActivity.SELECT_ADDRESS;
import static com.gollachut.bd.MyAddressActivity.refreshItem;
import static com.gollachut.bd.ui.MyAccountFragment.MANAGE_ADDRESS;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    // create address list variable
    private List<AddressModel> addressModalList;
    // create mode for where it come from , deliveryActivity or accountFragment
    private int MODE;
    // toge get previous  item select position
    private int preSelectedPosition = -1;


    public AddressAdapter(List<AddressModel> addressModalList, int MODE) {
        this.addressModalList = addressModalList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.address_item_layout,viewGroup,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, int position) {

        String name = addressModalList.get(position).getFullName();
        String address = addressModalList.get(position).getAddress();
        String pinCode = addressModalList.get(position).getPinCode();
        Boolean selected = addressModalList.get(position).getSelected();

        holder.setAddress(name,address,pinCode,selected,position);

    }

    @Override
    public int getItemCount() {
        return addressModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullName;
        private TextView address;
        private TextView pinCodel;
        private ImageView icon;
        private LinearLayout optionContainer;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            fullName = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            pinCodel = itemView.findViewById(R.id.pin_code);
            icon = itemView.findViewById(R.id.icon_view);
            optionContainer= itemView.findViewById(R.id.option_container);
        }
        private void setAddress(String userName, String userAddress, String userPinCode, Boolean selectedValue, final int itemPosition){

            fullName.setText(userName);
            address.setText(userAddress);
            pinCodel.setText(userPinCode);

            if(MODE == SELECT_ADDRESS){
                icon.setImageResource(R.mipmap.chacked);
                if(selectedValue){
                    icon.setVisibility(View.VISIBLE);
                    preSelectedPosition = itemPosition;
                }else {
                    icon.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(preSelectedPosition != itemPosition){
                            addressModalList.get(itemPosition).setSelected(true);
                            addressModalList.get(preSelectedPosition).setSelected(false);
                            // refresh layout to visible icon
                            refreshItem(preSelectedPosition,itemPosition);
                            preSelectedPosition =itemPosition;
                        }
                    }
                });
            }else if(MODE == MANAGE_ADDRESS){
                optionContainer.setVisibility(View.GONE);
                icon.setImageResource(R.mipmap.vertical_dots);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        optionContainer.setVisibility(View.VISIBLE);
                        refreshItem(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = itemPosition;
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        refreshItem(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = -1;
                    }
                });

            }

        }
    }
}
