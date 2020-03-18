package com.gollachut.bd.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gollachut.bd.Adapter.MyOrderAdapter;
import com.gollachut.bd.Model.MyOrderItemModal;
import com.gollachut.bd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment  {


    public MyOrdersFragment() {
        // Required empty public constructor
    }

    private RecyclerView myOrederRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrederRecyclerView = view.findViewById(R.id.my_order_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrederRecyclerView.setLayoutManager(layoutManager);

        List<MyOrderItemModal> myOrderItemModalList = new ArrayList<>();

        myOrderItemModalList.add(new MyOrderItemModal(R.mipmap.mobile_phone,2,"Nexus 2","Delivered on Monday 15 jan 2019"));
        myOrderItemModalList.add(new MyOrderItemModal(R.drawable.mobile3,4,"Nexus 5","Delivered on Monday 15 jan 2019"));
        myOrderItemModalList.add(new MyOrderItemModal(R.drawable.mobile4,1,"Nexus 2","Cancelled"));
        myOrderItemModalList.add(new MyOrderItemModal(R.mipmap.mobile_phone,0,"Nexus 2","Delivered on Monday 15 jan 2019"));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModalList);
        myOrederRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();
        return view;
    }

}
