package com.gollachut.bd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.gollachut.bd.Adapter.GridProductLayoutAdapter;
import com.gollachut.bd.Adapter.WishListAdapter;
import com.gollachut.bd.Model.HorizontalProductScrollmodal;
import com.gollachut.bd.Model.WishListModal;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView viewAllRecyclerView;
    private GridView gridView;
    public static List<WishListModal> wishListModalList;
    public static List<HorizontalProductScrollmodal> horizontalProductScrollmodalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // assign gridView
        gridView = findViewById(R.id.view_all_grid_view);

        // assign view all recycler view
        viewAllRecyclerView = findViewById(R.id.view_all_recycler_view);

        int layout_code = getIntent().getIntExtra("layout_code",-1);

        if(layout_code == 0) {

            // recycler view horizontal
            viewAllRecyclerView.setVisibility(View.VISIBLE);
            // crate layout manager and add too recycler view
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            viewAllRecyclerView.setLayoutManager(layoutManager);
            // create a list for dummy data

            WishListAdapter adapter = new WishListAdapter(wishListModalList, false);
            viewAllRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        }else if(layout_code ==1) {
            // grid view
            gridView.setVisibility(View.VISIBLE);



            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollmodalList);
            gridView.setAdapter(gridProductLayoutAdapter);


        }

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
