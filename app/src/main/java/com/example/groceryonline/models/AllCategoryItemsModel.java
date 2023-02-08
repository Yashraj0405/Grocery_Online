package com.example.groceryonline.models;

import java.io.Serializable;

public class AllCategoryItemsModel implements Serializable {
    String name;
    String img_url;
    String price;
    String qty;
    String type;

    public AllCategoryItemsModel() {}

    public AllCategoryItemsModel(String name, String img_url, String price, String qty, String type) {
        this.name = name;
        this.img_url = img_url;
        this.price = price;
        this.qty = qty;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
