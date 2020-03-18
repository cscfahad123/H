package com.gollachut.bd.Model;

public class MyOrderItemModal {

    private int productsImage;
    private String productTitle;
    private String deliveryStatus;

    private int ratting;

    public MyOrderItemModal(int productsImage, int ratting, String productTitle, String deliveryStatus) {

        this.productsImage = productsImage;
        this.ratting = ratting;
        this.productTitle = productTitle;
        this.deliveryStatus = deliveryStatus;
    }

    public int getProductsImage() {
        return productsImage;
    }

    public int getRatting() {
        return ratting;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
    }

    public void setProductsImage(int productsImage) {
        this.productsImage = productsImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
