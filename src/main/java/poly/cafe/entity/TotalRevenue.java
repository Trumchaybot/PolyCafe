package poly.cafe.entity;

public class TotalRevenue {
    private int id;
    private int month;
    private int year;
    private double orderIncome;
    private double exportIncome;
    private double totalIncome;

    public TotalRevenue() {
    }

    public TotalRevenue(int id, int month, int year, double orderIncome, double exportIncome, double totalIncome) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.orderIncome = orderIncome;
        this.exportIncome = exportIncome;
        this.totalIncome = totalIncome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getOrderIncome() {
        return orderIncome;
    }

    public void setOrderIncome(double orderIncome) {
        this.orderIncome = orderIncome;
    }

    public double getExportIncome() {
        return exportIncome;
    }

    public void setExportIncome(double exportIncome) {
        this.exportIncome = exportIncome;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    
}
