package com.example.amazonpart2mongodb.models;


import javax.validation.constraints.NotNull;

public class Product {

    private String id;
   @NotNull(message = "Please provide a Category Id")
    private String categoryId;

    @NotNull(message = "Please provide a product name")
    private String productName;


    private String productDesc1;
    private String productDesc2;
    private String productDesc3;
    private boolean isBestSeller;
    private String rating;
    private boolean stock;
    private String productImageSmall;
    private String productImageBig;
    @NotNull(message = "Please provide a product price")
    private String productPrice;
    private String quantity;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc1() {
        return productDesc1;
    }

    public void setProductDesc1(String productDesc1) {
        this.productDesc1 = productDesc1;
    }

    public String getProductDesc2() {
        return productDesc2;
    }

    public void setProductDesc2(String productDesc2) {
        this.productDesc2 = productDesc2;
    }

    public String getProductDesc3() {
        return productDesc3;
    }

    public void setProductDesc3(String productDesc3) {
        this.productDesc3 = productDesc3;
    }

   /* public boolean isBestSeller() {
        return isBestSeller;
    }*/
   public boolean getIsBestSeller() {
       return isBestSeller;
   }
    public void setIsBestSeller(boolean bestSeller) {
        isBestSeller = bestSeller;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean getStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public String getProductImageSmall() {
        return productImageSmall;
    }

    public void setProductImageSmall(String productImageSmall) {
        this.productImageSmall = productImageSmall;
    }

    public String getProductImageBig() {
        return productImageBig;
    }

    public void setProductImageBig(String productImageBig) {
        this.productImageBig = productImageBig;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Product(String id, String categoryId, String productName, String productDesc1, String productDesc2, String productDesc3, boolean isBestSeller, String rating, boolean stock, String productImageSmall, String productImageBig, String productPrice, String quantity) {
        this.id = id;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productDesc1 = productDesc1;
        this.productDesc2 = productDesc2;
        this.productDesc3 = productDesc3;
        this.isBestSeller = isBestSeller;
        this.rating = rating;
        this.stock = stock;
        this.productImageSmall = productImageSmall;
        this.productImageBig = productImageBig;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id='" + id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc1='" + productDesc1 + '\'' +
                ", productDesc2='" + productDesc2 + '\'' +
                ", productDesc3='" + productDesc3 + '\'' +
                ", isBestSeller=" + isBestSeller +
                ", rating='" + rating + '\'' +
                ", stock=" + stock +
                ", productImageSmall='" + productImageSmall + '\'' +
                ", productImageBig='" + productImageBig + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                '}';
    }
}
