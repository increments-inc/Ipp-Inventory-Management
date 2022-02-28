package com.incrementsinc.ipp_inventory_management.model;

public class Product {
    private int id;
    private String pClass;
    private String product;
    private String description;
    private String crossRef1;
    private String crossRef2;
    private String vendor;
    private String createdAt;
    private String updatedAt;

    // this empty constructor is necessary to avoid errors
    public Product() {
    }

    // this constructor will use to get all the value for the product from the database
    public Product(int id, String pClass, String product, String description, String crossRef1, String crossRef2, String vendor, String createdAt, String updatedAt) {
        this.id = id;
        this.pClass = pClass;
        this.product = product;
        this.description = description;
        this.crossRef1 = crossRef1;
        this.crossRef2 = crossRef2;
        this.vendor = vendor;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpClass() {
        return pClass;
    }

    public void setpClass(String pClass) {
        this.pClass = pClass;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCrossRef1() {
        return crossRef1;
    }

    public void setCrossRef1(String crossRef1) {
        this.crossRef1 = crossRef1;
    }

    public String getCrossRef2() {
        return crossRef2;
    }

    public void setCrossRef2(String crossRef2) {
        this.crossRef2 = crossRef2;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}