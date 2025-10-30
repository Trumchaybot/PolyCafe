package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Salary;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.SalaryDAO;

public class SalaryDAOImpl implements SalaryDAO {

    final String INSERT_SQL = "INSERT INTO Salary (UserName, month, year, total_hours, total_products, bonus, total_salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Salary SET UserName = ?, month = ?, year = ?, total_hours = ?, total_products = ?, bonus = ?, total_salary = ? WHERE id = ?";
    final String DELETE_SQL = "DELETE FROM Salary WHERE id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Salary";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Salary WHERE id = ?";

    @Override
    public Salary create(Salary entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getUserName(),
            entity.getMonth(),
            entity.getYear(),
            entity.getTotalHour(),
            entity.getTotalProduct(),
            entity.getBonus(),
            entity.getTotalSalary()
        );
        return entity;
    }

    @Override
    public void update(Salary entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getUserName(),
            entity.getMonth(),
            entity.getYear(),
            entity.getTotalHour(),
            entity.getTotalProduct(),
            entity.getBonus(),
            entity.getTotalSalary(),
            entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Salary> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Salary findById(Integer id) {
        List<Salary> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Salary> selectBySql(String sql, Object... args) {
        List<Salary> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Salary entity = new Salary();
                entity.setId(rs.getInt("id"));
                entity.setUserName(rs.getString("UserName"));
                entity.setMonth(rs.getInt("month"));
                entity.setYear(rs.getInt("year"));
                entity.setTotalHour(rs.getFloat("total_hours"));
                entity.setTotalProduct(rs.getInt("total_products"));
                entity.setBonus(rs.getFloat("bonus"));
                entity.setTotalSalary(rs.getFloat("total_salary"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
public List<Salary> findByCondition(String userName, Integer month, Integer year) {
    List<Salary> list = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM Salary WHERE 1=1 ");

    // Xây dựng điều kiện động
    if (userName != null && !userName.isEmpty()) {
        sql.append(" AND UserName = ?");
    }
    if (month != null) {
        sql.append(" AND month = ?");
    }
    if (year != null) {
        sql.append(" AND year = ?");
    }

    try {
        List<Object> params = new ArrayList<>();
        if (userName != null && !userName.isEmpty()) params.add(userName);
        if (month != null) params.add(month);
        if (year != null) params.add(year);

        ResultSet rs = XJdbc.executeQuery(sql.toString(), params.toArray());
        while (rs.next()) {
                Salary entity = new Salary();
                entity.setId(rs.getInt("id"));
                entity.setUserName(rs.getString("UserName"));
                entity.setMonth(rs.getInt("month"));
                entity.setYear(rs.getInt("year"));
                entity.setTotalHour(rs.getFloat("total_hour"));
                entity.setTotalProduct(rs.getInt("total_products"));
                entity.setBonus(rs.getFloat("bonus"));
                entity.setTotalSalary(rs.getFloat("total_salary"));
                list.add(entity);
            }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
