package com.gollachut.bd.Model;

public class MyRewardModal {

    private String title;
    private String expiryDate;
    private String couponBody;

    public MyRewardModal(String title, String expiryDate, String couponBody) {
        this.title = title;
        this.expiryDate = expiryDate;
        this.couponBody = couponBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCouponBody() {
        return couponBody;
    }

    public void setCouponBody(String couponBody) {
        this.couponBody = couponBody;
    }
}
