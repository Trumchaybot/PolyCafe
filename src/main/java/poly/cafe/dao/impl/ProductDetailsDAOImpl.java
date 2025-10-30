package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.ProductDetails;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ProductDetailsDAO;

public class ProductDetailsDAOImpl implements ProductDetailsDAO {

    final String INSERT_SQL = "INSERT INTO ProductDetails (product_id, material_id, quantity) VALUES (?, ?, ?)";
    final String UPDATE_SQL = "UPDATE ProductDetails SET product_id = ?, material_id = ?, quantity = ? WHERE id = ?";
    final String DELETE_SQL = "DELETE FROM ProductDetails WHERE id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM ProductDetails";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ProductDetails WHERE id = ?";
    final String SELECT_BY_PRODUCT_ID_SQL = "SELECT * FROM ProductDetails WHERE product_id = ?";

    @Override
    public ProductDetails create(ProductDetails entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getProductId(), entity.getMaterialId(), entity.getQuantity());
        return entity;
    }

    @Override
    public void update(ProductDetails entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getProductId(), entity.getMaterialId(), entity.getQuantity(), entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<ProductDetails> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ProductDetails findById(Integer id) {
        List<ProductDetails> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<ProductDetails> selectBySql(String sql, Object... args) {
        List<ProductDetails> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                ProductDetails entity = new ProductDetails();
                entity.setId(rs.getInt("id"));
                entity.setProductId(rs.getInt("product_id"));
                entity.setMaterialId(rs.getInt("material_id"));
                entity.setQuantity(rs.getInt("quantity"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
public List<ProductDetails> findByProductId(int productId) {
    return selectBySql(SELECT_BY_PRODUCT_ID_SQL, productId);
}

}
