package com.gollachut.bd.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gollachut.bd.Adapter.MyRewardAdapter;
import com.gollachut.bd.Model.MyRewardModal;
import com.gollachut.bd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRewardsFragment extends Fragment {


    public MyRewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView rewardsRecylerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_rewards, container, false);
        // assign rewards recycler view
        rewardsRecylerView = view.findViewById(R.id.my_rewards_recycler_view);

        // create layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewardsRecylerView.setLayoutManager(layoutManager);

        List<MyRewardModal> myRewardModalList = new ArrayList<>();

        myRewardModalList.add(new MyRewardModal("Cashback","till 3rd december 2019","Get 20% cash back up to 300"));
        myRewardModalList.add(new MyRewardModal("Discount","till 3rd december 2019","Get 20% cash back up to 300"));
        myRewardModalList.add(new MyRewardModal("coupon","till 3rd december 2019","Get 20% cash back up to 300"));
        myRewardModalList.add(new MyRewardModal("Buy one get one","till 3rd december 2019","Get 20% cash back up to 300"));
        myRewardModalList.add(new MyRewardModal("free Delivery","till 3rd december 2019","Get 20% cash back up to 300"));
        myRewardModalList.add(new MyRewardModal("Cashback","till 3rd december 2019","Get 20% cash back up to 300"));
        myRewardModalList.add(new MyRewardModal("coupon","till 3rd december 2019","Get 20% cash back up to 300"));
        myRewardModalList.add(new MyRewardModal("Buy one get one","till 3rd december 2019","Get 20% cash back up to 300"));
        myRewardModalList.add(new MyRewardModal("free Delivery","till 3rd december 2019","Get 20% cash back up to 300"));

        MyRewardAdapter myRewardAdapter = new MyRewardAdapter(myRewardModalList,false);
        rewardsRecylerView.setAdapter(myRewardAdapter);
        myRewardAdapter.notifyDataSetChanged();





        return view;
    }

}
