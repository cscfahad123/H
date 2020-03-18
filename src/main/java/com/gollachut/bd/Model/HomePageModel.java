package com.gollachut.bd.Model;

import java.util.List;

public class HomePageModel {

    public static final int BANNER_SLIDER =0;
    public static final int STRIP_AD_BANNER=1;
    public static  final int HORIZONTAL_PRODUCT_VIEW=2;
    public static final int GRID_PRODUCT_VIEW=3;
    private String backgroundColor;


    private int type;


    ////**************Banner Slider***********/////
    private List<SliderModal> sliderModalList;
    public HomePageModel(int type, List<SliderModal> sliderModalList) {
        this.type = type;
        this.sliderModalList = sliderModalList;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<SliderModal> getSliderModalList() {
        return sliderModalList;
    }
    public void setSliderModalList(List<SliderModal> sliderModalList) {
        this.sliderModalList = sliderModalList;
    }

    ////**************Banner Slider***********/////

    ////**************Strip add***********/////
    private String resource;


    public HomePageModel(int type, String resource, String backgroundColor) {
        this.type =type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }

    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    ////**************Strip add***********/////





    private String title;
    private List<HorizontalProductScrollmodal> horizontalProductScrollmodalList;
    ////**************Horizontal Products layout
    ///Grid product Layout***********/////
    private List<WishListModal> viewAllProductList;

    public HomePageModel(int type, String title, String backgroundColor, List<HorizontalProductScrollmodal> horizontalProductScrollmodalList,List<WishListModal> viewAllProductList) {
        this.type = type;
        this.title = title;
        this.backgroundColor =backgroundColor;
        this.horizontalProductScrollmodalList = horizontalProductScrollmodalList;
        this.viewAllProductList =viewAllProductList;
    }
    /// Grid product Layout***********/////
    public List<WishListModal> getViewAllProductList() {
        return viewAllProductList;
    }

    public void setViewAllProductList(List<WishListModal> viewAllProductList) {
        this.viewAllProductList = viewAllProductList;
    }
    ////**************Horizontal Products layout

    public HomePageModel(int type, String title, String backgroundColor, List<HorizontalProductScrollmodal> horizontalProductScrollmodalList) {
        this.type = type;
        this.title = title;
        this.backgroundColor =backgroundColor;
        this.horizontalProductScrollmodalList = horizontalProductScrollmodalList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalProductScrollmodal> getHorizontalProductScrollmodalList() {
        return horizontalProductScrollmodalList;
    }

    public void setHorizontalProductScrollmodalList(List<HorizontalProductScrollmodal> horizontalProductScrollmodalList) {
        this.horizontalProductScrollmodalList = horizontalProductScrollmodalList;
    }




}
