package com.incrementsinc.ipp_inventory_management.model;

public class Product {
    private int id;
    private String cls;
    private String productId;
    private String productName;
    private String productDes;
    private String date;
    private String time;
    private String crossRef;
    private String barcode;

    public Product() {
    }

    public Product(int id, String cls, String productId, String productName, String productDes, String date, String time, String crossRef, String barcode) {
        this.id = id;
        this.cls = cls;
        this.productId = productId;
        this.productName = productName;
        this.productDes = productDes;
        this.date = date;
        this.time = time;
        this.crossRef = crossRef;
        this.barcode = barcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCrossRef() {
        return crossRef;
    }

    public void setCrossRef(String crossRef) {
        this.crossRef = crossRef;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
