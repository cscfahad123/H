package com.gollachut.bd.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gollachut.bd.Adapter.WishListAdapter;
import com.gollachut.bd.Model.WishListModal;
import com.gollachut.bd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishListFragment extends Fragment {


    public MyWishListFragment() {
        // Required empty public constructor
    }
    private RecyclerView myWishListRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_my_wish_list, container, false);

        // assign recycler view by id
        myWishListRecyclerView = view.findViewById(R.id.my_wish_list_recycler_view);
        // set linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        myWishListRecyclerView.setLayoutManager(linearLayoutManager);
        // create a list for dummy data
        List<WishListModal> wishListModalList = new ArrayList<>();
        // add data on wish list



        WishListAdapter wishListAdapter = new WishListAdapter(wishListModalList, true);
        myWishListRecyclerView.setAdapter(wishListAdapter);
        wishListAdapter.notifyDataSetChanged();
        return view;
    }

}
