package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.TotalRevenue;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.TotalRevenueDAO;

public class TotalRevenueDAOImpl implements TotalRevenueDAO {

    final String INSERT_SQL = "INSERT INTO TotalRevenue (month, year, order_income, export_income, total_income) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE TotalRevenue SET month=?, year=?, order_income=?, export_income=?, total_income=? WHERE ID=?";
    final String DELETE_SQL = "DELETE FROM TotalRevenue WHERE ID=?";
    final String SELECT_ALL_SQL = "SELECT * FROM TotalRevenue";
    final String SELECT_BY_ID_SQL = "SELECT * FROM TotalRevenue WHERE ID=?";

    @Override
    public TotalRevenue create(TotalRevenue entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMonth(),
                entity.getYear(),
                entity.getOrderIncome(),
                entity.getExportIncome(),
                entity.getTotalIncome()
        );
        return entity;
    }

    @Override
    public void update(TotalRevenue entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMonth(),
                entity.getYear(),
                entity.getOrderIncome(),
                entity.getExportIncome(),
                entity.getTotalIncome(),
                entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<TotalRevenue> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TotalRevenue findById(Integer id) {
        List<TotalRevenue> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<TotalRevenue> selectBySql(String sql, Object... args) {
        List<TotalRevenue> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                TotalRevenue entity = new TotalRevenue();
                entity.setId(rs.getInt("ID"));
                entity.setMonth(rs.getInt("Thang"));
                entity.setYear(rs.getInt("Nam"));
                entity.setOrderIncome(rs.getDouble("order_income"));
                entity.setExportIncome(rs.getDouble("export_income"));
                entity.setExportIncome(rs.getDouble("total_income"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException("Error at selectBySql in TotalRevenueDAOImpl", e);
        }
        return list;
    }
    @Override
public List<TotalRevenue> findByCondition(Integer month, Integer year) {
    List<TotalRevenue> list = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM TotalRevenue WHERE 1=1 ");

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
            TotalRevenue entity = new TotalRevenue();
            entity.setId(rs.getInt("ID"));
                entity.setMonth(rs.getInt("month"));
                entity.setYear(rs.getInt("year"));
                entity.setOrderIncome(rs.getDouble("order_income"));
                entity.setExportIncome(rs.getDouble("export_income"));
                entity.setExportIncome(rs.getDouble("total_income"));
            list.add(entity);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
}
