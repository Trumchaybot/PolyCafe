package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Productivity;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ProductivityDAO;

public class ProductivityDAOImpl implements ProductivityDAO {

    final String INSERT_SQL = "INSERT INTO Productivity (UserName, product_id, quantity, work_date) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Productivity SET UserName = ?, product_id = ?, quantity = ?, work_date = ? WHERE id = ?";
    final String DELETE_SQL = "DELETE FROM Productivity WHERE id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Productivity";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Productivity WHERE id = ?";

    @Override
    public Productivity create(Productivity entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getUserName(),
            entity.getProductId(),
            entity.getQuantity(),
            entity.getWorkDate()
        );
        return entity;
    }

    @Override
    public void update(Productivity entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getUserName(),
            entity.getProductId(),
            entity.getQuantity(),
            entity.getWorkDate(),
            entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Productivity> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Productivity findById(Integer id) {
        List<Productivity> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Productivity> selectBySql(String sql, Object... args) {
        List<Productivity> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Productivity entity = new Productivity();
                entity.setId(rs.getInt("id"));
                entity.setUserName(rs.getString("UserName"));
                entity.setProductId(rs.getInt("product_id"));
                entity.setQuantity(rs.getInt("quantity"));
                entity.setWorkDate(rs.getDate("work_date"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
