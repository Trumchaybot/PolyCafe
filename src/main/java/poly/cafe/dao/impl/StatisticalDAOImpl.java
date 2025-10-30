package poly.cafe.dao.impl;

import java.sql.ResultSet;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.StatisticalDAO;

public class StatisticalDAOImpl implements StatisticalDAO {

    private int getIntValue(String sql) {
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private double getDoubleValue(String sql) {
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble(1);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int getTotalOrder() {
        return getIntValue("SELECT COUNT(*) FROM Orders");
    }

    @Override
    public int getTotalImport() {
        return getIntValue("SELECT COUNT(*) FROM Import");
    }

    @Override
    public int getTotalExport() {
        return getIntValue("SELECT COUNT(*) FROM Export");
    }

    @Override
    public double getTotalIncome() {
        return getDoubleValue("SELECT SUM(total_income) FROM TotalRevenue");
    }

    @Override
    public double getTotalOrderIncome() {
        return getDoubleValue("SELECT SUM(order_income) FROM TotalRevenue");
    }

    @Override
    public double getTotalExportIncome() {
        return getDoubleValue("SELECT SUM(export_income) FROM TotalRevenue");
    }

    @Override
    public double getTotalExpense() {
        return getDoubleValue("SELECT SUM(total_expense) FROM TotalExpenses");
    }

    @Override
    public double getTotalSalaryExpense() {
        return getDoubleValue("SELECT SUM(salary_expense) FROM TotalExpenses");
    }

    @Override
    public double getTotalImportExpense() {
        return getDoubleValue("SELECT SUM(import_expense) FROM TotalExpenses");
    }
}
