package poly.cafe.entity;

import java.util.Date;

public class Export {
    private Integer exportId;
    private String userName;
    private double totalAmount;
    private Date exportDate;

    public Export() {
    }

    public Export(Integer exportId, String userName, double totalAmount, Date exportDate) {
        this.exportId = exportId;
        this.userName = userName;
        this.totalAmount = totalAmount;
        this.exportDate = exportDate;
    }

    public Integer getExportId() {
        return exportId;
    }

    public void setExportId(Integer exportId) {
        this.exportId = exportId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }
}
