package poly.cafe.entity;

import java.util.Date;

public class Productivity {
    private int id;
    private String userName;
    private int productId;
    private Products product;
    private int quantity;
    private Date workDate;

    public Productivity() {
    }

    public Productivity(int id, String userName, int productId, Products product, int quantity, Date workDate) {
        this.id = id;
        this.userName = userName;
        this.productId = productId;
        this.product = product;
        this.quantity = quantity;
        this.workDate = workDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    
}
