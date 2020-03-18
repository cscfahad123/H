package com.gollachut.bd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.gollachut.bd.Adapter.HomePageAdapter;
import com.gollachut.bd.Model.HomePageModel;

import java.util.ArrayList;
import java.util.List;

import static com.gollachut.bd.DBqueries.lists;
import static com.gollachut.bd.DBqueries.loadCategories;
import static com.gollachut.bd.DBqueries.loadFragmentData;
import static com.gollachut.bd.DBqueries.loadedCategoriesNames;

public class CategoryActivity extends AppCompatActivity {

   public  RecyclerView singleCategoryRecyclerView;
   private  HomePageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("categoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        singleCategoryRecyclerView = findViewById(R.id.single_category_recycler_view);












        ////**************Home Recycler View***********/////
       //categorySecondRecycleView.findViewById(R.id.single_category_recycler_view);
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(this);
        categoryLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        singleCategoryRecyclerView.setLayoutManager(categoryLayoutManager);

        int listPosition =0;
        for(int x=0;x<loadedCategoriesNames.size();x++){
            if(loadedCategoriesNames.get(x).equals(title)){
                listPosition =x;
            }
        }
        if(listPosition ==0){
            loadedCategoriesNames.add(title);

            lists.add(new ArrayList<HomePageModel>());
            adapter = new HomePageAdapter(lists.get(loadedCategoriesNames.size()-1));
            loadFragmentData(singleCategoryRecyclerView,this,loadedCategoriesNames.size()-1,title);

        }else{
            adapter = new HomePageAdapter(lists.get(listPosition));
        }


        singleCategoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        ////**************Test Recycler View***********/////


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    // right site menu icons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.main_search_icon){
            // todo: search
            return true;
        }else if(id == android.R.id.home){
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
