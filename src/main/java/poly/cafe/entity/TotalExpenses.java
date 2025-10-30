package poly.cafe.entity;

public class TotalExpenses {
    private int id;
    private int month;
    private int year;
    private double salaryExpense;
    private double importExpense;
    private double totalExpense;

    public TotalExpenses() {
    }

    public TotalExpenses(int id, int month, int year, double salaryExpense, double importExpense, double totalExpense) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.salaryExpense = salaryExpense;
        this.importExpense = importExpense;
        this.totalExpense = totalExpense;
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

    public double getSalaryExpense() {
        return salaryExpense;
    }

    public void setSalaryExpense(double salaryExpense) {
        this.salaryExpense = salaryExpense;
    }

    public double getImportExpense() {
        return importExpense;
    }

    public void setImportExpense(double importExpense) {
        this.importExpense = importExpense;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    
}
