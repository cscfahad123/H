package com.gollachut.bd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gollachut.bd.Adapter.CartAdapter;
import com.gollachut.bd.Model.CartItemModel;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    private RecyclerView deliveryRecyclerView;
    private Button changeOrAddAddressBtn;

    // for detect from where we go to the address activity
    public static final int SELECT_ADDRESS=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // hide the action bar title
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowTitleEnabled(true);
       getSupportActionBar().setTitle("Delivery");

       // assign change or add address button
        changeOrAddAddressBtn= findViewById(R.id.change_or_add_address_btn);
        // assign delivery recycler
        deliveryRecyclerView= findViewById(R.id.delivery_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModalList = new ArrayList<>();
        cartItemModalList.add(new CartItemModel(0,R.mipmap.mobile_phone,"Pixel 2",2,"Bdt. 4999","Bdt. 5999",1,0,0));
        cartItemModalList.add(new CartItemModel(0,R.mipmap.mobile_phone,"Redmi 2",0,"Bdt. 4999","Bdt. 5999",1,1,1));
        cartItemModalList.add(new CartItemModel(0,R.mipmap.mobile_phone,"Iphone x",5,"Bdt. 80000","Bdt. 95000",2,1,0));

        // pass total
        cartItemModalList.add(new CartItemModel(1,"price(3 items)","Bdt. 19000","free","Bdt. 20000","bdt. 500"));
        CartAdapter cartAdapter = new CartAdapter(cartItemModalList);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeOrAddAddressBtn.setVisibility(View.VISIBLE);
        changeOrAddAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myAddressIntent = new Intent(DeliveryActivity.this, MyAddressActivity.class);
                myAddressIntent.putExtra("MODE",SELECT_ADDRESS);
                startActivity(myAddressIntent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
