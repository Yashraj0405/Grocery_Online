package com.example.groceryonline.models;

import java.io.Serializable;

public class OrderModel implements Serializable {

    String OrderPlacedDate;
    String ProductImage;
    String TotalQuantity;
    String productName;
    String productPrice;
    String productQuantityDetails;
    String totalPrice;

    public OrderModel() {
    }

    public OrderModel(String orderPlacedDate, String productImage, String totalQuantity, String productName, String productPrice, String productQuantityDetails, String totalPrice) {
        OrderPlacedDate = orderPlacedDate;
        ProductImage = productImage;
        TotalQuantity = totalQuantity;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantityDetails = productQuantityDetails;
        this.totalPrice = totalPrice;
    }

    public String getOrderPlacedDate() {
        return OrderPlacedDate;
    }

    public void setOrderPlacedDate(String orderPlacedDate) {
        OrderPlacedDate = orderPlacedDate;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantityDetails() {
        return productQuantityDetails;
    }

    public void setProductQuantityDetails(String productQuantityDetails) {
        this.productQuantityDetails = productQuantityDetails;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}

