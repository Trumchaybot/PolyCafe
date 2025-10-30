package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Materials;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.MaterialsDAO;

public class MaterialsDAOImpl implements MaterialsDAO {

    final String INSERT_SQL = "INSERT INTO Materials (material_name, unit, unit_price, quantity) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Materials SET material_name = ?, unit = ?, unit_price = ?, quantity = ? WHERE material_id = ?";
    final String DELETE_SQL = "DELETE FROM Materials WHERE material_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Materials";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Materials WHERE material_id = ?";

    @Override
    public Materials create(Materials entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getMaterialName(), entity.getUnit(), entity.getUnitPrice(), entity.getQuantity());
        return entity;
    }

    @Override
    public void update(Materials entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getMaterialName(), entity.getUnit(), entity.getUnitPrice(), entity.getQuantity(), entity.getMaterialId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Materials> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Materials findById(Integer id) {
        List<Materials> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Materials> selectBySql(String sql, Object... args) {
        List<Materials> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Materials nvl = new Materials();
                nvl.setMaterialId(rs.getInt("material_id"));
                nvl.setMaterialName(rs.getString("material_name"));
                nvl.setUnit(rs.getString("unit"));
                nvl.setUnitPrice(rs.getFloat("unit_price"));
                nvl.setQuantity(rs.getInt("quantity"));
                list.add(nvl);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
