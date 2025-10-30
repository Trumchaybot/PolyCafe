package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.ProductionStages;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ProductionStagesDAO;

public class ProductionStagesDAOImpl implements ProductionStagesDAO {

    final String INSERT_SQL = "INSERT INTO ProductionStages (productionStages_name) VALUES (?)";
    final String UPDATE_SQL = "UPDATE ProductionStages SET productionStages_name = ? WHERE productionStages_id = ?";
    final String DELETE_SQL = "DELETE FROM ProductionStages WHERE productionStages_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM ProductionStages";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ProductionStages WHERE productionStages_id = ?";

    @Override
    public ProductionStages create(ProductionStages entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getProductionStagesName());
        return entity;
    }

    @Override
    public void update(ProductionStages entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getProductionStagesName(), entity.getProductionStagesId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<ProductionStages> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ProductionStages findById(Integer id) {
        List<ProductionStages> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<ProductionStages> selectBySql(String sql, Object... args) {
        List<ProductionStages> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                ProductionStages entity = new ProductionStages();
                entity.setProductionStagesId(rs.getInt("productionStages_id"));
                entity.setProductionStagesName(rs.getString("productionStages_name"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
