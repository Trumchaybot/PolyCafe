package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Export;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ExportDAO;

public class ExportDAOImpl implements ExportDAO {

    final String INSERT_SQL = "INSERT INTO Export (UserName, total_amount, export_date) VALUES (?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Export SET UserName = ?, total_amount = ?, export_date = ? WHERE export_id = ?";
    final String DELETE_SQL = "DELETE FROM Export WHERE export_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Export";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Export WHERE export_id = ?";

    @Override
    public Export create(Export entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getUserName(),
            entity.getTotalAmount(),
            entity.getExportDate()
        );
        return entity;
    }

    @Override
    public void update(Export entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getUserName(),
            entity.getTotalAmount(),
            entity.getExportDate(),
            entity.getExportId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Export> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Export findById(Integer id) {
        List<Export> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Export> selectBySql(String sql, Object... args) {
        List<Export> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Export entity = new Export();
                entity.setExportId(rs.getInt("export_id"));
                entity.setUserName(rs.getString("UserName"));
                entity.setTotalAmount(rs.getDouble("total_amount"));
                entity.setExportDate(rs.getDate("export_date"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public int insertAndReturnId(Export entity) {
        try {
            ResultSet rs = XJdbc.executeUpdateAndReturnGeneratedKeys(INSERT_SQL,
                entity.getUserName(),
                entity.getTotalAmount(),
                entity.getExportDate()
            );

            if (rs.next()) {
                return rs.getInt(1); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
