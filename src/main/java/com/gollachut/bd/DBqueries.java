package com.gollachut.bd;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gollachut.bd.Adapter.CategoryAdapter;
import com.gollachut.bd.Adapter.HomePageAdapter;
import com.gollachut.bd.Model.CategoryModel;
import com.gollachut.bd.Model.HomePageModel;
import com.gollachut.bd.Model.HorizontalProductScrollmodal;
import com.gollachut.bd.Model.SliderModal;
import com.gollachut.bd.Model.WishListModal;
import com.gollachut.bd.ui.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DBqueries {
   public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();



    public static List<CategoryModel> categoryModelList =new ArrayList<CategoryModel>();

    public static List<List<HomePageModel>> lists = new ArrayList<>();
    public static List<String> loadedCategoriesNames =new ArrayList<>();

    public static void loadCategories(final RecyclerView categoryRecyclerView, final Context context){
        categoryModelList = new ArrayList<CategoryModel>();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot :task.getResult()){
                                String icon = "";
                                if (documentSnapshot.get("icon") != null){
                                    icon = documentSnapshot.get("icon").toString();
                                }
                                String categoryName = "";
                                if (documentSnapshot.get("categoryName") != null){
                                    categoryName = documentSnapshot.get("categoryName").toString();
                                }

                                categoryModelList.add(new CategoryModel(icon,categoryName));

                            }
                            CategoryAdapter categoryAdapter =new CategoryAdapter(categoryModelList);
                            categoryRecyclerView.setAdapter(categoryAdapter);
                            categoryAdapter.notifyDataSetChanged();

                        }else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context,error,Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }
    public static void loadFragmentData(final RecyclerView homeRecyclerView , final Context context, final int index, String categoryName){
        firebaseFirestore.collection("CATEGORIES")
                .document(categoryName).collection("TOP_DEALS").orderBy("index")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.i("Error","error is occures");
                if(task.isSuccessful()){
                    Log.i("afraid",task.getResult().toString());
                    for(QueryDocumentSnapshot documentSnapshot :task.getResult()){
                        Log.i("HomeFragment","snap");


                        if((long)documentSnapshot.get("view_type") == 0){


                            Log.i("Errorss","inside");
                            List<SliderModal> sliderModalList = new ArrayList<>();
                            long no_of_banners = (long)documentSnapshot.get("no_of_banners");
                            for(long x =1; x<no_of_banners+1;x++){
                                Log.i("Errors","error is 123");
                                sliderModalList.add(new SliderModal(documentSnapshot.get("banner_"+x)
                                        .toString(),documentSnapshot.get("banner_"+x+"_background")
                                        .toString()));
                            }
                            lists.get(index).add(new HomePageModel(0,sliderModalList));

                        }else if((long)documentSnapshot.get("view_type") == 1){
                            String background = "";
                            if (documentSnapshot.get("background") != null) {


                                Log.i("errors", "errors offer" + documentSnapshot.toString());
                                lists.get(index).add(new HomePageModel(1, documentSnapshot.get("strip_add_banner")
                                        .toString(), documentSnapshot.get("background").toString()));
                            }


                        }else if((long)documentSnapshot.get("view_type") == 2){



                            List<WishListModal> viewAllProductList =new ArrayList<>();
                            List<HorizontalProductScrollmodal> horizontalProductScrollmodalList = new ArrayList<>();                        Log.i("error","errors offer");
                            long no_of_products = (long)documentSnapshot.get("no_of_products");
                            for(long x =1; x<no_of_products+1;x++){
                                horizontalProductScrollmodalList
                                        .add(new HorizontalProductScrollmodal(documentSnapshot.get("product_ID_"+x).toString()
                                                ,documentSnapshot.get("product_image_"+x).toString()
                                                ,documentSnapshot.get("product_title_"+x).toString()
                                                ,documentSnapshot.get("product_subtitle_"+x).toString()
                                                ,documentSnapshot.get("product_price_"+x).toString()));
                                viewAllProductList.add(new WishListModal(documentSnapshot.get("product_image_"+x).toString()
                                               ,documentSnapshot.get("product_full_title_"+x).toString()
                                               ,(long)documentSnapshot.get("free_coupens_"+x)
                                               ,documentSnapshot.get("average_rating_"+x).toString()
                                               ,(long)documentSnapshot.get("total_ratings_"+x)
                                               ,documentSnapshot.get("product_price_"+x).toString()
                                               ,documentSnapshot.get("cutted_price_"+x).toString()
                                               ,(boolean)documentSnapshot.get("COD_"+x)));

                            }
                            lists.get(index).add(new HomePageModel(2,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_background").toString(), horizontalProductScrollmodalList,viewAllProductList));

                        }else if((long)documentSnapshot.get("view_type") == 3){
                            List<HorizontalProductScrollmodal> gridLayout = new ArrayList<>();                        Log.i("error","errors offer");
                            long no_of_products = (long)documentSnapshot.get("no_of_products");
                            for(long x =1; x<no_of_products+1;x++){
                                gridLayout
                                        .add(new HorizontalProductScrollmodal(documentSnapshot.get("product_ID_"+x).toString()
                                                ,documentSnapshot.get("product_image_"+x).toString()
                                                ,documentSnapshot.get("product_title_"+x).toString()
                                                ,documentSnapshot.get("product_subtitle_"+x).toString()
                                                ,documentSnapshot.get("product_price_"+x).toString()));

                            }
                            lists.get(index).add(new HomePageModel(3,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_background").toString(), gridLayout));

//                                Log.i("danger", "danger occurs");
//
//
//                                Log.i("danger", "hello" + documentSnapshot.toString());
//
//
//                                List<HorizontalProductScrollmodal> gridLayout = new ArrayList<>();
//                                long no_of_products = (long) documentSnapshot.get("no_of_products");
//                                for (long x = 1; x < no_of_products + 1; x++) {
//                                    Log.i("danger","danger is");
//
//
//                                    gridLayout
//                                            .add(new HorizontalProductScrollmodal(documentSnapshot.get("product_ID_" + x).toString()
//                                                    , documentSnapshot.get("product_image_" + x).toString()
//                                                    , documentSnapshot.get("product_title_" + x).toString()
//                                                    , documentSnapshot.get("product_subtitle_" + x).toString()
//                                                    , documentSnapshot.get("product_price_" + x).toString()));
//
//                                }
//                                homePageModalList.add(new HomePageModel(2, documentSnapshot.get("layout_title").toString(), documentSnapshot.get("layout_background").toString(), gridLayout));


                        }


                    }
                    HomePageAdapter homePageAdapter =new HomePageAdapter(lists.get(index));
                    homeRecyclerView.setAdapter(homePageAdapter);


                    homePageAdapter.notifyDataSetChanged();
                    HomeFragment.swipeRefreshLayout.setRefreshing(false);


                }else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context,error,Toast.LENGTH_LONG).show();

                }
            }
        });

    }



}
