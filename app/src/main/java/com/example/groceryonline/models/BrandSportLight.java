package com.example.groceryonline.models;

public class BrandSportLight {
    String brandSportLight_img;
    String type;

    public BrandSportLight() {
    }

    public BrandSportLight(String brandSportLight_img, String type) {
        this.brandSportLight_img = brandSportLight_img;
        this.type = type;
    }

    public String getBrandSportLight_img() {
        return brandSportLight_img;
    }

    public void setBrandSportLight_img(String brandSportLight_img) {
        this.brandSportLight_img = brandSportLight_img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
