package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.ExportDetails;
import poly.cafe.entity.Materials;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ExportDetailsDAO;

public class ExportDetailsDAOImpl implements ExportDetailsDAO {

    final String INSERT_SQL = "INSERT INTO ExportDetails (export_id, material_id, quantity, total_amount) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE ExportDetails SET export_id = ?, material_id = ?, quantity = ?, total_amount = ? WHERE id = ?";
    final String DELETE_SQL = "DELETE FROM ExportDetails WHERE id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM ExportDetails";
    final String SELECT_BY_EXPORT_ID = "SELECT * FROM v_ExportDetails WHERE export_id = ?";
    final String SELECT_BY_CHITIET_ID = "SELECT * FROM v_ExportDetailso WHERE id = ?";

    @Override
    public ExportDetails create(ExportDetails entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getExportId(),
            entity.getMaterialId(),
            entity.getQuantity(),
            entity.getTotalAmount()
        );
        return entity;
    }

    @Override
    public void update(ExportDetails entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getExportId(),
            entity.getMaterialId(),
            entity.getQuantity(),
            entity.getTotalAmount(),
            entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<ExportDetails> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ExportDetails findById(Integer id) {
        List<ExportDetails> list = selectBySql(SELECT_BY_CHITIET_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<ExportDetails> selectBySql(String sql, Object... args) {
        List<ExportDetails> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                ExportDetails entity = new ExportDetails();
                entity.setId(rs.getInt("id"));
                entity.setExportId(rs.getInt("export_id"));
                entity.setMaterialId(rs.getInt("material_id"));
                
                 Materials nvl = new Materials();
                nvl.setMaterialId(rs.getInt("material_id"));
                nvl.setMaterialName(rs.getString("material_name"));
                entity.setMaterial(nvl);
                
                entity.setQuantity(rs.getInt("quantity"));
                entity.setTotalAmount(rs.getDouble("total_amount"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public List<ExportDetails> findByExportId(int exportId) {
        return selectBySql(SELECT_BY_EXPORT_ID, exportId);
    }
}
