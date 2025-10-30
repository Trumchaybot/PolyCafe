package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.Products;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ProductsDAO;

public class ProductsDAOImpl implements ProductsDAO {

    final String INSERT_SQL = "INSERT INTO Products (product_name, description, unit_price, target, basic_salary, target_salary, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Products SET product_name = ?, description = ?, unit_price = ?, target = ?, basic_salary = ?, target_salary = ?, quantity = ? WHERE product_id = ?";
    final String DELETE_SQL = "DELETE FROM Products WHERE product_id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Products";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Products WHERE product_id = ?";
    final String SELECT_KHO_SQL = "SELECT product_id, product_name, unit_price, quantity FROM Products";
    final String UPDATE_KHO_SQL = "UPDATE Products SET product_name = ?, unit_price = ?, quantity = ? WHERE product_id = ?";
    final String DELETE_CHI_TIET_SQL = "DELETE FROM ProductDetails WHERE product_id = ?";

    @Override
    public Products create(Products entity) {
        XJdbc.executeUpdate(INSERT_SQL,
            entity.getProductName(),
            entity.getDescription(),
            entity.getUnitPrice(),
            entity.getTarget(),
            entity.getBasicSalary(),
            entity.getTargetSalary(),
            entity.getQuantity());
        return entity;
    }

    @Override
    public void update(Products entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
            entity.getProductName(),
            entity.getDescription(),
            entity.getUnitPrice(),
            entity.getTarget(),
            entity.getBasicSalary(),
            entity.getTargetSalary(),
            entity.getQuantity(),
            entity.getProductId());
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_CHI_TIET_SQL, id);
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Products> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }
    @Override
public List<Products> findKho() {
    List<Products> list = new ArrayList<>();
    try (ResultSet rs = XJdbc.executeQuery(SELECT_KHO_SQL)) {
        while (rs.next()) {
            Products sp = new Products();
            sp.setProductId(rs.getInt("product_id"));
            sp.setProductName(rs.getString("product_name"));
            sp.setUnitPrice(rs.getFloat("unit_price"));
            sp.setQuantity(rs.getInt("quantity"));
            list.add(sp);
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return list;
}

            
    @Override
public void updateKho(Products entity) {
    XJdbc.executeUpdate(UPDATE_KHO_SQL,
        entity.getProductName(),
        entity.getUnitPrice(),
        entity.getQuantity(),
        entity.getProductId());
}
    @Override
    public Products findById(Integer id) {
        List<Products> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }
 @Override
public int insertAndReturnId(Products entity) {
    try {
        ResultSet rs = XJdbc.executeUpdateAndReturnGeneratedKeys(INSERT_SQL,
                entity.getProductName(),
                entity.getDescription(),
                entity.getUnitPrice(),
                entity.getTarget(),
                entity.getBasicSalary(),
                entity.getTargetSalary(),
                entity.getQuantity()
        );

        if (rs.next()) {
            return rs.getInt(1); // Lấy San_pham_id vừa được tạo
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Insert thất bại
}

    private List<Products> selectBySql(String sql, Object... args) {
        List<Products> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(sql, args)) {
            while (rs.next()) {
                Products sp = new Products();
                sp.setProductId(rs.getInt("product_id"));
                sp.setProductName(rs.getString("product_name"));
                sp.setDescription(rs.getString("description"));
                sp.setUnitPrice(rs.getFloat("unit_price"));
                sp.setTarget(rs.getInt("target"));
                sp.setBasicSalary(rs.getFloat("basic_salary"));
                sp.setTargetSalary(rs.getFloat("target_salary"));
                sp.setQuantity(rs.getInt("quantity"));
                list.add(sp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
