package com.gollachut.bd.Model;

import androidx.annotation.NonNull;

public class CategoryModel {

    private String categoryIconName;
    private String categoryName;

    public CategoryModel(String categoryIconName, String categoryName) {
        this.categoryIconName = categoryIconName;
        this.categoryName = categoryName;
    }

    public String getCategoryIconName() {
        return categoryIconName;
    }

    public void setCategoryIconName(String categoryIconName) {
        this.categoryIconName = categoryIconName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @NonNull
    @Override
    public String toString() {
        return "icon: "+categoryIconName+" catname: "+categoryName;
    }
}
