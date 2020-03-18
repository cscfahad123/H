package com.gollachut.bd.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gollachut.bd.MyAddressActivity;
import com.gollachut.bd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {


//view_all_address_button

    public MyAccountFragment() {
        // Required empty public constructor
    }
    //create variable for view all button
    private Button viewAllAddressBtn;

    // for detect from where we go to the address activity
    public static final int MANAGE_ADDRESS=1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_account, container, false);

        // assign view all address btn by ui id
        viewAllAddressBtn = view.findViewById(R.id.view_all_address_button);
        // set on click listener on view all address btn
        viewAllAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewAddressIntent = new Intent(getContext(), MyAddressActivity.class);
                viewAddressIntent.putExtra("MODE",MANAGE_ADDRESS);
                startActivity(viewAddressIntent);

            }
        });




        return view;
    }

}
