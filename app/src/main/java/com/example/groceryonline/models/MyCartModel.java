package com.example.groceryonline.models;

import java.io.Serializable;

public class MyCartModel implements Serializable { //By making MyCartModel Serializable we can pass the object of this class to other activities of this Android APP using Intent Object.
    String ProductImage;
    String productName;
    String productQuantityDetails;
    String TotalQuantity;
    String productPrice;
    int totalPrice;
    String documentId;
    String currentDate;

    public MyCartModel() {
    }

    public MyCartModel(String productImage, String productName, String productQuantityDetails, String totalQuantity, String productPrice, int totalPrice, String documentId, String currentDate) {
        ProductImage = productImage;
        this.productName = productName;
        this.productQuantityDetails = productQuantityDetails;
        TotalQuantity = totalQuantity;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
        this.documentId = documentId;
        this.currentDate = currentDate;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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
