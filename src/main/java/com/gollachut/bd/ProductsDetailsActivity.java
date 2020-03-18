package com.gollachut.bd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gollachut.bd.Adapter.MyRewardAdapter;
import com.gollachut.bd.Adapter.ProductDetailsAdapter;
import com.gollachut.bd.Adapter.ProductImagesAdaoter;
import com.gollachut.bd.Model.MyRewardModal;
import com.gollachut.bd.Model.ProductSpecificationModel;
import com.gollachut.bd.ui.SignInFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


import static com.gollachut.bd.RegisterActivity.setSignUpFragment;

public class ProductsDetailsActivity extends AppCompatActivity {

    private ViewPager productsImagesViewPager;
    private TextView productTitle;
    private TextView averageRatingMiniview;
    private TextView totalRatingMiniview;
    private TextView productPrice;
    private TextView cuttedPrice;
    private ImageView codIndicator;
    private TextView tvcodIndicator;
    private TextView rewardTitle;
    private TextView rewardBody;
    private TabLayout viewPagerIndicator;
    //product description
    private ConstraintLayout productDetailsonlyContainer;
    private ConstraintLayout productDetailsTabsContainer;
    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;
    private TextView productOnlyDescriptionBody;
    private List<ProductSpecificationModel> productSpecificationModelList =new ArrayList<>();

    private String productDescription;
    private String productOtherDetails;

    //product description

    //// rating Layout
    private LinearLayout rateNowConatiner;
    private TextView totalRatings;
    private LinearLayout ratingNoContainer;
    private TextView totalRatingsFigure;
    private LinearLayout ratingProgressBarContainer;
    //// rating Layout
    private Button buyNowBtn;
    private LinearLayout coupenRedemtionLayout;
    // coupon redeem button
    private Button couponRedeembtn;
    private LinearLayout addToCartBtn;



    private static boolean AREADY_ADDED_TO_WISH_LIST =false;
    private FloatingActionButton addToWishListBtn;
    private FirebaseFirestore firebaseFirestore;
    /// coupon dialog

    public static  TextView couponTitle;
    public static  TextView couponBody;
    public static  TextView couponExparyDate;
    private static RecyclerView couponRecyclerView;
    private static LinearLayout selectedCoupon;


    // coupon dialog
    private Dialog signInDialog;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hide the action bar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // assign coupon reedm button

        couponRedeembtn = findViewById(R.id.couopn_redemption_button);


        addToWishListBtn = findViewById(R.id.addTo_wish_list_floating_button);
        productsImagesViewPager= findViewById(R.id.product_images_viewPager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);
        // assigning products description layout ids
        productDetailsViewPager = findViewById(R.id.product_details_view_pager);
        productDetailsTabLayout = findViewById(R.id.product_details_tab_layout);
        productTitle =findViewById(R.id.single_product_title);
        averageRatingMiniview =findViewById(R.id.single_product_rating_miniview);
        totalRatingMiniview =findViewById(R.id.total_ratings_mini_view);
        productPrice =findViewById(R.id.single_product_price);
        cuttedPrice =findViewById(R.id.single_product_cutted_price);
        tvcodIndicator =findViewById(R.id.tv_cod_indicator);
        codIndicator =findViewById(R.id.con_indicator_imageView);
        rewardTitle =findViewById(R.id.reward_title);
        rewardBody =findViewById(R.id.reward_body);
        productDetailsTabsContainer =findViewById(R.id.product_details_tab_container);
        productDetailsonlyContainer =findViewById(R.id.products_only_details_container);
        productOnlyDescriptionBody = findViewById(R.id.product_only_details_body);
        totalRatings =findViewById(R.id.totals_ratings);
        ratingNoContainer =findViewById(R.id.ratings_numbers_container);
        totalRatingsFigure =findViewById(R.id.total_ratings_figure);
        ratingProgressBarContainer =findViewById(R.id.ratings_progressbar_container);
        addToCartBtn =findViewById(R.id.add_to_cart_btn);
        coupenRedemtionLayout =findViewById(R.id.coupon_redemption_layout);

        // assign buy now button
        buyNowBtn = findViewById(R.id.buy_now_btn);
        firebaseFirestore =FirebaseFirestore.getInstance();
        final List<String> productImages =new ArrayList<>();
        firebaseFirestore.collection("PRODUCTS").document(getIntent().getStringExtra("PRODUCT_ID"))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Log.i("error","error is very dangerous for a programmer");
                    DocumentSnapshot documentSnapshot =task.getResult();
                    for(long x =1;x<(long)documentSnapshot.get("no_of_product_images")+1;x++){
                        productImages.add(documentSnapshot.get("product_image_"+x).toString());

                    }
                    ProductImagesAdaoter productImagesAdaoter = new ProductImagesAdaoter(productImages);
                    productsImagesViewPager.setAdapter(productImagesAdaoter);
                    productTitle.setText(documentSnapshot.get("product_title").toString());
                    averageRatingMiniview.setText(documentSnapshot.get("average_rating").toString());
                    totalRatingMiniview.setText("("+(long)documentSnapshot.get("total_ratings")+")ratings");
                    productPrice.setText("TK."+documentSnapshot.get("product_price").toString()+"/-");
                    cuttedPrice.setText("TK."+documentSnapshot.get("cutted_price").toString()+"/-");
                    if((boolean)documentSnapshot.get("COD")){
                        codIndicator.setVisibility(View.VISIBLE);
                        tvcodIndicator.setVisibility(View.VISIBLE);
                    }else{
                        codIndicator.setVisibility(View.INVISIBLE);
                        tvcodIndicator.setVisibility(View.INVISIBLE);

                    }
                    rewardTitle.setText((long)documentSnapshot.get("free_coupens")+ documentSnapshot.get("free_coupen_title").toString());
                    rewardBody.setText(documentSnapshot.get("free_coupen_body").toString());
                    if((boolean)documentSnapshot.get("use_tab_layout")){
                        productDetailsTabsContainer.setVisibility(View.VISIBLE);
                        productDetailsonlyContainer.setVisibility(View.GONE);
                       productDescription = documentSnapshot.get("product_description").toString();

                        productOtherDetails =documentSnapshot.get("product_other_details").toString();
                        for(long x=1;x<(long)documentSnapshot.get("total_spec_titles")+1;x++ ){
                            productSpecificationModelList.add(new ProductSpecificationModel(0,documentSnapshot.get("spec_title_"+x).toString()));
                            for(long y=1;y<(long)documentSnapshot.get("spec_title_"+x+"_total_fields")+1;y++){
                               productSpecificationModelList.add(new ProductSpecificationModel(1,documentSnapshot.get("spec_title_"+x+"_field_"+y+"_name").toString(),documentSnapshot.get("spec_title_"+x+"_field_"+y+"_name").toString()));
                            }
                        }
//                        for(long x=1;x<(long)documentSnapshot.get("total_spec_titles")+1;x++){
//                            productSpecificationModelList.add(new ProductSpecificationModel(0,documentSnapshot.get("spec_title_"+x).toString()));
//                            for(long y=1;y<(long)documentSnapshot.get("spec_title_"+x+"_total_fields")+1;y++){
//
//                                productSpecificationModelList.add(new ProductSpecificationModel(1,documentSnapshot.get("spec_title_"+x+"_field_"+y+"_name").toString(),documentSnapshot.get("spec_title_"+x+"_field_"+y+"_value").toString()));
//                                Log.i("Iamgones","I am a very bad person");
//
//                            }
//                        }

                    }else{

                        productDetailsTabsContainer.setVisibility(View.GONE);
                        productDetailsonlyContainer.setVisibility(View.VISIBLE);
                        productOnlyDescriptionBody.setText(documentSnapshot.get("product_description").toString());
                    }

                    totalRatings.setText((long)documentSnapshot.get("total_ratings")+"ratings");
                    for(int x=0;x<5;x++){
                        TextView rating = (TextView) ratingNoContainer.getChildAt(x);
                        rating.setText(String.valueOf((long)documentSnapshot.get((5-x) +"_star")));
                        ProgressBar progressBar =(ProgressBar)ratingProgressBarContainer.getChildAt(x);
                        int maxprogress =Integer.parseInt(String.valueOf((long)documentSnapshot.get("total_ratings")));
                        progressBar.setMax(maxprogress);
                        progressBar.setProgress(Integer.parseInt(String.valueOf((long)documentSnapshot.get((5-x) +"_star"))));
                    }
                    totalRatingMiniview.setText(String.valueOf((long)documentSnapshot.get("total_ratings")));
                    productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount(),productDescription,productOtherDetails,productSpecificationModelList));

                }else{
                    String error =task.getException().getMessage();
                    Toast.makeText(ProductsDetailsActivity.this, "error", Toast.LENGTH_SHORT).show();
                }

            }
        });





        viewPagerIndicator.setupWithViewPager(productsImagesViewPager,true);

        // on wish list click
        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser ==null){
                    signInDialog.show();
                }else {
                    if (AREADY_ADDED_TO_WISH_LIST) {
                        AREADY_ADDED_TO_WISH_LIST = false;
                        addToWishListBtn.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));

                    } else {
                        AREADY_ADDED_TO_WISH_LIST = true;
                        addToWishListBtn.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#CE0000")));


                    }
                }

            }
        });


        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                productDetailsViewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //// rating Layout
        rateNowConatiner = findViewById(R.id.rate_now_container);
        for(int x =0; x< rateNowConatiner.getChildCount(); x++){
          final int starPosition = x;
          rateNowConatiner.getChildAt(x).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(currentUser==null){
                      signInDialog.show();
                  }else {
                      setRating(starPosition);

                  }


              }
          });

        }
        //// rating Layout

        // set click listener on buy now button
        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser==null){
                    signInDialog.show();
                }else {
                    Intent deliveryIntent = new Intent(ProductsDetailsActivity.this, DeliveryActivity.class);
                    startActivity(deliveryIntent);
                }
            }
        });
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentUser==null){
                    signInDialog.show();
                }else {
                    //todo add to cart
                }

            }
        });

        ////******************************* coupon dialog*****************************

        final Dialog checkCouponPriceDialog = new Dialog(ProductsDetailsActivity.this);
        checkCouponPriceDialog.setContentView(R.layout.cupon_redeem_dialog);
        checkCouponPriceDialog.setCancelable(true);

        checkCouponPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView toggelRecyclerView = checkCouponPriceDialog.findViewById(R.id.toggle_recycler_view);
        couponRecyclerView = checkCouponPriceDialog.findViewById(R.id.coupons_recycler_view);
        selectedCoupon = checkCouponPriceDialog.findViewById(R.id.selected_coupon);
        // set selected coupon data
        couponTitle = checkCouponPriceDialog.findViewById(R.id.coupon_title);
        couponExparyDate = checkCouponPriceDialog.findViewById(R.id.coupon_validity);
        couponBody = checkCouponPriceDialog.findViewById(R.id.coupon_body);


        TextView orignalPrice =checkCouponPriceDialog.findViewById(R.id.orginal_price);
        TextView dicountedPrice = checkCouponPriceDialog.findViewById(R.id.discounted_price);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductsDetailsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        couponRecyclerView.setLayoutManager(layoutManager);

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


        MyRewardAdapter myRewardAdapter = new MyRewardAdapter(myRewardModalList,true);
        couponRecyclerView.setAdapter(myRewardAdapter);
        myRewardAdapter.notifyDataSetChanged();


        // set click listener
        toggelRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRecyclerView();
            }
        });

        // set clicklistener to redeem button

        couponRedeembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkCouponPriceDialog.show();

            }
       });
        ///signin Dialog
        signInDialog = new Dialog(ProductsDetailsActivity.this);
        signInDialog.setContentView(R.layout.sign_in_dialog);
        signInDialog.setCancelable(true);
        // set height and weight of the dialog layout
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        // assign dialog layout buttons
        Button dialogSignInBtn = signInDialog.findViewById(R.id.sign_in_btn);
        Button dialogSignUpBtn = signInDialog.findViewById(R.id.sign_up_btn);

        final Intent RegisterIntent = new Intent(ProductsDetailsActivity.this,RegisterActivity.class);
        // set click listener om signin and sigup button
        dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment.disableCloseBtn =true;
                SignUpFragment.disableCloseBtn =true;
                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity(RegisterIntent);

            }
        });
        dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SignUpFragment.disableCloseBtn =true;
                SignInFragment.disableCloseBtn =true;
                signInDialog.dismiss();
                setSignUpFragment = true;
                startActivity(RegisterIntent);


            }
        });
        ///signin Dialog

    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser =FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser==null){
            coupenRedemtionLayout.setVisibility(View.GONE);
        }else{
            coupenRedemtionLayout.setVisibility(View.VISIBLE);
        }


    }
    //    @Override
//    protected void onStart() {
//        super.onStart();
//        currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if(currentUser==null){
//            coupenRedemtionLayout.setVisibility(View.GONE);
//        }else{
//            coupenRedemtionLayout.setVisibility(View.VISIBLE);
//        }
//    }

    ////******************************* coupon dialog*****************************

    // create method for toggle recycler view of dialog
    public static void showDialogRecyclerView(){
        if(couponRecyclerView.getVisibility()== View.GONE){
            couponRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupon.setVisibility(View.GONE);
        }else{
            couponRecyclerView.setVisibility(View.GONE);
            selectedCoupon.setVisibility(View.VISIBLE);
        }

    }


    //// rating Layout
    private void setRating(int starPosition){
        for(int x= 0; x <rateNowConatiner.getChildCount(); x++){
            ImageView starBtn = (ImageView)rateNowConatiner.getChildAt(x);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if(x <= starPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));

            }

        }

    }

    //// rating Layout

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart, menu);
        return true;
    }

    // right site menu icons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
            return true;
        }else if(id== R.id.main_search_icon){
            // todo: search
            return true;
        }else if(id== R.id.main_cart_icon){
            //todo:cart
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
