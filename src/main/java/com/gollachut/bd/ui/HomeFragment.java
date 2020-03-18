package com.gollachut.bd.ui;


import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gollachut.bd.Adapter.CategoryAdapter;
import com.gollachut.bd.Adapter.HomePageAdapter;
import com.gollachut.bd.MainActivity;
import com.gollachut.bd.Model.CategoryModel;
import com.gollachut.bd.Model.HomePageModel;
import com.gollachut.bd.Model.HorizontalProductScrollmodal;
import com.gollachut.bd.Model.SliderModal;
import com.gollachut.bd.Model.WishListModal;
import com.gollachut.bd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.gollachut.bd.DBqueries.categoryModelList;
import static com.gollachut.bd.DBqueries.firebaseFirestore;

import static com.gollachut.bd.DBqueries.lists;
import static com.gollachut.bd.DBqueries.loadCategories;
import static com.gollachut.bd.DBqueries.loadFragmentData;
import static com.gollachut.bd.DBqueries.loadedCategoriesNames;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    public static SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView homeRecyclerView;
    private List<HomePageModel> homePageModelFakeList =new ArrayList<>();
    private List<CategoryModel> categoryModeFakelList=new ArrayList<>();
    private HomePageAdapter adapter;

    // category list variable


    // create instance for pull data from firabase






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        // assign recycler view layout
        swipeRefreshLayout=view.findViewById(R.id.refresh_layout);
        categoryRecyclerView= view.findViewById(R.id.category_recyclerView);
        homeRecyclerView = view.findViewById(R.id.home_page_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);



        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecyclerView.setLayoutManager(testingLayoutManager);


        //Category Fake List
        categoryModeFakelList.add(new CategoryModel("null",""));
        categoryModeFakelList.add(new CategoryModel("null",""));
        categoryModeFakelList.add(new CategoryModel("null",""));
        categoryModeFakelList.add(new CategoryModel("null",""));
        categoryModeFakelList.add(new CategoryModel("null",""));
        categoryModeFakelList.add(new CategoryModel("null",""));
        categoryModeFakelList.add(new CategoryModel("null",""));
        categoryModeFakelList.add(new CategoryModel("null",""));
        categoryModeFakelList.add(new CategoryModel("null",""));
        //Category Fake List
        //Home Page Fake List
       List<SliderModal> sliderModelFakeList =new ArrayList<>();
        sliderModelFakeList.add(new SliderModal("null","#ffffff"));
        sliderModelFakeList.add(new SliderModal("null","#ffffff"));
        sliderModelFakeList.add(new SliderModal("null","#ffffff"));
        sliderModelFakeList.add(new SliderModal("null","#ffffff"));
        sliderModelFakeList.add(new SliderModal("null","#ffffff"));



        List<HorizontalProductScrollmodal> horizontalProductScrollmodeFakelList =new ArrayList<>();
        horizontalProductScrollmodeFakelList.add(new HorizontalProductScrollmodal("","","","",""));
        horizontalProductScrollmodeFakelList.add(new HorizontalProductScrollmodal("","","","",""));
        horizontalProductScrollmodeFakelList.add(new HorizontalProductScrollmodal("","","","",""));
        horizontalProductScrollmodeFakelList.add(new HorizontalProductScrollmodal("","","","",""));
        horizontalProductScrollmodeFakelList.add(new HorizontalProductScrollmodal("","","","",""));
        horizontalProductScrollmodeFakelList.add(new HorizontalProductScrollmodal("","","","",""));
        horizontalProductScrollmodeFakelList.add(new HorizontalProductScrollmodal("","","","",""));

        homePageModelFakeList.add(new HomePageModel(0,sliderModelFakeList));
        homePageModelFakeList.add(new HomePageModel(1,"","#ffffff"));
        homePageModelFakeList.add(new HomePageModel(2,"","#ffffff",horizontalProductScrollmodeFakelList,new ArrayList<WishListModal>()));
        homePageModelFakeList.add(new HomePageModel(3,"","#ffffff",horizontalProductScrollmodeFakelList));
        //Home Page Fake List

        // add adapter on category list
        categoryAdapter = new CategoryAdapter(categoryModeFakelList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        adapter = new HomePageAdapter(homePageModelFakeList);
        homeRecyclerView.setAdapter(adapter);


        //MainActivity.drawer.setDrawerLockMode(0);

        // create layout for recycler view category

        if(categoryModelList.size()==0){
            loadCategories(categoryRecyclerView,getContext());

        }
        else{
            categoryAdapter.notifyDataSetChanged();
        }

        // initialize firebas fire store
      ;





        // create layout for recycler view category

        ////**************Banner Slider***********/////




        ////**************Banner Slider***********/////




        ////**************Horizontal Products layout***********/////
//
//        List<HorizontalProductScrollmodal> horizontalProductScrollmodalList = new ArrayList<>();
//        horizontalProductScrollmodalList.add(new HorizontalProductScrollmodal(R.mipmap.mobile_phone,"Redmi 4A ","quotqome processor","8900"));
//        horizontalProductScrollmodalList.add(new HorizontalProductScrollmodal(R.mipmap.account,"Redmi 4A ","quotqome processor","8900"));
//        horizontalProductScrollmodalList.add(new HorizontalProductScrollmodal(R.mipmap.banneradd,"Redmi 4A ","quotqome processor","8900"));
//        horizontalProductScrollmodalList.add(new HorizontalProductScrollmodal(R.mipmap.cart_white,"Redmi 4A ","quotqome processor","8900"));
//        horizontalProductScrollmodalList.add(new HorizontalProductScrollmodal(R.mipmap.close,"Redmi 4A ","quotqome processor","8900"));
//        horizontalProductScrollmodalList.add(new HorizontalProductScrollmodal(R.mipmap.gollachot,"Redmi 4A ","quotqome processor","8900"));
//        horizontalProductScrollmodalList.add(new HorizontalProductScrollmodal(R.mipmap.mobile_phone,"Redmi 4A ","quotqome processor","8900"));
//        horizontalProductScrollmodalList.add(new HorizontalProductScrollmodal(R.mipmap.logo,"Redmi 4A ","quotqome processor","8900"));
//        horizontalProductScrollmodalList.add(new HorizontalProductScrollmodal(R.mipmap.mobile_phone,"Redmi 4A ","quotqome processor","8900"));


        ////**************Horizontal Products layout***********/////




        ////**************Home Recycler View***********/////





        if(lists.size()==0){
            loadedCategoriesNames.add("Home");

            lists.add(new ArrayList<HomePageModel>());

            loadFragmentData(homeRecyclerView,getContext(),0,"Home");

        }
        else{
            adapter = new HomePageAdapter(lists.get(0));
            adapter.notifyDataSetChanged();
        }








        ////**************Home Recycler View***********/////
        //refresh layout

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(true);
                categoryModelList.clear();
                lists.clear();
                loadedCategoriesNames.clear();

                categoryRecyclerView.setAdapter(categoryAdapter);
                homeRecyclerView.setAdapter(adapter);
                loadCategories(categoryRecyclerView,getContext());
                loadedCategoriesNames.add("Home");

                lists.add(new ArrayList<HomePageModel>());

                loadFragmentData(homeRecyclerView,getContext(),0,"Home");
            }
        });
        //refresh layout

        return view;
    }







}
