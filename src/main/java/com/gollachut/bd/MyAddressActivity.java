package com.gollachut.bd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gollachut.bd.Adapter.AddressAdapter;
import com.gollachut.bd.Model.AddressModel;

import java.util.ArrayList;
import java.util.List;

import static com.gollachut.bd.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressActivity extends AppCompatActivity {

    private RecyclerView myAddressRecyclerView;
    // adapter class variable
    private static AddressAdapter addressAdapter;
    // Button variable
    private Button deliverHereButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // assign deliver button
        deliverHereButton = findViewById(R.id.deliver_here_btn);
        // assign recyclerView
        myAddressRecyclerView = findViewById(R.id.address_recycler_view);
        // set layout on recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressRecyclerView.setLayoutManager(layoutManager);

        List<AddressModel> addressModalList = new ArrayList<>();
        addressModalList.add(new AddressModel("Shakil","Uttara","123",true));
        addressModalList.add(new AddressModel("Tanjit","Badda","55",false));
        addressModalList.add(new AddressModel("Shagor","shajadput","156",false));
        addressModalList.add(new AddressModel("Raihan","Uttara","123",false));
        addressModalList.add(new AddressModel("sfdsf","sfsdfsdf","123",false));

        // get mode value from activity to detect where it comes from DeliveryActivity or MyAccountFragment
        int mode = getIntent().getIntExtra("MODE",-1);

        // hide or show deliver here button
        if(mode == SELECT_ADDRESS){
            deliverHereButton.setVisibility(View.VISIBLE);

        }else {
            deliverHereButton.setVisibility(View.INVISIBLE);

        }

        addressAdapter = new AddressAdapter(addressModalList,mode);
        ((SimpleItemAnimator)myAddressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        myAddressRecyclerView.setAdapter(addressAdapter);
        addressAdapter.notifyDataSetChanged();

    }

    public static  void refreshItem(int deSelect, int select){
        addressAdapter.notifyItemChanged(deSelect);
        addressAdapter.notifyItemChanged(select);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
