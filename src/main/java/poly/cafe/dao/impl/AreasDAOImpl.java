package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Areas;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.AreasDAO;

public class AreasDAOImpl implements AreasDAO {

    final String INSERT_SQL = "INSERT INTO Areas (area_name, description, status) VALUES (?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Areas SET area_name = ?, description = ?, status = ? WHERE area_id = ?";
    final String DELETE_SQL = "DELETE FROM Areas WHERE area_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Areas";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Areas WHERE area_id = ?";
    final String DELETE_CHI_TIET_KHU_VUC = "DELETE FROM AreaDetails WHERE area_id = ?";

    @Override
    public Areas create(Areas entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getAreaName(), entity.getDescription(), entity.getStatus());
        return entity;
    }

    @Override
    public void update(Areas entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getAreaName(), entity.getDescription(), entity.getStatus(), entity.getAreaId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_CHI_TIET_KHU_VUC, id);
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Areas> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Areas findById(Integer id) {
        List<Areas> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Areas> selectBySql(String sql, Object... args) {
        List<Areas> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Areas entity = new Areas();
                entity.setAreaId(rs.getInt("area_id"));
                entity.setAreaName(rs.getString("area_name"));
                entity.setDescription(rs.getString("description"));
                entity.setStatus(rs.getString("status"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
