package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.TotalExpenses;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.TotalExpensesDAO;

public class TotalExpensesDAOImpl implements TotalExpensesDAO {

    final String INSERT_SQL = "INSERT INTO TotalExpenses (month, year, salary_expense, import_expense, total_expense) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE TotalExpenses SET month=?, year=?, salary_expense=?, import_expense=?, total_expense=? WHERE ID=?";
    final String DELETE_SQL = "DELETE FROM TotalExpenses WHERE ID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM TotalExpenses";
    final String SELECT_BY_ID_SQL = "SELECT * FROM TotalExpenses WHERE ID=?";

    @Override
    public TotalExpenses create(TotalExpenses entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMonth(),
                entity.getYear(),
                entity.getSalaryExpense(),
                entity.getImportExpense(),
                entity.getTotalExpense()
        );
        return entity;
    }

    @Override
    public void update(TotalExpenses entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMonth(),
                entity.getYear(),
                entity.getSalaryExpense(),
                entity.getImportExpense(),
                entity.getTotalExpense(),
                entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<TotalExpenses> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TotalExpenses findById(Integer id) {
        List<TotalExpenses> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<TotalExpenses> selectBySql(String sql, Object... args) {
        List<TotalExpenses> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                TotalExpenses entity = new TotalExpenses();
                entity.setId(rs.getInt("ID"));
                entity.setMonth(rs.getInt("month"));
                entity.setYear(rs.getInt("year"));
                entity.setSalaryExpense(rs.getDouble("salary_expense"));
                entity.setImportExpense(rs.getDouble("import_expense"));
                entity.setTotalExpense(rs.getDouble("total_expense"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi selectBySql tại TotalExpensesDAOImpl", e);
        }
        return list;
    }
    @Override
public List<TotalExpenses> findByCondition(Integer month, Integer year) {
    List<TotalExpenses> list = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM TotalExpenses WHERE 1=1 ");

    // Xây dựng điều kiện động
    if (month != null) {
        sql.append(" AND month = ?");
    }
    if (year != null) {
        sql.append(" AND year = ?");
    }

    try {
        List<Object> params = new ArrayList<>();
        if (month != null) params.add(month);
        if (year != null) params.add(year);

        ResultSet rs = XJdbc.executeQuery(sql.toString(), params.toArray());
        while (rs.next()) {
            TotalExpenses entity = new TotalExpenses();
            entity.setId(rs.getInt("id"));
            entity.setMonth(rs.getInt("month"));
            entity.setYear(rs.getInt("year"));
            entity.setSalaryExpense(rs.getDouble("salary_expense"));
            entity.setImportExpense(rs.getDouble("import_expense"));
            entity.setTotalExpense(rs.getDouble("total_expense"));
            list.add(entity);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
}
