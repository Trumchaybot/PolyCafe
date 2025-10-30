package poly.cafe.entity;

import java.util.Date;

public class Import {
    private Integer importId;
    private String userName;
    private Integer supplierId;
    private Suppliers supplier;
    private double totalAmount;
    private Date importDate;

    public Import() {
    }

    public Import(Integer importId, String userName, Integer supplierId, Suppliers supplier, double totalAmount, Date importDate) {
        this.importId = importId;
        this.userName = userName;
        this.supplierId = supplierId;
        this.supplier = supplier;
        this.totalAmount = totalAmount;
        this.importDate = importDate;
    }

    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    
}
