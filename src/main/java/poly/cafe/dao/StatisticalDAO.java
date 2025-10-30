package poly.cafe.dao;

public interface StatisticalDAO {
    int getTotalOrder();
    int getTotalImport();
    int getTotalExport();
    double getTotalIncome();
    double getTotalOrderIncome();
    double getTotalExportIncome();
    double getTotalExpense();
    double getTotalSalaryExpense();
    double getTotalImportExpense();
}
