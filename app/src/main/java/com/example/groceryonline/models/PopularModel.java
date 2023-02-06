package com.example.groceryonline.models;

public class PopularModel {
    //Variable to store data in database ex. name(Product) = Vegetable,Snacks etc
    String name;
    //Variable to description in database of particular product
    String description;
    //Variable to store rating of a particular product
    String rating;
    //Variable to store discount on a particular product
    String discount;
    String type;
    //variable to store url of the product Image
    String img_url;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public PopularModel() {
    }

    public PopularModel(String name, String description, String rating, String discount, String type, String img_url) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.discount = discount;
        this.type = type;
        this.img_url = img_url;
    }

    // Getter and setter method
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
