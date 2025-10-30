package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.ImportDetails;
import poly.cafe.entity.Materials;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ImportDetailsDAO;

public class ImportDetailsDAOImpl implements ImportDetailsDAO {

    final String INSERT_SQL = "INSERT INTO ImportDetails (import_id, material_id, quantity, total_amount) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE ImportDetails SET import_id = ?, material_id = ?, quantity = ?, total_amount = ? WHERE id = ?";
    final String DELETE_SQL = "DELETE FROM ImportDetails WHERE id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM v_ImportDetails";
    final String SELECT_BY_IMPORT_ID = "SELECT * FROM v_ImportDetails WHERE import_id = ?";
    final String SELECT_BY_DETAIL_ID = "SELECT * FROM v_ImportDetails WHERE id = ?";


    @Override
    public ImportDetails create(ImportDetails entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getImportId(),
            entity.getMaterialId(),
            entity.getQuantity(),
            entity.getTotalAmount()
        );
        return entity;
    }

    @Override
    public void update(ImportDetails entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getImportId(),
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
    public List<ImportDetails> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ImportDetails findById(Integer id) {
        List<ImportDetails> list = selectBySql(SELECT_BY_DETAIL_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<ImportDetails> findByImportId(int importId) {
        return selectBySql(SELECT_BY_IMPORT_ID, importId);
    }

    private List<ImportDetails> selectBySql(String sql, Object... args) {
        List<ImportDetails> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                ImportDetails entity = new ImportDetails();
                entity.setId(rs.getInt("id"));
                entity.setImportId(rs.getInt("import_id"));
                entity.setMaterialId(rs.getInt("material_id"));
                entity.setQuantity(rs.getInt("quantity"));
                entity.setTotalAmount(rs.getDouble("total_amount"));
                
                Materials nvl = new Materials();
                nvl.setMaterialId(rs.getInt("material_id"));
                nvl.setMaterialName(rs.getString("material_name"));
                entity.setMaterial(nvl);
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
