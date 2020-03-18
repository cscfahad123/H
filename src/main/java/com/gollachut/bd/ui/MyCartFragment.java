package com.gollachut.bd.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gollachut.bd.Adapter.CartAdapter;
import com.gollachut.bd.AddAddressActivity;
import com.gollachut.bd.Model.CartItemModel;
import com.gollachut.bd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRecyclerView;
    private Button continueBtn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // assign continue btn

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_cart, container, false);
        //assign card recyclerView
        cartItemsRecyclerView= view.findViewById(R.id.cart_items_recycler_view);
        // assign continue btn
        continueBtn= view.findViewById(R.id.cart_continue_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModalList = new ArrayList<>();
        cartItemModalList.add(new CartItemModel(0,R.mipmap.mobile_phone,"Pixel 2",2,"Bdt. 4999","Bdt. 5999",1,0,0));
        cartItemModalList.add(new CartItemModel(0,R.mipmap.mobile_phone,"Redmi 2",0,"Bdt. 4999","Bdt. 5999",1,1,1));
        cartItemModalList.add(new CartItemModel(0,R.mipmap.mobile_phone,"Iphone x",5,"Bdt. 80000","Bdt. 95000",2,1,0));

        // pass total
        cartItemModalList.add(new CartItemModel(1,"price(3 items)","Bdt. 19000","free","Bdt. 20000","bdt. 500"));
        CartAdapter cartAdapter = new CartAdapter(cartItemModalList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        // add click listener on continue btn
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                Intent deliveryIntent = new Intent(getContext(), AddAddressActivity.class);
                getContext().startActivity(deliveryIntent);

            }
        });
        return view;
    }

}
