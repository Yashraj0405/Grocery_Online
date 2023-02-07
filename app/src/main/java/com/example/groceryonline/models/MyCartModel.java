package com.example.groceryonline.models;

public class MyCartModel {
    String ProductImage;
    String productName;
    String productQuantityDetails;
    String TotalQuantity;
    String productPrice;
    int totalPrice;

    public MyCartModel() {
    }

    public MyCartModel(String productImage, String productName, String productQuantityDetails, String totalQuantity, String productPrice, int totalPrice) {
        ProductImage = productImage;
        this.productName = productName;
        this.productQuantityDetails = productQuantityDetails;
        TotalQuantity = totalQuantity;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQuantityDetails() {
        return productQuantityDetails;
    }

    public void setProductQuantityDetails(String productQuantityDetails) {
        this.productQuantityDetails = productQuantityDetails;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
