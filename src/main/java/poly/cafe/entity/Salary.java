package poly.cafe.entity;

import java.util.Date;

public class Salary {
    private int id;
    private String userName;
    private int month;
    private int year;
    private float totalHour;
    private int totalProduct;
    private float bonus;
    private float totalSalary;

    public Salary() {
    }

    public Salary(int id, String userName, int month, int year, float totalHour, int totalProduct, float bonus, float totalSalary) {
        this.id = id;
        this.userName = userName;
        this.month = month;
        this.year = year;
        this.totalHour = totalHour;
        this.totalProduct = totalProduct;
        this.bonus = bonus;
        this.totalSalary = totalSalary;
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

    public float getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(float totalHour) {
        this.totalHour = totalHour;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public float getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(float totalSalary) {
        this.totalSalary = totalSalary;
    }

    
}
