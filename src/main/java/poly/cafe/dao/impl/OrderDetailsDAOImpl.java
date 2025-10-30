package poly.cafe.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.OrderDetails;
import poly.cafe.entity.Products;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.OrderDetailsDAO;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    final String INSERT_SQL = "INSERT INTO OrderDetails (order_id, product_id, quantity, total_amount) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE OrderDetails SET order_id = ?, product_id = ?, quantity = ?, total_amount = ? WHERE id = ?";
    final String DELETE_SQL = "DELETE FROM OrderDetails WHERE id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM v_OrderDetails";
    final String SELECT_BY_ID_SQL = "SELECT * FROM v_OrderDetails WHERE order_id = ?";
    final String SELECT_BY_CHITIET_ID = "SELECT * FROM v_OrderDetails WHERE id = ?";

    @Override
    public OrderDetails create(OrderDetails entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getOrderId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getTotalAmount()
        );
        return entity;
    }

    @Override
    public void update(OrderDetails entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getOrderId(),
                entity.getProductId(),
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
    public List<OrderDetails> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public OrderDetails findById(Integer donHangId) {
        List<OrderDetails> list = selectBySql(SELECT_BY_ID_SQL, donHangId);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<OrderDetails> selectBySql(String sql, Object... args) {
        List<OrderDetails> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                OrderDetails model = new OrderDetails();
                model.setId(rs.getInt("id"));
                model.setOrderId(rs.getInt("order_id"));
                model.setProductId(rs.getInt("product_id"));
                model.setQuantity(rs.getInt("quantity"));
                model.setTotalAmount(rs.getDouble("total_amount"));

                Products sp = new Products();
                sp.setProductId(rs.getInt("product_id"));
                sp.setProductName(rs.getString("product_name"));
                model.setProduct(sp);

                list.add(model);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<OrderDetails> findByOrderId(int orderId) {
        return selectBySql(SELECT_BY_ID_SQL, orderId);
    }

}
