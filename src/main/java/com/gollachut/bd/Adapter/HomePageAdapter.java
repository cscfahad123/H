package com.gollachut.bd.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gollachut.bd.Model.HomePageModel;
import com.gollachut.bd.Model.HorizontalProductScrollmodal;
import com.gollachut.bd.Model.SliderModal;
import com.gollachut.bd.Model.WishListModal;
import com.gollachut.bd.ProductsDetailsActivity;
import com.gollachut.bd.R;
import com.gollachut.bd.ViewAllActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModalList;

    //for create poll for horizontal recycler
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomePageAdapter(List<HomePageModel> homePageModalList) {
        this.homePageModalList = homePageModalList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }


    @Override
    public int getItemViewType(int position) {
        switch (homePageModalList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliding_add_layout, viewGroup, false);
                return new BannerSliderViewHolder(bannerSliderView);
            case HomePageModel.STRIP_AD_BANNER:
                View stripAdView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.strip_add_layout, viewGroup, false);
                return new StripAdBannerViewHolder(stripAdView);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_layout, viewGroup, false);
                return new HorizontalProductsViewHolder(horizontalProductView);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductsView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_product_layout, viewGroup, false);
                return new GridproductViewHolder(gridProductsView);
            default:
                return null;

        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        switch (homePageModalList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModal> sliderModalList = homePageModalList.get(position).getSliderModalList();
                ((BannerSliderViewHolder) viewHolder).setBannerSliderViewPager(sliderModalList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                String resource = homePageModalList.get(position).getResource();
                String color = homePageModalList.get(position).getBackgroundColor();
                ((StripAdBannerViewHolder) viewHolder).setStripAd(resource, color);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String layout_color = homePageModalList.get(position).getBackgroundColor();
                String horizontalTitle = homePageModalList.get(position).getTitle();
                List<WishListModal> viewAllProductList =homePageModalList.get(position).getViewAllProductList();
                List<HorizontalProductScrollmodal> horizontalProductScrollmodalList = homePageModalList.get(position).getHorizontalProductScrollmodalList();
                ((HorizontalProductsViewHolder) viewHolder).setHorizontalProductLayout(horizontalProductScrollmodalList,horizontalTitle,layout_color,viewAllProductList);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String GridLayoutcolor = homePageModalList.get(position).getBackgroundColor();
                String gridTitle = homePageModalList.get(position).getTitle();
                List<HorizontalProductScrollmodal> GridProductScrollmodalList = homePageModalList.get(position).getHorizontalProductScrollmodalList();
                ((GridproductViewHolder) viewHolder).setGridProductLayout(GridProductScrollmodalList,gridTitle,GridLayoutcolor);
                break;

            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return homePageModalList.size();
    }

    //*********** banner slide class
    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {

        private ViewPager bannderSliderViewPager;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
//        private  List<SliderModal> arrangeList;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);

            bannderSliderViewPager = itemView.findViewById(R.id.banner_slider_viewPager);


        }

        private void setBannerSliderViewPager(final List<SliderModal> sliderModalList) {
            currentPage =2;
            if(timer !=null){
                timer.cancel();
            }

//            arrangeList = new ArrayList<>();
//            for(int x =0; x < sliderModalList.size();x++){
//                arrangeList.set(x, sliderModalList.get(x));
//            }
//
//            arrangeList.add(0,sliderModalList.get(sliderModalList.size() - 2));
//            arrangeList.add(1,sliderModalList.get(sliderModalList.size() - 1));
//            arrangeList.add(sliderModalList.get(0));
//            arrangeList.add(sliderModalList.get(1));





            SliderAdapter sliderAdapter = new SliderAdapter(sliderModalList);
            bannderSliderViewPager.setAdapter(sliderAdapter);
            bannderSliderViewPager.setClipToPadding(false);
            bannderSliderViewPager.setPageMargin(20);
            bannderSliderViewPager.setCurrentItem(currentPage);


            // page change listener
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(sliderModalList);
                    }

                }
            };
            bannderSliderViewPager.addOnPageChangeListener(onPageChangeListener);
            startBannerSlideShow(sliderModalList);

            bannderSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    pageLooper(sliderModalList);
                    stopBannerSliderShow();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(sliderModalList);

                    }
                    return false;
                }
            });

        }

        private void pageLooper(List<SliderModal> sliderModalList) {

            if (currentPage == sliderModalList.size() - 2) {
                currentPage = 2;
                bannderSliderViewPager.setCurrentItem(currentPage, false);
            }

            if (currentPage == 1) {
                currentPage = sliderModalList.size() - 3;
                bannderSliderViewPager.setCurrentItem(currentPage, false);
            }

        }

        private void startBannerSlideShow(final List<SliderModal> sliderModalList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderModalList.size()) {
                        currentPage = 1;
                    }
                    bannderSliderViewPager.setCurrentItem(currentPage++, true);

                }
            };

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopBannerSliderShow() {
            timer.cancel();
        }
    }
    //*********** banner slide class

    //******** strip Add class
    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView stripAdImage;
        private ConstraintLayout stripAdContainer;

        public StripAdBannerViewHolder(@NonNull View itemView) {
            super(itemView);

            stripAdImage = itemView.findViewById(R.id.strip_add_image);
            stripAdContainer = itemView.findViewById(R.id.strip_add_container);
        }

        private void setStripAd(String resource, String color) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.mipmap.home)).into(stripAdImage);
            stripAdContainer.setBackgroundColor(Color.parseColor(color));
        }
    }
    //******** strip Add class

    //*** horizontal products view Layout

    public class HorizontalProductsViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout container;
        private TextView horizontalLayoutTitle;
        private Button horizontalViewAllButton;
        private RecyclerView horizontalRecycleView;


        public HorizontalProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            horizontalLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontalViewAllButton = itemView.findViewById(R.id.horizontal_scroll_layout_ViewAllButton);
            horizontalRecycleView = itemView.findViewById(R.id.horizontal_products_recyclerView);
            horizontalRecycleView.setRecycledViewPool(recycledViewPool);
        }

        private void setHorizontalProductLayout(List<HorizontalProductScrollmodal> horizontalProductScrollmodalList, final String title, String color, final List<WishListModal> viewAllProductList) {
            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            horizontalLayoutTitle.setText(title);

            if (horizontalProductScrollmodalList.size() > 8) {
                horizontalViewAllButton.setVisibility(View.VISIBLE);
                horizontalViewAllButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ViewAllActivity.wishListModalList =viewAllProductList;
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code",0);
                        viewAllIntent.putExtra("title",title);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });

            } else {
                horizontalViewAllButton.setVisibility(View.INVISIBLE);

            }
            HorizontalProductAdapter horizontalProductAdapter = new HorizontalProductAdapter(horizontalProductScrollmodalList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecycleView.setLayoutManager(linearLayoutManager);
            horizontalRecycleView.setAdapter(horizontalProductAdapter);
            horizontalProductAdapter.notifyDataSetChanged();
        }
    }

    //*** horizontal products view Layout


    // ** grid product view

    public class GridproductViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout container;
        private TextView gridLayouttitle;
        private Button gridLayoutViewAllBtn;

        private GridLayout gridProductLayout;



        public GridproductViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            gridLayouttitle = itemView.findViewById(R.id.grid_product_layout_title);
            gridLayoutViewAllBtn = itemView.findViewById(R.id.grid_product_layout_viewall_button);
            gridProductLayout = itemView.findViewById(R.id.grid_layout);
        }

        private void setGridProductLayout(final List<HorizontalProductScrollmodal> horizontalProductScrollmodalList, final String title, String color){
            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            gridLayouttitle.setText(title);

            for(int x=0; x < 4;x++){
                // assign all class via id
                ImageView productImage = gridProductLayout.getChildAt(x).findViewById(R.id.hs_product_image);
                TextView productTitle = gridProductLayout.getChildAt(x).findViewById(R.id.hs_product_title);
                final TextView productDescription = gridProductLayout.getChildAt(x).findViewById(R.id.hs_product_description);
                TextView productPrice = gridProductLayout.getChildAt(x).findViewById(R.id.hs_product_price);

                // access info from list
                Glide.with(itemView.getContext()).load(horizontalProductScrollmodalList.get(x).getProductImage()).apply(new RequestOptions().placeholder(R.mipmap.home_logo)).into(productImage);
//
                productTitle.setText(horizontalProductScrollmodalList.get(x).getProductTitle());
                productDescription.setText(horizontalProductScrollmodalList.get(x).getProductDescription());
                productPrice.setText("TK."+horizontalProductScrollmodalList.get(x).getProductPrice()+"/-");
                // disable background curve
                gridProductLayout.getChildAt(x).setBackgroundColor(Color.parseColor("#ffffff"));
                final int finalX=x;
                gridProductLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductsDetailsActivity.class);
                        productDetailsIntent.putExtra("PRODUCT_ID",horizontalProductScrollmodalList.get(finalX).getProductID());

//                        productDetailsIntent.putExtra("PRODUCT_ID",horizontalProductScrollmodalList.get(finalX).getProductID());
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });


            }

            gridLayoutViewAllBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //layout_code
                    ViewAllActivity.horizontalProductScrollmodalList =horizontalProductScrollmodalList;
                    Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code",1);
                    viewAllIntent.putExtra("title",title);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });
        }
    }
}
