package poly.cafe.entity;

public class OrderDetails {
    private int id;
    private int orderId;
    private Products product;
    private int productId;
    private int quantity;
    private double totalAmount;

    public OrderDetails() {
    }

    public OrderDetails(int id, int orderId, Products product, int productId, int quantity, double totalAmount) {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    
}
