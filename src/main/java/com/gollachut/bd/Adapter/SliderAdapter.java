package com.gollachut.bd.Adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gollachut.bd.Model.SliderModal;
import com.gollachut.bd.R;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<SliderModal> sliderModalList;

    public SliderAdapter(List<SliderModal> sliderModals) {
        this.sliderModalList = sliderModals;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
      // assign slider layout
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout,container,false);
       //assign constrain layout for styling
        ConstraintLayout bannerContainer = view.findViewById(R.id.banner_constrain);
        // add background color on constrain banner layout
        bannerContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModalList.get(position).getBackgroundColor())));
        ImageView banner = view.findViewById(R.id.banner_slide);
        // set banner
        Glide.with(container.getContext()).load(sliderModalList.get(position).getBanner()).apply(new RequestOptions().placeholder(R.mipmap.banneradd)).into(banner);
        container.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return sliderModalList.size();
    }
}
